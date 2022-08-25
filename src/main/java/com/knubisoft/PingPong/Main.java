package com.knubisoft.PingPong;

public class Main {
    public static void main(String[] args) {
        Runnable ping = new PingPong("ping");
        Thread t1 = new Thread(ping);
        t1.start();

        Runnable pong = new PingPong("pong");
        Thread t2 = new Thread(pong);
        t2.start();
    }
}
