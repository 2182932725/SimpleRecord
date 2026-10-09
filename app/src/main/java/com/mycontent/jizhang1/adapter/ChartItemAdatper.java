package com.mycontent.jizhang1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycontent.jizhang1.R;
import com.mycontent.jizhang1.db.ChartItemBean;
import com.mycontent.jizhang1.utils.FloatUtils;

import java.util.List;


/*
* 账单详情页面，LstView的适配器
* */
public class ChartItemAdatper extends BaseAdapter {
    Context context;
    List<ChartItemBean> mDatas;
    LayoutInflater inflater;
    public ChartItemAdatper(Context context, List<ChartItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
        inflater=LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if (view==null) {
            view=inflater.inflate(R.layout.item_chartfrag_lv,viewGroup,false);
            holder=new ViewHolder(view);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        //获取显示内容
        ChartItemBean bean = mDatas.get(i);
        holder.iv.setImageResource(bean.getsImageId());
        holder.typeTv.setText(bean.getType());
        float radio = bean.getRadio();
        String pert = FloatUtils.radioToPercent(radio);
        holder.radioTv.setText(pert);
        holder.totalTv.setText("￥ "+bean.getTotalMoney());
        return view;
    }

    class ViewHolder{
        TextView typeTv,radioTv,totalTv;
        ImageView iv;
        public ViewHolder(View view){
            typeTv=view.findViewById(R.id.item_chartfrag_tv_type);
            radioTv=view.findViewById(R.id.item_chartfrag_tv_pert);
            totalTv=view.findViewById(R.id.item_chartfrag_tv_sum);
            iv=view.findViewById(R.id.item_chartfrag_iv);

        }
    }
}
