package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class HighScoreManager {
	

	private String homeDirectory=System.getProperty("user.home");
	private String separator=System.getProperty("file.separator");
	private final String HIGHSCORE_FILE=homeDirectory+separator+"Desktop"+separator+"highScore.txt";
    private ArrayList<Score> scores;
    private ObjectOutputStream outputStream = null;
    private ObjectInputStream inputStream = null;
    private FileCreation cf=new FileCreation();
    
    public HighScoreManager() {
        scores = new ArrayList<Score>();
        cf.createFile();
    }
    
    /**
     * 
     * @return an ArrayList containing the scores read from the file and sorted in ascending order
     */
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    
    /**
     * Sort the scores, creating comparator object from ScoreComparator
     */
    private void sort() {
        ScoreComparator comparator = new ScoreComparator();
        Collections.sort(scores, comparator);
    }
    
    /**
     * Add new score in the file HighScore.txt
     * @param name
     * @param score
     */
    public void addScore(String name, int score) {
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
    }
    
    /**
     * Load the score file
     */
    @SuppressWarnings("unchecked")
	public void loadScoreFile() {
        try {
            inputStream = new ObjectInputStream(new FileInputStream(HIGHSCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not foundError: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Class not Found Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("IO Error: " + e.getMessage());
            }
        }
    }
    
    /**
     * Update the file with new scores
     */
    public void updateScoreFile() {
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(HIGHSCORE_FILE));
            outputStream.writeObject(scores);
        } catch (FileNotFoundException e) {
            System.out.println("File not found Error: " + e.getMessage() + ",the program will try and make a new file");
        } catch (IOException e) {
            System.out.println("IO Error: " + e.getMessage());
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}