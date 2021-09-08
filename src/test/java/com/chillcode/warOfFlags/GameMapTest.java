package com.chillcode.warOfFlags;

import com.chillcode.warOfFlags.actors.Actor;
import com.chillcode.warOfFlags.actors.ActorFactory;
import com.chillcode.warOfFlags.actors.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


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