package com.chillcode.warOfFlags;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GameMapTest {

    public static GameMap gameMap;

    public static final String testMap = "FFFFFFFFFFFFFFFF" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..PRSPRSPRSP..F" + System.lineSeparator() + "F..S........R..F"
            + System.lineSeparator() + "F..R........S..F" + System.lineSeparator() + "F..P..FFFF..P..F" + System.lineSeparator() + "F..S..FFFF..R..F" + System.lineSeparator() + "F..R..FFFF..S..F" + System.lineSeparator() + "F..P..FFFF..P..F" + System.lineSeparator() + "F..S........R..F"
            + System.lineSeparator() + "F..R........S..F" + System.lineSeparator() + "F..PSRPSRPSRP..F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "F..............F" + System.lineSeparator() + "FFFFFFFFFFFFFFFF";

    @BeforeAll
    public static void initGameMap(){
        gameMap = new GameMap(testMap);
    }

    @Test
    void populateActorMatrix() {
        gameMap.populateActorMatrix(testMap.split("\r\n|\r|\n"));
    }
}