package com.cdiclass.countnostatic;

import java.util.Scanner;

public class CountNoStatic {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Cuantos hilos quieres crear? ");
        int numThreads = sc.nextInt();
        
        Thread[] threads = new Thread[numThreads];
        int count = 0;
        
        for (int i = 0; i < numThreads; i++){
            MyThread r = new MyThread(count);
            threads[i] = new Thread(r);
            threads[i].start();
        }
        
        
        for (int i = 0; i < numThreads; i++){
            try {
                threads[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Thread Interruption exception");
            }
        }
        
        System.out.println("Fin del programa");
        
    
    }
}
