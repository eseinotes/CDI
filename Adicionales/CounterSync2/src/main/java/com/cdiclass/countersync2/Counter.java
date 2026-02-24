package com.cdiclass.countersync2;

public class Counter {
    
    private int cont;

    public Counter(int cont) {
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }
    
    public void add(){
        cont++;
    }
    
    
}
