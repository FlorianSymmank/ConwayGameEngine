package ConwayGameEngine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameMementoTest {

    @org.junit.jupiter.api.Test
    void getState() {
        ConwayGame game = new ConwayGameImpl(1,1);
        GameMemento gameMemento = new GameMemento();
        assertEquals(game, gameMemento.getState());
    }

    @Test
    void setState() {
        ConwayGame game = new ConwayGameImpl(1,1);
        GameMemento gameMemento = new GameMemento();
        gameMemento.setState(game);
        assertEquals(game, gameMemento.getState());
    }
}