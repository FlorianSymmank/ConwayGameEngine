package ConwayGameEngine;

/**
 * Interface for objects that want to be notified when the unique state is changed.
 */
public interface UniqueStateChangedListener {
    /**
     * Called when the unique state is changed.
     *
     * @param newState The new unique state.
     */
    void uniqueChanged(boolean newState);
}
