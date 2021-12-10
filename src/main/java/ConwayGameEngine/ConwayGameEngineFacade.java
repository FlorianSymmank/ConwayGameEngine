package ConwayGameEngine;

import java.util.List;

public interface ConwayGameEngineFacade {

    ConwayGame getGame(int i);

    List<ConwayGame> getAllGames();

    List<Score> getAllScores();

    void saveGame(ConwayGame game);

    void saveScore(Score score);

    void deleteGames();

    void deleteScores();

    void setDefaultDirectory(String defaultDirectory);
}
