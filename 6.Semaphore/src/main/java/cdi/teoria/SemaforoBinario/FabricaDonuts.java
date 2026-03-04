package cdi.teoria.SemaforoBinario;

public class FabricaDonuts {
    static int num_trabajadores;
    public static void main(String[] args) {
        num_trabajadores = 20;
        
        ZonaTrabajo zonaTrabajo = new ZonaTrabajo();
        Thread trabajadores[] = new Thread[num_trabajadores];

        for (int i = 0; i < num_trabajadores; i++) {
            trabajadores[i] = new Thread(zonaTrabajo, ""+i);
            trabajadores[i].start();
        }

        for (int i = 0; i < num_trabajadores; i++) {
            try {
                trabajadores[i].join();
            } catch (InterruptedException e) {
            }
            
        }

        System.out.println("La fábrica quedó vacía… y los donuts sobrevivieron a la invasión de clientes 😎🍩");
    }   
}