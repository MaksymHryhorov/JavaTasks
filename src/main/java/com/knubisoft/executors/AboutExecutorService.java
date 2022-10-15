package com.knubisoft.executors;

import lombok.SneakyThrows;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AboutExecutorService {
    @SneakyThrows
    public static void main(String[] args) {
        // ExecutorService extends Executor
        // additionally has methods to execute tasks with the ability to control their completion,
        // and methods to manage executor termination.

        // return value Future that has methods to control task completion
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Runnable runnable = () -> {
            for (int x = 0; x < 10; x++) {
                System.out.println(x);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Future<?> future = executorService.submit(runnable);
        System.out.println("Result: " + future.get());

        executorService.shutdown();


        // with Callable parameter
        ExecutorService service = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
            String str = "My new String";

            for (int i = 0; i < 10; i++) {
                str = str + i;
                System.out.println(str);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return str;
        };

        Future<String> future1 = service.submit(callable);
        System.out.println("Result: " + future1.get());
        service.shutdown();

        // invokeAll method

        ExecutorService myExecutorService = Executors.newCachedThreadPool();
        List<Callable<String>> callables = Arrays.asList(
                () -> sleepAndGet(1, "Bravo"),
                () -> sleepAndGet(2, "Alpha"),
                () -> sleepAndGet(3, "Charlie")
        );


        List<String> results = myExecutorService.invokeAll(callables)
                .stream()
                .peek(fut -> System.out.println("is done: " + fut.isDone() + " is cancelled: " + fut.isCancelled()))
                .map(fut -> {
                    try {
                        return fut.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).toList();


        String res = myExecutorService.invokeAny(callables);
        System.out.println(res);

        myExecutorService.shutdown();

    }

    private static String sleepAndGet(int i, String bravo) {

        return bravo;
    }
}
