package cdi.teoria.SemaforoBinarioMaquinas;

public class FabricaDonuts {
    static int num_trabajadores;
    static int num_maquinas;
    public static void main(String[] args) {
        num_trabajadores = 20;
        num_maquinas = 3;
        
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