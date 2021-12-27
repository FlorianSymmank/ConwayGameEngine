package ConwayGameEngine;

import java.time.LocalDateTime;

/**
 * Final scores are used to represent the final result of a game.
 */
public class FinalScoreImpl implements FinalScore {

    private final int playerID;
    private final String playerName;
    private final LocalDateTime date;
    private final int generationScore;
    private final int deathScore;
    private final int resurrectionScore;

    /**
     * Constructor for FinalScoreImpl.
     *
     * @param playerID the player's ID
     * @param playerName the player's name
     * @param date the date of the game
     * @param generationScore the score for the generation
     * @param deathScore the score for the death
     * @param resurrectionScore the score for the resurrection
     */
    public FinalScoreImpl(int playerID, String playerName, LocalDateTime date, int generationScore, int deathScore, int resurrectionScore) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.date = date;
        this.generationScore = generationScore;
        this.deathScore = deathScore;
        this.resurrectionScore = resurrectionScore;
    }

    @Override
    public int getResurrectionScore() {
        return resurrectionScore;
    }

    @Override
    public int getDeathScore() {
        return deathScore;
    }

    @Override
    public int getGenerationScore() {
        return generationScore;
    }

    @Override
    public int getPlayerID() {
        return playerID;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public LocalDateTime getDate() {
        return date;
    }

    @Override
    public Memento<FinalScore> saveToState() {
        ScoreMemento memento = new ScoreMemento();
        memento.setState(this);
        return memento;
    }
}
