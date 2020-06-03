package com.thread;

public class MainThread {
    static String str1;

    public static void main(String[] str){
    final int fin = 9;
    String str2 = null;
    System.out.println(fin+9);
    boolean True = false;
    boolean b = True;
    System.out.println(b);
    char ch = 65535;
    System.out.println(ch);
    byte byt = 127;
    char c = 32767;
    System.out.println(c);
    double dd = 123.456;
    System.out.println(str2);
    int a=10;

    System.out.println(22>>1);
    System.out.println(Hello.i);

        /*Runnable runnable1 = () -> {
            System.out.println(Thread.currentThread().getName());
            Singleton.getInstance();
        };
        Runnable runnable2 = () -> {
            System.out.println(Thread.currentThread().getName());
            Singleton.getInstance();
        };
        Thread t2 = new Thread(runnable1,"Thread-B");
        t2.start();
        Thread t1 = new Thread(runnable2,"Thread-A");
        t1.start();*/
    }


}
