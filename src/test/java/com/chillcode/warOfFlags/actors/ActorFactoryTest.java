package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ActorFactoryTest {

    public static GameMap gameMap;

    public static final String testMap =
            "FFFFFFFFFFFFFFFF" + System.lineSeparator()
                    + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator()
                    + "F..PRSPRSPRSP..F" + System.lineSeparator() + "F..S........R..F" + System.lineSeparator()
                    + "F..R........S..F" + System.lineSeparator() + "F..P..FFFF..P..F" + System.lineSeparator()
                    + "F..S..FFFF..R..F" + System.lineSeparator() + "F..R..FFFF..S..F" + System.lineSeparator()
                    + "F..P..FFFF..P..F" + System.lineSeparator() + "F..S........R..F" + System.lineSeparator()
                    + "F..R........S..F" + System.lineSeparator() + "F..PSRPSRPSRP..F" + System.lineSeparator()
                    + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator()
                    + "FFFFFFFFFFFFFFFF";

    @BeforeAll
    public static void initGameMap() {
        gameMap = new GameMap(testMap);
    }

    @Test
    void createFromCharGivenRThenActorRock() {
        char c = 'R';
        Actor expected = new Rock(ActorFactory.getName(), gameMap);

        Actor actual = ActorFactory.createFromChar(c, gameMap);

        assertTrue(actual instanceof Rock);
    }

    @Test
    void createFromCharGivenZThenIllegalArgumentException() {
        char c = 'Z';

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> ActorFactory.createFromChar(c, gameMap));
    }

    @Test
    void createPlayerGivenPlayerTeamPaperThenActorPaper(){
        Player.PlayerTeam team = Player.PlayerTeam.PAPER;

        Actor actual = ActorFactory.createPlayer(team, gameMap);

        assertTrue(actual instanceof Paper);
    }




}