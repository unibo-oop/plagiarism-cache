package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for saving the score.
 *
 */
public class FileHandler {
    private File file;
    private String scoreSet;
    private FileInputStream fIn;
    private byte[] fileContent;
    private FileOutputStream fOut;

    /**
     * Constructor.
     * 
     * @throws IOException if unable to save.
     */
    public FileHandler() throws IOException {
        File tmpFile = new File("res/CClimber-ScoreBoard.txt");
        file = new File(tmpFile.getAbsolutePath());
        file.createNewFile();
        fIn = new FileInputStream(file);
        fOut = new FileOutputStream(file, true);

    }

    /**
     * Write the list into the file.
     * 
     * @param nome  the name of player.
     * @param score the score of player.
     * @throws IOException if not able to save.
     */
    public void write(final String nome, final int score) throws IOException {
        scoreSet = nome + "," + score + "\n";
        fileContent = scoreSet.getBytes();
        fOut.write(fileContent);
    }

    /**
     * Read the list from the file.
     * 
     * @return the content of the file.
     * @throws IOException if not able to save.
     */
    public List<String> read() throws IOException {
        List<String> scores = new ArrayList<>();
        // Open the file
        BufferedReader br = new BufferedReader(new InputStreamReader(fIn));
        String strLine;
        // Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            scores.add(strLine);
        }
        return scores;
    }

    /**
     * Close the data stream.
     * 
     * @throws IOException if unable to save.
     */
    public void close() throws IOException {
        fIn.close();
    }

    /**
     * 
     * Clear the file of Leaderboard.
     * 
     * @throws IOException if unable to save.
     * 
     */
    public void clearFile() throws IOException {
        file.delete();
        file.createNewFile();
        fIn = new FileInputStream(file);
        fOut = new FileOutputStream(file, true);
    }
}
