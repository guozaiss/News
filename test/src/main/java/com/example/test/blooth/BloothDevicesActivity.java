package com.example.test.blooth;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.test.R;
import com.readyidu.eshw.api.bluetooth.ESHWBluetoothApi;
import com.readyidu.eshw.api.bluetooth.ESHWBluetoothListener;
import com.readyidu.eshw.util.Block;

import java.util.Timer;

public class BloothDevicesActivity extends AppCompatActivity implements View.OnClickListener {
    private Timer mTimer;
    private BroadcastReceiver bluetoothReconnectReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("com.readyidu.eshw.connect.ble.DEVICES_CHANGE".equals(intent.getAction())) {
                Log.e("AAA", intent.getStringExtra("deviceName") + " " + intent.getStringExtra("deviceAddress") + " " + intent.getStringExtra("deviceRssi"));
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blooth_devices);
        View scan = findViewById(R.id.scan);
        View start = findViewById(R.id.start);
        scan.setOnClickListener(this);
        start.setOnClickListener(this);
        registerReceiver(bluetoothReconnectReceiver, new IntentFilter("com.readyidu.eshw.connect.ble.DEVICES_CHANGE"));
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_DENIED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.WAKE_LOCK) == PackageManager.PERMISSION_DENIED)
                || (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED)) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.BLUETOOTH,
                    Manifest.permission.WAKE_LOCK,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.RECORD_AUDIO}, 1234);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ESHWBluetoothApi.stopBluetooth();
        unregisterReceiver(bluetoothReconnectReceiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                connectDevices();
                break;
        }
    }

    private ESHWBluetoothApi mThermometerBluetoothApi;

    private void connectDevices() {
        mThermometerBluetoothApi = new ESHWBluetoothApi();
        mThermometerBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {
            @Override
            public void dataReceive(String data) {//只上传心率数据
                float result = ThermometerInstrument.getResult(data);
                Log.e("AA", result + "");
            }

            @Override
            public void bluetoothDisconnect() {
                Log.e("AAAA", "连接bong手环");
            }

            @Override
            public void bluetoothConnect() {
                Log.e("AAAA", "断开bong手环");
            }
        });
        ESHWBluetoothApi.startBluetooth(this, new Block() {
                    @Override
                    public void block(String... strings) {
                        Log.d("AAA", "startBluetooth");
                        try {
                            mThermometerBluetoothApi.bluetoothConnect(//C6:05:04:03:5A:C7 C6:05:04:03:5B:28
                                    "MKErWenJi001", "C6:05:04:03:5A:C7",
                                    "00005970-6d75-4753-5053-676e6f6c7553",
                                    "02005970-6d75-4753-5053-676e6f6c7553",
                                    "02005970-6d75-4753-5053-676e6f6c7553", true, true);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }

}