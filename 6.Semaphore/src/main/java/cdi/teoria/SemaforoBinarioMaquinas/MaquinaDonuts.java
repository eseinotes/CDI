package cdi.teoria.SemaforoBinarioMaquinas;

public class MaquinaDonuts {

    public void fabricarDonut(int id_maquina) throws InterruptedException{
        System.out.println("Trabajador "+Thread.currentThread().getName()+" mezcla ingredientes y sopla burbujas de azúcar en la máquina " +id_maquina+ "✨🍩");
        Thread.sleep(15);
    }

}
