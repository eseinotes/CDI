
package com.cdiclass.mutualexclusion;

import java.util.concurrent.locks.ReentrantLock;


public class Bank {
    private final int[] accounts;
    private final ReentrantLock bankLock = new ReentrantLock();

    public Bank(int nAccounts, int initialBalance) {
        this.accounts = new int[nAccounts];
        for (int i = 0; i < nAccounts; i++) accounts[i] = initialBalance;
    }

    public int size() {
        return accounts.length;
    }

    // Operación compuesta: requiere exclusión mutua real.
    public boolean transfer(int from, int to, int amount) {
        if (from == to) throw new IllegalArgumentException("from == to");
        if (amount <= 0) throw new IllegalArgumentException("amount <= 0");

        bankLock.lock();
        try {
            if (accounts[from] < amount) return false;
            accounts[from] -= amount;
            accounts[to] += amount;
            return true;
        } finally {
            bankLock.unlock();
        }
    }

    // Snapshot consistente del total (también protegido por el MISMO lock del banco).
    public int totalBalance() {
        bankLock.lock();
        try {
            int sum = 0;
            for (int v : accounts) sum += v;
            return sum;
        } finally {
            bankLock.unlock();
        }
    }
}

