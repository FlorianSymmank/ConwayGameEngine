package ConwayGameEngine;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConwayGameEngineFacadeImplTest {

    ConwayGameEngineFacade facade;

    @BeforeEach
    void setUp() {
        facade = new ConwayGameEngineFacadeImpl("src/test/resources/");
    }

    @AfterEach
    void tearDown() {
        facade.deleteGames();
        facade.deleteScores();
    }

    @Test
    void getGame() throws IOException {
        ConwayGame game = new ConwayGameImpl("Hannes", 1, 1, 2);
        game.getCell(0, 0).invert();
        facade.saveGame(game);

        assertEquals(game.getCell(0, 0).isAlive(), facade.getGame(0).getCell(0, 0).isAlive());
        assertEquals(game.getCell(0, 1).isAlive(), facade.getGame(0).getCell(0, 1).isAlive());
    }

    @Test
    void getAllGames() throws IOException {
        facade.saveGame(new ConwayGameImpl("Hannes", 1, 1, 1));
        facade.saveGame(new ConwayGameImpl("Hannes", 1, 1, 1));

        List<ConwayGame> games = facade.getAllGames();
        assertEquals(2, games.size());
    }

    @Test
    void getAllScores() throws IOException {

        facade.saveScore(new FinalScoreImpl(1, "Hans", LocalDateTime.now(), 1, 1, 1));
        facade.saveScore(new FinalScoreImpl(2, "Meier", LocalDateTime.now(), 1, 1, 1));

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
    void saveGame() throws IOException {
        ConwayGame game = new ConwayGameImpl("Hannes", 1, 5, 5);
        game.setUniqueStateChangedListener(new UniqueStateChangedListener() {
            @Override
            public void uniqueChanged(boolean newState) {

            }
        });

        game.getCell(1, 1).resurrect();
        game.getCell(2, 2).resurrect();
        game.getCell(3, 0).resurrect();
        game.getCell(3, 1).resurrect();
        game.getCell(3, 2).resurrect();

        game.setScoreChangedListener(new ScoreChangedListener() {
            @Override
            public void scoreChanged(Score score) {

            }

            @Override
            public void scoreCleared() {

            }

            @Override
            public void scoreRemoved(Score score) {

            }
        });
        facade.saveGame(game);
    }

    @Test
    void saveScore() throws IOException {
        FinalScore score = new FinalScoreImpl(1, "Hans", LocalDateTime.now(), 1, 1, 1);
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
