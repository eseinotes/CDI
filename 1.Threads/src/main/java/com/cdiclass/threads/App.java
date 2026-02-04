package com.cdiclass.threads;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
                
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Cuantos hilos quieres crear? ");
        int numThreads = sc.nextInt();
        
        Thread[] threads = new Thread[numThreads];
        
        for (int i = 0; i < numThreads; i++){
            MyThread r = new MyThread(i);
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
