package com.cdiclass.countnostatic;

public class MyThread implements Runnable{
    
    private int count = 0;
    
    public MyThread(int count){
        this.count = count;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {           
            count++;
            System.out.println(Thread.currentThread().getName()+ " -> contador = " + count);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    
    }
    
}
