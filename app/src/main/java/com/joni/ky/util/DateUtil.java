package com.joni.ky.util;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2018/1/16.
 */

public class DateUtil {

    private long currTime;
    private int month;
    private int day;

    public long getCurrTime() {
        currTime = System.currentTimeMillis();
        return currTime;
    }

    public int getMonth() {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String times = sdr.format(new Date(getCurrTime()));
        String m = "!";
        for (int i=0; i<times.length(); i++){
            if (times.charAt(i)=='年'){
                m = times.substring(i+1,i+3);
            }
        }
        month = Integer.parseInt(m);
        return month;
    }

    public int getDay() {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日  HH:mm");
        String times = sdr.format(new Date(getCurrTime()));
        String d = "!";
        for (int i=0; i<times.length(); i++){
            if (times.charAt(i)=='月'){
                d = times.substring(i+1,i+3);
            }
        }
        day = Integer.parseInt(d);
        return day;
    }
}
