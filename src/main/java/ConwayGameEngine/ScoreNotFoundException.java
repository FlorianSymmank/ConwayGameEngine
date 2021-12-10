package ConwayGameEngine;

public class ScoreNotFoundException extends Exception {
    public ScoreNotFoundException(String scorename) {
        super("Score " + scorename + " not found");
    }
}
