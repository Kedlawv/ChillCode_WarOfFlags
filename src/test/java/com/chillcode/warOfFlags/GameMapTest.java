package com.chillcode.warOfFlags;

import com.chillcode.warOfFlags.actors.Actor;
import com.chillcode.warOfFlags.actors.ActorFactory;
import com.chillcode.warOfFlags.actors.Paper;
import com.chillcode.warOfFlags.actors.Player;
import com.chillcode.warOfFlags.util.Vector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class GameMapTest {

    public static GameMap gameMap;

    public static final String testMap =
            "F..R........S..F" + System.lineSeparator() + "F..P..FFFF..P..F";

    @BeforeAll
    public static void initGameMap() {
        gameMap = new GameMap(testMap);
    }

    @Test
    void givenAStringMatrixAndCorrectTypeInActorMatrixThenTrue() {
        Actor[][] expected = {
                {ActorFactory.createFlag(gameMap), null, null, ActorFactory.createPlayer(Player.PlayerTeam.ROCK, gameMap),
                        null, null, null, null, null, null, null, null,
                        ActorFactory.createPlayer(Player.PlayerTeam.SCISSORS, gameMap), null, null,
                        ActorFactory.createFlag(gameMap)
                }
                ,
                {ActorFactory.createFlag(gameMap), null, null, ActorFactory.createPlayer(Player.PlayerTeam.PAPER, gameMap),
                        null, null,
                        ActorFactory.createFlag(gameMap), ActorFactory.createFlag(gameMap), ActorFactory.createFlag(gameMap),
                        ActorFactory.createFlag(gameMap), null, null,
                        ActorFactory.createPlayer(Player.PlayerTeam.PAPER, gameMap), null, null,
                        ActorFactory.createFlag(gameMap)
                }
        };

        Actor[][] actual = gameMap.getActorMatrix();

        int expectedMismatches = 0;
        int mismatches = getMismatches(expected, actual);

        assertEquals(expectedMismatches, mismatches);
    }

    @Test
    void givenAStringMatrixAndIncorrectTypeInActorMatrixThenFalse() {
        Actor[][] expected = {
                {null, null, null, ActorFactory.createPlayer(Player.PlayerTeam.PAPER, gameMap),
                        null, null, null, null, null, null, null, ActorFactory.createFlag(gameMap),
                        ActorFactory.createPlayer(Player.PlayerTeam.SCISSORS, gameMap), null, null,
                        ActorFactory.createFlag(gameMap)
                }
                ,
                {ActorFactory.createFlag(gameMap), null, null, ActorFactory.createPlayer(Player.PlayerTeam.PAPER, gameMap),
                        null, null,
                        ActorFactory.createFlag(gameMap), ActorFactory.createFlag(gameMap), ActorFactory.createFlag(gameMap),
                        ActorFactory.createFlag(gameMap), null, null,
                        ActorFactory.createPlayer(Player.PlayerTeam.PAPER, gameMap), null, null,
                        ActorFactory.createFlag(gameMap)
                }
        };

        Actor[][] actual = gameMap.getActorMatrix();

        int expectedMismatches = 3;
        int mismatches = getMismatches(expected, actual);

        assertEquals(expectedMismatches, mismatches);
    }

    @Test
    public void withinBoundariesGivenAGrid3x3AndVectorWithinBoundariesThenTrue() {

        // grid just to visualize reversed X,Y
        Vector[][] grid3x3Vector = {
                {new Vector(0, 0), new Vector(1, 0), new Vector(2, 0)}, // index 0,2 holds vector 2,0
                {new Vector(0, 1), new Vector(1, 1), new Vector(2, 1)},
                {new Vector(0, 2), new Vector(1, 2), new Vector(2, 2)}
        };

        String grid3x3String =
                "..." + System.lineSeparator() +
                        "..." + System.lineSeparator() +
                        "..." + System.lineSeparator();

        GameMap localGameMap = new GameMap(grid3x3String);
        Vector position = new Vector(1, 1);

        boolean actual = localGameMap.withinBoundaries(position);

        assertTrue(actual);
    }

    @Test
    public void withinBoundariesGivenAGrid3x3AndVectorOutOfBoundsXThenFalse() {

        String grid3x3String =
                "..." + System.lineSeparator() +
                        "..." + System.lineSeparator() +
                        "..." + System.lineSeparator();

        GameMap localGameMap = new GameMap(grid3x3String);
        Vector position = new Vector(3, 1);

        boolean actual = localGameMap.withinBoundaries(position);

        assertFalse(actual);
    }

    @Test
    public void withinBoundariesGivenAGrid3x3AndVectorOutOfBoundsYThenFalse() {

        String grid3x3String =
                "..." + System.lineSeparator() +
                        "..." + System.lineSeparator() +
                        "..." + System.lineSeparator();

        GameMap localGameMap = new GameMap(grid3x3String);
        Vector position = new Vector(1, 3);

        boolean actual = localGameMap.withinBoundaries(position);

        assertFalse(actual);
    }

    @Test
    public void getPositionForNullThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> gameMap.getPosition(null));
    }

    @Test
    public void getPositionForNewActorThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> gameMap.getPosition(new Paper("Absent", gameMap)));
    }

    @Test
    public void getPositionGivenExistingActorReturnCorrectVector() {
        GameMap localGameMap = new GameMap(
                "F.P" + System.lineSeparator()
                        + "RPS" + System.lineSeparator() +
                        ".F.");

        Actor rockIndex1_0VectorX0Y1 = localGameMap.getActorMatrix()[1][0];
        Vector expected = new Vector(0,1);

        assertEquals(expected, localGameMap.getPosition(rockIndex1_0VectorX0Y1));
    }

    @Test
    public void getActorGivenPositionWithinBoundsOfAnActorReturnCorrectActor(){
        GameMap localGameMap = new GameMap(
                "F.P" + System.lineSeparator()
                        + "RPS" + System.lineSeparator() +
                        ".F.");
        Vector inputPosition = new Vector(2,0);
        Actor expected = localGameMap.getActorMatrix()[0][2];

        Actor actual = localGameMap.getActor(inputPosition);

        assertSame(expected, actual);  // same as expected == actual
    }

    @Test
    public void getActorGivenPositionOutsideBoundsThrowIllegalArgumentException(){
        GameMap localGameMap = new GameMap(
                "F.P" + System.lineSeparator()
                        + "RPS" + System.lineSeparator() +
                        ".F.");
        Vector inputPosition = new Vector(3,0);

        assertThrows(IllegalArgumentException.class, ()-> localGameMap.getActor(inputPosition));
    }

    @Test
    public void getActorGivenPositionWithinBoundsForNoActorReturnNull(){
        GameMap localGameMap = new GameMap(
                "F.P" + System.lineSeparator()
                        + "RPS" + System.lineSeparator() +
                        ".F.");
        Vector inputPosition = new Vector(2,2);
        Actor actual = localGameMap.getActor(inputPosition);

        assertNull(actual);
    }

    @Test
    public void setPosition(){

    }

    private int getMismatches(Actor[][] expected, Actor[][] actual) {
        int mismatches = 0;

        for (int i = 0; i < actual.length; i++) {
            for (int j = 0; j < actual[0].length; j++) {
                Actor act = actual[i][j];
                Actor exp = expected[i][j];
                if (act == null) {
                    if (exp != null) {
                        mismatches++;
                    }
                } else if (exp == null) {
                    mismatches++;
                } else if (act.getClass() != exp.getClass()) {
                    mismatches++;
                }
            }
        }
        return mismatches;
    }


}