package edu.t1.app;


import lombok.Getter;

import java.util.Deque;
import java.util.LinkedList;


public class CustomThreadPool {
    @Getter
    private final Deque<Runnable> taskQueue = new LinkedList<>();
    private final Thread[] threads;
    private volatile boolean isShutdown = false;

    public CustomThreadPool(int poolSize) {
        this.threads = new Thread[poolSize];

        for (int i = 0; i < poolSize; i++) {
            threads[i] = new TaskThread();
            threads[i].setName("TaskThread " + i);
            threads[i].start();
        }
    }

    public void execute(Runnable task) throws InterruptedException {
        if (isShutdown) {
            throw new IllegalStateException("Thread pool is shutdown");
        }

        synchronized (taskQueue) {
            taskQueue.push(task);
            taskQueue.notify();
        }
    }

    public void shutdown() throws InterruptedException {
        isShutdown = true;
        awaitTermination();
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public void awaitTermination() throws InterruptedException {
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                thread.join();
            }
        }
    }

    private class TaskThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task = null;
                synchronized (taskQueue) {
                    if (taskQueue.isEmpty()) {
                        try {
                            System.out.println("TaskQueue wating");
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    } else {
                        task = taskQueue.poll();
                    }
                }

                if (task != null) {
                    try {
                        task.run();
                        System.out.println("Current thread: " + Thread.currentThread().getName());
                    } catch (RuntimeException e) {
                        System.out.println("Execution error: " + e.getMessage());
                    }
                }
            }
        }
    }

}

