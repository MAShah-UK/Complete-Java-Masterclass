// Java source files always contain their package name at the top.
package com.cjm.ms;

// Imported from custom 'com.cjm.test' package.
import com.cjm.test.DataOutput;

// Imported from custom PackageDemo library.
import com.cjm.game.BasicEngineeringOrder;
import com.cjm.game.BasicGame;
import com.cjm.game.ManageData;

import javafx.scene.Node;

public class Main {
    public static void main(String[] args) {
        importPackages();
        createWindow();
        manageData();
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
}
