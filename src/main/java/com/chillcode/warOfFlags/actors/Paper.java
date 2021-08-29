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
        throw new RuntimeException("Method not implemented!");
    }
}
