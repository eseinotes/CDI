package cdi.p3;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * Clase principal del programa.
 * Se encarga de:
 *  - Leer la imagen original
 *  - Lanzar los hilos con dos estrategias distintas
 *  - Esperar a que terminen
 *  - Guardar las imágenes resultantes
 */
public class P4 {

    // Número de hilos que se van a crear
    static int num_threads;

    public static void main(String[] args) {

        // Fijamos el número de hilos
        num_threads = 5;

        // Nombre de la imagen recibido por línea de comandos
        String img_name = args[1];

        // Abrimos la imagen original
        BufferedImage imgBiWorker = null;

        try {
            imgBiWorker = ImageIO.read(new File(img_name));
        } catch (Exception ex) {
            System.out.println("No se encuentra la imagen");
            System.exit(1);
        }

        // Creamos una imagen vacía con el mismo tamaño y tipo
        // Aquí los hilos escribirán el resultado
        BufferedImage resultImgBiWorker =
                new BufferedImage(imgBiWorker.getWidth(), imgBiWorker.getHeight(), imgBiWorker.getType());

        // ==============================
        // PRIMERA ESTRATEGIA: BiWorker
        // ==============================

        // Creamos el array de hilos
        BiWorker biWorkers[] = new BiWorker[num_threads];

        // Inicializamos y lanzamos los hilos
        for (int i = 0; i < num_threads; i++) {
            biWorkers[i] = new BiWorker("" + i, imgBiWorker, resultImgBiWorker);
            biWorkers[i].start(); // Arranca el hilo
        }

        // Esperamos a que todos los hilos terminen
        for (int i = 0; i < num_threads; i++) {
            try {
                biWorkers[i].join(); // Bloquea hasta que el hilo termina
            } catch (InterruptedException ex) {
            }
        }

        // Guardamos la imagen resultante
        try {
            ImageIO.write(resultImgBiWorker, "png", new File("resultBiWorker.png"));
        } catch (Exception ex) {
            System.exit(1);
        }

        // ======================================
        // SEGUNDA ESTRATEGIA: DistWorker + MyImage
        // ======================================

        // Elegimos la estrategia según argumento:
        // "0" → filas
        // "1" → columnas
        String strategy = switch (args[2]) {
            case "0" -> "filas";
            case "1" -> "columnas";
            default -> "filas";
        };

        // Creamos imagen original y resultado usando MyImage
        MyImage imgDistWorker = new MyImage(img_name, strategy);
        MyImage resultImgDistWorker = new MyImage(imgDistWorker.getWidth(), imgDistWorker.getHeight(), imgDistWorker.getType());

        // Creamos los hilos
        DistWorker distWorkers[] = new DistWorker[num_threads];

        for (int i = 0; i < num_threads; i++) {
            distWorkers[i] = new DistWorker("" + i, imgDistWorker, resultImgDistWorker);
            distWorkers[i].start();
        }

        // Esperamos a que terminen
        for (int i = 0; i < num_threads; i++) {
            try {
                distWorkers[i].join();
            } catch (InterruptedException ex) {
            }
        }

        // Guardamos el resultado final
        resultImgDistWorker.saveImg();

        System.out.println("Program of exercise X has terminated");
    }
}