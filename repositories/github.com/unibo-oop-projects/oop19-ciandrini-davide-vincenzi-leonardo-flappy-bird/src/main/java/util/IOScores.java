package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the class that print on a text file the final user score.
 */
public class IOScores {

    /**
     * Print the user score in a text file.
     * @param score Final UserScore
     */
    public void writeScore(final int score) {
        final File folder = new File(System.getProperty("user.home"), "FlappyBirdScores.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder, true))) {
            bw.write(Integer.toString(score));
            bw.newLine();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }





}
