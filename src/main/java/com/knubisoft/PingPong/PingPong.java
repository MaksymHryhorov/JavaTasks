package com.knubisoft.PingPong;

public class PingPong implements Runnable {
    String word;

    PingPong(String s) {
        word = s;
    }

    public void run() {
        try {
            for (int i = 0; i < 30; i++) {
                System.out.println(word);
                Thread.sleep(1000);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
