package ru.igojig.task_1;

public class PingPong {

    private static final int COUNT = 200000;

    private boolean flag = true;

    public static void main(String[] args) {

        PingPong pingPong = new PingPong();

        Runnable r1 = () -> {
            for (int i = 0; i < COUNT; i++) {
                pingPong.print();
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < COUNT; i++) {
                pingPong.print();
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }

    synchronized public void print() {

        if (flag) {
            System.out.println("ping");
        } else {
            System.out.println("pong");
        }

        flag = !flag;

    }

    synchronized void ping() {
        while (!flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ping");
        flag = false;
        notify();
    }

    synchronized void pong() {
        while (flag) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("pong");
        flag = true;
        notify();
    }
}
