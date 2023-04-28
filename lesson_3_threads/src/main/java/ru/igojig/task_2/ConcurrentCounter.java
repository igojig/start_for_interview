package ru.igojig.task_2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {
    private final Lock lock = new ReentrantLock();

    private int counter = 0;

    public void increase() {
        lock.lock();
        try {
            ++counter;
        } finally {
            lock.unlock();
        }
    }

    public void decrease() {
        lock.lock();
        try {
             --counter;
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {

        try {
            if(lock.tryLock(500, TimeUnit.MILLISECONDS)){
               try{
                   return counter;
               }
               finally {
                   lock.unlock();
               }
            }
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
// не корректно, но для проверки, наверное можно бросить Exception
        return -1;
    }

}
