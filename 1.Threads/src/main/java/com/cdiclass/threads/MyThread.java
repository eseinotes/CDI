package com.cdiclass.threads;

public class MyThread implements Runnable{
    
    private int name;
    
    MyThread(int name){
        this.name = name;
    }
    
    
    @Override
    public void run(){
        try {
            Thread.sleep(1000);
            System.out.println("Soy el hilo " + name);
        } catch (InterruptedException ex) {
            System.out.println("Thread Interruption exception");
        }
        
    }
    
}
