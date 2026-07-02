package controller.timer;

import controller.TamagotchiController;
import javafx.application.Platform;

/**
 * 
 * Tamagotchi timer.
 *
 */
public class TamagotchiTimer extends MyAbstractTimer {
    private double age = 1;

    /**
     *
     * @param newController
     *            is a new controller
     * @param newAmount
     *            is a new decrementive factor
     * @param newSleep
     *            is the sleep time for a timer
     */
    public TamagotchiTimer(final TamagotchiController newController, final int newSleep, final int newAmount) {
        super(newSleep, newAmount);
        setRules(new TamagotchiTimerRules(newController));

    }

    /**
     * 
     * Private class Tamagotchi Rules
     *
     */
    private class TamagotchiTimerRules extends MyTimerRule {
        TamagotchiTimerRules(final TamagotchiController newController) {
            super(newController);
        }

        public void run() {
            while (getController().modAllStats(-getAmount())) {
                try {
                    getController().ageUp();
                    if (getController().getAge() > age) {
                        age += 1;
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                getController().updateAge();
                            }
                        });
                    }
                    Thread.sleep(getSleep());
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            getController().update();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            getController().characterIsDead();
        }
    }
}
