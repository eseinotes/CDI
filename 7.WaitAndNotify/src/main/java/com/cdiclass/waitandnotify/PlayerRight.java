package com.cdiclass.waitandnotify;

public class PlayerRight extends Thread{
    private final Ball ball;
    private final String name;

    public PlayerRight(String name, Ball ball) {
        this.name = name;
        this.ball = ball;
    }

    @Override
    public void run() {
        while (!ball.isMatchOver()) {
            ball.hitFromRight(name);
        }
    }
    
}
