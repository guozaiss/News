package com.example.test.blooth;

public class BloodPressure {
	private int dPressure;
	private int sPressure;
	private int heartRate;

	public BloodPressure() {
		// TODO Auto-generated constructor stub
	}

	public BloodPressure(int dPressure, int sPressure, int heartRate) {
		super();
		this.dPressure = dPressure;
		this.sPressure = sPressure;
		this.heartRate = heartRate;
	}

	public int getdPressure() {
		return dPressure;
	}

	public void setdPressure(int dPressure) {
		this.dPressure = dPressure;
	}

	public int getsPressure() {
		return sPressure;
	}

	public void setsPressure(int sPressure) {
		this.sPressure = sPressure;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	@Override
	public String toString() {
		return "BloodPressure [dPressure=" + dPressure + ", sPressure="
				+ sPressure + ", heartRate=" + heartRate + "]";
	}
}
