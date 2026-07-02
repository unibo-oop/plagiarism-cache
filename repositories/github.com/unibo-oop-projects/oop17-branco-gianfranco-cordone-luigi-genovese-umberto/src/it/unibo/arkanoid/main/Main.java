package it.unibo.arkanoid.main;

import java.io.File;

import it.unibo.arkanoid.view.ViewImpl;
import javafx.application.Application;

/**
 * The main class.
 *
 */
public final class Main {

    private Main() {

    }

    /**
     * Create folder if doesn't exit and launch application.
     * 
     * @param args
     *            argument
     */

    public static void main(final String[] args) {
        final File arkanoidDir = new File(System.getProperty("user.home") + File.separator + ".arkanoid");
        if (!arkanoidDir.exists()) {
            try {
                arkanoidDir.mkdir();
            } catch (SecurityException se) {
                System.out.println("Error. While create directory");
            }
        }

        Application.launch(ViewImpl.class, args);
    }

}
