package com.cdiclass.countersync3;

public class CounterSync3 {

    public static void main(String[] args) {
        
        Thread[] threads = new Thread[100];

        int cont = 0;

        for (int i=0; i < threads.length; i++){
            threads[i] = new Thread(new MyThread(cont));
            threads[i].start();
        }
            
        for (int i=0; i < threads.length; i++){           
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                
            }
        }
            
        System.out.println("Counter: "+ cont);
        
    }
}
