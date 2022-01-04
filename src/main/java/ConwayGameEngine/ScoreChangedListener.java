package ConwayGameEngine;

/**
 * Interface for objects that want to be notified when the score changes.
 */
public interface ScoreChangedListener {
    /**
     * Called when the score changes.
     * @param score the new changed score
     */
    void scoreChanged(Score score);

    /**
     * Called when a score is cleared.
     */
    void scoreCleared();

    /**
     * Called when a score is removed.
     * @param score the score that was removed
     */
    void scoreRemoved(Score score);
}
