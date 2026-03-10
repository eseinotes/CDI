package com.cdiclass.waitandnotify;

public class PlayerLeft extends Thread {
    private final Ball ball;
    private final String name;

    public PlayerLeft(String name, Ball ball) {
        this.name = name;
        this.ball = ball;
    }

    @Override
    public void run() {
        while (!ball.isMatchOver()) {
            ball.hitFromLeft(name);
        }
    }
}
