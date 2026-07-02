package javagotchi.controller.home;

import javax.swing.SwingUtilities;

import javafx.application.Platform;
import javafx.stage.Stage;
import javagotchi.controller.menu.MenuController;
import javagotchi.controller.menu.MenuControllerImpl;
import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.controller.minigame.main.MiniGameImpl;
import javagotchi.model.Javagotchi;
import javagotchi.view.home.Home;
import javagotchi.view.home.Tutorial;
import javagotchi.view.menu.MenuView;
import javagotchi.view.menu.MenuViewImpl;
/**
 * Class that implements the controller to manage the home view.
 * @author elisa
 *
 */
public class HomeControllerImpl implements HomeController {

    private final Javagotchi javagotchi;
    private Timers timers;

    /**
     * Constructor for the Home Controller.
     * 
     * @param javagotchi the javagotchi chosen to play
     * 
     */
    public HomeControllerImpl(final Javagotchi javagotchi) {
        this.javagotchi = javagotchi;
    }

    /**
     * Getter for the javagotchi.
     * @return the home controller javagotchi.
     */
    public final Javagotchi getJavagotchi() {
        return this.javagotchi;
    }

    /**
     * Getter for the timers.
     * @return the home controller timers.
     */
    public final Timers getTimers() {
        return this.timers;
    }

    /**
     * Method to initialize and start timers.
     * @param homeview the home view that timers will update in the time
     */
    public final void startTimers(final Home homeview) {
        this.timers = new TimersImpl(this, homeview);
        this.timers.start();
    }

    /**
     * Method to save the data on file.
     */
    public final void save() {
        final MenuController mc = new MenuControllerImpl();
        mc.resumeFile();
        mc.update(javagotchi);
        mc.writeOnFile();
    }


    /**
     * Method to call when the action "play" is performed on the home view.
     */
    public void playHandler() {
        this.javagotchi.play();
        this.timers.stop();
        final MiniGame minigame = new MiniGameImpl(this);
        SwingUtilities.invokeLater(minigame);
    }


    /**
     * Method to call when the action "go back" is performed on the home view.
     * It takes the Stage as an argument to close it.
     * @param stage  the stage to be closed
     */
    public void backHandler(final Stage stage) {
        stage.close();
        this.timers.stop();
        this.save();
        final MenuController mc = new MenuControllerImpl();
        mc.resumeFile();
        final MenuView previouspage = new MenuViewImpl(mc);
        previouspage.getMenuManager().showSavedAvatarMenu();
    }


    /**
     * Method to call when the action "show tutorial" is performed on the home view.
     */
    public void tutorialHandler() {
        final Tutorial tutorial = new Tutorial(this.timers);
        Platform.runLater(() -> tutorial.show());
    }


    /**
     * Method to check if the javagotchi is alive. 
     * @throws DeathException if the javagotchi is dead.
     */
    public void checkLiveness() throws DeathException {
        if (!this.javagotchi.isAlive()) {
            this.timers.stop();
            throw new DeathException(this);
        }
    }

}
