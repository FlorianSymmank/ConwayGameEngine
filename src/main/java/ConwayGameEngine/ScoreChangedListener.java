package ConwayGameEngine;

public interface ScoreChangedListener {
    void changed(Score score);
    void cleared();
    void removed(Score score);
}
