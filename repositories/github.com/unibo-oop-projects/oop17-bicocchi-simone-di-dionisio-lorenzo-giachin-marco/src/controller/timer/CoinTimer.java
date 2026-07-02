package controller.timer;

import controller.TamagotchiController;

/**
 * This is an implementation of MyAbstractTimer.
 */
public class CoinTimer extends MyAbstractTimer {

    /**
     * @param newController
     *            is a new controller
     * @param newAmount
     *            is a new decrementative factor
     * @param newSleep
     *            is a new sleep time for my timer
     */
    public CoinTimer(final TamagotchiController newController, final int newSleep, final int newAmount) {
        super(newSleep, newAmount);
        setRules(new CoinTimerRule(newController));
    }

    /**
     * 
     * Private class Tamagotchi CoinRules
     *
     */

    private final class CoinTimerRule extends MyTimerRule {

        CoinTimerRule(final TamagotchiController newController) {
            super(newController);
        }

        @Override
        public void run() {
            while (true) {
                try {
                    getController().setCoin(getAmount());
                    getController().updateMoney();
                    Thread.sleep(getSleep());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
