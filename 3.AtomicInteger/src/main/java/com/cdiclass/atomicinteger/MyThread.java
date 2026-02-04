package com.cdiclass.atomicinteger;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread  implements Runnable{
    
    private AtomicInteger count;
    
    public MyThread(AtomicInteger count){
        this.count = count;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {           
            System.out.println(Thread.currentThread().getName()+ " -> contador = " + count.addAndGet(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    
    }
    
}
