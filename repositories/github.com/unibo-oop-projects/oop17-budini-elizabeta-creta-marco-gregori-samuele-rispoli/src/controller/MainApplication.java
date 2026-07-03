package controller;

import view.ViewImpl;
import view.ViewInterface;

public class MainApplication {
    /**
     * The class responsible for launching the application
     * @param args
     */
    public static void main(final String[] args) {
        
        final ViewInterface v = new ViewImpl();
        v.startView();

    }
}
