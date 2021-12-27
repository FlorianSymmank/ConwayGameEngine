package ConwayGameEngine;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This interface is used to hold the final score of the game.
 */
public interface FinalScore extends Originator<FinalScore>, Serializable {
    /**
     * Gets the final resurrection score.
     *
     * @return the final resurrection score.
     */
    int getResurrectionScore();

    /**
     * Gets the final death score.
     *
     * @return the final death score.
     */
    int getDeathScore();

    /**
     * Gets the final generation score.
     *
     * @return the final generation score.
     */
    int getGenerationScore();

    /**
     * Gets the players ID.
     * @return the players ID.
     */
    int getPlayerID();

    /**
     * Gets the players name.
     * @return the players name.
     */
    String getPlayerName();

    /**
     * Gets the date the game was played.
     * @return the date the game was played.
     */
    LocalDateTime getDate();
}
