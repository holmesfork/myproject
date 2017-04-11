package com.example.holmesk.cehua.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.08 10:50
 */

public class MyBase extends BaseAdapter {

    private List<String> list;
    private Context context;

    public MyBase(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
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
        view = View.inflate(context, android.R.layout.simple_list_item_1, null);
        TextView tv = (TextView) view.findViewById(android.R.id.text1);
        tv.setText(list.get(i));
        return view;
    }

}
