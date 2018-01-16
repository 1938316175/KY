package com.joni.ky.activity;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.joni.ky.R;
import com.joni.ky.db.DBManager;
import com.joni.ky.fragment.HomeFragment;
import com.joni.ky.fragment.LeftFragment;
import com.joni.ky.fragment.RightFragment;
import com.joni.ky.model.ImgBt;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener{

    public static ViewPager viewPager;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    private RelativeLayout his_re, home_rel, ch_rel;
    private ImageButton chartImgBt, homeImgBt, historyImgBt;
    private ImgBt imgBt;
    private SQLiteDatabase db;
    private DBManager manager;
    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivity = MainActivity.this;

        initView();
        initDB();

        fragmentList.add(new LeftFragment());
        fragmentList.add(new HomeFragment());
        fragmentList.add(new RightFragment());

        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList, null));
        viewPager.setOnPageChangeListener(listener);
    }

    private void initView(){
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        chartImgBt = (ImageButton) findViewById(R.id.chart_imgbt);
        homeImgBt = (ImageButton) findViewById(R.id.home_imgbt);
        historyImgBt = (ImageButton) findViewById(R.id.history_imgbt);
        his_re = (RelativeLayout)findViewById(R.id.history_rela);
        home_rel = (RelativeLayout)findViewById(R.id.home_rela);
        ch_rel = (RelativeLayout)findViewById(R.id.chart_rela);
        his_re.setOnClickListener(this);
        home_rel.setOnClickListener(this);
        ch_rel.setOnClickListener(this);
        imgBt = new ImgBt(chartImgBt, homeImgBt, historyImgBt);
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    imgBt.setChartImgBtTrue();
                    break;
                case 1:
                    imgBt.setHomeImgBtTrue();
                    break;
                case 2:
                    chartImgBt.setBackgroundResource(R.drawable.history_yes);
                    imgBt.setHistoryImgBtTrue();
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList){
            super(fm);
            this.fragmentList = fragmentList;
        }

        /**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.chart_rela:
                if (!imgBt.getChartImgBt()) {
                    viewPager.setCurrentItem(0);
                }
                break;
            case R.id.home_rela:
                if (!imgBt.getHomeImgBt()) {
                    viewPager.setCurrentItem(1);
                }
                break;
            case R.id.history_rela:
                if (!imgBt.getHistoryImgBt()) {
                    viewPager.setCurrentItem(2);
                }
                break;
        }
    }

    public void initDB(){
        manager = new DBManager(MainActivity.this);
        manager.initManager("ky_data.db");
        db = manager.getDatabase("ky_data.db");
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewPager.setCurrentItem(1);
        imgBt.setHomeImgBtTrue();
    }

    public SQLiteDatabase getDb(){
        if (db == null){
            db = manager.getDatabase("ky_data.db");
        }
        return db;
    }
}
