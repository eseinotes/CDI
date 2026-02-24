package com.cdiclass.countclass;

public class Count {
    
    private int cont;
    
    Count(int cont){
        this.cont = cont;
    }
    
    public void add(){
        cont++;
    }

    public int getCont() {
        return cont;
    }
    
    
    
}
