package model.utilities;

import java.util.Timer;
import java.util.TimerTask;

import controller.event.PowerUpController;

/**
 * Simple timer class that uses java.util.Timer to schedule a timer task 
 * to execute once an arbitrary amount of seconds have passed.
 */
public class PowerUpTimer {

    private final Timer timer;
    private final PowerUpController pwupController;

    public PowerUpTimer(final long seconds, final PowerUpController pwupController) {
            timer = new Timer();
            timer.schedule(new PowerUpTask(), seconds * 1000);
            this.pwupController = pwupController;
        }

    class PowerUpTask extends TimerTask {
        @Override
        public void run() {
            pwupController.deactivatePowerUp(pwupController.getPwup());
            timer.cancel(); //Terminate the timer thread
        }
    }
}
