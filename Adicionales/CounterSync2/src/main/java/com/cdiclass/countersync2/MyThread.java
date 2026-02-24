package com.cdiclass.countersync2;

public class MyThread implements Runnable{
    
    private Counter count;

    public MyThread(Counter count) {
        this.count = count;
    }
    
    @Override
    public void run(){
        
        synchronized(count){
            count.add();
        }    
    }
    
}
