package com.bny.DoubleOnMatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DoubleOnMatch {
    public static void main(String[] are) {
        List<Long> arr = new ArrayList<>();
        arr.add(1l);
        arr.add(2l);
        arr.add(4l);
        arr.add(11l);
        arr.add(12l);
        arr.add(8l);

        Long max = doubleSize(arr, 11l);
        System.out.println(max);

    }

    public static long doubleSize(List<Long> arr, long num) {
        long max = num;
        if(arr.contains(num)){
            max = max << 1;
        }else {
            return 0;
        }
        Predicate<Long> evenAndGrtMultof2 = e->(e%2==0 && e>=num*2);
        List<Long> evnList = arr.stream().filter(evenAndGrtMultof2).collect(Collectors.toList());
        while (evnList.contains(max)) {
            max = max << 1;
        }
        return max;
    }
}
