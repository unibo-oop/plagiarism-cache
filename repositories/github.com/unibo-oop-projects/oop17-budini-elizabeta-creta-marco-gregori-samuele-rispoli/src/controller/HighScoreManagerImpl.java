package controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import utilities.Pair;

/**
 * Class responsible for adding scores to the list of scores and for saving the
 * list on the file system
 *
 */

public class HighScoreManagerImpl implements HighScoreManager {
    private Optional<List<Pair<String, Integer>>> list;
    private final String file;
    private final int maxScores;
    private boolean toSave;

    /**
     * Constructor for HighScoreManager
     * 
     * @param fileName
     *            the file name on the filesystem
     * @param mScores
     *            the number of maximum scores to save
     * @throws IllegalArgumentException
     *             if the file name is not valid or the score is negative
     */
    public HighScoreManagerImpl(final String fileName, final int mScores) throws IllegalArgumentException {
        if (mScores <= 0 || fileName.isEmpty()) {
            throw new IllegalArgumentException("File name not valid, Score must be > 0");
        }
        this.list = Optional.empty();
        this.file = fileName;
        this.maxScores = mScores;
        this.toSave = false;
    }

    /**
     * adds a score to the list
     */
    public void addScore(Pair<String, Integer> score) {
        if (!this.list.isPresent()) {
            this.readScores();
        }
        final List<Pair<String, Integer>> list = this.list.get();
        list.add(score);
        this.sortScores(list);
        this.cutScores(list);
        this.list = Optional.of(list);

        this.toSave = true;
    }

    /**
     * Cut the excess scores according to maxScores
     * 
     * @param list2
     * @return true if the list is modified
     */
    private Boolean cutScores(List<Pair<String, Integer>> list2) {
        final boolean modified;
        if (list2.size() > this.maxScores) {
            modified = true;
        } else {
            modified = false;
        }
        while (list2.size() > this.maxScores) {
            list2.remove(this.maxScores);
        }
        return modified;

    }

    private void sortScores(List<Pair<String, Integer>> list2) {
        Collections.sort(list2, (x, y) -> y.getY() - x.getY());

    }

    /**
     * reads the scores from fileSystem
     */
    private void readScores() {
        final List<Pair<String, Integer>> list = new LinkedList<>();
        try (DataInputStream in = new DataInputStream(new FileInputStream(this.file))) {
            while (true) {
                /* reads the name of the player */
                final String name = in.readUTF();
                /* reads the score of the relative player */
                final Integer score = Integer.valueOf(in.readInt());
                list.add(new Pair<String, Integer>(name, score));
            }
        } catch (final Exception ex) {
        }
        this.sortScores(list);
        /* if the list was modified by the cutting method i need to save it */
        if (this.cutScores(list)) {
            this.toSave = true;
        }
        this.list = Optional.of(list);

    }

    /**
     * getter of the list of scores
     */
    public List<Pair<String, Integer>> getScores() {
        if (!this.list.isPresent()) {
            this.readScores();
        }
        return new ArrayList<>(this.list.get());
    }

    /**
     * when necessary saves the scores on file
     */
    public void writeScores() throws IOException {
        if (this.list.isPresent() && this.toSave) {
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream(this.file))) {
                for (final Pair<String, Integer> p : this.list.get()) {
                    out.writeUTF(p.getX());
                    out.writeInt(p.getY().intValue());
                }
                this.toSave = false;
            } catch (IOException e) {
                System.out.println("IOException on writing scores");
            }
        }

    }

}
