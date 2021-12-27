package ConwayGameEngine;

import java.time.LocalDateTime;

/**
 * Memento class for the Score class.
 */
public class ScoreMemento implements Memento<FinalScore> {

    private FinalScore state;
    private LocalDateTime date;

    @Override
    public FinalScore getState() {
        return state;
    }

    @Override
    public void setState(FinalScore state) {
        this.date = LocalDateTime.now();
        this.state = state;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }
}
