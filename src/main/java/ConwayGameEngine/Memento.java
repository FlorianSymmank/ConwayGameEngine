package ConwayGameEngine;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Memento class for the game.
 * @param <T>
 */
public interface Memento<T> extends Serializable {
    /**
     * gets the state of the memento
     * @return
     */
    T getState();

    /**
     * sets the state of the memento
     * @param state
     */
    void setState(T state);

    /**
     * gets the last change date of the memento
     * @return
     */
    LocalDateTime getDate();
}
