package com.cdiclass.count;

public class MyThread implements Runnable{
    
    public static int count = 0;
    
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
