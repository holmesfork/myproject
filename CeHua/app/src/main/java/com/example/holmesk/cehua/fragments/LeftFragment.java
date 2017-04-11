package com.example.holmesk.cehua.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.holmesk.cehua.adapter.MyBase;
import com.example.holmesk.cehua.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.08 10:41
 */


public class LeftFragment extends Fragment {

    private List<String> list;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.leftfragment, container, false);
        v.setBackgroundColor(Color.RED);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        ListView myList = (ListView) getView().findViewById(R.id.myList);
        list = new ArrayList<>();
        list.add("微信");
        list.add("微博");
        list.add("QQ");
        list.add("陌陌");
        myList.setAdapter(new MyBase(list, getContext()));
    }
}
