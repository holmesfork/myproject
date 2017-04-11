package com.example.holmesk.cehua.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.holmesk.cehua.utils.GetData;
import com.example.holmesk.cehua.beans.MyBean;
import com.example.holmesk.cehua.adapter.MyDataBase;
import com.example.holmesk.cehua.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.10 19:02
 */

public class MyFragment extends Fragment {

    private String path;
    private String uri;
    private ListView mylist;
    private GetData getData;

    public MyFragment(String uri, String path) {
        this.path = path;
        this.uri = uri;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_selection_common, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        mylist = (ListView) getView().findViewById(R.id.myViewList);
        getData = new GetData();
        getData.getDataFromNet(uri, path);
        Log.d("abc", path + uri);
        getData.setData(new GetData.OnGetDataListener() {
            @Override
            public void getData(String result) {
                Gson gson = new Gson();
                List<MyBean.ResultBean.DataBean> data = gson.fromJson(result, MyBean.class).getResult().getData();
                mylist.setAdapter(new MyDataBase(getContext(), data));
            }
        });

    }

}
