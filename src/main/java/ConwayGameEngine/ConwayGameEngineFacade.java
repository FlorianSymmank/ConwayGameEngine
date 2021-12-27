package ConwayGameEngine;

import java.util.List;

/**
 * Main interaction point for the Conway Game Engine.
 */
public interface ConwayGameEngineFacade {

    /**
     * Returns the i-th game in the list of games (sorted by date).
     * @param i index of the game to return
     * @return
     */
    ConwayGame getGame(int i);

    /**
     * Returns the list of games (sorted by date).
     * @return List of games
     */
    List<ConwayGame> getAllGames();

    /**
     * Returns the list of scores (sorted by date).
     * @return List of scores
     */
    List<FinalScore> getAllScores();

    /**
     * Save the game to the default directory.
     * @param game game to save
     */
    void saveGame(ConwayGame game);

    /**
     * Save the score to the default directory.
     * @param score score to save
     */
    void saveScore(FinalScore score);

    /**
     * delete all games in the default directory.
     */
    void deleteGames();

    /**
     * delete all scores in the default directory.
     */
    void deleteScores();

    /**
     * set the default directory. All files(games/scores) will be saved in this directory.
     * @param defaultDirectory Path to the default directory
     */
    void setDefaultDirectory(String defaultDirectory);
}
