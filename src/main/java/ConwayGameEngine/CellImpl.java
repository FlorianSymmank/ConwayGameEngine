package ConwayGameEngine;

/**
 * CellImpl class
 */
public class CellImpl implements Cell {
    private final int x;
    private final int y;
    private boolean alive = false;

    /**
     * Creates new Cell with given coordinates
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public CellImpl(int y, int x) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void die() {
        alive = false;
    }

    @Override
    public void resurrect() {
        alive = true;
    }

    @Override
    public void invert() {
        alive = !alive;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        CellImpl other = (CellImpl) obj;
        if (x != other.x) return false;

        if (y != other.y) return false;

        return alive == other.alive;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + x;
        result = 31 * result + y;
        result = 31 * result + (alive ? 1 : 0);
        return result;
    }
}
