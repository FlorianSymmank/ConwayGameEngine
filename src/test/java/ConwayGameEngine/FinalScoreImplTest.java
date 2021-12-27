package ConwayGameEngine;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class FinalScoreImplTest {
    @Test
    void saveToState() {
        FinalScoreImpl finalScore = new FinalScoreImpl(1, "test", LocalDateTime.now(), 1, 1, 1);
        Memento<FinalScore> state = finalScore.saveToState();
        assertNotNull(state);

        assertEquals(finalScore.getDeathScore(), state.getState().getDeathScore());
        assertEquals(finalScore.getGenerationScore(), state.getState().getGenerationScore());
        assertEquals(finalScore.getResurrectionScore(), state.getState().getResurrectionScore());
        assertEquals(finalScore.getPlayerName(), state.getState().getPlayerName());
        assertEquals(finalScore.getDate(), state.getState().getDate());
        assertEquals(finalScore.getPlayerID(), state.getState().getPlayerID());
    }
}