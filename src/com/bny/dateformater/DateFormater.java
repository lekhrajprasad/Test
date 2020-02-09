package com.bny.dateformater;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.function.Consumer;

public class DateFormater {

    public static void main(String[] str) {

        String[] dateTimeStringArr = {"1st Jul 1980","10st Jul 1980"};
        DateTimeFormatter oldf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter newf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Consumer<String> dateFormater = e -> {
            e = e.replaceFirst("[a-zA-Z]{2}", "");
            if (e.length() == 10) {
                e = "0".concat(e);
            }
            System.out.println(LocalDate.parse(e, oldf).format(newf));
        };
        Arrays.stream(dateTimeStringArr).forEach(dateFormater);
    }

}
