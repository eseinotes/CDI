package com.cdiclass.atomicinteger;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Cuantos hilos quieres crear? ");
        int numThreads = sc.nextInt();
        
        Thread[] threads = new Thread[numThreads];
        AtomicInteger count = new AtomicInteger(0);
        
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
        System.out.println("Resultado final "+count.get());
        
    }
}
