package com.tangzhe.java8.cxytd;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by 唐哲
 * 2018-03-29 13:28
 * CompletableFuture
 */
public class CompletableFutureTest {

    public static void test01() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<String> call = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "result";
            }
        };

        FutureTask<String> task = new FutureTask<>(call);
        executor.submit(task);
        executor.shutdown();

        System.out.println(task.get());
    }

    public static void test02() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        Callable<String> call = () -> {
            Thread.sleep(1000);
            return "result";
        };

        FutureTask<String> task = new FutureTask<>(call);
        executor.submit(task);
        executor.shutdown();

        System.out.println(task.get());
    }

    public static void test03() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "future1";
        }, executor);

        executor.shutdown();

        System.out.println(f1.get()); //阻塞的
        System.out.println("上面是阻塞的");
    }

    public static void test04() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "future1";
        }, executor);

        executor.shutdown();

        //非阻塞的
        f1.thenAccept(r -> {
            System.out.println(r);
        });
        System.out.println("上面是非阻塞的");

        f1.thenRun(() -> {
            System.out.println("任务完成之后需要通知某某");
        });
    }

    /**
     * 加工
     */
    public static void test05() throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(5);

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "future1";
        }, executor);

        executor.shutdown();

        CompletableFuture<String> f2 = f1.thenApply(new Function<String, String>() {
            @Override
            public String apply(String future1Result) {
                return future1Result + " future2第二次加工结果";
            }
        });

        System.out.println(f2.get());
    }

    /**
     * 并行执行结果合并
     */
    public static void test06() throws Exception {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync( () -> {
            return "猿天地";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync( () -> {
            return "你好";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, new BiFunction<String, String, String>() {
            @Override
            public String apply(String f1Result, String f2Result) {
                return f1Result + f2Result;
            }
        });
        System.out.println(f3.get());
    }

    public static void main(String[] args) throws Exception {
        test08();
    }

    /**
     *  4023
        111.1
        111.1
        111.1
        111.1
     */
    public static void test07() throws Exception {
        Instant start = Instant.now();

        List<String> shops = Arrays.asList("iphone", "ipad", "mac", "air");
        List<Double> prices = new ArrayList<>();

        for (String shop : shops) {
			prices.add(getPrice(shop));
		}

		Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

		prices.forEach(System.out::println);
    }

    /**
     *  1063
        111.1
        111.1
        111.1
        111.1
     */
    public static void test08() throws Exception {
        Instant start = Instant.now();

        List<String> shops = Arrays.asList("iphone", "ipad", "mac", "air");
        List<Double> prices;

        List<CompletableFuture<Double>> tasks = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> {
            return getPrice(shop);
        })).collect(Collectors.toList());

        prices = tasks.stream().map(CompletableFuture::join).collect(Collectors.toList());

        Instant end = Instant.now();
        System.out.println(Duration.between(start, end).toMillis());

        prices.forEach(System.out::println);
    }

    //模拟数据库交互
    private static double getPrice(String shop) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 111.1;
    }

}
