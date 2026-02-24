package com.cdiclass.countersync;

public class MyThread implements Runnable{
        
    private Counter cont;

    public MyThread(Counter cont) {
        this.cont = cont;
    }
    
    
    @Override
    public void run(){
        cont.add();    
    }
   
}
