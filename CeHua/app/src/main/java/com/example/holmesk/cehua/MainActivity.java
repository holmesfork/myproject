package com.example.holmesk.cehua;

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

import com.google.gson.Gson;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private ImageView img;
    private HorizontalScrollView sc;
    private ViewPager vp;
    private int indicatorWidth;
    private static final String URI = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573";
    private LayoutInflater inflater;
    private List<Fragment> fragmentList;

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
        SlidingMenu menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        // 设置触摸屏幕的模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;
        menu.setBehindOffset(width / 3);
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

    private void setlisenten() {

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (rg != null && rg.getChildCount() > position) {
                    ((RadioButton) rg.getChildAt(position)).performClick();
                }

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
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
        indicatorWidth = dm.widthPixels / 4;
        ViewGroup.LayoutParams params = img.getLayoutParams();
        params.width = indicatorWidth;

        img.setLayoutParams(params);
        //获取布局填充器
        inflater = LayoutInflater.from(this);
        initNavigationHSV();
        fragmentList = new ArrayList<>();
        for (int i = 0; i < UriType.getTypeList().size(); i++) {
            fragmentList.add(new MyFragment(URI, UriType.getTypeList().get(i)));
        }
        TabFragmentPagerAdapter mAdapter = new TabFragmentPagerAdapter(getSupportFragmentManager(), fragmentList);
        vp.setAdapter(mAdapter);

    }

    private void initNavigationHSV() {

        rg.removeAllViews();
        for (int i = 0; i < UriType.getTypeList().size(); i++) {
            GetData getdata = new GetData();
            getdata.getDataFromNet(URI, UriType.getTypeList().get(i));
            final int finalI = i;
            getdata.setData(new GetData.OnGetDataListener() {
                @Override
                public void getData(String result) {
                    Gson gson = new Gson();
                    String category = gson.fromJson(result, MyBean.class).getResult().getData().get(finalI).getCategory();
                    RadioButton rb = (RadioButton) inflater.inflate(R.layout.nav_radiogroup_item, null);
                    rb.setId(finalI);
                    rb.setText(category);
                    rb.setLayoutParams(new ViewGroup.LayoutParams(indicatorWidth,
                            ViewGroup.LayoutParams.MATCH_PARENT));
                    rg.addView(rb);
                }
            });


        }
    }
}
