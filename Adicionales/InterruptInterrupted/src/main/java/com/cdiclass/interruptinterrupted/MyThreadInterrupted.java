package com.cdiclass.interruptinterrupted;

public class MyThreadInterrupted  implements Runnable{
    
    @Override
    public void run(){
        
        try {
            Thread.currentThread().interrupted();
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("He sido interrumpido");
        }
    
    }
    
}