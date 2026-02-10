package com.cdiclass.interrupts;


public class UserWorker implements Runnable {
    private final int id;
    private final String username;

    // ¿por qué volatile?
    private volatile int videoState = 0;

    public UserWorker(int id, String username) {
        this.id = id;
        this.username = username;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public int getVideoState() { return videoState; }

    @Override
    public void run() {
        
        // Espera activa (simulamos que el usuario está “navegando”/viendo vídeos durante 7 segundos)
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 7000) {                
            
            // 1 de cada 10 usuarios intentará subir un vídeo ocasionalmente.
            if (id % 10 == 0 && videoState == 0) {
                // ¿por qué no sale del while aunque supere por mucho el tiempo?
                uploadVideo();
            }
            
            // isInterrupted() permitirá salir del bucle run()
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            
        }
        
    }

    private void uploadVideo() {
        
        if (Thread.currentThread().isInterrupted()) return;
        
        videoState = 2; // subiendo
        
        try {
            Thread.sleep(10000);
            // Si nadie nos interrumpió, subido
            videoState = 1;
        } catch (InterruptedException e) {
            // Si nos interrumpen durante sleep, saltamos aquí
            // -> estado error
            videoState = 3;
            // ¿qué estamos haciendo aquí?
            //Thread.currentThread().interrupt();
        }
    }

}
    

