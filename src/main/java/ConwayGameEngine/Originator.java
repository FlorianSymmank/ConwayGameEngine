package ConwayGameEngine;

public interface Originator<T> {
    Memento<T> saveToState();
    default T restoreFromState(Memento<T> memento) {
        return memento.getState();
    }
}
