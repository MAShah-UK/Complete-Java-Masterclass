package com.cjm.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        printMessage();
        sortEmployees();
    }
    public static void printMessage() {
        System.out.println("BEGIN: printMessage");

        class StartJoinThread {
            public void exec(Thread thread) {
                thread.start();
                try {
                    thread.join();
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        StartJoinThread thread = new StartJoinThread();

        // Create Runnable subclass instance.
        class CodeToRun implements Runnable {
            @Override
            public void run() {
                System.out.println("1. Output to console from CodeToRun instance.");
            }
        }
        thread.exec( new Thread(new CodeToRun()) );

        // Create anonymous Runnable subclass.
        thread.exec( new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("2. Output to console from anonymous class.");
                }
            }) );

        // Use lambda expression to create anonymous Runnable subclass.
        // Only works when the interface has one abstract method as the compiler knows
        // which method the lambda is referring to.
        // An interface with one declared method is a functional interface.
        thread.exec( new Thread(() -> System.out.println("3.1 Output to console from lambda.")) );

        thread.exec( new Thread(() -> {
            System.out.println("3.2 Second output to console from lambda.");
            System.out.println("3.2 time the lambda contains multiple statements.");
        }) );
    }
    public static void sortEmployees() {
        System.out.println("\nBEGIN: sortEmployees");

        class Employee {
            private String name;
            private int age;
            public Employee(String name, int age) {
                this.name = name;
                this.age = age;
            }
            public String getName() {
                return name;
            }
            public void setName(String name) {
                this.name = name;
            }
            public int getAge() {
                return age;
            }
            public void setAge(int age) {
                this.age = age;
            }
        }

        Employee john = new Employee("John Doe", 45);
        Employee jane = new Employee("Jane Doe", 43);
        Employee jack = new Employee("Jack Doe", 25);
        Employee jim = new Employee("Jim Doe", 23);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(jane);
        employees.add(jack);
        employees.add(jim);

        // Sort using anonymous Comparator subclass.
        Collections.sort(employees, new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getName().compareTo(employee2.getName());
            }
        });
        System.out.print("List of employees sorted using Collections.sort(): ");
        for(Employee employee: employees) {
            System.out.print(employee.getName() + ". ");
        }
    }
}