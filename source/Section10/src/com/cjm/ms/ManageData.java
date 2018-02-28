package com.cjm.ms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManageData {
    // Input from console since file I/O hasn't been covered yet.
    public static List<Integer> loadDataToObject(int num) {
        List<Integer> data = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        for (int i = 1; i <= num; i++) {
            System.out.print("Enter value for index " + i + "/" + num + ": ");
            data.add(sc.nextInt());
            sc.nextLine();
        }

        return data;
    }

    // Output to console since file I/O hasn't been covered yet.
    public static void saveDataFromObject(ISaveable obj) {
        List list = obj.saveData();
        for (int i = 0; i < list.size(); i++) {
            System.out.println("Index: " + i + " Value: " + list.get(i));
        }
    }
}

// Basic class representing elements in a game.
class BasicGame implements ISaveable {
    private int countHumans;
    private int countMonsters;
    private int countRobots;

    public BasicGame() {
        loadData(ManageData.loadDataToObject(3));
    }

    @Override
    public List<Integer> saveData() {
        return Arrays.asList(countHumans, countMonsters, countRobots);
    }

    @Override
    public void loadData(List<Integer> values) {
        countHumans = values.get(0);
        countMonsters = values.get(1);
        countRobots = values.get(2);
    }
}

// Basic class representing parts to be ordered.
class BasicEngineeringOrder implements ISaveable {

    private int bolts;
    private int nuts;
    private int rebar;
    private int screws;
    private int screwDrivers;

    public BasicEngineeringOrder() {
        loadData(ManageData.loadDataToObject(5));
    }

    @Override
    public List<Integer> saveData() {
        return Arrays.asList(bolts, nuts, rebar, screws, screwDrivers);
    }

    @Override
    public void loadData(List<Integer> values) {
        bolts = values.get(0);
        nuts = values.get(1);
        rebar = values.get(2);
        screws = values.get(3);
        screwDrivers = values.get(4);
    }
}