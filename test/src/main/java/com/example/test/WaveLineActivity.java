package com.example.test;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.example.test.weight.WavyLineView;

public class WaveLineActivity extends AppCompatActivity {

    private WavyLineView wavyLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_line);

        setupViews();
    }

    private int amplitude;
    private boolean jia;
    private int zhi = 10;
    private int delay = 30;

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (amplitude == 0) {
                        jia = true;
                    } else if (amplitude == 100) {
                        jia = false;
                    }
                    if (jia) {
                        amplitude += zhi;
                        delay = 30;
                    } else {
                        amplitude -= zhi;
                        delay = 20;
                    }

                    wavyLineView.setAmplitude(amplitude);
                    Message message = handler.obtainMessage();
                    message.what = 0;
                    handler.sendMessageDelayed(message, delay);
                    break;
            }
        }
    };

    private void setupViews() {
        wavyLineView = (WavyLineView) findViewById(R.id.wavyLineView);
        float initPeriod = (float) (2 * Math.PI / 180);
        int initAmplitude = 25;
        int initStrokeWidth = 2;
        wavyLineView.setPeriod(initPeriod);
        wavyLineView.setAmplitude(initAmplitude);
        wavyLineView.setColor(getResources().getColor(R.color.colorAccent));
        wavyLineView.setStrokeWidth(dp2px(this, initStrokeWidth));
        Message message = handler.obtainMessage();
        message.what = 0;
        handler.sendMessageDelayed(message, 500);

    }

    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
