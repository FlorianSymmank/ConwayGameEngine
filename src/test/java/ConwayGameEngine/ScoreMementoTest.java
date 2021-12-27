package ConwayGameEngine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreMementoTest {

    @Test
    void getState() {
        ConwayGame game = new ConwayGameImpl("Hannes", 1, 1, 1);
        Memento<ConwayGame> gameMemento = game.saveToState();
        assertEquals(game, gameMemento.getState());
    }
}