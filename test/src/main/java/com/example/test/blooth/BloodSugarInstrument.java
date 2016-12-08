package com.example.test.blooth;

import android.util.Log;

public class BloodSugarInstrument {
	public static float getResult(String data) {
		Log.i("pull", "data " + data);
		String dataArray[] = data.split(" ");
		if (dataArray != null && dataArray.length == 8
				&& "88".equals(dataArray[6])) {
			return ((float) Integer.parseInt(dataArray[4] + dataArray[5], 16)) / 18.0f;
		}
		return 0;
	}
}
