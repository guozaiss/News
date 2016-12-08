package com.example.test.blooth;

import java.util.concurrent.TimeUnit;

public class BongHeartRate extends Bong {

	public String getRateCommand() {
		// TODO Auto-generated method stub
		return "2600000052"
				+ getStrTimeForHex(System.currentTimeMillis()
						- TimeUnit.HOURS.toMillis(3))
				+ getStrTimeForHex(System.currentTimeMillis());
	}

	public String getStartRateCommand() {
		// TODO Auto-generated method stub
		return "2600000051"
				+ getStrTimeForHex(System.currentTimeMillis()
						- TimeUnit.HOURS.toMillis(3))
				+ getStrTimeForHex(System.currentTimeMillis());
	}

	@Override
	public Integer getResult(String data) {
		// TODO Auto-generated method stub
		String dataArray[] = data.split(" ");
		if (dataArray.length == 4 && "01".equals(dataArray[2])) {
			return Integer.parseInt(dataArray[3], 16);
		}
		return 0;
	}
}
