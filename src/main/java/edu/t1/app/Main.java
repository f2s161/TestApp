package edu.t1.app;



public class Main {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPool pool = new CustomThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final int finalThreadNum = i;
            pool.execute(() -> System.out.println("Executing task " + finalThreadNum));
        }

        for (int i = 0; i < 100; i++) {
            final int finalThreadNum = i;
            pool.execute(() -> System.out.println("Executing task2 " + finalThreadNum));
        }

        pool.awaitTermination();

        pool.shutdown();
        pool.execute(() -> System.out.println("Executing task test shutdown"));

    }
}
