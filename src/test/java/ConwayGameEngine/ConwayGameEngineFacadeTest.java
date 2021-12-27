package ConwayGameEngine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConwayGameEngineFacadeTest {

    ConwayGameEngineFacade facade;

    @BeforeEach
    void setUp() {
        facade = new ConwayGameEngineFacadeImpl();
        facade.setDefaultDirectory("src/test/resources/");
    }

    @AfterEach
    void tearDown() {
        facade.deleteGames();
        facade.deleteScores();
    }


    @Test
    void getGame() {
        ConwayGame game = new ConwayGameImpl(1, 2);
        game.getCell(0, 0).invert();
        facade.saveGame(game);

        assertEquals(game.getCell(0, 0).isAlive(), facade.getGame(0).getCell(0, 0).isAlive());
        assertEquals(game.getCell(0, 1).isAlive(), facade.getGame(0).getCell(0, 1).isAlive());

    }

    @Test
    void getAllGames() {
        facade.saveGame(new ConwayGameImpl(1, 1));
        facade.saveGame(new ConwayGameImpl(1, 1));

        List<ConwayGame> games = facade.getAllGames();
        assertEquals(2, games.size());
    }

    @Test
    void getAllScores() {

        facade.saveScore(new FinalScoreImpl(1, "Hans", LocalDate.now(), 1, 1, 1));
        facade.saveScore(new FinalScoreImpl(2, "Meier", LocalDate.now(), 1, 1, 1));

        List<FinalScore> scores = facade.getAllScores();
        assertEquals(2, scores.size());

        boolean foundHans = false;
        boolean foundMeier = false;

        for (FinalScore score : scores) {
            if (score.getPlayerName().equals("Hans")) {
                foundHans = true;
            }
            if (score.getPlayerName().equals("Meier")) {
                foundMeier = true;
            }
        }

        assertTrue(foundHans);
        assertTrue(foundMeier);

    }

    @Test
    void saveGame() {
        ConwayGame game = new ConwayGameImpl(1, 1);
        facade.saveGame(game);
    }

    @Test
    void saveScore() {
        FinalScore score = new FinalScoreImpl(1, "Hans", LocalDate.now(), 1, 1, 1);
        facade.saveScore(score);
    }

    @Test
    void deleteGames() {
        facade.deleteGames();
    }

    @Test
    void deleteScores() {
        facade.deleteScores();
    }
}
