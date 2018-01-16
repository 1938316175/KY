package com.joni.ky.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/6.
 * 本工具类用于数据库操作
 */

public class DBHelper {

    private SQLiteDatabase db;
    private Context context;
    private static String TAG = "DBHelper";

    public DBHelper(SQLiteDatabase db, Context context){
        this.db = db;
        this.context = context;
    }

    /**
    ** 得到单个月份的数据
    * */
    public int loadSingleMonth(String month){
        int time = 0;
        Cursor cursor = db.query("month_day", null, "month=?", new String[]{month}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                //记载数据库中指定字段的值
                time = cursor.getInt(cursor.getColumnIndex("time"));

            }while(cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return time;
    }

    /**
     ** 得到单日的数据
     * */
    public int loadSingleDay(String month, String day){
        int time = -1;
        Cursor cursor = db.query("day_data", null, "mon=? and day=?", new String[]{month, day}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                //记载数据库中指定字段的值
                time = cursor.getInt(cursor.getColumnIndex("time"));
                Log.e(TAG, "loadDays: " + time);
            }while(cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return time;
    }

    /**
     ** 得到单日的数据
     * */
    public Map loadDays(String month){
        int time = 0;
        String day = "";
        List<String> list = new ArrayList<>();
        Map map = new HashMap();
        Cursor cursor = db.query("day_data", null, "mon=?", new String[]{month}, null, null, null);
        if(cursor.moveToFirst()){
            do{
                //记载数据库中指定字段的值
                day = cursor.getString(cursor.getColumnIndex("day"));
                time = cursor.getInt(cursor.getColumnIndex("time"));
                map.put(day, time);
            } while(cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return map;
    }

    /**
     * 更新某个字段中的值
     * */
    public void updateDay(String table, String day, int data){
        ContentValues values = new ContentValues();
        values.put("time", data);
        db.update(table, values, "day = '" + day + "'", null);
        values.clear();
    }
    public void updateMonth(String table, String month, int data){
        ContentValues values = new ContentValues();
        values.put("time", data);
        db.update(table, values, "month = '" + month + "'", null);
        values.clear();
    }

    /**
     * 插入某个字段
     * */
    public void insertData(String table, String month, String day, int time){
        ContentValues values = new ContentValues();
        values.put("month", month);
        values.put("day", day);
        values.put("time", time);
        db.insert(table, null, values);
        values.clear();
    }

}
