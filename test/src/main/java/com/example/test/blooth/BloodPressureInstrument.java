package com.example.test.blooth;

public class BloodPressureInstrument {

	public static BloodPressure getResult(String data) {
		String dataArray[] = data.split(" ");
		if (dataArray != null && dataArray.length == 17
				&& "1C".equals(dataArray[4])) {
			return new BloodPressure(Integer.parseInt(dataArray[8], 16),
					Integer.parseInt(dataArray[6], 16), Integer.parseInt(
							dataArray[12], 16));
		}
		return null;
	}
}
