package ConwayGameEngine;

import java.util.List;

public interface ConwayGameEngineFacade {

    ConwayGame getGame(int i);

    List<ConwayGame> getAllGames();

    List<FinalScore> getAllScores();

    void saveGame(ConwayGame game);

    void saveScore(FinalScore score);

    void deleteGames();

    void deleteScores();

    void setDefaultDirectory(String defaultDirectory);
}
