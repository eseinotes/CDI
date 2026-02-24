package com.cdiclass.countersync;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CounterSync {

    public static void main(String[] args) {
        
        Thread[] threads = new Thread[100];

        Counter counter = new Counter(0);

        for (int i=0; i < threads.length; i++){
            threads[i] = new Thread(new MyThread(counter));
            threads[i].start();
        }
            
        for (int i=0; i < threads.length; i++){           
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(CounterSync.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
        System.out.println("Counter: "+ counter.getCont());
        
    }
}
