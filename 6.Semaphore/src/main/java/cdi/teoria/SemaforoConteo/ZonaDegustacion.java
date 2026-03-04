package cdi.teoria.SemaforoConteo;

import java.util.concurrent.Semaphore;

public class ZonaDegustacion implements Runnable {
    Semaphore semaphore;

    public ZonaDegustacion(int num_clientes){
        semaphore = new Semaphore(num_clientes);
    }

    public void run(){
        System.out.println("Cliente "+Thread.currentThread().getName()+" llega corriendo a la fábrica de donuts, ¡ansioso por probarlos! 🏃‍♂️🍩");
        try {
            semaphore.acquire();
            System.out.println("Cliente "+Thread.currentThread().getName()+" logró entrar a la zona de degustación, ¡que comience el festín! 🎉🍩");
            Thread.sleep(15);
        } catch (InterruptedException e) {
        }finally{
            System.out.println("Cliente "+Thread.currentThread().getName()+" salió de la fábrica de donuts satisfecho y con la sonrisa más pegajosa del día 😋");
            semaphore.release();
        }

        
    }

}
