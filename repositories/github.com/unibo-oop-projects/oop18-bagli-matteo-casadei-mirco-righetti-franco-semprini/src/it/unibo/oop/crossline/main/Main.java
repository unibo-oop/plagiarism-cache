package it.unibo.oop.crossline.main;

import it.unibo.oop.crossline.launcher.LauncherControllerImpl;
import it.unibo.oop.crossline.launcher.LauncherModelImpl;
import it.unibo.oop.crossline.launcher.LauncherViewImpl;

/**
 * This is where the application starts.
 */
public final class Main {

    private Main() {
    }

    /**
     * Entry point of the application.
     * @param args the command line parameters
     */
    public static void main(final String[] args) {
        new LauncherControllerImpl(new LauncherViewImpl(), new LauncherModelImpl());
    }

}
