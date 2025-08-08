package dev.syntax.thread;

import dev.syntax.model.Microwave;
import dev.syntax.model.MicrowaveListener;

public class HeatTask implements Runnable, MicrowaveListener {
	private Microwave microwave;

	public HeatTask(Microwave microwave) {
		super();
		this.microwave = microwave;
	}

	@Override
	public void run() {
		while(true) {
			synchronized (this) {
				while (!microwave.getIsRunning()) {
					try {
//						System.out.println("열 일시정지됨, 대기중");
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}				
					
				}
			}
			
			if (microwave.getIsRunning()) {		
				//System.out.println("3도씩 가열중!!");
				microwave.increaseTemp(3);
				//System.out.println("현재 온도: " + microwave.getTemperature());
				sleep(1000);
			}
			
		}
		
	}
	

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void onStart() {
        //System.out.println("[HeatTask] 가열 시작 신호 받음");
        synchronized(this) {
            this.notify();
        }
    }

    @Override
    public void onStop() {
       // System.out.println("[HeatTask] 가열 중지 신호 받음");
    }
	
	
}
