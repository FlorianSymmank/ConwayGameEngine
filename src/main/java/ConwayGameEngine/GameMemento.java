package ConwayGameEngine;

public class GameMemento implements Memento<ConwayGame> {

    private ConwayGame state;

    @Override
    public ConwayGame getState() {
        return state;
    }

    @Override
    public void setState(ConwayGame state) {
        this.state = state;
    }
}
