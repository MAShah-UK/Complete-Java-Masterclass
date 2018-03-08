package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        printObjList();
        createTeam();
    }

    // Practice working with raw types.
    public static void printObjList() {
        System.out.println("BEGIN: printObjList");

        // If type parameter isn't specified it defaults to Object.
        // The list can now accept any datatype due to upcasting,
        // but this is not recommended since it can break type checking.
        List objList = new ArrayList(); // Equivalent: List<Object> items = new ArrayList<Object>();
        objList.add(1); // Integer upcasted to Object.
        objList.add(2);
        objList.add(3);
        objList.add("four"); // String upcasted to Object.
        objList.add(5);

        for (Object i : objList) {
            // Will give a ClassCastException for 4th element.
            // System.out.println((Integer) i);
            System.out.println(i.toString()); // Integer and String override Object.toString().
        }
    }

    // Practice creating generic classes.
    public static void createTeam() {
        System.out.println("\nBEGIN: createTeam");

        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer beckham = new SoccerPlayer("Beckham");

        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(joe);
        // adelaideCrows.addPlayer(pat); // Error since FootballPlayer was specified.
        // adelaideCrows.addPlayer(beckham); // Same as above.

        Team<BaseballPlayer> chicagoCubs = new Team<>("Chicago Cubs");
        chicagoCubs.addPlayer(pat);

        Team<SoccerPlayer> UKTeam = new Team<>("UKTeam");
        UKTeam.addPlayer(beckham);

        // This code will execute without bounded type parameters.
        // Team<String> brokenTeam = new Team<>("This won't work.");
        // brokenTeam.addPlayer("no-one");
    }
}
