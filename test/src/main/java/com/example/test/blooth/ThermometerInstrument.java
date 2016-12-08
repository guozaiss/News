package com.example.test.blooth;

public class ThermometerInstrument {
	public static float getResult(String data) {
		String dataArray[] = data.split(" ");
		if (dataArray != null && dataArray.length == 8
				&& !"BB".equals(dataArray[6]) && !"AA".equals(dataArray[5])) {
			String resultStr = dataArray[4] + dataArray[5];
			return (float) Integer.parseInt(resultStr, 16) / 100;
		}
		return 0;
	}
}
