package controller;

import javafx.application.Application;

/**
 * The class containing the main method.
 */
public final class Main {

    private Main() {
    }

    /**
     * The entry point of application.
     * 
     * @param args passed
     */
    public static void main(final String[] args) {
        Application.launch(MainViewHandler.class, args);
    }
}
