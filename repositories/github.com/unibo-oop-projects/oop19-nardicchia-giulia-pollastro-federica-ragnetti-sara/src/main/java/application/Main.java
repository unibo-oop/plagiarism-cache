package application;

import java.io.File;

import javafx.application.Application;
import view.ViewImpl;

/**
 * This class represent the Main class of the JavaFX-based application.
 * 
 */
public final class Main {

    private Main() {

    }

    /**
     * 
     * @param args unused.
     */
    public static void main(final String[] args) {
        final File brainTrainer = new File(System.getProperty("user.home") + File.separator + ".brainTrainer");
        if (!brainTrainer.exists()) {
            try {
                brainTrainer.mkdir();
            } catch (SecurityException se) {
                se.printStackTrace();
            }
        }
        Application.launch(ViewImpl.class);
    }

}
