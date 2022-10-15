package com.knubisoft.executors;

import java.util.concurrent.Executor;

public class AboutExecutors {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Start");

        // предназначен для отделения интерфейса отправленных задач от реализации выполнения задач
        // return type is void. No information about the task completion and no ability to cancel task
        // synchronous executor
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };

        executor.execute(runnable);


        // Async executor that executes each task in a new Thread
        Executor asyncExecutor = new Executor() {
            @Override
            public void execute(Runnable command) {
                new Thread(command).start();
            }
        };

        executor.execute(runnable);
    }
}
