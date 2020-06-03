package com.thread;

public class Singleton {
    private static volatile Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        System.out.println("Singleton - "+Thread.currentThread().getName());
        return ourInstance;
    }

    private Singleton() {
        System.out.println("Singleton");
    }
}
