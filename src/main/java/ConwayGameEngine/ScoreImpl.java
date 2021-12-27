package ConwayGameEngine;

/**
 * ScoreImpl class
 */
public class ScoreImpl implements Score {
    private String name;
    private int score;

    /**
     * Creates a new ScoreImpl object
     * @param name name of the score
     * @param score score
     */
    public ScoreImpl(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public String getName() {
        return name;
    }
}
