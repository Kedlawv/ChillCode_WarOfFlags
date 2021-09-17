package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;
import com.chillcode.warOfFlags.util.Direction;
import com.chillcode.warOfFlags.util.Vector;


/**
 * Rock player class
 */
public class Rock extends Player {

    public Rock(String name, GameMap mapReference) {
        super(name, mapReference);
    }

    @Override
    public void onGameCycle() {
        if (!isAlive())
            return;

        // Make next move
        Vector myPosition = mapReference.getPosition(this);
        Vector nearestFlagPosition = mapReference.getNearestFlagPosition(this);
        Direction targetDirection = getMoveDirection(myPosition, nearestFlagPosition);

        mapReference.tryMovePlayer(this, myPosition, targetDirection);
    }

    @Override
    public int Fight(Player otherPlayer) {
        throw new RuntimeException("Method not implemented!");
    }

    @Override
    public PlayerTeam getTeam() {
        return PlayerTeam.ROCK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;

        Rock otherRock = (Rock) o;
        return (otherRock.getName().equals(this.getName())
                && otherRock.getKilledPlayers() == this.getKilledPlayers()
                && otherRock.isAlive() == this.isAlive()
                && otherRock.getCapturedFlags() == this.getCapturedFlags());
    }
}

