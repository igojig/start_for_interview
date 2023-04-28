package ru.igojig.task_1;

public class PingPong {

    private static final int COUNT = 20;

    private boolean flag=true;

    public static void main(String[] args) {

        PingPong pingPong = new PingPong();

        Runnable r1 = () -> {
            for (int i = 0; i < COUNT; i++) {
                pingPong.ping();
            }
        };

        Runnable r2 = () -> {
            for (int i = 0; i < COUNT; i++) {
                pingPong.pong();
            }
        };

        new Thread(r1).start();
        new Thread(r2).start();
    }

    synchronized void ping()  {
        while (!flag){
            try{
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("ping");
        flag=false;
        notify();
    }

    synchronized void pong() {
        while (flag){
            try{
                wait();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println("pong");
        flag=true;
        notify();
    }
}
