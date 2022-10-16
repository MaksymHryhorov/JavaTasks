package com.knubisoft.executors;

import java.util.concurrent.Semaphore;

public class AboutSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        new SemaphoreCreator(semaphore, new MyQueue("Person1")).start();
        new SemaphoreCreator(semaphore, new MyQueue("Person2")).start();
        new SemaphoreCreator(semaphore, new MyQueue("Person3")).start();
        new SemaphoreCreator(semaphore, new MyQueue("Person4")).start();
        new SemaphoreCreator(semaphore, new MyQueue("Person5")).start();
    }
}


