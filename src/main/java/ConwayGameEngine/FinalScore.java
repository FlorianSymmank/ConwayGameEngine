package ConwayGameEngine;

import java.io.Serializable;
import java.time.LocalDate;

public interface FinalScore extends Originator<FinalScore>, Serializable {
    int getResurrectionScore();

    int getDeathScore();

    int getGenerationScore();

    int getPlayerID();

    String getPlayerName();

    LocalDate getDate();
}
