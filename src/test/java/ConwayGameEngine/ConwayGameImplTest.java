package ConwayGameEngine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConwayGameImplTest {

    ConwayGame game;

    @BeforeEach
    void setUp() {
        game = new ConwayGameImpl("Hannes", 1, 10, 10);
        game.getCell(1, 1).resurrect();
        game.getCell(2, 2).resurrect();
        game.getCell(3, 0).resurrect();
        game.getCell(3, 1).resurrect();
        game.getCell(3, 2).resurrect();

//        System.out.println("Initial state:");
//        printBoard(game);
//
//        System.out.println("Generation 1:");
        game.tickGeneration();
//        printBoard(game);
//
//        System.out.println("Generation 2:");
        game.tickGeneration();
//        printBoard(game);
//
//        System.out.println("Generation 3:");
        game.tickGeneration();
//        printBoard(game);

    }

    @Test
    void tickGeneration() {
        assertTrue(game.isUnique());

        while (game.tickGeneration()) assertTrue(game.isUnique());

        assertFalse(game.isUnique());
    }

    @Test
    void reset() {
        game.reset();
        assertEquals(0, game.getGenerationScore().getScore());
        assertEquals(0, game.getDeathScore().getScore());
        assertEquals(0, game.getResurrectionScore().getScore());
        assertFalse(game.getCell(0, 0).isAlive());
        assertTrue(game.isUnique());
    }

    @Test
    void getCell() {
        assertThrows(IndexOutOfBoundsException.class, () -> game.getCell(-1, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> game.getCell(10, 0));
        assertThrows(IndexOutOfBoundsException.class, () -> game.getCell(0, 10));

        assertFalse(game.getCell(0, 0).isAlive());
    }

    @Test
    void getGenerationScore() {
        assertTrue(game.isUnique());
        assertEquals(3, game.getGenerationScore().getScore());
    }

    @Test
    void getDeathScore() {
        assertTrue(game.isUnique());
        assertEquals(6, game.getDeathScore().getScore());
    }

    @Test
    void getResurrectionScore() {
        assertTrue(game.isUnique());
        assertEquals(6, game.getResurrectionScore().getScore());
    }

    @Test
    void isUnique() {
        assertTrue(game.isUnique());

        while (game.tickGeneration()) assertTrue(game.isUnique());

        assertFalse(game.isUnique());
    }

    @Test
    void saveToState() {
        assertNotNull(game.saveToState().getState());
    }


    @Test
    void changedListenerCalled() {

        final int[] deathScores = new int[4];
        final int[] deathScoreIndex = {0};

        final int[] genScores = new int[4];
        final int[] genScoreIndex = {0};

        final int[] resScores = new int[4];
        final int[] resScoreIndex = {0};

        final boolean[] clearCalled = {false};

        game = new ConwayGameImpl("Hannes", 1, 10, 10);
        game.getCell(1, 1).resurrect();
        game.getCell(2, 2).resurrect();
        game.getCell(3, 0).resurrect();
        game.getCell(3, 1).resurrect();
        game.getCell(3, 2).resurrect();

        final int[] deathScoreChangedCalledCount = {0};

        game.setScoreChangedListener(new ScoreChangedListener() {
            @Override
            public void changed(Score score) {

                if (score.getName().equals(ConwayGame.Scores.DEATH_SCORE.toString())) {
                    deathScores[deathScoreIndex[0]] += score.getScore();
                    deathScoreIndex[0]++;
                }

                if (score.getName().equals(ConwayGame.Scores.GENERATION_SCORE.toString())) {
                    genScores[genScoreIndex[0]] += score.getScore();
                    genScoreIndex[0]++;
                }

                if (score.getName().equals(ConwayGame.Scores.RESURRECTION_SCORE.toString())) {
                    resScores[resScoreIndex[0]] += score.getScore();
                    resScoreIndex[0]++;
                }


            }

            @Override
            public void cleared() {
                clearCalled[0] = true;
            }

            @Override
            public void removed(Score score) {

            }
        });

        game.tickGeneration();
        assertEquals(2, deathScores[0]);
        assertEquals(2, resScores[0]);
        assertEquals(1, genScores[0]);

        game.tickGeneration();
        System.out.println();
        assertEquals(4, deathScores[1]);
        assertEquals(4, resScores[1]);
        assertEquals(2, genScores[1]);

        game.tickGeneration();
        assertEquals(6, deathScores[2]);
        assertEquals(6, resScores[2]);
        assertEquals(3, genScores[2]);

        game.reset();
        assertEquals(0, deathScores[3]);
        assertEquals(0, resScores[3]);
        assertEquals(0, genScores[3]);

        assertTrue(clearCalled[0]);
    }

    @Test
    void uniqueListenerCalled() {
        final boolean[] uniqueChangedCalled = {false};

        game = new ConwayGameImpl("Hannes", 1, 10, 10);
        game.getCell(1, 1).resurrect();
        game.getCell(2, 2).resurrect();
        game.getCell(3, 0).resurrect();
        game.getCell(3, 1).resurrect();
        game.getCell(3, 2).resurrect();

        game.setUniqueStateChangedListener(new UniqueStateChangedListener() {
            @Override
            public void changed(boolean newState) {
                uniqueChangedCalled[0] = true;
            }
        });

        while (game.tickGeneration()) {
            assertTrue(game.isUnique());
            assertFalse(uniqueChangedCalled[0]);
        }

        assertTrue(uniqueChangedCalled[0]);
    }

    // quick visual test
    void printBoard(ConwayGame game) {
        for (int y = 0; y < 10; y++) {
            if (y == 0) System.out.println("  0 1 2 3 4 5 6 7 8 9");
            for (int x = 0; x < 10; x++) {
                if (x == 0) System.out.print(y + " ");
                System.out.print(game.getCell(y, x).isAlive() ? "X " : "  ");
            }
            System.out.println();
        }
    }
}