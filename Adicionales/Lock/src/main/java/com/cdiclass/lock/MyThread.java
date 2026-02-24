package com.cdiclass.lock;

import java.util.concurrent.locks.ReentrantLock;

public class MyThread implements Runnable{
    
    private ReentrantLock lock;
    private Counter counter;

    public MyThread(ReentrantLock lock, Counter counter) {
        this.lock = lock;
        this.counter = counter;
    }
    
    @Override
    public void run(){
        
        lock.lock();
        try {
            counter.add();
        } finally {
            lock.unlock();
        }
    
    }
    
}
