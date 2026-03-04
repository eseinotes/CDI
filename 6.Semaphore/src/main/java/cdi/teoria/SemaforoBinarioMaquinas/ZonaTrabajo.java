package cdi.teoria.SemaforoBinarioMaquinas;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ZonaTrabajo implements Runnable {
    Semaphore semaphore;
    MaquinaDonuts maquinasDonuts[];
    Random random;

    public ZonaTrabajo(){
        semaphore = new Semaphore(1);
        maquinasDonuts = new MaquinaDonuts[FabricaDonuts.num_maquinas];
        for (int i = 0; i < FabricaDonuts.num_maquinas; i++) {
            maquinasDonuts[i] = new MaquinaDonuts();
        }
        this.random = new Random();
    }

    public void run(){
        System.out.println("Trabajador "+Thread.currentThread().getName()+" se acerca a la máquina de donuts, ¡a punto de empezar la magia! 🔧🍩 ");
        try {
            semaphore.acquire();
            int maquina = random.nextInt(FabricaDonuts.num_maquinas);
            maquinasDonuts[maquina].fabricarDonut(maquina);
        }catch (InterruptedException e) {
        }finally{
            System.out.println("Trabajador "+Thread.currentThread().getName()+" terminó con la máquina, ¡donuts listos y seguros! 😎🍩");
            semaphore.release();
        }

        
    }

}
