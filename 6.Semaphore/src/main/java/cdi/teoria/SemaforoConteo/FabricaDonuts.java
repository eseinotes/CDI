package cdi.teoria.SemaforoConteo;

public class FabricaDonuts {
    static int num_clientes;
    public static void main(String[] args) {
        num_clientes = 20;
        int clientes_fabrica = 50*20/100;
        
        ZonaDegustacion zonaDegustacion = new ZonaDegustacion(clientes_fabrica);
        Thread clientes[] = new Thread[num_clientes];

        for (int i = 0; i < num_clientes; i++) {
            clientes[i] = new Thread(zonaDegustacion, ""+i);
            clientes[i].start();
        }

        for (int i = 0; i < num_clientes; i++) {
            try {
                clientes[i].join();
            } catch (InterruptedException e) {
            }
            
        }

        System.out.println("La fábrica quedó vacía… y los donuts sobrevivieron a la invasión de clientes 😎🍩");
    }   
}