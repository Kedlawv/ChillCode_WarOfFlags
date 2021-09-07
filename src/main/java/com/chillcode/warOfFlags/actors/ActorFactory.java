package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Static class for creating new actor instances
 */
public class ActorFactory {

    /**
     * A predefined collection of names for the players
     */
    private static final Queue<String> names = new LinkedList<>(Arrays.asList(
            "Marcel", "Moises", "Zane", "Dashawn", "Sean", "Rashad", "Seth", "Oliver", "Chris", "Quinton",
            "August", "Yusuf", "Jeramiah", "Bryce", "Rory", "Preston", "Eli", "Elisha", "Vicente", "Cristian",
            "Justice", "Eddie", "Allan", "Aarav", "Layne", "Owen", "Julio", "Soren", "Levi", "Mekhi", "Paul",
            "Griffin", "Agustin", "Johan", "Jaydin", "Skylar", "Rodrigo", "Brian", "John", "Davon", "Damari",
            "Ty", "Raymond", "Ronald", "Noah", "Abdiel", "Tyree", "Trent", "Rafael", "Jamarion"));


    /**
     * Use this method for getting names
     * todo sort out the one name problem
     * wabu => not sure what the author meant but this will return always the same name;
     *      change peek() to poll() and maybe add() the peeked value to rotate the names;
     *      I'm guessing that the author took a shortcut for testing purposes.
     *      names filed is final so there is no way to reset the field after a test.
     *
     * @return name
     */
    public static String getName() {
        return names.peek();
    }

    /**
     * Returns a new player instance, depending on given team
     *
     * @param team
     * @param mapReference
     * @return
     */
    public static Actor createPlayer(Player.PlayerTeam team, GameMap mapReference) {
        Actor actor;

        switch(team){
            case ROCK: actor = new Rock(getName(),mapReference);
            break;
            case PAPER: actor = new Paper(getName(),mapReference);
            break;
            case SCISSORS: actor = new Scissors(getName(),mapReference);
            break;
            default: throw new IllegalArgumentException();
        }

        return actor;
    }

    /**
     * Returns a new Flag instance
     *
     * @param mapReference
     * @return
     */
    public static Actor createFlag(GameMap mapReference) {
        return new Flag(mapReference);
    }

    /**
     * Returns a new actor instance, depending on given character
     * wabu => if character is not one of R,P,S,F,. throw IllegalArgumentException
     *
     * @param c
     * @param mapReference
     * @return
     */
    public static Actor createFromChar(char c, GameMap mapReference) {

        Actor actor;
        switch (c) {
            case 'R':
                actor = new Rock(getName(), mapReference);
                break;
            case 'P':
                actor = new Paper(getName(), mapReference);
                break;
            case 'S':
                actor = new Scissors(getName(), mapReference);
                break;
            case 'F':
                actor = new Flag(mapReference);
                break;
            case '.':
                actor = null;
                break;
            default:
                throw new IllegalArgumentException("Character needs to be one of R,P,S,F or '.'");

        }

        return actor;
    }
}

