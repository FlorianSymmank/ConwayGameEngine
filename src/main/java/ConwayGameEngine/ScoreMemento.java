package ConwayGameEngine;

public class ScoreMemento implements Memento<FinalScore> {

    private FinalScore state;

    @Override
    public FinalScore getState() {
        return state;
    }

    @Override
    public void setState(FinalScore state) {
        this.state = state;
    }
}
