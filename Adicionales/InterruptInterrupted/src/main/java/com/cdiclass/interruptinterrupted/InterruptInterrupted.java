package com.cdiclass.interruptinterrupted;

public class InterruptInterrupted {

    public static void main(String[] args) {
        
        
        Thread t1 = new Thread(new MyThreadInterrupt());
        Thread t2 = new Thread(new MyThreadInterrupted());
        
        t1.start();
        t2.start();
        
        
    }
}
