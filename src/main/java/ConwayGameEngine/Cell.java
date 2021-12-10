package ConwayGameEngine;

import java.io.Serializable;

public interface Cell extends Serializable {
    void die();

    void resurrect();

    void invert();

    boolean isAlive();

    int getX();

    int getY();
}
