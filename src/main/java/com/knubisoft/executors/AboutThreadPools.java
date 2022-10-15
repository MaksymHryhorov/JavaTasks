package com.knubisoft.executors;

import lombok.SneakyThrows;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class AboutThreadPools {
    @SneakyThrows
    public static void main(String[] args) {
        // ThreadPoolExecutor, ScheduledThreadPoolExecutor, ForkJoinPool

        // thread pool, queue to transfer submitted tasks, thread factory
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
                4,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>());

        System.out.println("Core pool size: " + threadPoolExecutor.getCorePoolSize()); // потоки которые нужно оставить в пуле
        System.out.println("Max pool size: " + threadPoolExecutor.getMaximumPoolSize());


        ThreadPoolExecutor thread = new ThreadPoolExecutor(1,
                10,
                5L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(1));

        for (int i = 0; i < 10; i++) {
            thread.submit(() -> {

            });
        }

        for (int i = 0; i < 10; i++) {
            sleep(1);
            logTasksCount(threadPoolExecutor);
            if (threadPoolExecutor.isTerminated()) {
                logTasksCount(threadPoolExecutor);
                break;
            }
        }

        threadPoolExecutor.shutdown();
    }

    private static void logTasksCount(ThreadPoolExecutor threadPoolExecutor) {
        System.out.println("tasks count all: " + threadPoolExecutor.getTaskCount() +
                " completed " + threadPoolExecutor.getCompletedTaskCount());

    }
}
