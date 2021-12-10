package ConwayGameEngine;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConwayGameEngineFacadeTest {

    @Test
    void getGameMemento() {
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        assertEquals(new ConwayGameImpl(1, 1), facade.getGame(1));
    }

    @Test
    void getAllGames() {
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        List<ConwayGame> games = facade.getAllGames();
        assertEquals(1, games.size());
        // TODO: add more tests
    }

    @Test
    void getAllScores() {
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        List<Score> scores = facade.getAllScores();
        assertEquals(1, scores.size());
        // TODO: add more tests
    }

    @Test
    void saveGame() {
        ConwayGame game = new ConwayGameImpl(1, 1);
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        facade.saveGame(game);
    }

    @Test
    void saveScore() {
        Score score = new ScoreImpl("test", 1);
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        facade.saveScore(score);
    }

    @Test
    void deleteGames() {
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        facade.deleteGames();
    }

    @Test
    void deleteScores() {
        ConwayGameEngineFacade facade = new ConwayGameEngineFacadeImpl();
        facade.deleteScores();
    }
}
