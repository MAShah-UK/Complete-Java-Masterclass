package com.cjm.ms;

import java.util.ArrayList;

public abstract class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

// Should be in it's own file, but I don't want to disorganise code and packages haven't been covered yet.
class BaseballPlayer extends Player {
    public BaseballPlayer(String name) {
        super(name);
    }
}

class FootballPlayer extends Player {
    public FootballPlayer(String name) {
        super(name);
    }
}

class SoccerPlayer extends Player {
    public SoccerPlayer(String name) {
        super(name);
    }
}



