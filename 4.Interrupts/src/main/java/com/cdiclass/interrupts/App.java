package com.cdiclass.interrupts;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        int numUsers = 30;
        List<UserWorker> workers = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        boolean interruption = true;

        System.out.println("Arrancando servidor de vídeos con " + numUsers + " usuarios...");

        for (int i = 1; i <= numUsers; i++) {
            UserWorker w = new UserWorker(i, "user_" + i);
            Thread t = new Thread(w, "UserThread-" + i);
            workers.add(w);
            threads.add(t);
            t.start();
        }       
        
        if (interruption){
            try {
                // Dejamos el “servidor” funcionando un poco
                Thread.sleep(5000);
            } catch (InterruptedException ex) {

            }
            System.out.println("\n🚧 UPDATE GLOBAL: el servidor se va a reiniciar (interrumpimos a todos)\n");

            // interrupt(): enviamos la señal de cancelación a todos
            for (Thread t : threads) {
                t.interrupt();

            }
        }

        // join(): esperamos a que todos terminen (apagado ordenado)
        for (Thread t : threads) {
            try {
                // ¿qué pasa aquí?
                //System.out.println(t.isInterrupted());
                t.join();
            } catch (InterruptedException ex) {
                
            }
        }

        System.out.println("Servidor sin usuarios. Estado final:");
        for (UserWorker w : workers) {
            
            if ( w.getVideoState() == 0)
                System.out.println("El usuario: " + w.getUsername() + " no subió ningún vídeo");
            else if ( w.getVideoState() == 1 )
                System.out.println("El usuario: " + w.getUsername() + " subió un vídeo correctamente");
            else if ( w.getVideoState() == 3 )
                System.out.println("El usuario: " + w.getUsername() + " el vídeo se interrumpió durante la subida");
            
            
            
        }
        

    }
}
