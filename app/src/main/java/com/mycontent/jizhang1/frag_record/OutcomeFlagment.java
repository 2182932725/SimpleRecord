package com.mycontent.jizhang1.frag_record;

import com.mycontent.jizhang1.R;
import com.mycontent.jizhang1.db.DBManager;
import com.mycontent.jizhang1.db.TypeBean;

import java.util.List;

public class OutcomeFlagment extends BaseRecordFragment {


    //重写父类方法
    @Override
    public void loadDataYoGV() {
        super.loadDataYoGV();
        //获取数据库当中的数据源
        List<TypeBean> outlist = DBManager.getTypeList(0);
        typeList.addAll(outlist);
        adapter.notifyDataSetChanged();
        typeTv.setText("其他");
        typeIv.setImageResource(R.mipmap.ic_qita_fs);
    }

    @Override
    public void saveAccountToDB() {

        accountBean.setKind(0);
        DBManager.insertItemToAccounttb(accountBean);
    }
}
