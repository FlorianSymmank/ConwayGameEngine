package ConwayGameEngine;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Main interaction class with the user.
 */
public class ConwayGameEngineFacadeImpl implements ConwayGameEngineFacade {

    private String defaultDirectory = "";
    private static final String GAME_FILE_EXTENSION = ".cg";
    private static final String SCORE_FILE_EXTENSION = ".cgs";

    /**
     * Creates the facade.
     * @param defaultDirectory the default directory to save the files to.
     */
    public ConwayGameEngineFacadeImpl(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    @Override
    public ConwayGame getGame(int i) {
        return getAllGames().get(i);
    }

    @Override
    public List<ConwayGame> getAllGames() {
        List<Memento<ConwayGame>> games = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(GAME_FILE_EXTENSION)) {
                try {
                    games.add(readConwayGame(file.getAbsolutePath()));
                } catch (IOException | ClassNotFoundException e) {

                }
            }
        }

        games.sort(Comparator.comparing(Memento::getDate));
        return games.stream().map(Memento::getState).collect(Collectors.toList());
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
        List<Memento<FinalScore>> scores = new ArrayList<>();
        for (File file : Objects.requireNonNull(new File(defaultDirectory).listFiles())) {
            if (file.isFile() && file.getName().endsWith(SCORE_FILE_EXTENSION)) {
                try {
                    scores.add(readConwayGameScore(file.getAbsolutePath()));
                } catch (IOException | ClassNotFoundException e) {

                }
            }
        }

        scores.sort(Comparator.comparing(Memento::getDate));
        return scores.stream().map(Memento::getState).collect(Collectors.toList());
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

    @Override
    public ConwayGame createGame(String playerName, int PlayerID, int width, int height) {
        return new ConwayGameImpl(playerName, PlayerID, width, height);
    }

    /**
     * Reads a ConwayGame from a file.
     * @param fileName path to the file
     * @return the ConwayGame
     * @throws IOException if the file cannot be read
     * @throws ClassNotFoundException if the file does not contain a ConwayGame
     */
    private Memento<ConwayGame> readConwayGame(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Memento<ConwayGame> mem = (Memento<ConwayGame>) ois.readObject();

        fin.close();
        ois.close();

        return mem;
    }

    /**
     * Reads a FinalScore from a file.
     * @param fileName path to the file
     * @return the FinalScore
     * @throws IOException if the file cannot be read
     * @throws ClassNotFoundException if the file does not contain a FinalScore
     */
    private Memento<FinalScore> readConwayGameScore(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fin = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fin);
        Memento<FinalScore> mem = (Memento<FinalScore>) ois.readObject();

        fin.close();
        ois.close();

        return mem;
    }
}
