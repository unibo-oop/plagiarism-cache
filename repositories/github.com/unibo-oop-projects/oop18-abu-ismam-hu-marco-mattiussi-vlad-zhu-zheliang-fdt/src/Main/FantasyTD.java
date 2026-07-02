package main;
import view.mainmenu.MainMenu;
import javafx.application.Application;
/**
 * Main class, entry point of the program.
 */
public final class FantasyTD {
    private FantasyTD() {
    }
    /**
     * starts the Application, launching the MainMenu class.
     * @param args of main
     */
    public static void main(final String[] args) {
        Application.launch(MainMenu.class);
    }
}
