package com.chillcode.warOfFlags.util;

import com.chillcode.warOfFlags.GameMap;
import com.chillcode.warOfFlags.actors.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameUtilsTest {

    @Mock
    GameMap gameMap;

    @Test
    void getCharGivenAllCorrectTypesThenCorrectCharacters() {
        Actor rock = new Rock("Not relevant", gameMap);
        Actor paper = new Paper("Not relevant", gameMap);
        Actor scissors = new Scissors("Not relevant", gameMap);
        Actor flag = new Flag(gameMap);
        Actor fullStop = null;
        List<Character> expected = Arrays.asList('R', 'P', 'S', 'F', '.');

        List<Character> actual = new ArrayList<>();
        actual.add(GameUtils.getChar(rock));
        actual.add(GameUtils.getChar(paper));
        actual.add(GameUtils.getChar(scissors));
        actual.add(GameUtils.getChar(flag));
        actual.add(GameUtils.getChar(fullStop));

        assertEquals(expected, actual);
    }

    @Test
    public void getCharGivenIncorrectTypeThenThrowIllegalArgumentException() {
        Actor illegalActor = new Actor(new GameMap(".")) {
            @Override
            public GameMap getMapReference() {
                return super.getMapReference();
            }
        };

        assertThrows(IllegalArgumentException.class, () -> GameUtils.getChar(illegalActor));
    }

    @Test
    public void toVectorGivenFourCorrectDirectionsThenFourCorrectVectors() {
        Vector[] expected = {
                new Vector(0, -1),
                new Vector(0, 1),
                new Vector(-1, 0),
                new Vector(1, 0)
        };

        Vector[] actual = new Vector[4];
        actual[0] = GameUtils.toVector(Direction.UP);
        actual[1] = GameUtils.toVector(Direction.DOWN);
        actual[2] = GameUtils.toVector(Direction.LEFT);
        actual[3] = GameUtils.toVector(Direction.RIGHT);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void invertedGivenFourDirectionsThenCorrectFourOpposites() {
        List<Direction> expected = Arrays.asList(
                Direction.UP,
                Direction.DOWN,
                Direction.LEFT,
                Direction.RIGHT);

        List<Direction> actual = new ArrayList<>();
        actual.add(GameUtils.inverted(Direction.DOWN));
        actual.add(GameUtils.inverted(Direction.UP));
        actual.add(GameUtils.inverted(Direction.RIGHT));
        actual.add(GameUtils.inverted(Direction.LEFT));

        assertEquals(expected, actual);
    }


}