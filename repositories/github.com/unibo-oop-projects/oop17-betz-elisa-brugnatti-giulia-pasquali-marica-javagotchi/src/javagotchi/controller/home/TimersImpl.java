package javagotchi.controller.home;

import javax.swing.Timer;

import javafx.application.Platform;
import javagotchi.view.home.Home;

/**
 * Class that implements a group of timers. 
 * It includes methods to start and stop them.
 * @author elisa
 *
 */
public class TimersImpl implements Timers {

    private static final int DROP_CLEANLINESS_DELAY = 9000; // milliseconds
    private static final int DROP_ENERGY_DELAY = 10000; // milliseconds
    private static final int DROP_HUNGER_DELAY = 7000; // milliseconds
    private static final int UPDATE_AGE_DELAY = 6000; // milliseconds

    private final Timer dropCleanlinessTimer;
    private final Timer dropEnergyTimer;
    private final Timer dropHungerTimer;
    private final Timer updateAgeTimer;
    private final Home homeview;
    private final HomeController homecontroller;

    /**
     * TimersImpl constructor.
     * @param homecontroller the home controller
     * @param homeview  the home view that will be update
     */
    public TimersImpl(final HomeController homecontroller, final Home homeview) {
        this.homeview = homeview;
        this.homecontroller = homecontroller;

        this.dropCleanlinessTimer = new Timer(DROP_CLEANLINESS_DELAY, e -> { 
            this.homecontroller.getJavagotchi().getNeeds().getCleanliness().reduce();
            this.homeview.updateBars();
        });

        this.dropEnergyTimer = new Timer(DROP_ENERGY_DELAY, e -> {
            this.homecontroller.getJavagotchi().getNeeds().getEnergy().reduce();
            this.homeview.updateBars();
        });

        this.dropHungerTimer = new Timer(DROP_HUNGER_DELAY, e -> {
            this.homecontroller.getJavagotchi().getNeeds().getHunger().reduce();
            this.homeview.updateBars();
        });

        this.updateAgeTimer = new Timer(UPDATE_AGE_DELAY, e -> {
            this.homecontroller.getJavagotchi().getInformation().getOlder();
            Platform.runLater(() -> homeview.updateAge());
        });
    }

    /**
     * Method to start the timers.
     */
    public void start() {
        this.dropCleanlinessTimer.start();
        this.dropEnergyTimer.start();
        this.dropHungerTimer.start();
        this.updateAgeTimer.start();
    }

    /**
     * Method to stop the timers.
     */
    public void stop() {
        this.dropCleanlinessTimer.stop();
        this.dropEnergyTimer.stop();
        this.dropHungerTimer.stop();
        this.updateAgeTimer.stop();
    }
}
