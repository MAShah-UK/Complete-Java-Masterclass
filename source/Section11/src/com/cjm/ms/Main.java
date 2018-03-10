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

        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);

        // This code will execute without bounded type parameters, which is not what we want.
        // Team<String> brokenTeam = new Team<>("This won't work.");
        // brokenTeam.addPlayer(beckham);

        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.matchResult(fremantle, 1, 0);
        hawthorn.matchResult(adelaideCrows, 3, 8);

        adelaideCrows.matchResult(fremantle, 2, 1);
        // Doesn't work since we specified that only teams within the same sport can be compared.
        // adelaideCrows.matchResult(baseballTeam, 1, 1);

        System.out.println("\nRankings");
        System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.ranking());
        System.out.println(melbourne.getName() + ": " + melbourne.ranking());
        System.out.println(fremantle.getName() + ": " + fremantle.ranking());
        System.out.println(hawthorn.getName() + ": " + hawthorn.ranking());

        // Overriding Comparable and implementing compareTo allows using Collections.sort() to
        // automatically sort through collections containing Team objects.
        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));
        System.out.println(hawthorn.compareTo(adelaideCrows));
        System.out.println(melbourne.compareTo(fremantle));
    }
}
