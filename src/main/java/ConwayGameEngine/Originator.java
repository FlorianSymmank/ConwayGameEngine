package ConwayGameEngine;

/**
 * Originator class.
 *
 * @param <T>
 */
public interface Originator<T> {
    /**
     * Save the current state.
     *
     * @return state as memento
     */
    Memento<T> saveToState();

    /**
     * Restore the state from memento.
     *
     * @param memento memento from which to restore state
     */
    default T restoreFromState(Memento<T> memento) {
        return memento.getState();
    }
}
