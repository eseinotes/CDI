package com.cdiclass.countersync3;

public class MyThread implements Runnable{
    
    private int cont;

    public MyThread(int cont) {
        this.cont = cont;
    }
        
    
    @Override
    public void run(){
        
        cont++;
        
    
    }
    
    
    
}
