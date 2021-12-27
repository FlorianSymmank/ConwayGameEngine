package ConwayGameEngine;

/**
 * Gets thrown when a score is not found in the score table.
 */
public class ScoreNotFoundException extends Exception {
    /**
     * Creates a new exception with the specified scorename as message.
     * @param scorename
     */
    public ScoreNotFoundException(String scorename) {
        super("Score " + scorename + " not found");
    }
}
