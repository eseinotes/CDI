package com.cdiclass.mutualexclusion;

import java.util.Random;

public class TransferTask implements Runnable {
    private final Bank bank;
    private final Stats stats;
    private final int ops;
    private final Random rnd = new Random();

    public TransferTask(Bank bank, Stats stats, int ops) {
        this.bank = bank;
        this.stats = stats;
        this.ops = ops;
    }

    @Override
    public void run() {
        int n = bank.size();

        for (int i = 0; i < ops; i++) {
            int from = rnd.nextInt(n);
            int to = rnd.nextInt(n);
            while (to == from) to = rnd.nextInt(n);

            int amount = 1 + rnd.nextInt(50);

            boolean ok = bank.transfer(from, to, amount);
            if (ok) stats.incOk();
            else stats.incFail();
        }
    }
}