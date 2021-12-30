package ConwayGameEngine;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * ConwayGameImpl
 */
public class ConwayGameImpl implements ConwayGame {

    private final ScoreHolder scoreHolder = new ScoreHolderImpl();

    private final int rows;
    private final int columns;
    private final Cell[][] grid;
    private final String name;
    private final int playerID;

    private boolean isUnique = true;
    private final Set<Integer> states = new HashSet<>();

    private UniqueStateChangedListener listener;

    /**
     * Creates a new instance of ConwayGameImpl with given size. All cells are dead.
     *
     * @param rows    number of rows
     * @param columns number of columns
     */
    public ConwayGameImpl(String name, int playerID, int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.name = name;
        this.playerID = playerID;

        grid = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                grid[row][col] = new CellImpl(row, col);
            }
        }

        scoreHolder.addScore(Scores.DEATH_SCORE.toString(), 0);
        scoreHolder.addScore(Scores.RESURRECTION_SCORE.toString(), 0);
        scoreHolder.addScore(Scores.GENERATION_SCORE.toString(), 0);
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
        return isUnique();
    }

    @Override
    public void reset() {
        setUnique(true);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j].die();
            }
        }

        states.clear();

        scoreHolder.clear();
        scoreHolder.addScore(Scores.DEATH_SCORE.toString(), 0);
        scoreHolder.addScore(Scores.RESURRECTION_SCORE.toString(), 0);
        scoreHolder.addScore(Scores.GENERATION_SCORE.toString(), 0);
    }


    @Override
    public Cell getCell(int row, int column) {
        return grid[row][column];
    }

    @Override
    public Score getGenerationScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(Scores.GENERATION_SCORE.toString());
        } catch (ScoreNotFoundException ignored) {
            // cant happen
        }
        return score;
    }

    @Override
    public Score getDeathScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(Scores.DEATH_SCORE.toString());
        } catch (ScoreNotFoundException ignored) {
            // cant happen
        }
        return score;

    }

    @Override
    public Score getResurrectionScore() {
        Score score = null;
        try {
            score = scoreHolder.getScore(Scores.RESURRECTION_SCORE.toString());
        } catch (ScoreNotFoundException e) {
            // cant happen
        }

        return score;
    }

    @Override
    public boolean isUnique() {
        return isUnique;
    }


    @Override
    public void setScoreChangedListener(ScoreChangedListener listener) {
        scoreHolder.setScoreChangedListener(listener);
    }

    @Override
    public void setUniqueStateChangedListener(UniqueStateChangedListener listener) {
        this.listener = listener;
    }

    @Override
    public FinalScore getFinalScore() {
        return new FinalScoreImpl(playerID, name, LocalDateTime.now(), getFinalScore().getGenerationScore(), getFinalScore().getDeathScore(), getFinalScore().getResurrectionScore());
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getColumns() {
        return columns;
    }

    @Override
    public Memento<ConwayGame> saveToState() {
        GameMemento memento = new GameMemento();
        memento.setState(this);
        return memento;
    }

    /**
     * Checks if the game is unique.
     *
     * @return true if the game is unique, false otherwise.
     */
    private boolean checkUnique() {
        if (!isUnique) return false;

        // get hash of the grid(cell array) and try to add it to the states set.
        // if the hash is already in the set, the game is not unique.
        return states.add(Arrays.deepHashCode(grid));
    }

    /**
     * Scores the game and checks if the game is unique.
     *
     * @param aliveToDeadCount amount of cells that died in the last generation.
     * @param deadToAliveCount amount of cells that were resurrected in the last generation.
     */
    private void score(int aliveToDeadCount, int deadToAliveCount) {
        if (checkUnique()) {
            scoreHolder.addScore(Scores.DEATH_SCORE.toString(), aliveToDeadCount);
            scoreHolder.addScore(Scores.RESURRECTION_SCORE.toString(), deadToAliveCount);
            scoreHolder.addScore(Scores.GENERATION_SCORE.toString(), 1);
        } else {
            setUnique(false);
        }
    }

    /**
     * Sets the unique state of the game. Notifies the listener if the state has changed.
     *
     * @param unique the new unique state.
     */
    private void setUnique(boolean unique) {
        if (unique != isUnique) {
            if (listener != null) listener.changed(isUnique);
            isUnique = unique;
        }
    }

    /**
     * Counts the amount of neighbors of a cells which are alive.
     *
     * @param row    the row of the cell.
     * @param column the column of the cell.
     * @return the amount of neighbors of the cell which are alive.
     */
    private int countAliveNeighboursOf(int row, int column) {
        int count = 0;

        for (int i = row - 1; i <= row + 1; i++)
            for (int j = column - 1; j <= column + 1; j++) {
                if (i == row && j == column) continue;
                count += isCellAlive(i, j) ? 1 : 0;
            }

        return count;
    }

    /**
     * Checks if a cell is alive.
     *
     * @param row    the row of the cell.
     * @param column the column of the cell.
     * @return true if the cell is alive, false otherwise.
     */
    private boolean isCellAlive(int row, int column) {
        try {
            return getCell(row, column).isAlive();
        } catch (ArrayIndexOutOfBoundsException ex) {
            return false;
        }
    }
}
