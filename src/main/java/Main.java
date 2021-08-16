import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.LongAdder;

import static java.util.Arrays.stream;


public class Main {

    public static void main(String[] args) throws InterruptedException {

        long[] arrShop1 = new Random().longs(100, 1, 100000).toArray();
        long[] arrShop2 = new Random().longs(100, 1, 100000).toArray();
        long[] arrShop3 = new Random().longs(100, 1, 100000).toArray();

        LongAdder report = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        stream(arrShop1).forEach(i -> executorService.submit(() -> report.add(i)));
        stream(arrShop2).forEach(i -> executorService.submit(() -> report.add(i)));
        stream(arrShop3).forEach(i -> executorService.submit(() -> report.add(i)));

        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("\nРезультат: " + report.sum());
        executorService.shutdown();
    }

}