package com.cjm.ms;

import java.util.ArrayList;
import java.util.List;

public class LeagueTable<T extends Player> {
    List<Team<T>> teams = new ArrayList<>();

    public void addTeam(Team<T> team) {
        if (teams.con)
        teams.add(team);
    }
}
