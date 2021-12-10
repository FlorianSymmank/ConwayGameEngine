package ConwayGameEngine;

import java.io.Serializable;

public interface ConwayGame extends Originator<ConwayGame>, Serializable {
    Cell getCell(int row, int column);

    void reset();

    boolean tickGeneration();

    Score getGenerationScore();

    Score getDeathScore();

    Score getResurrectionScore();

    boolean isUnique();

    void setScoreChangedListener(ScoreChangedListener listener);
}
