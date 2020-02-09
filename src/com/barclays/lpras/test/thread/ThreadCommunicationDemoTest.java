package com.barclays.lpras.test.thread;

import java.sql.Time;
import java.time.Instant;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class ThreadCommunicationDemoTest {

    public static void main(String[] str){
        ThreadCommunicationDemoTest threadCommDemoTest = new ThreadCommunicationDemoTest();
        threadCommDemoTest.demo1();


    }

    private void demo1(){

        Runnable runnable = () -> { printNumber(); };

        Thread thA = new Thread(runnable,"A");
        Thread thB = new Thread(runnable,"B");
        thA.start();

        thB.start();
    }

    public synchronized void printNumber(){
        IntStream.range(0, 10).forEach(intConsumer);
    }

    IntConsumer intConsumer = e -> {
        System.out.println(e + " ==> " + Thread.currentThread().getName());
        try {
            Thread.sleep((long) (3000 * Math.random()));
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    };

}
