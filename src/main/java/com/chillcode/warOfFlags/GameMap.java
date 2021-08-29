package com.chillcode.warOfFlags;

import com.chillcode.warOfFlags.actors.*;
import com.chillcode.warOfFlags.util.Direction;
import com.chillcode.warOfFlags.util.GameUtils;
import com.chillcode.warOfFlags.util.Vector;
import com.codecool.capture_the_flag.actors.*;

import java.util.ArrayList;
import java.util.List;

/**
 * GameMap class
 */
public class GameMap {

    /**
     * A 2D matrix of all actor references (null if the field is empty)
     */
    private final Actor[][] actorMatrix;

    /**
     * Contains all players present on the map (also dead ones)
     */
    private final List<Player> players;

    /**
     * Contains all flags present on the map (also captured ones)
     */
    private final List<Flag> flags;


    /**
     * Returns a new GameMap instance, constructed from given char matrix
     */
    public GameMap(String charMatrix) {
        players = new ArrayList<>();
        flags = new ArrayList<>();

        // initialize the Actor array to the correct size
        String[] rows = charMatrix.split("\r\n|\r|\n");
        actorMatrix = new Actor[rows.length][rows[0].length()];

    }

    public void populateActorMatrix(String[] rows) {
        String[][] matrix = new String[rows.length][rows[0].length()];
        for (int i = 0; i < rows.length; i++) {
            matrix[i] = rows[i].split("");
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j].equals("F")) {
                    actorMatrix[i][j] = new Flag(this);
                } else if (matrix[i][j].equals(".")) {
                    actorMatrix[i][j] = null;
                } else if (matrix[i][j].equals("R")) {
                    actorMatrix[i][j] = new Rock(ActorFactory.getName(), this);
                } else if (matrix[i][j].equals("S")) {
                    actorMatrix[i][j] = new Scissors(ActorFactory.getName(), this);
                } else if (matrix[i][j].equals("P")) {
                    actorMatrix[i][j] = new Paper(ActorFactory.getName(), this);
                }


            }
        }


    }

    /**
     * Returns a char matrix of map's current state
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < actorMatrix.length; i++) {
            for (int j = 0; j < actorMatrix[0].length; j++) {
                if (actorMatrix[i][j] instanceof Flag) {
                    sb.append("F");
                } else if (actorMatrix[i][j] == null) {
                    sb.append(".");
                } else if (actorMatrix[i][j] instanceof Rock) {
                    sb.append("R");
                } else if (actorMatrix[i][j] instanceof Scissors) {
                    sb.append("S");
                } else if (actorMatrix[i][j] instanceof Paper) {
                    sb.append("P");
                }
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString();
    }

    /**
     * Returns an actor instance present on given position
     * Should return null if no actor is present
     * Should throw an IllegalArgumentException if the position is outside map's boundaries
     *
     * @param position
     * @return
     */
    public Actor getActor(Vector position) {
        return actorMatrix[position.getY()][position.getX()];
    }

    /**
     * Returns a position of given actor instance
     * Should throw an IllegalArgumentException if actor is not found or no actor is given
     *
     * @param actor
     * @return
     */
    public Vector getPosition(Actor actor) {
        for (int i = 0; i < actorMatrix.length; i++) {
            for (int j = 0; j < actorMatrix[0].length; j++) {
                if (actorMatrix[i][j] == actor) {
                    return new Vector(j, i);
                }
            }
        }
        return null;
    }


    /**
     * Assignes given actor to given position
     * Should throw an IllegalArgumentException if the position is occupied by an another actor
     *
     * @param actor
     * @param position
     */
    public void setPosition(Actor actor, Vector position) {

        if(withinBoundaries(position)){
            actorMatrix[position.getY()][position.getX()] = actor;
        }else{
            throw new IllegalArgumentException();
        }
    }

    /**
     * Attempts to move given player to a new position
     * If necessary, restricts movement or simulates fights between players
     *
     * @param player
     * @param currentPosition
     * @param dir
     */
    public void tryMovePlayer(Player player, Vector currentPosition, Direction dir) {
        Vector dirVector = GameUtils.toVector(dir);
        Vector targetPosition = new Vector(currentPosition.getX() + dirVector.getX(), currentPosition.getY() + dirVector.getY());

        if (!withinBoundaries(targetPosition))
            return;

        Actor actorOnTargetPosition = getActor(targetPosition);

        if (actorOnTargetPosition == null) {
            actorMatrix[currentPosition.getY()][currentPosition.getX()] = null;
            setPosition(player, targetPosition);
        } else if (actorOnTargetPosition instanceof Flag) {
            actorMatrix[currentPosition.getY()][currentPosition.getX()] = null;
            actorMatrix[targetPosition.getY()][targetPosition.getX()] = null;
            setPosition(player, targetPosition);

            player.setCapturedFlags(player.getCapturedFlags() + 1);
            ((Flag) actorOnTargetPosition).setCaptured(true);
        } else if (actorOnTargetPosition instanceof Player) {
            Player otherPlayer = (Player) actorOnTargetPosition;
            int fightResult = player.Fight(otherPlayer);

            if (fightResult == 1) {
                // Player has won, move to the target position
                otherPlayer.setAlive(false);
                actorMatrix[currentPosition.getY()][currentPosition.getX()] = null;
                actorMatrix[targetPosition.getY()][targetPosition.getX()] = null;
                setPosition(player, targetPosition);

                player.setKilledPlayers(player.getKilledPlayers() + 1);
            } else if (fightResult == 0) {
                // Other player has won
                player.setAlive(false);
                actorMatrix[currentPosition.getY()][currentPosition.getX()] = null;
                actorMatrix[targetPosition.getY()][targetPosition.getX()] = null;
                setPosition(otherPlayer, currentPosition);

                otherPlayer.setKilledPlayers(otherPlayer.getKilledPlayers() + 1);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Returns the position of an uncaptured flag that is closest to given player
     * Should throw IllegalArgumentException if there are no uncaptured flags
     *
     * @param player
     * @return
     */
    public Vector getNearestFlagPosition(Player player) {
        throw new RuntimeException("Method not implemented!");
    }

    /**
     * Returns true if given position is within the map's boundaries
     *
     * @param position
     * @return
     */
    public boolean withinBoundaries(Vector position) {
        return position.getX() < actorMatrix.length && position.getY() < actorMatrix[0].length;
    }

    public Actor[][] getActorMatrix() {
        return actorMatrix;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Flag> getFlags() {
        return flags;
    }
}

