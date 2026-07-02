package models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ScoresImpl is a class that implements Scores and its contracts.
 * It generates new scores to add to file and save it.
 */
public class ScoresImpl implements Scores {

    private List<Pair<String, Integer>> scores = new ArrayList<>();

    /**
     * This is the constructor for ScoresImpl
     */
    public ScoresImpl() {
        try {
            Scanner s = new Scanner(new File("score.txt"));
            while (s.hasNextLine()) {
               String[] split = s.nextLine().split(":");
               scores.add(new Pair<>(split[0], Integer.parseInt(split[1])));
            }
            s.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No file found");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setScore(final String name, final Integer score) {
        String result = name + ":" + score.toString();
        writeScoreIntoFile(result);
        this.scores.add(new Pair<>(name, score));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Pair<String, Integer>> getAllScores() {
        return this.scores;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getScore() {
        String score = readScoreFromFile();
        return score;
    }

    /**
     * Write into file a new score
     * 
     * @param score the single score that will be saved
     */
    private void writeScoreIntoFile(final String score) {
        File scoreFile = new File("score.txt");
        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter writeFile = null;
        BufferedWriter writer = null;
        try {
            writeFile = new FileWriter(scoreFile, true);
            writer = new BufferedWriter(writeFile);
            writer.append(score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close(); 
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read from file a score
     * 
     * @return score a single score that has been saved
     */
    private String readScoreFromFile() { 
        // format name:score (ex: Marco:100)

        FileReader readFile = null;
        BufferedReader reader = null;

        try {
            readFile = new FileReader("score.txt");
            reader = new BufferedReader(readFile); 
            return reader.readLine();
        } catch (Exception e) {
            return "0";
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
