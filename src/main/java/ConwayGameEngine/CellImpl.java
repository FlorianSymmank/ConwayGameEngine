package ConwayGameEngine;

public class CellImpl implements Cell {
    private final int x;
    private final int y;
    private boolean alive = false;

    public CellImpl(int x, int y) {
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
}
