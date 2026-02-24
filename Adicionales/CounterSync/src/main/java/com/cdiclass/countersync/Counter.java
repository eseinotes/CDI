package com.cdiclass.countersync;

public class Counter {
    
    private int cont = 0;

    public Counter(int cont) {
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }
    
    public synchronized void add(){
        cont++;
    }
    
}
