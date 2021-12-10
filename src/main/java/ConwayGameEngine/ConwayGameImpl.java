package ConwayGameEngine;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ConwayGameImpl implements ConwayGame {

    private final static String RESURRECTION_SCORE = "RESURRECTION_SCORE";
    private final static String DEATH_SCORE = "DEATH_SCORE";
    private final static String GENERATION_SCORE = "GENERATION_SCORE";
    private final ScoreHolder scoreHolder = new ScoreHolderImpl();

    private final int rows;
    private final int columns;
    private final Cell[][] grid;

    private boolean isUnique = true;
    private final Set<Integer> states = new HashSet<>();

    public ConwayGameImpl(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = new CellImpl(i, j);
            }
        }

        scoreHolder.addScore(DEATH_SCORE, 0);
        scoreHolder.addScore(RESURRECTION_SCORE, 0);
        scoreHolder.addScore(GENERATION_SCORE, 0);
    }

    @Override
    public boolean tickGeneration() {

        if (!isUnique)
            return false;

        LinkedList<Cell> aliveCells = new LinkedList<Cell>();
        LinkedList<Cell> deadCells = new LinkedList<Cell>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell c = getCell(i, j);
                int neighbours = countAliveNeighboursOf(i, j);

                // Any cell with three live neighbours is alive.
                if (neighbours == 3) {
                    aliveCells.add(c);
                    continue;
                }

                // Any live cell with two live neighbours survives.
                if (neighbours == 2 && c.isAlive()) {
                    aliveCells.add(c);
                    continue;
                }

                // All other live cells die in the next generation. Similarly, all other dead cells stay dead.
                deadCells.add(c);
            }
        }

        int deadToAliveCount = 0;
        int aliveToDeadCount = 0;

        for (Cell c : aliveCells) {
            if (!c.isAlive()) {
                c.resurrect();
                deadToAliveCount++;
            }
        }

        for (Cell c : deadCells) {
            if (c.isAlive()) {
                c.die();
                aliveToDeadCount++;
            }
        }

        score(aliveToDeadCount, deadToAliveCount);
        return true;
    }

    @Override
    public void reset() {
        isUnique = true;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].die();
            }
        }

        states.clear();

        scoreHolder.clear();
        scoreHolder.addScore(DEATH_SCORE, 0);
        scoreHolder.addScore(RESURRECTION_SCORE, 0);
        scoreHolder.addScore(GENERATION_SCORE, 0);
    }

    @Override
    public void setScoreChangedListener(ScoreChangedListener listener) {
        scoreHolder.setScoreChangedListener(listener);
    }

    @Override
    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    @Override
    public Score getGenerationScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(GENERATION_SCORE);
        } catch (ScoreNotFoundException ignored) {
            // cant happen
        }
        return score;
    }

    @Override
    public Score getDeathScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(GENERATION_SCORE);
        } catch (ScoreNotFoundException ignored) {
            // cant happen
        }
        return score;

    }

    @Override
    public Score getResurrectionScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(RESURRECTION_SCORE);
        } catch (ScoreNotFoundException e) {
            // cant happen
        }

        return score;
    }

    @Override
    public boolean isUnique() {
        return isUnique;
    }

    private boolean checkUnique() {

        if (!isUnique) return false;

        boolean unique = states.add(Arrays.deepHashCode(grid));
        isUnique = unique;
        return unique;
    }

    private void score(int aliveToDeadCount, int deadToAliveCount) {
        if (checkUnique()) {
            scoreHolder.addScore(DEATH_SCORE, aliveToDeadCount);
            scoreHolder.addScore(RESURRECTION_SCORE, deadToAliveCount);
            scoreHolder.addScore(GENERATION_SCORE, 1);
        }
    }

    private int countAliveNeighboursOf(int row, int column) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++)
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) continue;
                count += isCellAlive(i, j) ? 1 : 0;
            }

        return count;
    }

    private boolean isCellAlive(int row, int column) {
        try {
            return getCell(row, column).isAlive();
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }

    @Override
    public Memento<ConwayGame> saveToState() {
        GameMemento memento = new GameMemento();
        memento.setState(this);
        return memento;
    }
}
