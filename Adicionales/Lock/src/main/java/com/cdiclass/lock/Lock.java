package com.cdiclass.lock;

import java.util.concurrent.locks.ReentrantLock;

public class Lock {

    public static void main(String[] args) {
        
        Thread[] threads = new Thread[100];
        
        Counter counter = new Counter(0);
        ReentrantLock lock = new ReentrantLock();
        
        for (int i=0; i < threads.length; i++){
            threads[i] = new Thread(new MyThread(lock, counter));
            threads[i].start();
        }
            
        for (int i=0; i < threads.length; i++){           
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                
            }
        }
            
        System.out.println("Counter: "+ counter.getCount());
        
    }
}
