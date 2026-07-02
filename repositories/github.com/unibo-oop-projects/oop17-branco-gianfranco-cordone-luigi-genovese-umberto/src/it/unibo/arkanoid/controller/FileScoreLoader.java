package it.unibo.arkanoid.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 
 * Class used to write on the file and read the score from file.
 *
 */
public class FileScoreLoader implements ScoreLoader {

    private static final String SCORE = System.getProperty("user.home") + File.separator + ".arkanoid" + File.separator
            + "Scores";
    private static final String FILESCORE = "Score";
    private final File classic = new File(SCORE + File.separator + "Score");

    /**
     * 
     */
    public FileScoreLoader() {
        try {
            final File score = new File(SCORE);
            if (!score.exists()) {
                try {
                    score.mkdirs();
                } catch (SecurityException se) {
                    System.out.println("Error. While create directory");
                }
            }
            try {
                this.classic.createNewFile();
            } catch (SecurityException se) {
                System.out.println("Error. While create " + SCORE);
            }
            if (Files.size(Paths.get(SCORE + File.separator + FILESCORE)) == 0) {
                final ScoreList s = new ScoreList();
                final ObjectOutputStream writer = new ObjectOutputStream(
                        new BufferedOutputStream(new FileOutputStream(classic)));
                writer.writeObject(s);
                writer.flush();
                writer.close();
            }

        } catch (IOException e) {
            System.out.println("Error. While create or reading File.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveScore(final String name, final int score) throws IOException {
        final ScoreList scores = this.loadScore();
        final ObjectOutputStream writer = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(classic)));
        scores.addScore(name, score);
        writer.writeObject(scores);
        writer.flush();
        writer.close();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ScoreList loadScore() throws IOException {
        final ObjectInputStream reader = new ObjectInputStream(new BufferedInputStream(new FileInputStream(classic)));
        ScoreList score = null;
        try {
            score = (ScoreList) reader.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException(e.getMessage());
        } finally {
            reader.close();
        }
        return score;
    }

}
