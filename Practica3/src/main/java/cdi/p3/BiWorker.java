package cdi.p3;

import java.awt.image.BufferedImage;

/**
 * Hilo que procesa la imagen usando BufferedImage directamente.
 * Cada hilo procesa filas intercaladas.
 */
public class BiWorker extends Thread{

    BufferedImage or_img;   // Imagen original
    BufferedImage out_img;  // Imagen resultado

    public BiWorker(String id, BufferedImage or_img, BufferedImage out_img){
        super(id);
        this.or_img = or_img;
        this.out_img = out_img;
    }

    public void run(){

        System.out.println("Hello world, I’m the java thread number"+ getName());

        int id = Integer.parseInt(getName());

        // Cada hilo procesa filas intercaladas:
        for(int row = id; row < or_img.getHeight(); row+=P4.num_threads){
            for (int col = 0; col < or_img.getWidth();col++){

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