package com.example.test.blooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.test.R;
import com.readyidu.eshw.api.nativeinter.bluetooth.ESHWBluetoothApi;
import com.readyidu.eshw.api.nativeinter.bluetooth.ESHWBluetoothListener;
import com.readyidu.eshw.util.codeblock.Block;

import java.util.Timer;
import java.util.TimerTask;

public class BloothDevicesActivity extends AppCompatActivity implements View.OnClickListener {
    private Timer mTimer;
    private BroadcastReceiver bluetoothReconnectReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("com.readyidu.eshw.connect.ble.DEVICES_CHANGE".equals(intent.getAction())) {
                Log.e("AAA", intent.getStringExtra("deviceName") + " " + intent.getStringExtra("deviceAddress"));
                short rssi = intent.getExtras().getShort(
                        BluetoothDevice.EXTRA_RSSI);
                Log.e("AAAAAAA", String.valueOf(rssi));
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
            case R.id.scan:
                scanBloothDevices();
                break;
            case R.id.start:
                connectDevices();
                break;
        }
    }

    private void scanBloothDevices() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            // 设备不支持蓝牙
        }
        // 打开蓝牙
        if (adapter.isEnabled()) {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            // 设置蓝牙可见性，最多300秒
            intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
            startActivity(intent);
        }
    }

    private void scanDevices() {
        ESHWBluetoothApi.scanBluetoothDevice();
    }

    //TODO:链接蓝牙设备
    private ESHWBluetoothApi mBloodPressureBluetoothApi;
    private ESHWBluetoothApi mThermometerBluetoothApi;
    private ESHWBluetoothApi mBloodSugarBluetoothApi;
    private ESHWBluetoothApi bangBluetoothApi;

    private BongSport mBongSport;
    private BongHeartRate mBongHeartRate;

    private void connectDevices() {
        //血压，心率
        mBloodPressureBluetoothApi = new ESHWBluetoothApi();
        mBloodPressureBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {
            @Override
            public void bluetoothConnect() {
                Log.d("AAA", "连接血压计");
            }

            @Override
            public void bluetoothDisconnect() {
                Log.d("AAA", "断开血压计");
            }

            @Override
            public void dataReceive(String data) {
                try {
                    BloodPressure result = BloodPressureInstrument.getResult(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //体温
        mThermometerBluetoothApi = new ESHWBluetoothApi();
        mThermometerBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {
            @Override
            public void bluetoothConnect() {
                Log.d("AAA", "连接体温计");
            }

            @Override
            public void bluetoothDisconnect() {
                Log.d("AAA", "断开体温计");
            }

            @Override
            public void dataReceive(String data) {
                try {
                    float result = ThermometerInstrument.getResult(data);
                    Log.d("AAA", result + "Temperature");
                    if (result > 0) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //血糖
        mBloodSugarBluetoothApi = new ESHWBluetoothApi();
        mBloodSugarBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {

            @Override
            public void bluetoothConnect() {
                Log.d("AAA", "连接血糖计");
            }

            @Override
            public void bluetoothDisconnect() {
                Log.d("AAA", "断开血糖计");
            }

            @Override
            public void dataReceive(String data) {
                try {
                    float result = BloodSugarInstrument.getResult(data);
                    if (result > 0) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mBongSport = new BongSport();
        mBongHeartRate = new BongHeartRate();
        bangBluetoothApi = new ESHWBluetoothApi();
        bangBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {

            @Override
            public void dataReceive(String data) {
                BongSport.Sport sport = mBongSport.getResult(data);
                Log.d("AAA", "BongSport" + sport + "");
                if (sport != null) {
//                    energyTv.setText("能量：" + sport.getEnergy() + "焦耳");
//                    stepTv.setText("步数：" + sport.getStep());
//                    distanceTv.setText("距离：" + sport.getDistance() + "米");
                }
                int rate = mBongHeartRate.getResult(data);
                Log.d("AAA", "BongSport--------" + rate + "");
                if (rate > 0) {
//                    heartTv.setText("心率：" + rate + "次/分");
                }
            }

            @Override
            public void bluetoothDisconnect() {
                Log.d("AAA", "连接bong手环");

            }

            @Override
            public void bluetoothConnect() {
                Log.d("AAA", "断开bong手环");
            }
        });

        ESHWBluetoothApi.startBluetooth(this, new Block() {
                    @Override
                    public void block(String... strings) {
                        Log.d("AAA", "startBluetooth");
//                        try {
//                            mBloodSugarBluetoothApi.bluetoothDeviceConnect(
//                                    "MKXueTangYi001", "E0:E5:CF:91:3A:00",
//                                    "0000fc00-0000-1000-8000-00805f9b34fb",
//                                    "0000fca1-0000-1000-8000-00805f9b34fb",
//                                    "0000fca1-0000-1000-8000-00805f9b34fb", true);
//
//                            mThermometerBluetoothApi.bluetoothDeviceConnect(
//                                    "MKErWenJi001", "C6:05:04:03:5B:28",
//                                    "00005970-6d75-4753-5053-676e6f6c7553",
//                                    "02005970-6d75-4753-5053-676e6f6c7553",
//                                    "02005970-6d75-4753-5053-676e6f6c7553", true, true);
//
//                            mBloodPressureBluetoothApi.bluetoothDeviceConnect(
//                                    "MKXueYaJi001", "00:15:83:00:3E:14",
//                                    "000018f0-0000-1000-8000-00805f9b34fb",
//                                    "00002af0-0000-1000-8000-00805f9b34fb",
//                                    "00002af1-0000-1000-8000-00805f9b34fb", true);
//
//                            bangBluetoothApi.bluetoothDeviceConnect(
//                                    "bong3HR", "C9:D4:F2:04:6A:DC",
//                                    "6e400001-b5a3-f393-e0a9-e50e24dcca1e",
//                                    "6e400003-b5a3-f393-e0a9-e50e24dcca1e",
//                                    "6e400002-b5a3-f393-e0a9-e50e24dcca1e", false, true);
//                            mTimer = new Timer();
//                            mTimer.schedule(new BoneTimerTask(), 1000, 1000);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
                    }
                }
        );
    }

    private Handler bongHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                bangBluetoothApi.sendData(mBongSport.getSportCommand());
            } else {
                bangBluetoothApi.sendData(mBongHeartRate.getStartRateCommand());
                bangBluetoothApi.sendData(mBongHeartRate.getRateCommand());
            }
        }
    };

    class BoneTimerTask extends TimerTask {
        @Override
        public void run() {
            if (bangBluetoothApi.isConnect()) {
                bongHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                bongHandler.sendEmptyMessage(1);
            }
        }
    }
}