package ConwayGameEngine;

public interface Originator<T> {
    Memento<T> saveToState();
    void restoreFromState(Memento<T> memento);
}
