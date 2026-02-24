package com.cdiclass.lock;

public class Counter {
    
    private int count;

    public Counter(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
    
    public void add(){
        count++;
    }
    
}
