package com.example.holmesk.weektest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.07 20:20
 */

public class MyBase extends BaseAdapter {

    private Context context;
    private List<MyBean.ListBean> list;

    public MyBase(List<MyBean.ListBean> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = View.inflate(context, R.layout.item, null);

        TextView tv = (TextView) view.findViewById(R.id.site_name);
        TextView tv1 = (TextView) view.findViewById(R.id.address);

        tv.setText("地点名：" + list.get(i).getSite_name());
        tv1.setText("地点：" + list.get(i).getAddress());

        return view;
    }
}
