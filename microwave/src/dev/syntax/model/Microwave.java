package dev.syntax.model;

import java.util.ArrayList;
import java.util.List;

public class Microwave {

	private List<MicrowaveListener> listeners = new ArrayList<>();
	private boolean isOpened;
	private boolean isRunning;
	private int inputTime;
	private int temperature;
	
	

	public Microwave() {
		super();
		this.isOpened = false;
		this.isRunning = false;
		this.temperature = 20;

	}
	
	public void addListener(MicrowaveListener listener) {
		listeners.add(listener);
	}
	
	public int getTemperature() {
		return temperature;
	}
	
	public void increaseTemp(int temp) {
		if (this.temperature + temp >= 100) {
			this.temperature = 100;			
		} else {
			this.temperature += temp;
		}
	}
	
	public void decreaseTemp(int temp) {
		if (this.temperature - temp <= 20) {
			this.temperature = 20;
		} else {
			this.temperature -= temp;
		}
	}
	
	public boolean getIsOpened() {
		return isOpened;
	}

	public boolean getIsRunning() {
		return isRunning;
	}
	
	public void turnOn() {
		if (isRunning) {
			System.out.println("⚠️이미 작동중입니다⚠️");
		} else if (!isOpened) {
			this.isRunning = true;
			Print.startPrint(getTime(), getTemperature());
			for (MicrowaveListener listener : listeners) {
				listener.onStart();
			}
		} else {
			arletIsOpened();
		}
	}
	
	public void turnOff() {
		if (!this.isRunning) {
			System.out.println("⚠️이미 정지 상태입니다⚠️");
		} else {
			if (getTime() == 0) Print.endPrint();
			if (getTime() > 0) Print.pausePrint(getTime(), getTemperature());
			this.isRunning = false;
			for (MicrowaveListener listener : listeners) {
				listener.onStop();
			}			
		}
	}
	
	public void setDoorOpened() {
		if (this.isOpened) {
			System.out.println("⚠️문이 이미 열려 있습니다⚠️");
		} else {
			this.isOpened = true;
			if (this.isRunning) turnOff();
			System.out.println("◻️◻️문을 열었습니다◻️◻️");		
		}
	}
	
	public void setDoorClosed() {
		if (!this.isOpened) {
			System.out.println("⚠️문이 이미 닫혀 있습니다⚠️");
		} else {
			this.isOpened = false;
			System.out.println("◼️◼️문을 닫았습니다◼️◼️");
			if(this.inputTime > 0) turnOn();
					
		}
	}
	
	public void arletIsOpened() {
		System.out.println("⚠️문을 닫아주세요⚠️");
	}
	
	public void printTime() {
		if (!this.isRunning && this.inputTime > 0) {
			System.out.println("⛔정지 상태입니다⛔ " + getTime() + "초 남음..");
		} else {
			System.out.println(getTime() + "초 남음..");
		}
	}
	
	public void setTime(int seconds) {
		this.inputTime = seconds;
	}
	
	public int getTime() {
		return this.inputTime;
	}
	
}