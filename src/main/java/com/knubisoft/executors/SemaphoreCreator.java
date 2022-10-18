package com.knubisoft.executors;

import lombok.SneakyThrows;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class SemaphoreCreator extends Thread {
    private final Semaphore semaphore;
    private final MyQueue myQueue;

    SemaphoreCreator(Semaphore semaphore, MyQueue myQueue) {
        this.semaphore = semaphore;
        this.myQueue = myQueue;
    }

    public void run() {
        try {
            semaphore.acquire();
            System.out.println(myQueue.getName() + " is moving to the class");
            passExam(myQueue);

            System.out.println(myQueue.getName() + " is going to the exit" + "\r\n");
            semaphore.release();

            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    private static void passExam(MyQueue queue) {
        sleep(5000);

        Random random = new Random();
        int randomValue = random.nextInt(5);

        switch (randomValue) {
            case 0, 1, 2 -> System.out.println(queue.getName() + " flunked the exam");
            case 3, 4, 5 -> System.out.println(queue.getName() + " Passed the exam");
        }

    }
}
