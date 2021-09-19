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
        if (actor == null) return '.';

        String prefix = "com.chillcode.warOfFlags.actors.";

        String className = actor.getClass().getName().replace(prefix, "");

        switch (className) {
            case "Rock":
                return 'R';
            case "Paper":
                return 'P';
            case "Scissors":
                return 'S';
            case "Flag":
                return 'F';
            default:
                throw new IllegalArgumentException(
                        "Input is not any of the legal types : Rock, Paper, Scissors, Flag, null");
        }
    }


    /**
     * Returns a vector representing given direction
     * Throws an IllegalArgumentException when given invalid input
     *
     * @param dir
     * @return
     */
    public static Vector toVector(Direction dir) {
        if (dir == null) throw new IllegalArgumentException("Argument can't be null");

        switch (dir) {
            case UP:
                return new Vector(0, -1);
            case DOWN:
                return new Vector(0, 1);
            case LEFT:
                return new Vector(-1, 0);
            case RIGHT:
                return new Vector(1, 0);
            default:
                throw new IllegalArgumentException();
        }
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
        if (dir == null) throw new IllegalArgumentException("Argument can't be null");

        switch (dir) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
            default:
                throw new IllegalArgumentException();
        }
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

