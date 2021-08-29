package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

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
}

