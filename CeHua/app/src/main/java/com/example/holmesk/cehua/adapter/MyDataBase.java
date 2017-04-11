package com.example.holmesk.cehua.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.holmesk.cehua.beans.MyBean;
import com.example.holmesk.cehua.R;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

/**
 * 作者：holmes k
 * 时间：2017.04.10 20:37
 */

public class MyDataBase extends BaseAdapter {

    private Context context;
    private List<MyBean.ResultBean.DataBean> data;

    public MyDataBase(Context context, List<MyBean.ResultBean.DataBean> data) {
        this.context = context;
        this.data = data;
        imageOptions();
    }

    @Override
    public int getCount() {
        return data != null ? data.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    class ViewHolder {
        ImageView newspic;
        TextView newstitle, newsdate;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = View.inflate(context, R.layout.listitem, null);
            viewHolder.newspic = (ImageView) view.findViewById(R.id.newsimg);
            viewHolder.newstitle = (TextView) view.findViewById(R.id.newstitle);
            viewHolder.newsdate = (TextView) view.findViewById(R.id.newsdate);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        x.image().bind(viewHolder.newspic, data.get(i).getThumbnail_pic_s(), imageOptions);
        viewHolder.newstitle.setText(data.get(i).getTitle());
        viewHolder.newsdate.setText(data.get(i).getDate() + "    " + data.get(i).getAuthor_name());
        return view;
    }

    private ImageOptions imageOptions;

    public void imageOptions() {

        imageOptions = new ImageOptions.Builder()
                .setFadeIn(true)
                .build();


    }
}
