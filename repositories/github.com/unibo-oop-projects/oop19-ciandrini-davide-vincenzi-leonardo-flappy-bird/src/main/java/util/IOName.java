package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This is the class that print on a text file the user name.
 */
public class IOName {

    /**
     * Print the user name in a text file.
     * @param name UserName
     */
    public void writeName(final String name) {
        final File folder = new File(System.getProperty("user.home"), "FlappyBirdScores.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(folder, true))) {
            bw.write(name);
            bw.newLine();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
