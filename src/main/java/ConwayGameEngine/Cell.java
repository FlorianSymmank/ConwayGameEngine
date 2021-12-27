package ConwayGameEngine;

import java.io.Serializable;

/**
 * Represents a cell in the game of life. It has a state (dead or alive), and a position.
 */
public interface Cell extends Serializable {
    /**
     * Sets the state of the cell to dead.
     */
    void die();

    /**
     * Sets the state of the cell to alive.
     */
    void resurrect();

    /**
     * Sets the state of the cell to be the opposite of its current state.
     */
    void invert();

    /**
     * Returns the state of the cell.
     *
     * @return true if the cell is alive, false if it is dead.
     */
    boolean isAlive();

    /**
     * Returns the x position of the cell.
     *
     * @return the x position of the cell.
     */
    int getX();

    /**
     * Returns the y position of the cell.
     *
     * @return the y position of the cell.
     */
    int getY();
}
