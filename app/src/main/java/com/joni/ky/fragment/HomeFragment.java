package com.joni.ky.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.joni.ky.R;
import com.joni.ky.activity.MainActivity;
import com.joni.ky.db.DBHelper;
import com.joni.ky.util.DateUtil;

import java.util.Date;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private Button startButton;
    private Button stopButton;
    private TextView currText;
    private TextView allText;
    private static int START_BUTTON = 0;
    private static int STOP_BUTTON = 1;
    private boolean state = false;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        startButton = (Button) v.findViewById(R.id.start);
        stopButton = (Button) v.findViewById(R.id.stop);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        currText = (TextView)v.findViewById(R.id.currTime);
        allText = (TextView)v.findViewById(R.id.allTime);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                DBHelper helper = new DBHelper(MainActivity.mainActivity.getDb(), MainActivity.mainActivity);
                int d = helper.loadSingleDay("2","5");
                Log.e("HHER", "onClick: "+d);
                //必须用到服务，不然可能会被杀死
                /*if (!state) {
                    state = true;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            int i=0;
                            long last = System.currentTimeMillis();
                            long curr = 0;
                            while (state) {
                                curr = System.currentTimeMillis();
                                if (curr - last > 1000) {
                                    Message message = new Message();
                                    message.what = START_BUTTON;
                                    message.obj = (++i);
                                    handler.sendMessage(message);
                                    last = curr;
                                }
                            }
                        }
                    }).start();
                }else {
                    Toast.makeText(MainActivity.mainActivity, "已经在计时中...", Toast.LENGTH_SHORT).show();
                }*/
                break;
            case R.id.stop:
                //保存服务中的时间数据
                if (state) {
                    state = false;
                    Message message2 = new Message();
                    message2.what = STOP_BUTTON;
                    message2.obj = "时间";
                    handler.sendMessage(message2);
                }else {
                    Toast.makeText(MainActivity.mainActivity, "还未开始计时!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == START_BUTTON){
                int time = (int)msg.obj;
                int hour = time/3600;
                int min = (time - hour*3600)/60;
                int sec = time - hour*3600 - min*60;
                currText.setText("本次累计时间  " + (hour>9?hour:("0"+hour)) + ":" + (min>9?min:("0"+min)) + ":" + (sec>9?sec:("0"+sec)));
            }else if (msg.what == STOP_BUTTON){

            }
        }
    };
}
