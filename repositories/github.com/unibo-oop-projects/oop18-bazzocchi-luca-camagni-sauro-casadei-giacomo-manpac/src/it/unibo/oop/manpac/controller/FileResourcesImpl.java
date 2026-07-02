package it.unibo.oop.manpac.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Class for high score recovery from files.
 */

public final class FileResourcesImpl implements FileResources {

    private static final String HOME = System.getProperty("user.home");
    private static final String SEPARATOR = System.getProperty("file.separator");
    private static final String DEFAULT_FILE = "ManpacHighScore.pac";

    private final File file = new File(HOME + SEPARATOR + DEFAULT_FILE);

    @Override
    public int startResources(final int defaultHighScore) {
        if (this.file.exists() && !this.file.isDirectory()) {
            return this.readFile();
        }
        this.setHighScore(defaultHighScore);
        return defaultHighScore;
    }

    private int readFile() {
        int highScore = 0;
        try (BufferedReader inputStream = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));) {
            highScore = Integer.parseInt(inputStream.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException n) {
            System.err.println("Highscore non corretto");
            n.printStackTrace();
        } finally {
            if (highScore <= 0) {
                this.setHighScore(0);
            }
        }
        return highScore;
    }

    @Override
    public void setHighScore(final int highScore) {
        try (BufferedWriter outputStream = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(this.file)));) {
            outputStream.write(Integer.toString(highScore));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
