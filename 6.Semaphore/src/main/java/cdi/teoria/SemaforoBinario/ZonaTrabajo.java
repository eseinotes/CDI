package cdi.teoria.SemaforoBinario;

import java.util.concurrent.Semaphore;

public class ZonaTrabajo implements Runnable {
    Semaphore semaphore;

    public ZonaTrabajo(){
        semaphore = new Semaphore(1);
    }

    public void run(){
        System.out.println("Trabajador "+Thread.currentThread().getName()+" se acerca a la máquina de donuts, ¡a punto de empezar la magia! 🔧🍩 ");
        try {
            semaphore.acquire();
            System.out.println("Trabajador "+Thread.currentThread().getName()+" mezcla ingredientes y sopla burbujas de azúcar ✨🍩");
            Thread.sleep(15);
        } catch (InterruptedException e) {
        }finally{
            System.out.println("Trabajador "+Thread.currentThread().getName()+" terminó con la máquina, ¡donuts listos y seguros! 😎🍩");
            semaphore.release();
        }

        
    }

}
