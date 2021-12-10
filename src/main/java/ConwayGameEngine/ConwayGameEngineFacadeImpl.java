package ConwayGameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConwayGameEngineFacadeImpl implements ConwayGameEngineFacade {

    private String defaltDirectory = "";
    private static final String GAME_FILE_EXTENSION = ".cg";
    private static final String SCORE_FILE_EXTENSION = ".cgs";

    private ConwayGame readConwayGame(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Memento<ConwayGame> mem = (Memento<ConwayGame>) ois.readObject();

        return mem.getState();
    }

    @Override
    public ConwayGame getGame(int i) {
        return getAllGames().get(i);
    }

    @Override
    public List<ConwayGame> getAllGames() {
        List<ConwayGame> games = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(defaltDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(GAME_FILE_EXTENSION)) {
                try {
                    games.add(readConwayGame(file.getAbsolutePath()));
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return games;
    }

    @Override
    public void saveGame(ConwayGame game) {
        try {
            Memento<ConwayGame> mem = game.saveToState();
            String filename = defaltDirectory + "game" + System.currentTimeMillis() + GAME_FILE_EXTENSION;
            FileOutputStream fout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(mem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteGames() {
        for (File file : Objects.requireNonNull(new File(defaltDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(GAME_FILE_EXTENSION)) {
                file.delete();
            }
        }
    }

    @Override
    public List<Score> getAllScores() {
        return null;
    }

    @Override
    public void saveScore(Score score) {

    }

    @Override
    public void deleteScores() {

    }

    @Override
    public void setDefaultDirectory(String defaultDirectory) {
        this.defaltDirectory = defaultDirectory;
    }
}
