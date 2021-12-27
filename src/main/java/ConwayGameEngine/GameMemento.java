package ConwayGameEngine;

import java.time.LocalDateTime;

/**
 * Memento class for the Game class.
 */
public class GameMemento implements Memento<ConwayGame> {

    private LocalDateTime date;
    private ConwayGame state;

    @Override
    public ConwayGame getState() {
        return state;
    }

    @Override
    public void setState(ConwayGame state) {
        this.date = LocalDateTime.now();
        this.state = state;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }
}
