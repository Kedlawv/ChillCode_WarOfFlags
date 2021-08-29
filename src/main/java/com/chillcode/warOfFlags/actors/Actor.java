package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

/**
 * Base class for all Actors - Players and Flags
 */
public abstract class Actor {

    /**
     * Reference to Game's map
     */
    protected GameMap mapReference;

    public Actor(GameMap mapReference) {
        this.mapReference = mapReference;
    }

    public GameMap getMapReference() {
        return mapReference;
    }
}

