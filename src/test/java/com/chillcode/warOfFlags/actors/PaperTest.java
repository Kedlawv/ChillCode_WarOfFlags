package com.chillcode.warOfFlags.actors;

import com.chillcode.warOfFlags.GameMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaperTest {

    @Mock
    GameMap gameMap;

    // todo lets pretend that GameMap is not implemented and use Mockito to deliver an Instance of GameMap
    //  to the Paper constructor
    @Test
    void testEqualsGivenTwoPaperWithSameStateThenEquals() {
        String name = "Frodo";
        int capturedFlags = 2;
        int killedPlayers = 5;

        Paper paper1 = new Paper(name,gameMap);
        paper1.setCapturedFlags(capturedFlags);
        paper1.setKilledPlayers(killedPlayers);

        Paper paper2 = new Paper(name,gameMap);
        paper1.setCapturedFlags(capturedFlags);
        paper1.setKilledPlayers(killedPlayers);

        assertTrue(paper1.equals(paper2));
    }
}