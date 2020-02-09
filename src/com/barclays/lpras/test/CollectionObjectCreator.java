package com.barclays.lpras.test;

import java.util.*;

public class CollectionObjectCreator {
    public void createCollectionObject() {
        List<Customer> custs = new ArrayList<>();
        Set<Customer> custSet= new HashSet<>();
        Map<Customer,Customer> custMap = new HashMap<>();
        List<Customer> linkedCusts = new LinkedList<>();

        int n=1000;
        for(int i=1;i<=n;i++){
            Customer cust = new Customer(i, "a@bny"+1+".com", "lekh"+i,new Date());
            custs.add(cust);

            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ==> " +cust);
        }
        for(int i=1;i<=n;i++){
            Customer cust = new Customer(i, "a@bny"+1+".com", "lekh"+i,new Date());
            custSet.add(cust);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ==> " +cust);
        }
        for(int i=1;i<=n;i++){
            Customer cust = new Customer(i, "a@bny"+1+".com", "lekh"+i,new Date());
            custMap.put(cust, cust);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ==> " +cust);
        }
        for(int i=1;i<=n;i++){
            Customer cust = new Customer(i, "a@bny"+1+".com", "lekh"+i,new Date());
            linkedCusts.add(cust);
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " ==> " +cust);
        }
    }
}
