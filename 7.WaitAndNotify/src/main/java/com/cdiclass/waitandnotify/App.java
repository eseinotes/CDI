package com.cdiclass.waitandnotify;

import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] args) {
        Ball ball = new Ball(5); 

        Thread left1 = new PlayerLeft("Izquierda 1", ball);
        Thread left2 = new PlayerLeft("Izquierda 2", ball);
        Thread right1 = new PlayerRight("Derecha 1", ball);
        Thread right2 = new PlayerRight("Derecha 2", ball);

        left1.start();
        left2.start();
        right1.start();
        right2.start();

        try {
            left1.join();
            left2.join();
            right1.join();
            right2.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        System.out.println("\nPartido terminado.");
        System.out.println("Puntuación final -> Izquierda: " + ball.getLeftScore() + " | Derecha: " + ball.getRightScore());
    }
    
}
