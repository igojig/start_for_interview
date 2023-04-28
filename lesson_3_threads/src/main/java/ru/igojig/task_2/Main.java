package ru.igojig.task_2;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int THREAD_COUNT = 20;
    private static final int ELEMENT_COUNT_IN_THREAD = 10_000_000;

    public static void main(String[] args) throws InterruptedException {

        List<Thread> threadList=new ArrayList<>();

        ConcurrentCounter counter = new ConcurrentCounter();

        for (int i = 0; i < THREAD_COUNT; i++) {

            threadList.add(new Thread(() -> {
                for (int j = 0; j < ELEMENT_COUNT_IN_THREAD; j++) {
                    counter.increase();
                }
            }));

            threadList.add(new Thread(() -> {
                for (int j = 0; j < ELEMENT_COUNT_IN_THREAD; j++) {
                    counter.decrease();
                }
            }));
        }

        for(Thread thread: threadList){
            thread.start();
        }

        Thread.sleep(1000);

        System.out.printf("Intermediate value of counter: %d%n", counter.getCounter());

        for(Thread thread: threadList){
            thread.join();
        }

        // должен получиться 0
        System.out.printf("Final value of counter: %d%n", counter.getCounter());

    }
}
