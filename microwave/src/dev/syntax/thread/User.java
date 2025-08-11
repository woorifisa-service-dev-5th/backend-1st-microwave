package dev.syntax.thread;

import java.util.Scanner;
import dev.syntax.model.Microwave;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger(User.class);

	
	private int userNum;
	private Microwave microwave;
	private Scanner scanner;

	public User(int userNum, Microwave microwave) {
		this.userNum = userNum;
		this.microwave = microwave;
		this.scanner = new Scanner(System.in);
	}

	@Override
	public void run() {
		log.debug("User#{} 스레드 시작", userNum);
		while (true) {
			showMenu();
			log.debug("User#{} 입력 대기 중", userNum);
			
			int input = Integer.parseInt(scanner.nextLine());
			log.debug("입력값: {}", input);
			
			handleInput(input);
		}
	}

	private void showMenu() {
		System.out.println("\n[User #" + userNum + "| 전자레인지 조작]");
		System.out.println("1. 문 열기");
		System.out.println("2. 문 닫기");
		System.out.println("3. 작동 시작");
		System.out.println("4. 일시 정지");
		System.out.println("5. 남은 시간");
		System.out.println("6. 종료");
		System.out.print("선택 > ");
	}

	private void handleInput(int input) {
		switch (input) {
		case 1 -> { // 문 열기 요청
			log.info("문 열기 요청");
			microwave.setDoorOpened();
		}
		case 2 -> { // 문 닫기 요청
			log.info("문 닫기 요청");
			microwave.setDoorClosed();
		}
		case 3 -> { //시작 요청
			log.info("작동 시작 요청");
			if (microwave.getTime() > 0) {
				log.debug("남은 시간 있음 → 즉시 작동");
				microwave.turnOn();
			} else if (microwave.getIsOpened()) {
				microwave.arletIsOpened();
			} else {
				log.info("작동 시간 입력");
				System.out.print("작동할 시간(초)을 입력하세요 > ");
				int seconds = Integer.parseInt(scanner.nextLine());
				log.debug("사용자 입력 시간: {}초", seconds);
				// 전자레인지에 시간 설정
				microwave.setTime(seconds);
				microwave.turnOn();
			}
		}
		case 4 -> { // 작동 멈추기 요청
			log.info("작동 멈추기 요청");
			microwave.turnOff();
		}
		case 5 -> { // 시간 확인 요청
			log.info("시간 확인 요청");
			microwave.printTime();
		}
		case 6 -> { // 종료 요청
			log.info("종료 요청 -> 쓰레드 종료");
			log.info("프로그램 종료");
			System.exit(0);
		}
		
		default -> log.warn("잘못된 입력: " + input);
		
		}
	}
}