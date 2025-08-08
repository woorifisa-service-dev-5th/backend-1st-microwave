package dev.syntax;
import java.util.Scanner;

import dev.syntax.model.Microwave;
import dev.syntax.thread.HeatTask;
import dev.syntax.thread.Timer;
import dev.syntax.thread.User;

public class MicrowaveMain {
    public static void main(String[] args) {
        Microwave microwave = new Microwave();
        Timer timer = new Timer(microwave);
        HeatTask heat = new HeatTask(microwave);
        User user = new User(1,microwave);
        microwave.addListener(heat);
        microwave.addListener(timer);
        // HeatTask 실행 스레드
        Thread timerThread = new Thread(timer);
        Thread heatThread = new Thread(heat);
        Thread userThread = new Thread(user);
        userThread.start();
        timerThread.start();
        heatThread.start();
    }
}