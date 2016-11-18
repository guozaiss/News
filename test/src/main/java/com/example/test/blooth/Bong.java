package com.example.test.blooth;

import java.util.Calendar;
import java.util.TimeZone;

public abstract class Bong {

	abstract public Object getResult(String data);

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
