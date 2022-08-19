package com.xingtao.xingtaomall.search.thread;

import java.util.concurrent.*;

/**
 * @Description
 * @Version
 * @BelongsPackage com.xingtao.xingtaomall.search
 * @BelongsProject xingtaomall
 * @Author WT
 * @Date 2022/6/2
 */
public class threadTest {
     public static ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            //重写run方法
            Integer i = 10 / 2;
            return i;
        }, executor);
        Integer integer = future.get();
        System.out.println(integer);
    }
}
