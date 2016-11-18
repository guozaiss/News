package com.example.test.blooth;

import java.util.concurrent.TimeUnit;


public class BongSport extends Bong {
	public String getSportCommand() {
		// TODO Auto-generated method stub
		return "2000000015"
				+ getStrTimeForHex(System.currentTimeMillis()
						- TimeUnit.HOURS.toMillis(3))
				+ getStrTimeForHex(System.currentTimeMillis());
	}

	@Override
	public Sport getResult(String data) {
		// TODO Auto-generated method stub
		String dataArray[] = data.split(" ");
		if (dataArray.length == 12) {
			Sport sport = new Sport();
			sport.setEnergy(Integer.parseInt(
					dataArray[0].substring(dataArray[0].length() - 2)
							+ dataArray[1] + dataArray[2] + dataArray[3], 16));
			sport.setStep(Integer.parseInt(dataArray[4] + dataArray[5]
					+ dataArray[6] + dataArray[7], 16));
			sport.setDistance(Integer.parseInt(dataArray[8] + dataArray[9]
					+ dataArray[10] + dataArray[11], 16));
			return sport;
		}
		return null;
	}

	public  class Sport {
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
}
