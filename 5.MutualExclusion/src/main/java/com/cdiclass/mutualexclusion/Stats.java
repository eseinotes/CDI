package com.cdiclass.mutualexclusion;


public class Stats {
    private int okTransfers = 0;
    private int failedTransfers = 0;

    public synchronized void incOk() {
        okTransfers++;
    }

    public synchronized void incFail() {
        failedTransfers++;
    }

    public synchronized String snapshot() {
        return "ok=" + okTransfers + ", fail=" + failedTransfers;
    }
}