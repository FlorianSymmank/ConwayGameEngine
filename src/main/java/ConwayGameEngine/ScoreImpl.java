package ConwayGameEngine;

public class ScoreImpl implements Score {
    private String name;
    private int score;

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
