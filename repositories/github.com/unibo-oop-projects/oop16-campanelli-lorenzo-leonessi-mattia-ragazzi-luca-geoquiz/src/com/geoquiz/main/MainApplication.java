package com.geoquiz.main;

import java.io.File;

import com.geoquiz.utility.LocalFolder;
import com.geoquiz.view.menu.MainWindow;

import javafx.application.Application;

/**
 * Main class of the application.
 *
 */
public final class MainApplication {

    private MainApplication() {
    };

    /**
     * Launch application.
     * 
     * @param args
     *            arguments.
     *
     */
    public static void main(final String[] args) {

        final File geoQuizDir = new File(LocalFolder.getLocalFolderPath());
        if (!geoQuizDir.exists()) {
            geoQuizDir.mkdir();
        }

        Application.launch(MainWindow.class, args);

    }

}
