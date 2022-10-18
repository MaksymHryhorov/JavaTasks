package com.knubisoft.executors;

import java.util.concurrent.Semaphore;

public class AboutSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        new SemaphoreCreator(semaphore, new MyQueue("P1")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P2")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P3")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P4")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P5")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P6")).start();
        new SemaphoreCreator(semaphore, new MyQueue("P7")).start();
    }
}


