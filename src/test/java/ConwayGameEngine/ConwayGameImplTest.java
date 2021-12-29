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

        final boolean[] generationScoreChangedCalled = {false};
        final boolean[] deathScoreChangedCalled = {false};
        final boolean[] resurrectionScoreChangedCalled = {false};
        final boolean[] clearCalled = {false};

        game = new ConwayGameImpl("Hannes", 1, 10, 10);
        game.getCell(1, 1).resurrect();
        game.getCell(2, 2).resurrect();
        game.getCell(3, 0).resurrect();
        game.getCell(3, 1).resurrect();
        game.getCell(3, 2).resurrect();

        game.setScoreChangedListener(new ScoreChangedListener() {
            @Override
            public void changed(Score score) {
                if (score.getName() == "RESURRECTION_SCORE") {
                    resurrectionScoreChangedCalled[0] = true;
                }
                if (score.getName() == "DEATH_SCORE") {
                    deathScoreChangedCalled[0] = true;
                }
                if (score.getName() == "GENERATION_SCORE") {
                    generationScoreChangedCalled[0] = true;
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

        assertTrue(generationScoreChangedCalled[0]);
        assertTrue(deathScoreChangedCalled[0]);
        assertTrue(resurrectionScoreChangedCalled[0]);

        game.reset();

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

        while (game.tickGeneration()){
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