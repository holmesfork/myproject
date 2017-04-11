package com.example.holmesk.cehua.activttys;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.holmesk.cehua.R;
import com.example.holmesk.cehua.adapter.TabFragmentPagerAdapter;
import com.example.holmesk.cehua.beans.MyBean;
import com.example.holmesk.cehua.databases.Titlesdb;
import com.example.holmesk.cehua.fragments.LeftFragment;
import com.example.holmesk.cehua.fragments.MyFragment;
import com.example.holmesk.cehua.utils.GetData;
import com.example.holmesk.cehua.utils.UriType;
import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

import static com.example.holmesk.cehua.utils.UriType.URI;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ImageView img;
    private HorizontalScrollView sc;
    private ViewPager vp;
    private int indicatorWidth;
    private LayoutInflater inflater;
    private List<Fragment> fragmentList;
    private SlidingMenu menu;
    private SQLiteDatabase db;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        addCeHua();


    }

    //添加侧滑页面
    private void addCeHua() {
        // configure the SlidingMenu
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        //DisplayMetrics metric = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metric);
        //int width = metric.widthPixels;     // 屏幕宽度（像素）
        //int height = metric.heightPixels;
        //menu.setBehindOffset(width / 3);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        menu.setFadeDegree(0.35f);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //为侧滑菜单设置布局
        menu.setMenu(R.layout.leftmenu);
        getSupportFragmentManager().beginTransaction().add(R.id.myLeft, new LeftFragment()).commit();
    }

    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg);
        img = (ImageView) findViewById(R.id.img);
        sc = (HorizontalScrollView) findViewById(R.id.sc);
        vp = (ViewPager) findViewById(R.id.vp);

        initmyView();
        setlisenten();
    }

    //设置监听
    private void setlisenten() {
        //vp滑动监听
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (rg != null && rg.getChildCount() > position) {
                    ((RadioButton) rg.getChildAt(position)).performClick();
                }
                switch (position) {
                    case 0:
                        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                        break;
                    default:
                        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
                        break;
                }


            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        //RadioGroup的选中监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            private int currentIndicatorLeft;

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (rg.getChildAt(checkedId) != null) {
                    TranslateAnimation animation = new TranslateAnimation(
                            currentIndicatorLeft,
                            ((RadioButton) rg.getChildAt(checkedId)).getLeft(), 0f, 0f);
                    animation.setDuration(100);
                    animation.setFillAfter(true);
                    //执行位移动画
                    img.startAnimation(animation);
                    vp.setCurrentItem(checkedId);    //ViewPager 跟随一起 切换
                    //记录当前 下标的距最左侧的 距离
                    currentIndicatorLeft = ((RadioButton) rg.getChildAt(checkedId)).getLeft();
                    sc.smoothScrollTo(
                            (checkedId > 1 ? ((RadioButton) rg.getChildAt(checkedId)).getLeft() : 0) - ((RadioButton) rg.getChildAt(2)).getLeft(), 0);
                }
            }

        });
    }

    private void initmyView() {
        //DisplayMetrics 类提供了一种关于显示的通用信息，如显示大小，分辨率和字体
        DisplayMetrics dm = new DisplayMetrics();
        //将当前窗口的一些信息放在DisplayMetrics类中，
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //radiobutton的宽度
        indicatorWidth = dm.widthPixels / 7;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = indicatorWidth;

        img.setLayoutParams(params);
        //获取布局填充器
        inflater = LayoutInflater.from(this);
        initNavigationHSV();
        addButtons();

        fragmentList = new ArrayList<>();
        for (int i = 0; i < UriType.getTypeList().size(); i++) {
            fragmentList.add(new MyFragment(URI, UriType.getTypeList().get(i)));
        }
        TabFragmentPagerAdapter mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(mAdapter);

    }

    //添加radiobutton
    private void addButtons() {
        rg.removeAllViews();
        Cursor titles = db.query("titles", null, null, null, null, null, null);
        int a = 0;
        while (titles.moveToNext()) {
            String title = titles.getString(titles.getColumnIndex("title"));
            RadioButton rb = (RadioButton) inflater.inflate(R.layout.nav_radiogroup_item, null);
            rb.setId(a);
            rb.setText(title);
            rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth,
                    ViewGroup.LayoutParams.MATCH_PARENT));
            rg.addView(rb);
            a++;
        }


    }

    private void initNavigationHSV() {
        Titlesdb titlesdb = new Titlesdb(this);
        db = titlesdb.getWritableDatabase();

        for (int i = 0; i < UriType.getTypeList().size(); i++) {
            GetData getdata = new GetData();
            getdata.getDataFromNet(URI, UriType.getTypeList().get(i));
            getdata.setData(new GetData.OnGetDataListener() {
                @Override
                public void getData(String result) {
                    Gson gson = new Gson();
                    String category = gson.fromJson(result, MyBean.class).getResult().getData().get(0).getCategory();
                    ContentValues value = new ContentValues();
                    value.put("title", category);
                    db.insert("titles", null, value);
                }
            });
        }
    }
}
