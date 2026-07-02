package application;

import controller.Controller;
import view.ViewImpl;

/**
 * Class responsible for stating the entire program.
 *
 */

public final class Main {
    
    private Main() {
        
    }

    /**
     * Main method which starts the program.
     * @param args args.
     */
    
    public static void main(final String... args) {
        Controller.getController().setView(new ViewImpl());
    }
}
