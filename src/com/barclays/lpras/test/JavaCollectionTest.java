package com.barclays.lpras.test;

import java.util.*;
import java.util.concurrent.*;

public class JavaCollectionTest {
    public static void main(String[] str) throws InterruptedException {
    int n=100000;
        Callable<String> task1 = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                for(int i=1; i<n;i++){
                    CollectionObjectCreator collectionObjectCreator = new CollectionObjectCreator();
                    collectionObjectCreator.createCollectionObject();
                }
                return "1";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
        Callable<String> task2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                for(int i=1; i<n;i++){
                    CollectionObjectCreator collectionObjectCreator = new CollectionObjectCreator();
                    collectionObjectCreator.createCollectionObject();
                }
                return "2";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        Callable<String> task3 = () -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                for(int i=1; i<n;i++){
                    CollectionObjectCreator collectionObjectCreator = new CollectionObjectCreator();
                    collectionObjectCreator.createCollectionObject();
                }
                return "3";
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        //ExecutorService executor = Executors.newFixedThreadPool(1);
        //Future<Integer> future = executor.submit(task);

        //Integer result = future.get();

        //System.out.println("future done? " + future.isDone());



        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(task1,task2, task3);

        executor.invokeAll(callables)
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch (Exception e) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEach(System.out::println);

        //System.out.print("result: " + result);
        executor.shutdownNow();
    }




}
