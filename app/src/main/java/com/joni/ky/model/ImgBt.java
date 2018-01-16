package com.joni.ky.model;

import android.widget.ImageButton;

import com.joni.ky.R;

/**
 * Created by Administrator on 2018/1/11.
 */

public class ImgBt {

    private boolean chartImgBt;
    private boolean homeImgBt;
    private boolean historyImgBt;
    private ImageButton chartImg;
    private ImageButton homeImg;
    private ImageButton historyImg;

    public ImgBt(ImageButton chartImg, ImageButton homeImg, ImageButton historyImg){
        this.historyImg = historyImg;
        this.homeImg = homeImg;
        this.chartImg = chartImg;
    }

    public boolean getChartImgBt() {
        return chartImgBt;
    }

    public boolean getHomeImgBt() {
        return homeImgBt;
    }

    public boolean getHistoryImgBt() {
        return historyImgBt;
    }

    public void setChartImgBtTrue() {
        chartImgBt = true;
        homeImgBt = false;
        historyImgBt = false;
        chartImg.setBackgroundResource(R.drawable.chart_yes);
        homeImg.setBackgroundResource(R.drawable.home_no);
        historyImg.setBackgroundResource(R.drawable.history_no);
    }

    public void setHomeImgBtTrue() {
        chartImgBt = false;
        homeImgBt = true;
        historyImgBt = false;
        chartImg.setBackgroundResource(R.drawable.chart_no);
        homeImg.setBackgroundResource(R.drawable.home_yes);
        historyImg.setBackgroundResource(R.drawable.history_no);
    }

    public void setHistoryImgBtTrue() {
        chartImgBt = false;
        homeImgBt = false;
        historyImgBt = true;
        chartImg.setBackgroundResource(R.drawable.chart_no);
        homeImg.setBackgroundResource(R.drawable.home_no);
        historyImg.setBackgroundResource(R.drawable.history_yes);
    }
}
