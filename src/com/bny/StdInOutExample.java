package com.bny;

import java.io.Console;

public class StdInOutExample {
    public static void main(String[] str){
        Console console = System.console();
        String in = console.readLine();
        console.writer().println(in);
    }
}
