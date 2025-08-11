package dev.syntax.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.syntax.model.Microwave;
import dev.syntax.model.MicrowaveListener;

public class Timer implements Runnable, MicrowaveListener {

	private static Logger log = LoggerFactory.getLogger(Timer.class);
	private int remainTime;

	private Microwave microwave;

	public Timer(Microwave microwave) {
		super();
		this.microwave = microwave;
	}

	/**
	 * 1. 사용자가 아예 타이머를 끈 (종료)상태 2. 시간이 다 됐을 때 종료 알람 3. 남은 시간을 찍어주는 기능
	 */
	public synchronized void cancelTimer() {
		microwave.turnOff();
        log.info("사용자가 타이머를 종료했습니다. (남은 시간: {}s)", microwave.getTime());
	}

	public synchronized void finishAlarm() {
		microwave.setTime(0);
		microwave.turnOff();
        log.info("요리 완료 알람: 타이머 종료.");

	}

	

	@Override
	public void run() {
        log.info("Timer 스레드 시작");
		while (true) {
			synchronized (this) {
				while (!microwave.getIsRunning()) {
					try {
						wait();
					} catch (InterruptedException e) {
                        log.warn("타이머 대기 중 인터럽트 발생 → 스레드 종료");
						break;
					}
				}
			}
			
			if(microwave.getIsRunning()) {
				remainTime = microwave.getTime()-1;
				microwave.setTime(remainTime);
				sleep(1000);
                log.debug("틱: {}s 남음", remainTime);

			}
			

			if (remainTime <= 0) {
				finishAlarm();
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
        log.info("[TimerTask] 시작 신호 수신");
		synchronized (this) {
			this.notify();
		}
	}

	@Override
	public void onStop() {
        log.info("[TimerTask] 중지 신호 수신");
	}

}