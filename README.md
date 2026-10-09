# 简约记账（SimpleRecord）

一款**纯本地离线**的个人记账 Android 应用，基于 SQLite 实现账单的本地化存储与聚合统计，无需登录、无需服务端，全功能离线可用。

## 功能特性

- **收支记账**：支持支出 / 收入两大类，内置 15 个支出分类与 9 个收入分类，可为每笔账单添加备注并选择时间。
- **今日账单**：首页展示当日收支明细，头部汇总今日 / 本月收支总额。
- **预算管理**：可设置月度预算，实时显示剩余金额（剩余 = 预算 − 本月支出）。
- **历史查询**：按日 / 月查看历史账单。
- **关键词搜索**：按备注内容模糊搜索账单。
- **月度图表**：以柱状图展示当月每日收支趋势，并按分类统计占比展示消费结构。
- **隐私保护**：首页金额支持明文 / 密文一键切换。
- **删除管理**：长按账单条目弹窗确认后删除。

## 技术栈

| 分类 | 技术 |
| --- | --- |
| 语言 / 框架 | Java、Android（minSdk 15 / targetSdk 32） |
| 数据存储 | SQLite（SQLiteOpenHelper） |
| 图表 | MPAndroidChart v3.0.2 |
| 轻量存储 | SharedPreferences（预算） |
| UI | ListView / ViewPager + Fragment / TabLayout / 自定义 Dialog |

## 项目结构

```
app/src/main/java/com/mycontent/jizhang1/
├── MainActivity.java        # 首页：今日账单 + 收支汇总 + 预算 + 隐私切换
├── RecordActivity.java      # 记账：支出/收入双 Tab
├── HistoryActivity.java     # 历史账单查询
├── SearchActivity.java      # 备注关键词搜索
├── MonthChartActivity.java  # 月度图表（柱状图 + 分类占比）
├── AboutActivity.java       # 关于页面
├── db/                      # 数据层：SQLite 建表与 CRUD/统计封装
│   ├── DBOpenHelper.java    # 建表 + 初始化收支类型
│   ├── DBManager.java       # 统一数据访问（增删改查 + 聚合统计）
│   └── *.java               # 实体类（AccountBean / TypeBean 等）
├── adapter/                 # 列表 / ViewPager 适配器
├── frag_record/             # 记账 Fragment（收入 / 支出）
├── frag_chart/              # 图表 Fragment（收入 / 支出）
└── utils/                   # 预算 / 备注 / 日历 / 时间选择等自定义 Dialog
```

## 数据库设计

两张表，均为本地 SQLite 表：

**typetb（收支类型表）**

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | integer PK autoincrement | 主键 |
| typename | varchar(10) | 类型名称 |
| imageId | integer | 选中态图标资源 id |
| sImageId | integer | 未选中态图标资源 id |
| kind | integer | 0 = 支出，1 = 收入 |

**accounttb（记账流水表）**

| 字段 | 类型 | 说明 |
| --- | --- | --- |
| id | integer PK autoincrement | 主键 |
| typename | varchar(10) | 类型名称 |
| sImageId | integer | 类型图标 |
| beizhu | varchar(80) | 备注 |
| money | float | 金额 |
| time | varchar(60) | 记录时间 |
| year / month / day | integer | 冗余存储的年 / 月 / 日，用于按时间维度聚合 |
| kind | integer | 0 = 支出，1 = 收入 |

> 时间维度（year / month / day）单独冗余一列，是为了让 `where year=? and month=? and day=?` 这类按时间聚合的查询免去对 `time` 字符串的解析。

## 构建运行

1. 使用 Android Studio 打开项目根目录。
2. 确认已安装 SDK Platform 32（compileSdk 32）。
3. 连接设备或启动模拟器，点击 Run 即可。
4. 最低支持 Android 4.0（API 15）。

## 已知问题与改进方向

- **金额精度**：`money` 字段使用 `float` 存储，存在浮点精度风险，建议改为「以分为单位的整数（int）」或 `TEXT + BigDecimal`。
- **SQL 拼接**：部分查询（如备注模糊搜索）使用字符串拼接 SQL，本地单机无注入风险，但最佳实践应统一改为参数绑定。
- **架构演进**：当前为传统 Activity + 自定义数据层写法，可引入 Room / MVVM / 协程 / Flow 进一步解耦 UI 与数据访问。
- **索引**：本地单用户、数据量级小，现有查询无需索引；若后续支持大量历史数据导入，可为 `(year, month, day)`、`(year, month, kind)` 建联合索引。
