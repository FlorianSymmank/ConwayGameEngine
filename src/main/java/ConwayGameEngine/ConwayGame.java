package ConwayGameEngine;

import java.io.Serializable;

/**
 * Represents the game of life. It's a 2D Board of cells, which interact with each other to generate a new generation.
 */
public interface ConwayGame extends Originator<ConwayGame>, Serializable {
    /**
     * Returns the cell at the given coordinates.
     *
     * @param row    row (y) of the wanted cell
     * @param column column (x) of the wanted cell
     * @return the cell at the given coordinates
     * @throws IndexOutOfBoundsException if the row or column is out of bounds.
     */
    Cell getCell(int row, int column);

    /**
     * Resets the game to the initial state.
     * Includes resetting the scores, the unique state, resets cells to dead and clears the state history.
     *
     * @return the number of rows of the game.
     */
    void reset();

    /**
     * Generates a new generation of the game and updates the scores.
     * If the game is not unique after the new Generation, the state can't be changed any further. Can be reset with {@link #reset()}.
     *
     * @return false if the game is not unique, true otherwise.
     */
    boolean tickGeneration();

    /**
     * Returns the Generation of the game.
     *
     * @return the Generation of the game.
     */
    Score getGenerationScore();

    /**
     * Returns the score of the game.
     *
     * @return the score of the game.
     */
    Score getDeathScore();

    /**
     * Returns the score of the game.
     *
     * @return the score of the game.
     */
    Score getResurrectionScore();

    /**
     * Returns if the game is unique.
     *
     * @return true if the game is unique, false otherwise.
     */
    boolean isUnique();

    /**
     * Sets a listener to be notified when the game scores are changed.
     *
     * @param listener ScoreChangedListener to notify.
     */
    void setScoreChangedListener(ScoreChangedListener listener);

    /**
     * Sets a listener to be notified when the unique state is changed.
     *
     * @param listener UniqueStateChangedListener to notify.
     */
    void setUniqueStateChangedListener(UniqueStateChangedListener listener);
}
