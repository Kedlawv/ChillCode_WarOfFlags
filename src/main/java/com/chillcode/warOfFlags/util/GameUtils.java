package com.chillcode.warOfFlags.util;

import com.chillcode.warOfFlags.actors.Actor;

public class GameUtils {

    /**
     * Returns a character representing given actor
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param actor
     * @return
     */
    public static char getChar(Actor actor) {
        throw new RuntimeException("Method not implemented!");
    }


    /**
     * Returns a vector representing given direction
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param dir
     * @return
     */
    public static Vector toVector(Direction dir) {
        throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns a direction converted from given vector
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param vector
     * @return
     */
    public static Direction toDirection(Vector vector) {

        if (vector.getX() == 0 && vector.getY() == -1)
            return Direction.UP;

        if (vector.getX() == 0 && vector.getY() == 1)
            return Direction.DOWN;

        if (vector.getX() == -1 && vector.getY() == 0)
            return Direction.LEFT;

        if (vector.getX() == 1 && vector.getY() == 0)
            return Direction.RIGHT;

        throw new IllegalArgumentException();
    }

    /**
     * Returns a direction that is opposite to given direction
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param dir
     * @return
     */
    public static Direction inverted(Direction dir) {
        throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns the amount of steps a player has to make in order to get from pos1 to pos2
     *
     * @param pos1
     * @param pos2
     * @return
     */
    public static int getDistance(Vector pos1, Vector pos2) {
        throw new RuntimeException("Method not implemented!");
    }
}

