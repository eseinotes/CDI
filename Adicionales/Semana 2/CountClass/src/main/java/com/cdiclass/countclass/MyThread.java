package com.cdiclass.countclass;

public class MyThread  implements Runnable{
    
    private Count count;
    
    public MyThread(Count count){
        this.count = count;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 3; i++) {           
            count.add();
            System.out.println(Thread.currentThread().getName()+ " -> contador = " + count.getCont());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    
    }
    
}
