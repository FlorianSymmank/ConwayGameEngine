package ConwayGameEngine;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ScoreHolderImpl
 */
public class ScoreHolderImpl implements ScoreHolder {
    private ScoreChangedListener listener;
    private final Map<String, Integer> scores = new HashMap<>();

    @Override
    public void addScore(Score score) {
        addScore(score.getName(), score.getScore());
    }

    @Override
    public void addScore(String name, int score) {
        scores.put(name, scores.getOrDefault(name, 0) + score);
        if (listener != null) {
            try {
                listener.changed(getScore(name));
            } catch (ScoreNotFoundException ignored) {
                // we just added this score, so it should be there
            }
        }
    }

    @Override
    public Score getScore(String name) throws ScoreNotFoundException {

        if (scores.containsKey(name))
            return new ScoreImpl(name, scores.get(name));

        throw new ScoreNotFoundException(name);
    }

    @Override
    public List<Score> getScores() {
        return scores.entrySet().stream()
                .map(e -> new ScoreImpl(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public void clear() {
        scores.clear();
        if (listener != null) listener.cleared();
    }

    @Override
    public void removeScore(String name) {
        if (scores.containsKey(name)) {
            int score = scores.get(name);
            if (listener != null) listener.removed(new ScoreImpl(name, score));
        }
        scores.remove(name);
    }

    @Override
    public void setScoreChangedListener(ScoreChangedListener listener) {
        this.listener = listener;
    }
}
