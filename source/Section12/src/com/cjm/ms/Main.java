// Java source files always contain their package name at the top.
package com.cjm.ms;

// Import from custom PackageDemo library.
import com.cjm.game.BasicEngineeringOrder;
import com.cjm.game.BasicGame;
import com.cjm.game.ManageData;
// Import from custom 'com.cjm.test' package.
import com.cjm.test.DataOutput;
// Import built-in class.
import javafx.scene.Node;
// Import static methods directly from MathLib library.
import static com.cjm.Math.Series.*;

public class Main {
    public static void main(String[] args) {
        importPackages();
        createWindow();
        createHelloWorld();
        //manageData();
        useMathLib();
        createScopeCheck();
        createX();
    }

    // Practice importing packages.
    public static void importPackages() {
        System.out.println("\nBEGIN: importPackages");

        // Defined in:
        // javax.xml.soap.Node
        // com.sun.tools.jdeps.DepsAnalyzer.Node
        // javafx.scene.Node
        Node node1 = null;
        // Can also specify package prior to class:
        javafx.scene.Node node2 = null;
        java.util.List<String> list = new java.util.ArrayList<>();
    }

    // Practice importing packages.
    public static void createWindow() {
        System.out.println("\nBEGIN: createWindow");

        MyWindow myWindow = new MyWindow("Complete Java");
        myWindow.setVisible(true);
    }

    // Practice creating my own package.
    public static void createHelloWorld() {
        System.out.println("\nBEGIN: createHelloWorld");

        DataOutput dOut = new DataOutput();
        dOut.toConsole("Hello World");
    }

    // Practice creating and importing libraries (JAR files).
    public static void manageData() {
        System.out.println("\nBEGIN: manageData");

        System.out.println("Loading values for BasicGame.");
        BasicGame bGame = new BasicGame();

        System.out.println("\nLoading values for BasicEngineeringOrder.");
        BasicEngineeringOrder bEngOrder = new BasicEngineeringOrder();

        System.out.println("\nSaving BasicGame data.");
        ManageData.saveDataFromObject(bGame);

        System.out.println("\nSaving BaseEngineeringOrder data.");
        ManageData.saveDataFromObject(bEngOrder);
    }

    // Practice creating and importing libraries (JAR files) via exercise:
    public static void useMathLib() {
        System.out.println("\nBEGIN: useMathLib");

        System.out.println("Sums for n=[0, 10] are: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(sum(i) + " ");
        }
        System.out.println("\nFactorials for n=[0, 10] are: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(factorial(i) + " ");
        }
        System.out.println("\nFibonacci for n=[0, 10] are: ");
        for (int i = 0; i <= 10; i++) {
            System.out.print(fibonacci(i) + " ");
        }
    }

    // Practice scoping logic.
    public static void createScopeCheck() {
        System.out.println("\nBEGIN: createScopeCheck");

        String privateVar = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        System.out.println("scopeInstance privateVar is " + scopeInstance.getPrivateVar() + ".");
        System.out.println(privateVar);
        scopeInstance.timesTwo();
        System.out.println();

        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        innerClass.timesTwo();

        // Can access field within inner class from outside even though it's private.
        scopeInstance.useInner();
    }

    // Practice scoping logic through exercise.
    public static void createX() {
        System.out.println("\nBEGIN: createX");

        X x = new X();
        x.x();
    }
}

/* com.cjm.Math.Series code:
package com.cjm.Math;

// Create a suitably named package containing a class called Series
// with the following static methods:
// - sum(int n) returns the sum of all numbers from 0 to n.
//   The first 10 numbers are: 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55.
// - factorial(int n) returns the product of all numbers from 1 to n
//   i.e. 1 * 2 * 3 * 4 ... * (n - 1) * n.
//   The first 10 factorials are:
//   0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800.
// - fibonacci(n) returns the nth Fibonacci number. These are defined as:
//   f(0) = 0, f(1) = 1, f(n) = f(n-1) + f(n-2)
//   (so f(2) is also 1. The first 10 fibonacci numbers are:
//   0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55.
// When you have tested your functions, delete the Main class and
// produce a jar file. Create a new project and add your Series library,
// then test the three methods in the main() method of your new project.

public class Series {
    // Seq: 0, 1, 3, 6, 10, 15, 21, 28, 36, 45, 55...
    public static long sum(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
        // Fastest solution: return (n*(n+1))/2;
    }

    // Seq: 0, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800...
    public static long factorial(int n) {
        long fac = (n > 0) ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            fac *= i;
        }
        return fac;
    }

    // Seq: 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55...
    public static long fibonacci(int n) {
        long init = (n > 0) ? 1 : 0;
        // fib(n-2), fib(n-1), fib(n)
        long[] fib = new long[]{0, 0, init};
        for (int i = 2; i <= n; i++) {
            fib[0] = fib[1];
            fib[1] = fib[2];
            fib[2] = fib[0] + fib[1];
        }
        return fib[2];
    }
} */
