package controller;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 *the creation of the HighscoreManager class.
 */
public class HighscoreManager {

    private static final int LAST_HIGHSCORE_INDEX = 9;
    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = File.separator;
    private static final String FILE_NAME = HOME + SEPARATOR + "Highscores.txt";
    private final List<Integer> highscores = new ArrayList<Integer>();


    /**
     * reads the file and loads the highscore list.
     */
    private void readFile() {
        BufferedReader br = null;
        String s;
        highscores.clear();
        try {
            br = new BufferedReader(new FileReader(new File(FILE_NAME)));
            s = br.readLine();
            while (s != null) {
                highscores.add(Integer.parseInt(s));
                s = br.readLine();
            }
            if (br != null) {
                br.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * checks if the passed score is an actual highscores.
     * @param score that has to be checked.
     */ 
    public void checkHighscores(final int score) {
        BufferedWriter bw = null;
        readFile();
        final File file = new File(FILE_NAME);
        boolean newHighscoreFound = false;
        for (int i = 0; i < highscores.size(); i++) {
            if (score > highscores.get(i)) {
                newHighscoreFound = true;
                break;
            }
        }
        if (newHighscoreFound) {
            highscores.add(LAST_HIGHSCORE_INDEX, score);
            Collections.sort(highscores);
            Collections.reverse(highscores);
//            for (final int i : highscores) {
//                System.out.println(i);
//            }
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
            try {
                bw = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
                for (int i = 0; i <= LAST_HIGHSCORE_INDEX; i++) {
                    bw.write(Integer.toString(highscores.get(i)));
                    bw.newLine();
                }
                bw.close();
            } catch (IOException ex1) {
                System.out.printf("ERROR writing score to file: %s\n", ex1);
            }
        }
    }

    /**
     * returns an array list of strings containing all the current highscores.
     * @return toBeReturned means the score that has to be returned to the apposite panel.
     */
    public List<String> getHighscores() {
        final List<String> toBeReturned = new ArrayList<>();
        final File file = new File(FILE_NAME);
            try {
                if (file.createNewFile()) {
                    initializeHighscores();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        try {
            highscores.clear();
            readFile();
            for (final int i : highscores) {
                toBeReturned.add(String.valueOf(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
//        } finally {
//            System.out.println("There you have your highscores.");
//        }
        return toBeReturned;
    }

    private void initializeHighscores() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File(FILE_NAME)));
            for (int i = 0; i < 10; i++) {
                bw.write(Integer.toString(0));
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex1) {
            System.out.printf("ERROR writing score to file: %s\n", ex1);
        }
    }
}
