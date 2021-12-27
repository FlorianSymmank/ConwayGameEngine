package ConwayGameEngine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameMementoTest {

    @Test
    void getState() {
        ConwayGame game = new ConwayGameImpl(1, 1);
        Memento<ConwayGame> gameMemento = game.saveToState();
        assertEquals(game, gameMemento.getState());
    }
}