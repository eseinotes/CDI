package cdi.teoria.SemaforosCombinados;

import java.util.concurrent.Semaphore;

public class ZonaDegustacion implements Runnable {
    Semaphore aforo;
    Semaphore wc;

    public ZonaDegustacion(int num_clientes){
        aforo = new Semaphore(num_clientes);
        wc = new Semaphore(1);
    }

    public void run(){
        System.out.println("Cliente "+Thread.currentThread().getName()+" llega corriendo a la fábrica de donuts, ¡ansioso por probarlos! 🏃‍♂️🍩");
        try {
            aforo.acquire();
            System.out.println("Cliente "+Thread.currentThread().getName()+" logró entrar a la zona de degustación, ¡que comience el festín! 🎉🍩");
            Thread.sleep(15);
            wc.acquire();
            try{
                System.out.println("Cliente "+Thread.currentThread().getName()+" entra al baño como un ninja, misión: sobrevivir a los donuts 🌪️🚽🍩");
                Thread.sleep(5);
                System.out.println("Cliente "+Thread.currentThread().getName()+" salió del baño como un campeón, ¡los donuts no lo detendrán! 🏆🍩");
            }finally{
                wc.release();
            }
        } catch (InterruptedException e) {
        }finally{
            System.out.println("Cliente "+Thread.currentThread().getName()+" salió de la fábrica de donuts satisfecho y con la sonrisa más pegajosa del día 😋");
            aforo.release();
        }

        
    }

}
