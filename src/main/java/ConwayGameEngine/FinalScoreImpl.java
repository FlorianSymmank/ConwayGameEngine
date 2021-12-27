package ConwayGameEngine;

import java.time.LocalDate;

public class FinalScoreImpl implements FinalScore {

    private final int playerID;
    private final String playerName;
    private final LocalDate date;
    private final int generationScore;
    private final int deathScore;
    private final int resurrectionScore;

    public FinalScoreImpl(int playerID, String playerName, LocalDate date, int generationScore, int deathScore, int resurrectionScore) {
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
    public LocalDate getDate() {
        return date;
    }

    @Override
    public Memento<FinalScore> saveToState() {
        ScoreMemento memento = new ScoreMemento();
        memento.setState(this);
        return memento;
    }
}
