package ConwayGameEngine;

public interface ConwayGame extends Originator<ConwayGame> {
    Cell getCell(int row, int column);

    void reset();

    boolean tickGeneration();

    Score getGenerationScore();

    Score getDeathScore();

    Score getResurrectionScore();

    boolean isUnique();

    void setScoreChangedListener(ScoreChangedListener listener);
}
