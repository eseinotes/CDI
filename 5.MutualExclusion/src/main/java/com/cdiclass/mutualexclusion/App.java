package com.cdiclass.mutualexclusion;

/**
 * Clase principal que lanza la simulación de transferencias bancarias concurrentes.
 *
 * Crea un banco con varias cuentas, lanza múltiples hilos que realizan
 * transferencias aleatorias entre cuentas y, al finalizar, comprueba que
 * el saldo total del banco no ha cambiado.
 *
 * Objetivo didáctico:
 * - Mostrar cómo múltiples hilos acceden a un recurso compartido (Bank).
 * - Comprobar que la exclusión mutua está correctamente implementada.
 * - Detectar posibles condiciones de carrera si la sincronización falla.
 *
 * Si el total final no coincide con el inicial, significa que existe
 * un problema de concurrencia.
 */

public class App {

    public static void main(String[] args) {
        int nAccounts = 20;
        int initialBalance = 1000;
        int nThreads = 30;
        int opsPerThread = 50_000;

        Bank bank = new Bank(nAccounts, initialBalance);
        Stats stats = new Stats();

        int expectedTotal = bank.totalBalance();

        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            threads[i] = new Thread(new TransferTask(bank, stats, opsPerThread), "T-" + i);
            threads[i].start();
        }

        for (Thread t : threads){ 
            try {
                t.join();
            } catch (InterruptedException ex) {
                
            }
        }

        int finalTotal = bank.totalBalance();

        System.out.println("Expected total: " + expectedTotal);
        System.out.println("Final total:    " + finalTotal);
        System.out.println("Stats:          " + stats.snapshot());

        if (finalTotal != expectedTotal) {
            throw new AssertionError("Total mismatch! Race condition somewhere.");
        }
    }
}
