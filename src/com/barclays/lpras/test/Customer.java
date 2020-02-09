package com.barclays.lpras.test;

import java.util.Date;

public class Customer {
    int regNum;
    String email;
    String name;
    Date date;

    public Customer(int regNum, String email, String name, Date date) {
        this.regNum = regNum;
        this.email = email;
        this.name = name;
        this.date = date;
    }

    public int getRegNum() {
        return regNum;
    }

    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }

    public Customer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "regNum=" + regNum +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", date=" + date +
                '}';
    }
}
