package ConwayGameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConwayGameEngineFacadeImpl implements ConwayGameEngineFacade {

    private String defaultDirectory = "";
    private static final String GAME_FILE_EXTENSION = ".cg";
    private static final String SCORE_FILE_EXTENSION = ".cgs";

    @Override
    public ConwayGame getGame(int i) {
        return getAllGames().get(i);
    }

    @Override
    public List<ConwayGame> getAllGames() {
        List<ConwayGame> games = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(GAME_FILE_EXTENSION)) {
                try {
                    games.add(readConwayGame(file.getAbsolutePath()));
                } catch (IOException | ClassNotFoundException e) {

                }
            }
        }
        return games;
    }

    @Override
    public void saveGame(ConwayGame game) {
        try {
            Memento<ConwayGame> mem = game.saveToState();
            String filename = defaultDirectory + "game" + System.currentTimeMillis() + GAME_FILE_EXTENSION;

            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(mem);

            fout.close();
            oos.close();

        } catch (IOException e) {

        }
    }

    @Override
    public void deleteGames() {
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(GAME_FILE_EXTENSION)) {
                file.delete();
            }
        }
    }

    @Override
    public List<FinalScore> getAllScores() {
        List<FinalScore> scores = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(SCORE_FILE_EXTENSION)) {
                try {
                    scores.add(readConwayGameScore(file.getAbsolutePath()));
                } catch (IOException | ClassNotFoundException e) {

                }
            }
        }
        return scores;
    }

    @Override
    public void saveScore(FinalScore score) {
        try {
            Memento<FinalScore> mem = score.saveToState();
            String filename = defaultDirectory + "score" + System.currentTimeMillis() + SCORE_FILE_EXTENSION;

            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);

            oos.writeObject(mem);

            fout.close();
            oos.close();

        } catch (IOException e) {

        }
    }

    @Override
    public void deleteScores() {
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(SCORE_FILE_EXTENSION)) {
                file.delete();
            }
        }
    }

    @Override
    public void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    private ConwayGame readConwayGame(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Memento<ConwayGame> mem = (Memento<ConwayGame>) ois.readObject();

        fin.close();
        ois.close();

        return mem.getState();
    }

    private FinalScore readConwayGameScore(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Memento<FinalScore> mem = (Memento<FinalScore>) ois.readObject();

        fin.close();
        ois.close();

        return mem.getState();
    }
}
