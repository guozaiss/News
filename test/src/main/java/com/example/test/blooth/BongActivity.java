package com.example.test.blooth;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.example.test.R;
import com.readyidu.eshw.api.bluetooth.ESHWBluetoothApi;
import com.readyidu.eshw.api.bluetooth.ESHWBluetoothListener;
import com.readyidu.eshw.util.Block;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class BongActivity extends Activity {
    /**
     * 手环
     */
    private ESHWBluetoothApi bongBluetoothApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bong);

        bongBluetoothApi = new ESHWBluetoothApi();
        ESHWBluetoothApi.startBluetooth(this, new Block() {
            @Override
            public void block(String... strings) {
                bongBluetoothApi.bluetoothConnect( //bong3HR     C9:D4:F2:04:6A:DC    F0:5B:8D:C5:3B:2D  bong2PH   D6:5A:79:6B:88:C5
                        "bong2PH", "D6:5A:79:6B:88:C5",
                        "6e400001-b5a3-f393-e0a9-e50e24dcca1e",
                        "6e400003-b5a3-f393-e0a9-e50e24dcca1e",
                        "6e400002-b5a3-f393-e0a9-e50e24dcca1e", true, true);
                bongBluetoothApi.setBluetoothListener(new ESHWBluetoothListener() {
                    @Override
                    public void bluetoothConnect() {
                        mUpdateBongStateHandler.removeCallbacks(mUpdateBongStateRunnable);
                        mUpdateBongStateHandler.post(mUpdateBongStateRunnable);
//                        BongHeartRate rate = new BongHeartRate();
//                        bongBluetoothApi.sendData(rate.getStartRateCommand());//测量心率
                        bongBluetoothApi.sendData(ay());//同步时间
                    }

                    @Override
                    public void bluetoothDisconnect() {

                    }

                    @Override
                    public void dataReceive(String s) {
//                        int rate = new BongHeartRate().getResult(s);
//                        Log.d("Heart Rate->", String.valueOf(rate));
                        Sport sport = getResult(s);
                        if (sport != null) {
                            Log.d("BongSport ->", sport.getDistance() + " " + sport.getEnergy() + " " + sport.getStep());
                        }
//                        bongBluetoothApi.sendData("end");
//                        mUpdateBongStateHandler.removeCallbacks(mUpdateBongStateRunnable);
//                        mUpdateBongStateHandler.post(mUpdateBongStateRunnable);
                    }
                });
            }
        });
    }

    //TODO: 每隔一段时间上传BONG更新状态指令
    private final long UPDATE_RATE = 1000l;
    private Handler mUpdateBongStateHandler = new Handler();
    private Runnable mUpdateBongStateRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                BongHeartRate rate = new BongHeartRate();
                //当处于测心律时，需要上传更新指令
//                bongBluetoothApi.sendData(rate.getRateCommand());
                //当处于测跑步时，需要上传更新指令
                bongBluetoothApi.sendData(getSportCommand());
                mUpdateBongStateHandler.postDelayed(mUpdateBongStateRunnable, UPDATE_RATE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };

    public String ay() {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(System.currentTimeMillis()));
        return "100000" + year(instance) + other(instance.get(Calendar.MONTH) + 1) +
                other(instance.get(Calendar.DAY_OF_MONTH)) + other(instance.get(Calendar.HOUR_OF_DAY))
                + other(instance.get(Calendar.MINUTE)) + other(instance.get(Calendar.SECOND)) + "0800";
    }

    private String year(Calendar instance) {
        String s = Integer.toHexString(instance.get(Calendar.YEAR));
        if (!TextUtils.isEmpty(s)) {
            if (s.length() < 4) {
                return "0" + s;
            } else {
                return s;
            }
        }
        return "0000";
    }

    private String other(int number) {
        String s = Integer.toHexString(number);
        if (!TextUtils.isEmpty(s)) {
            if (s.length() < 2) {
                return "0" + s;
            } else {
                return s;
            }
        }
        return "00";
    }

    public String aL() {
        return "29000000200";
    }

    public String getSportCommand() {
        return "2000000013"
                + getStrTimeForHex(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(1))
                + getStrTimeForHex(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(2));
    }

    public static Date weeHours(Date date) {//weeHours(new Date(System.currentTimeMillis())).getTime()
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);
        return cal.getTime();
    }

    public Sport getResult(String data) {
        Log.i("guo", data);
        String dataArray[] = data.split(" ");
        if (dataArray.length == 16) {
            try {
                int year = Integer.parseInt(dataArray[3], 16) & 0xf0;
                year |= (Integer.parseInt(dataArray[2], 16) & 0x03) << 4;
                year += 2000;

                int month = Integer.parseInt(dataArray[0], 16) >> 4;

                int day = Integer.parseInt(dataArray[1], 16) >> 7;
                day |= (Integer.parseInt(dataArray[0], 16) & 0x0f) << 1;

                int hour = (Integer.parseInt(dataArray[1], 16) >> 2) & 0x1f;

                int minute = (Integer.parseInt(dataArray[2], 16) & 0xf0) >> 4;
                minute |= (Integer.parseInt(dataArray[1], 16) & 0x03) << 4;

                int steps0, steps1;
                steps0 = Integer.parseInt(dataArray[7], 16) & 0x7f;
                steps0 |= (Integer.parseInt(dataArray[2], 16) << 4) & 0x80;

                steps1 = Integer.parseInt(dataArray[11], 16) & 0x7f;
                steps1 |= (Integer.parseInt(dataArray[2], 16) << 5) & 0x80;

                Log.e("AAAAAAAA", year + " " + month + " " + day + " " + hour + " " + minute + " " + steps0 + " " + steps1);
                Sport sport = new Sport();
                sport.setEnergy(0);
                sport.setStep(steps0);
                sport.setDistance(0);
                return sport;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public class Sport {
        private int energy;
        private int step;
        private int distance;

        public Sport() {

        }

        public Sport(int energy, int step, int distance) {
            super();
            this.energy = energy;
            this.step = step;
            this.distance = distance;
        }

        public int getEnergy() {
            return energy;
        }

        public void setEnergy(int energy) {
            this.energy = energy;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public String toString() {
            return "Sport [energy=" + energy + ", step=" + step + ", distance="
                    + distance + "]";
        }
    }

    public String getStrTimeForHex(long time) {
        TimeZone CHINA_TIMEZONE = TimeZone.getTimeZone("GMT+08:00");

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        calendar.setTimeZone(CHINA_TIMEZONE);
        int year = calendar.get(Calendar.YEAR) % 100;// 只取2位年份
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return (year > 0xf ? "" : "0")
                + Integer.toHexString(year)
                + // 只取2位年份
                (month > 0xf ? "" : "0") + Integer.toHexString(month)
                + (day > 0xf ? "" : "0") + Integer.toHexString(day)
                + (hour > 0xf ? "" : "0") + Integer.toHexString(hour)
                + (minute > 0xf ? "" : "0") + Integer.toHexString(minute);
    }
}
