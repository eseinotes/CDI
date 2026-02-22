package cdi.p3;

/**
 * Hilo que procesa una región contigua de la imagen
 * (por filas o por columnas, según la estrategia).
 */
public class DistWorker extends Thread {

    MyImage or_img;   // Imagen original
    MyImage out_img;  // Imagen donde se escribe el resultado

    public DistWorker(String id, MyImage or_img, MyImage out_img){
        super(id);
        this.or_img = or_img;
        this.out_img = out_img;
    }

    public void run(){

        System.out.println("Hello world, I’m the java thread number"+ getName());

        // Convertimos el nombre del hilo en entero
        int id = Integer.parseInt(getName());

        // Obtenemos los límites de la región que le toca procesar
        int firstRow = or_img.getFirstRow(id);
        int lastRow = or_img.getLastRow(id);
        int firstColumn = or_img.getFirstColumn(id);
        int lastColumn = or_img.getLastColumn(id);

        // Recorremos la región asignada
        for(int row = firstRow; row <= lastRow; row++){
            for (int col = firstColumn; col <= lastColumn;col++){

                // Leemos el píxel original
                int rgbOriginal = or_img.getRGB(col, row);

                // Extraemos los componentes ARGB
                int alpha = (rgbOriginal >> 24) & 0xFF;
                int red   = (rgbOriginal >> 16) & 0xFF;
                int green = (rgbOriginal >> 8) & 0xFF;
                int blue  = rgbOriginal & 0xFF;

                // Calculamos la media (escala de grises)
                int average = (red + green + blue) / 3;

                // Construimos el nuevo color
                int newColor = (alpha << 24)
                             | (average << 16)
                             | (average << 8)
                             | average;

                // Escribimos el nuevo píxel
                out_img.setRGB(col, row, newColor);
            }
        }

        System.out.println("Bye from thread number "+ getName());
    }
}