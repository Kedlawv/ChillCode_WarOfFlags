package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

/**
 * Paper player class
 */
public class Paper extends Player {

    public Paper(String name, GameMap mapReference) {
        super(name, mapReference);
    }

    @Override
    public void onGameCycle() {
        throw new RuntimeException("Method not implemented!");
    }

    @Override
    public int Fight(Player otherPlayer) {
        throw new RuntimeException("Method not implemented!");
    }

    @Override
    public PlayerTeam getTeam() {
        return PlayerTeam.PAPER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Paper otherPaper = (Paper) o;
        return (otherPaper.getName().equals(this.getName())
                || otherPaper.getKilledPlayers() == this.getKilledPlayers()
                || otherPaper.isAlive() == this.isAlive()
                || otherPaper.getCapturedFlags() == this.getCapturedFlags());
    }


}
