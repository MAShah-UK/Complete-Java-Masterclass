package com.cjm.ms;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    private final List<Employee> employees;

    // Point of entry.
    public static void main(String[] args) {
        Main main = new Main();

        main.printMessage();
        main.sortEmployees();
        main.concatStrings();
        main.printNameAndNumbers();
        main.filterEmployees();
        main.printRandomNumbers();
        main.printNames();
        main.addIntegers();
        main.streamEmployees();

        main.challenge1();
        main.challenge2to5();
    }
    // Initialise fields.
    public Main() {
        employees = Arrays.asList(
            new Employee("John Doe", 45),
            new Employee("Jane Doe", 43),
            new Employee("Jack Doe", 25),
            new Employee("Jim Doe", 23)
        );
    }
    // Practice working with basic lambda expressions.
    public void printMessage() {
        System.out.println("BEGIN: printMessage");

        // Create Runnable subclass instance.
        class CodeToRun implements Runnable {
            @Override
            public void run() {
                System.out.println("Output to console from CodeToRun instance.\n");
            }
        }
        StartJoinThread.exec(new CodeToRun());

        // Create anonymous Runnable subclass.
        StartJoinThread.exec(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Output to console from anonymous class.\n");
                }
            });

        // Use lambda expression to create anonymous Runnable subclass.
        // Only works when the interface has one abstract method as the compiler knows
        // which method the lambda is referring to.
        // An interface with one declared method is a functional interface.
        StartJoinThread.exec(() -> System.out.println("Output to console from lambda.\n"));

        StartJoinThread.exec(() -> {
            System.out.println("Second output to console from lambda.");
            System.out.println("time the lambda contains multiple statements.\n");
        });

        // Use lambdas with one argument.
        ConsoleOutput output = (msg) -> System.out.println(msg);
        output.print("This message is from a lambda expression that requires one argument.");
        output = msg -> System.out.println(msg); // Parenthesis not required for one argument.
        output.print("This is another message from a lambda expression that requires one argument.");
    }
    // Practice using lambdas with argument lists.
    public void sortEmployees() {
        System.out.println("\nBEGIN: sortEmployees");

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
        // Sort using anonymous Comparator subclass - generated via lambda expression.
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
    // Practice returning data from lambdas.
    public void concatStrings() {
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
        // Equivalent: upperConcat = (s1, s2) -> { return s1.toUpperCase()  + s2.toUpperCase(); };
        String concatString_lambda = upperConcat.exec("Hello", "World");
        System.out.println("'Hello' and 'World' UpperConcat using lambda: " +
                concatString_lambda);
    }
    // Practice capturing local variables with lambdas.
    public void printNameAndNumbers() {
        System.out.println("\nBEGIN: printNameAndNumbers");

        ConsoleOutput output = (message) -> System.out.println(employees.get(0).getName());
        System.out.print("Employee name: ");
        output.print("");

        // employeeName = "Jane Doe";
        // Compiler error because captures local variables must be marked final,
        // or be effectively final.
        // The same rule applies to anonymous classes.

        // Valid because employeeName is effectively final as the reference is never changed.
        System.out.print("Employee names: ");
        for(Employee employee: employees) {
            output = (message) -> System.out.print(employee.getName() + ". ");
            output.print("");
        }
        System.out.println();

        // Alternate solution: uses Consumer interface to take one argument and execute code with it.
        System.out.print("Employee names: ");
        employees.forEach((employee) -> System.out.print(employee + ". "));
        System.out.println();
    }
    // Practice working with the Predicate interface.
    public void filterEmployees() {
        System.out.println("\nBEGIN: filterEmployees");

        class AgeFilter {
            // Predicate is used to execute code if certain conditions are met.
            // Consumer is used to execute code that doesn't require returning data.
            public <T> void exec(List<T> list, Predicate<T> filter, Consumer<T> action) {
                list.forEach(data -> {
                    if(filter.test(data)) {
                        action.accept(data);
                    }
                });
            }
        }
        AgeFilter ageFilter = new AgeFilter();

        Employee john = new Employee("John Doe", 45);
        Employee jane = new Employee("Jane Doe", 43);
        Employee jack = new Employee("Jack Doe", 25);
        Employee jim = new Employee("Jim Doe", 23);

        List<Employee> employees = new ArrayList<>();
        employees.add(john);
        employees.add(jane);
        employees.add(jack);
        employees.add(jim);

        // Filter employees based on age.
        // Anonymous class version:
        System.out.print("The following employees are over the age of 24 (using anon classes): ");
        ageFilter.exec(employees, new Predicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge()>24;
            }
        }, new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.print(employee.getName() + " (" + employee.getAge() + ")" + ". ");;
            }
        });
        System.out.println();
        // Lambda version:
        System.out.print("The following employees are over the age of 24 (using lambdas): ");
        Predicate<Employee> ageOver24 = employee -> employee.getAge()>24;
        Consumer<Employee> printNameAge = employee ->
                System.out.print(employee.getName() + " (" + employee.getAge() + ")" + ". ");
        ageFilter.exec(employees, // Type inference used to determine T as type Employee.
                       ageOver24, // Condition.
                       printNameAge); // Runs if condition is met.
        System.out.println();
        // Chain predicates to increase filtering capability.
        System.out.print("The following employees are between 24 and 44 years of age: ");
        Predicate<Employee> ageUnder44 = employee -> employee.getAge()<44;
        Predicate<Employee> chainedPredicate = ageOver24.and(ageUnder44);
        ageFilter.exec(employees, chainedPredicate, printNameAge);
        System.out.println();
    }
    // Practice working with the Supplier interface.
    public void printRandomNumbers() {
        System.out.println("\nBEGIN: printRandomNumbers");

        Random random = new Random();
        // Supplier requires zero arguments, but returns data.
        Supplier<Integer> randomSupplier = () -> random.nextInt(1000);
        System.out.print("10 random numbers: ");
        for(int i=0; i<10; i++) {
            System.out.print(randomSupplier.get() + ". ");
        }
        System.out.println();
    }
    // Practice working with the Function and BiFunction interfaces.
    public void printNames() {
        System.out.println("\nBEGIN: printNames");

        // Function is used to write methods that take one input and produce an output.
        // The first type parameter maps to the input, the second maps to the output.
        Function<Employee, String> extractFirstName = employee ->
                employee.getName().substring(0, employee.getName().indexOf(' '));
        System.out.print("Each employee's first name is: ");
        for(Employee employee: employees) {
            String firstName = extractFirstName.apply(employee);
            System.out.print(firstName + ". ");
        }
        System.out.println();

        // Function objects can be chained with other Function objects, as long as
        // the output of the first function matches the input of the second.
        Function<String, String> capitalise = str -> str.toUpperCase();
        Function<Employee, String> capitaliseName = extractFirstName.andThen(capitalise);
        System.out.print("Each employee's capitalised first name is: ");
        for(Employee employee: employees) {
            String firstName = capitaliseName.apply(employee);
            System.out.print(firstName + ". ");
        }
        System.out.println();

        // BiFunction is like Function, but requires two arguments.
        Function<Employee, String> extractSurname = employee ->
                employee.getName().substring(employee.getName().indexOf(" ") + 1);
        BiFunction<String, String, String> getFullName = (str1, str2) -> str1 + " " + str2;
        System.out.print("Each employee's full name is: ");
        for(Employee employee: employees) {
            String firstName = extractFirstName.apply(employee);
            String surname = extractSurname.apply(employee);
            String fullName = getFullName.apply(firstName, surname);
            System.out.print(fullName + ". ");
        }
        System.out.println();
    }
    // Practice working with the UnaryOperator interface.
    public void addIntegers() {
        System.out.println("\nBEGIN: addIntegers");

        UnaryOperator<Integer> incrementByFive = value -> value + 5;
        System.out.println("Inputting 10 into the incrementByFive lambda results in: " +
                incrementByFive.apply(10));
    }
    // Practice working with Stream.
    public void streamEmployees() {
        System.out.println("\nBEGIN: streamEmployees");

        Consumer<String> printDelimited = (String str) -> System.out.print(str + ". ");

        // Print employee names beginning with 'JA'.
        System.out.print("Employee names that start with 'JA' include: ");
        employees.stream()
                .map(Employee::getName) // employee -> employee.getName()
                .map(String::toUpperCase) // string -> string.toUpperCase()
                .filter(string -> string.startsWith("JA"))
                .sorted()
                .forEach(printDelimited);
        System.out.println();

        /*
        // Source for Stream. The source can be a collection, array, or an IO channel.
        employees
                // Returns a Stream that contains all the items in the list, in the same order.
                .stream()
                // Returns a Stream that contains the name of each employee.
                // Map always returns same number of items.
                .map(Employee::getName)
                // Returns a Stream that contains each name in upper case.
                .map(String::toUpperCase)
                // Returns a Stream in which items match the Predicate's constraints (returns true).
                // Filter returns same items or less compared to input Stream.
                .filter(string -> string.startsWith("JA"))
                // Returns Stream with natural ordering.
                // Sorted always returns the same number of items, but can change ordering.
                .sorted()
                // Performs an action for each item.
                // forEach is a terminal operation as it has a non-Stream return type.
                .forEach(string -> System.out.print(string + ". "));
        */

        // Print names of employees from different Lists.
        Department accounting = new Department("Accounting");
        accounting.addEmployee(employees.get(0));

        Department humanResources = new Department("Human Resources");
        employees.stream()
                .skip(1)
                .forEach(humanResources::addEmployee);

        List<Department> departments = new ArrayList<>();
        departments.add(accounting);
        departments.add(humanResources);

        System.out.print("Employees from all departments: ");
        departments.stream()
                // Concatenates sub-Streams from the current Stream, i.e. the employees sub-Streams
                // within the current department Stream.
                .flatMap(department -> department.getEmployees().stream())
                .map(Employee::toString)
                .forEach(printDelimited);
        System.out.println();

        // Stream.of(): generates Stream. Stream.concat() concatenates Streams.
        Stream<String> ioNumberStream = Stream.of("I26", "I17", "I29", "071");
        Stream<String> inNumberStream = Stream.of("N40", "N36", "I26", "I17", "I29");
        Stream<String> ionNumberStream = Stream.concat(ioNumberStream, inNumberStream);
        System.out.print("Unique elements: ");
        System.out.println(ionNumberStream
                .distinct()
                .peek(printDelimited) // Like forEach, but not a terminal operation.
                .count() + " (count).");

        // Can alternatively store the result of Stream operations using Stream.collect().
        List<String> allEmployees = departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .map(Employee::toString)
                .collect(Collectors.toList());

        // Reduce compares the entire Stream, two items at a time. It returns what's
        // remaining at the end.
        System.out.print("The youngest employee is: ");
        departments.stream()
                .flatMap(department -> department.getEmployees().stream())
                .reduce((Employee e1, Employee e2) -> e1.getAge() < e2.getAge() ? e1 : e2)
                .ifPresent((Employee e) ->
                        System.out.println(e.getName() + " (" + e.getAge() + ")."));
    }

    // Course section challenges.
    public void challenge1() {
        System.out.println("\nBEGIN: challenge1");

        // Challenge 1: Replace the anonymous inner class with a lambda equivalent.
        System.out.print("Class: ");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String myString = "Let's split this up into an array";
                String[] parts = myString.split(" ");
                for(String part: parts) {
                    System.out.print(part + ". ");
                }
                System.out.println();
            }
        };
        StartJoinThread.exec(runnable);

        // My solution.
        System.out.print("My solution: ");
        runnable = () -> {
            String myString = "Let's split this up into an array";
            Stream.of(myString.split(" "))
                    .forEach((String s) -> System.out.print(s + ". "));
            System.out.println();
        };
        StartJoinThread.exec(runnable);

        // Course solution.
        System.out.print("Course solution: ");
        runnable = () -> {
            String myString = "Let's split this up into an array";
            String[] parts = myString.split(" ");
            for(String part: parts) {
                System.out.print(part + ". ");
            }
            System.out.println();
        };
        StartJoinThread.exec(runnable);
    }
    public void challenge2to5() {
        System.out.println("\nBEGIN: challenge2to5");

        String originalString = "Hello, this is the original String.";
        System.out.println("Every other character for the given String is as follows:");
        System.out.println("Original String: " + originalString);

        class everySecondChar {
            // Challenge 2: Method to convert to lambda equivalent.
            public String exec(String source) {
                StringBuilder returnValue = new StringBuilder();
                for (int i = 0; i < source.length(); i++) {
                    if(i % 2 == 1) {
                        returnValue.append(source.charAt(i));
                    }
                }
                return returnValue.toString();
            }
            // Challenge 4.
            public String exec(Function<String, String> function, String input) {
                return function.apply(input);
            }
        }
        String output = new everySecondChar().exec(originalString);
        System.out.println("Class: " + output);

        // Challenge 2: My solution.
        // Probably more efficient to not use Streams like this, but good practice.
        UnaryOperator<String> myLambda = (String source) -> IntStream
                    .range(0, source.length())
                    .filter((int i) -> i % 2 == 1)
                    .mapToObj((int i) -> String.valueOf(source.toCharArray()[i]))
                    .reduce((String a, String b) -> a + b)
                    .get();
        output = myLambda.apply(originalString); // Challenge 3: Execute lambda.
        System.out.println("My C2 solution: " + output);

        // Challenge 2: Course solution.
        Function<String, String> courseLambda = (String s) -> {
            StringBuilder returnValue = new StringBuilder();
            for(int i = 0; i < s.length(); i++) {
                if(i % 2 == 1) {
                    returnValue.append(s.charAt(i));
                }
            }
            return returnValue.toString();
        };
        output = courseLambda.apply(originalString); // Challenge 3.
        System.out.println("Course C2 solution: " + output);

        // Challenge 4: Pass lambda into method.
        output = new everySecondChar().exec(myLambda, originalString); // Challenge 5: Return result.
        System.out.println("My C4 solution: " + output);
    }

}



