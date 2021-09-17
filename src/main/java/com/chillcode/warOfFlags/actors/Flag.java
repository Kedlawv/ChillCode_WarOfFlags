package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

import java.util.Objects;

/**
 * Flag actor class
 */
public class Flag extends Actor {

    private boolean captured;

    public Flag(GameMap mapReference) {
        super(mapReference);
        this.captured = false;
    }

    /**
     * Returns true after being captured by a player
     *
     * @return
     */
    public boolean isCaptured() {
        return captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flag flag = (Flag) o;
        return captured == flag.captured;
    }

    @Override
    public int hashCode() {
        return Objects.hash(captured);
    }
}

