package com.cdiclass.waitandnotify;

import java.util.Random;

public class Ball {
    private String direction; // "left" or "right"
    private String turn;      // which side must hit now: "left" or "right"
    private boolean matchOver;

    private int leftScore;
    private int rightScore;
    private final int pointsToWin;

    private final Random random = new Random();

    public Ball(int pointsToWin) {
        this.pointsToWin = pointsToWin;

        // The match starts with the left side serving
        this.turn = "left";
        this.direction = "right";
        this.matchOver = false;

        this.leftScore = 0;
        this.rightScore = 0;

        System.out.println("Comienza el partido.");
        System.out.println("Saca el lado izquierdo.");
    }

    public synchronized int getLeftScore() {
        return leftScore;
    }

    public synchronized int getRightScore() {
        return rightScore;
    }

    public void hitFromLeft(String playerName) {
        try {
            synchronized (this) {
                while (!matchOver && !turn.equals("left")) {
                    wait();
                }

                if (matchOver) {
                    return;
                }

                System.out.println("------------------>"+ playerName + " golpea la pelota hacía del lado izquierdo hacía el derecho.");
                Thread.sleep(3000);

                boolean miss = random.nextInt(7) == 0; // 1 in 7 chance

                if (miss) {
                    rightScore++;
                    System.out.println(playerName + " falla la bola.");
                    System.out.println("Punto para el lado derecho.");
                    printScore();

                    checkMatchOver();
                    if (!matchOver) {
                        turn = "left";
                        direction = "right";
                        System.out.println("Saca el lado izquierdo.");
                    }

                    notifyAll();
                    return;
                }

                direction = "right";
                turn = "right";
                System.out.println("La bola esta ahora del lado derecho.");

                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void hitFromRight(String playerName) {
        try {
            synchronized (this) {
                while (!matchOver && !turn.equals("right")) {
                    wait();
                }

                if (matchOver) {
                    return;
                }

                System.out.println("<------------------" +playerName + " Golpea la bola del lado derecho al izquierdo.");
                Thread.sleep(3000);

                boolean miss = random.nextInt(7) == 0; // 1 in 7 chance

                if (miss) {
                    leftScore++;
                    System.out.println(playerName + " falla la bola.");
                    System.out.println("Punto para el lado derecho.");
                    printScore();

                    checkMatchOver();
                    if (!matchOver) {
                        turn = "left";
                        direction = "right";
                        System.out.println("Izquierda saca.");
                    }

                    notifyAll();
                    return;
                }

                direction = "left";
                turn = "left";
                System.out.println("La bola está ahora del lado izquierdo.");

                notifyAll();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void printScore() {
        System.out.println("Puntuacion -> Izquierda: " + leftScore + " | Derecha: " + rightScore);
    }

    private void checkMatchOver() {
        if (leftScore >= pointsToWin || rightScore >= pointsToWin) {
            matchOver = true;
            System.out.println("Se acaba el partido!.");
        }
    }

    public synchronized boolean isMatchOver() {
        return matchOver;
    }
}