package com.mycontent.jizhang1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mycontent.jizhang1.adapter.AccountAdapter;
import com.mycontent.jizhang1.db.AccountBean;
import com.mycontent.jizhang1.db.DBManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    ListView searchLv;
    EditText searchEt;
    TextView emptyTv;
    List<AccountBean> mDatas;  //数据源
    AccountAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        mDatas=new ArrayList<>();
        adapter=new AccountAdapter(this,mDatas);
        searchLv.setAdapter(adapter);
        searchLv.setEmptyView(emptyTv);   //设置无数据时，显示的控件
    }

    private void initView() {
        searchEt=findViewById(R.id.search_et);
        searchLv=findViewById(R.id.search_lv);
        emptyTv=findViewById(R.id.search_tv_empty);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_iv_back:
                finish();
                break;
            case R.id.search_iv_sh:
                String msg = searchEt.getText().toString().trim();   //trim（）函数，去除字符串本身带有的回车空格等无用的字符
                //判断输入内容是否为空，如果为空，就提示不能搜索
                if (TextUtils.isEmpty(msg)) {
                    Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //开始在数据库中搜索
                List<AccountBean> list = DBManager.getAccounttbListByRemarkFromAccounttb(msg);
                mDatas.clear();
                mDatas.addAll(list);
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
