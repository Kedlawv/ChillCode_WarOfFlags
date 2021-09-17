package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

/**
 * Scissors player class
 */
public class Scissors extends Player {

    public Scissors(String name, GameMap mapReference) {
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
        return PlayerTeam.SCISSORS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Scissors otherScissors = (Scissors) o;
        return (otherScissors.getName().equals(this.getName())
                && otherScissors.getKilledPlayers() == this.getKilledPlayers()
                && otherScissors.isAlive() == this.isAlive()
                && otherScissors.getCapturedFlags() == this.getCapturedFlags());
    }
}

