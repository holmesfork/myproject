package com.example.holmesk.weektest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mylist;
    private List<MyBean.ListBean> list;
    private MyBase myBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mylist = (ListView) findViewById(R.id.mylist);
        Util util = new Util();
        util.execute("http://result.eolinker.com/Kn5mQ5bc3f37907192691b123841b88fd19ed7dfdb7d55c?uri=shuju");
        util.setGetData(new Util.OnReadData() {
            @Override
            public void getData(String data) {
                Gson gson = new Gson();
                list = gson.fromJson(data, MyBean.class).getList();
                myBase = new MyBase(list, MainActivity.this);
                mylist.setAdapter(myBase);
            }
        });
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "id:" + list.get(i).getId(), Toast.LENGTH_LONG).show();
            }
        });

        mylist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                list.remove(i);
                myBase.notifyDataSetChanged();

                return false;
            }
        });

    }
}
