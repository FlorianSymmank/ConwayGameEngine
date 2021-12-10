package ConwayGameEngine;

public interface Memento<T> {
    T getState();
    void setState(T state);
}
