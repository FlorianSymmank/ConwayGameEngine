package ConwayGameEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreHolderImplTest {

    ScoreHolderImpl scoreHolder;

    @BeforeEach
    void setUp() {
        scoreHolder = new ScoreHolderImpl();
    }

    @Test
    void addScore() throws ScoreNotFoundException {
        scoreHolder.addScore("Hannes", 1);
        assertEquals(1, scoreHolder.getScores().size());
        assertEquals(1, scoreHolder.getScore("Hannes").getScore());
        scoreHolder.addScore("Hannes", 1);
        assertEquals(2, scoreHolder.getScore("Hannes").getScore());

        assertThrows(ScoreNotFoundException.class, () -> scoreHolder.getScore("Hannes2"));
    }

    @Test
    void addScore2() throws ScoreNotFoundException {

        Score score = new ScoreImpl("Hannes", 1);

        scoreHolder.addScore(score);
        assertEquals(1, scoreHolder.getScores().size());
        assertEquals(1, scoreHolder.getScore("Hannes").getScore());
        scoreHolder.addScore(score);
        assertEquals(2, scoreHolder.getScore("Hannes").getScore());

        assertThrows(ScoreNotFoundException.class, () -> scoreHolder.getScore("Hannes2"));

    }

    @Test
    void getScore() throws ScoreNotFoundException {
        Score score = new ScoreImpl("Hannes", 1);
        scoreHolder.addScore(score);
        assertEquals(1, scoreHolder.getScore("Hannes").getScore());
    }

    @Test
    void getScores() {
        scoreHolder.addScore("Hannes", 1);
        scoreHolder.addScore("Dieter", 1);
        scoreHolder.addScore("Meier", 1);

        assertEquals(3, scoreHolder.getScores().size());

        assertEquals(1, scoreHolder.getScores().stream().filter(score -> score.getName().equals("Hannes")).count());
        assertEquals(1, scoreHolder.getScores().stream().filter(score -> score.getName().equals("Dieter")).count());
        assertEquals(1, scoreHolder.getScores().stream().filter(score -> score.getName().equals("Meier")).count());
    }

    @Test
    void clear() {
        scoreHolder.addScore("Hannes", 1);
        scoreHolder.addScore("Dieter", 1);
        scoreHolder.addScore("Meier", 1);

        scoreHolder.clear();

        assertEquals(0, scoreHolder.getScores().size());
    }

    @Test
    void removeScore() {

        scoreHolder.addScore("Hannes", 1);
        scoreHolder.addScore("Dieter", 1);
        scoreHolder.addScore("Meier", 1);

        scoreHolder.removeScore("Hannes");

        assertEquals(2, scoreHolder.getScores().size());
    }
}