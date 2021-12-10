package ConwayGameEngine;

import java.util.List;

public interface ScoreHolder {
    void addScore(Score score);

    void addScore(String name, int score);

    Score getScore(String name) throws ScoreNotFoundException;

    List<Score> getScores();

    void clear();

    void removeScore(String name);

    void setScoreChangedListener(ScoreChangedListener listener);
}
