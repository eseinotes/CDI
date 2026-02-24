package cdi.p3;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Clase que gestiona las imágenes.
 * Añade:
 *  - Estrategia de división del trabajo (filas o columnas)
 *  - Métodos para calcular qué parte procesa cada hilo
 */
public class MyImage {

    BufferedImage img;   // Imagen real
    String strategy;     // Estrategia de división

    // Constructor que carga una imagen desde fichero
    public MyImage(String img_name, String strategy){
        this.strategy = strategy;
        try{
            img = ImageIO.read(new File(img_name));
        }catch(Exception ex){
            System.exit(1);
        }
    }

    // Constructor que crea una imagen vacía
    public MyImage(int width, int height, int type){
        this.img = new BufferedImage(width, height, type);
    }

    // Devuelve la primera fila que debe procesar el hilo "id"
    public int getFirstRow(int id){
        int toret = 0;
        if (strategy.equals("filas")){
            int numRowsPerThread = img.getHeight() / P4.num_threads;
            toret = numRowsPerThread * id;
        }
        return toret;
    }

    // Devuelve la última fila que debe procesar el hilo "id"
    public int getLastRow(int id){
        int toret = img.getHeight() -1;

        /*
        * El alto de la imagen no tiene por qué ser múltiplo del número de hilos.
        * Si no controlamos esto, podrían quedar filas sin procesar.
        * Por eso el último hilo no entra en este cálculo y procesa la imagen hasta el final.
        */
        if (strategy.equals("filas") && id != P4.num_threads-1){
            toret = getFirstRow(id+1) -1;
        }
        return toret;
    }

    // Devuelve la primera columna asignada al hilo
    public int getFirstColumn(int id){
        int toret = 0;
        if (strategy.equals("columnas")){
            int numColumnsPerThread = img.getWidth() / P4.num_threads;
            toret = numColumnsPerThread * id;
        }
        return toret;
    }

    // Devuelve la última columna asignada al hilo
    public int getLastColumn(int id){
        int toret = img.getWidth() -1;

        /*
        * El ancho de la imagen no tiene por qué ser múltiplo del número de hilos.
        * Si no controlamos esto, podrían quedar columnas sin procesar.
        * Por eso el último hilo no entra en este cálculo y procesa la imagen hasta el final.
        */

        if (strategy.equals("columnas") && id != P4.num_threads-1){
            toret = getFirstColumn(id+1) -1;
        }

        return toret;
    }

    // Devuelve el valor RGB de un píxel
    public int getRGB(int col, int row){
        return img.getRGB(col, row);
    }

    // Modifica el valor RGB de un píxel
    public void setRGB(int col, int row, int newColor){
        img.setRGB(col, row, newColor);
    }

    // Guarda la imagen en disco
    public void saveImg(){
        try{
            ImageIO.write(img, "png", new File("resultDistWorker.png"));
        }catch(Exception ex){
            System.exit(1);
        }
    }

    public int getWidth(){
        return img.getWidth();
    }

    public int getHeight(){
        return img.getHeight();
    }

    public int getType(){
        return img.getType();
    }
}