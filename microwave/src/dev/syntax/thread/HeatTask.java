package dev.syntax.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.syntax.model.Microwave;
import dev.syntax.model.MicrowaveListener;

public class HeatTask implements Runnable, MicrowaveListener {
	
	private static Logger log = LoggerFactory.getLogger(HeatTask.class);
	
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
						log.info("가열 일시정지됨, 대기 중...");
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
						log.error("스레드 인터럽트 발생", e);
						break;
					}				
					
				}
			}
			
			if (microwave.getIsRunning()) {		
				log.debug("3도씩 가열중!!");

				microwave.increaseTemp(3);
				log.debug("현재 온도: {}도", microwave.getTemperature());
				sleep(1000);
			}
			
		}
		
	}
	

    private void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("슬립 중 인터럽트 발생", e);
        }
    }

    @Override
    public void onStart() {
    	log.info("[HeatTask] 가열 시작 신호 받음 → 스레드 깨움");
        synchronized(this) {
            this.notify();
        }
    }

    @Override
    public void onStop() {
    	log.info("[HeatTask] 가열 중지 신호 받음");
    }
	
	
}