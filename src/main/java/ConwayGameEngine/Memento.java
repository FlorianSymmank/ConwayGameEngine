package ConwayGameEngine;

import java.io.Serializable;

public interface Memento<T> extends Serializable {
    T getState();
    void setState(T state);
}
