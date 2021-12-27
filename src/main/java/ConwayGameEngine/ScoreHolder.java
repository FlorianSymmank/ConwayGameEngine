package ConwayGameEngine;

import java.io.Serializable;
import java.util.List;

/**
 * This class is used to store the scores of the players.
 */
public interface ScoreHolder extends Serializable {
    /**
     * This method is used to add a score to the list of scores.
     *
     * @param score The score to be added.
     */
    void addScore(Score score);

    /**
     * This method is used to add a score to the list of scores.
     *
     * @param name  The name/title of the score.
     * @param score The score to be added.
     */
    void addScore(String name, int score);

    /**
     * Gets the score  with the given name.
     *
     * @param name The name of the score.
     * @return The score with the given name.
     * @throws ScoreNotFoundException If the score with the given name is not found.
     */
    Score getScore(String name) throws ScoreNotFoundException;

    /**
     * Gets the list of scores.
     *
     * @return The list of scores.
     */
    List<Score> getScores();

    /**
     * Clears the list of scores.
     */
    void clear();

    /**
     * Removes the score with the given name.
     *
     * @param name The name of the score.
     */
    void removeScore(String name);

    /**
     * Sets the listener to be notified when the list of scores are changed/cleared/removed.
     *
     * @param listener The listener to be set.
     */
    void setScoreChangedListener(ScoreChangedListener listener);
}
