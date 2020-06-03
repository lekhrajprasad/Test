package com.bny.employee;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Employee {
    private int id = 0;

    public Employee(int id) {
        this.setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

public class EmployeeMain{
    public static void main(String[] are){
        Set<Employee> empSet = new HashSet<>();
        empSet.add(new Employee(Integer.parseInt("10")));
        empSet.add(new Employee(Integer.parseInt("10")));
        empSet.add(new Employee(Integer.parseInt("10")));

        System.out.println(empSet.size());
    }
}
