package com.barclays.lpras.test.thread;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ThreadCommunicationDemo {

    public void printNumber(){
        IntStream.range(0, 10).forEach(System.out::println);
    }

}
