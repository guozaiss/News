package com.example.test;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

public class SurfaceActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener, View.OnClickListener {

    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        videoView = (VideoView) findViewById(R.id.videoView);
        //设置视频控制器
        videoView.setMediaController(new MediaController(this));
        //播放完成回调
        videoView.setOnCompletionListener(this);
        //设置视频路径
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/" + R.raw.test));
        //开始播放视频
        View start = findViewById(R.id.start);
        View pause = findViewById(R.id.pause);
        View go = findViewById(R.id.go);
        View stop = findViewById(R.id.stop);
        start.setOnClickListener(this);
        pause.setOnClickListener(this);
        go.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //开始播放视频
//        videoView.start();
    }

    int progress = 0;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    progress += 2; // milliseconds
                    videoView.seekTo(progress);
                    handler.sendEmptyMessageDelayed(0,1);
                    break;
                case 1:
                    handler.removeMessages(0);
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                videoView.start();
                break;
            case R.id.pause:
                videoView.pause();
                break;
            case R.id.go:
//                videoView.seekTo(3*1000);
                break;
            case R.id.stop:
//                videoView.
                break;
        }
    }

}
