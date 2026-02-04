package com.cdiclass.threadlocal;

public class MyThread implements Runnable{
    
    private static ThreadLocal<Integer> count = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 0;
        }
    };
    
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            int value = count.get();
            count.set(value + 1);

            System.out.println(Thread.currentThread().getName()+ " -> contador = "+ count.get()
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
