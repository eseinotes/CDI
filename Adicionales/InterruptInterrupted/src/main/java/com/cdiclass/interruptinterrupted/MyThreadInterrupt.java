package com.cdiclass.interruptinterrupted;

public class MyThreadInterrupt implements Runnable{
    
    @Override
    public void run(){
        
        try {
            Thread.currentThread().interrupt();
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("He sido interrumpido");
        }
    
    }
    
}
