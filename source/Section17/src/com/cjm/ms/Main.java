package com.cjm.ms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        printMessage();
        sortEmployees();
        concatStrings();
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
                System.out.println("Output to console from CodeToRun instance.\n");
            }
        }
        thread.exec( new Thread(new CodeToRun()) );

        // Create anonymous Runnable subclass.
        thread.exec( new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Output to console from anonymous class.\n");
                }
            }) );

        // Use lambda expression to create anonymous Runnable subclass.
        // Only works when the interface has one abstract method as the compiler knows
        // which method the lambda is referring to.
        // An interface with one declared method is a functional interface.
        thread.exec( new Thread(() -> System.out.println("Output to console from lambda.\n")) );

        thread.exec( new Thread(() -> {
            System.out.println("Second output to console from lambda.");
            System.out.println("time the lambda contains multiple statements.\n");
        }) );

        // Use lambdas with one argument.
        ConsoleOutput output = (msg) -> System.out.println(msg);
        output.print("This message is from a lambda expression that requires one argument.");
        output = msg -> System.out.println(msg); // Parenthesis not required for one argument.
        output.print("This is another message from a lambda expression that requires one argument.");
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
        System.out.print("List of employees sorted using anonymous class: ");
        for(Employee employee: employees) {
            System.out.print(employee.getName() + ". ");
        }
        System.out.println();
        // Sort using lambda generated anonymous Comparator subclass.
        Collections.sort(employees, (Employee employee1, Employee employee2) ->
                employee1.getName().compareTo(employee2.getName()));
        // Employee type can be inferred from employees parameter.
        Collections.sort(employees, (employee1, employee2) ->
                employee1.getName().compareTo(employee2.getName()));
        System.out.print("List of employees sorted using lambdas: ");
        for(Employee employee: employees) {
            System.out.print(employee.getName() + ". ");
        }
        System.out.println();
    }
    public static void concatStrings() {
        System.out.println("\nBEGIN: concatStrings");

        // Return data using anonymous class.
        UpperConcat upperConcat = new UpperConcat() {
            @Override
            public String exec(String s1, String s2) {
                return s1.toUpperCase() + s2.toUpperCase();
            }
        };
        String concatString_anonymousClass = upperConcat.exec("Hello", "World");
        System.out.println("'Hello' and 'World' UpperConcat using anonymous class: " +
                concatString_anonymousClass);
        // Return data using lambda expression.
        upperConcat = (s1, s2) -> s1.toUpperCase() + s2.toUpperCase(); // Types inferred.
        String concatString_lambda = upperConcat.exec("Hello", "World");
        System.out.println("'Hello' and 'World' UpperConcat using lambda: " +
                concatString_lambda);
    }
}

interface ConsoleOutput {
    void print(String message);
}

interface UpperConcat {
    String exec(String s1, String s2);
}