package com.joni.ky.db;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/12/28.
 * 使用类的时候，先new对象
 * 然后再调用initManager方法
 * 就可以通过getDatebase方法获取到数据库的对象
 */

public class DBManager {

    private static String TAG = "DBManager";
    private static String databasepath = "/data/data/%s/database";
    private Context mContext;

    public DBManager(Context mContext){
        this.mContext = mContext;
        Log.e(TAG, "初始化管理器");
    }

    public boolean initManager(String dbfile){
        if(!isExists(dbfile)) {
            if(mContext == null) {
                return false;
            }
            Log.e(TAG, "文件不存在存在，准备复制");

            String path = getFilePath(null);
            File file = new File(path);
            if(!file.exists() && !file.mkdirs()){
                Log.e(TAG, "创建 \""+path+"\" 路径失败");
                return false;
            }
            if(!copyResToPath(dbfile, getFilePath(dbfile))){
                return false;
            }
        }
        Log.e(TAG, "文件已经存在");
        return true;
    }

    public SQLiteDatabase getDatabase(String dbfile) {
        //先判断是否已经复制过了
        Log.e(TAG, "请求获取数据库对象");
        if(isExists(dbfile)){
            Log.e(TAG, "文件已经存在");
            SQLiteDatabase db = null;
            try {
                db = SQLiteDatabase.openDatabase(getFilePath(dbfile), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS);
            }catch (Exception e){
                e.printStackTrace();
                Log.e(TAG, "获取已经存在数据库对象失败");
            }
            Log.e(TAG, "已返回数据库对象");
            return db;
        }
        return null;
    }

    private boolean copyResToPath(String assetsSrc, String des){
        Log.e(TAG, "正在复制文件...");
        Log.e(TAG, "des:" + des+"\ndbfile:"+assetsSrc);
        InputStream istream = null;
        OutputStream ostream = null;
        try{
            AssetManager am = mContext.getAssets();
            istream = am.open(assetsSrc);
            ostream = new FileOutputStream(des);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = istream.read(buffer))>0){
                ostream.write(buffer, 0, length);
            }
            istream.close();
            ostream.close();
        }
        catch(Exception e){
            e.printStackTrace();
            Log.e(TAG, "复制文件失败");
            try{
                if(istream!=null)
                    istream.close();
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
            return false;
        }
        return true;
    }

    /**
     * 判断文件是否存在
     * */
    private boolean isExists(String dbFile){
        String filePath = getFilePath(dbFile);
        File file =  new File(filePath);
        if (file.exists()){
            return true;
        }
        return false;
    }

    public String getFilePath(String dbFile){
        if (dbFile == null){
            return String.format(databasepath, mContext.getApplicationInfo().packageName);
        }
        return String.format(databasepath, mContext.getApplicationInfo().packageName) + "/" + dbFile;
    }
}
