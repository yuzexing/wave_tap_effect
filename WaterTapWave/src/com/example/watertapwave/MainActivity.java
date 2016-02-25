package com.example.watertapwave;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.FrameLayout;

import com.example.watertapwave.view.WaterTabWaveView;

public class MainActivity extends Activity {

    public static Handler mHandler;
    private WaterTabWaveView wtwv;
    private static final int START = 110;
    private static final int STOP = 120;
    private static int state = START;
    private Runnable run =  new Runnable() {
        @Override
        public void run() {
            mHandler.removeCallbacksAndMessages(null);
            wtwv.resetWave();
            mHandler.postDelayed(run, 3000);
        }
    };

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wtwv = new WaterTabWaveView(getApplicationContext());
        wtwv.setFillWaveSourceShapeRadius(0);
        wtwv.setWaveInfo(50, 1, 4, 10, Color.WHITE);
        FrameLayout fl = new FrameLayout(getApplicationContext());
        fl.setBackgroundDrawable(getResources().getDrawable(android.R.color.black));
        fl.addView(wtwv);
        setContentView(fl);
//        mHandler.postDelayed(run, 3000);
        new Thread() {
            public void run() {
                SystemClock.sleep(3000);
               runOnUiThread(new Runnable() {
                public void run() {
                    wtwv.resetWave();
                }
            });
            };
        }.start();
    }
}
