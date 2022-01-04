package ConwayGameEngine;

import java.io.IOException;
import java.util.List;

/**
 * Main interaction point for the Conway Game Engine.
 */
public interface ConwayGameEngineFacade {

    /**
     * Returns the i-th game in the list of games (sorted by date).
     *
     * @param i index of the game to return
     * @return i-th game
     */
    ConwayGame getGame(int i) throws IOException;

    /**
     * Returns the list of games (sorted by date).
     *
     * @return List of games
     */
    List<ConwayGame> getAllGames() throws IOException;

    /**
     * Returns the list of scores (sorted by date).
     *
     * @return List of scores
     */
    List<FinalScore> getAllScores() throws IOException;

    /**
     * Save the game to the default directory.
     *
     * @param game game to save
     */
    void saveGame(ConwayGame game) throws IOException;

    /**
     * Save the score to the default directory.
     *
     * @param score score to save
     */
    void saveScore(FinalScore score) throws IOException;

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
     *
     * @param defaultDirectory Path to the default directory
     */
    void setDefaultDirectory(String defaultDirectory);

    /**
     * Create a new game.
     *
     * @param playerName Name of the player
     * @param PlayerID   ID of the player
     * @param width      width of the game
     * @param height     height of the game
     * @return created game
     */
    ConwayGame createGame(String playerName, int PlayerID, int width, int height);
}
