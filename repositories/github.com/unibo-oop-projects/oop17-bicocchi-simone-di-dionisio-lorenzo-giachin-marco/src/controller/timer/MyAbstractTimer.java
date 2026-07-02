package controller.timer;

import java.util.Timer;

/**
 * 
 */
public abstract class MyAbstractTimer implements MyTimer {
    /**
     * 
     */
    private Timer timer = new Timer();
    private MyTimerRule rules;
    private int sleep;
    private int amount;

    MyAbstractTimer(final int newSleep, final int newAmount) {
        this.sleep = newSleep;
        this.amount = newAmount;
    }

    @Override
    public void start() {
        this.timer.schedule(rules, sleep);
    }

    @Override
    public void stop() {
        this.timer.cancel();
    }

    /**
     * 
     * @return the sleep time
     */
    public int getSleep() {
        return sleep;
    }

    /**
     * 
     * @return the valuo of amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * This method set the timer rule.
     * 
     * @param newRules
     *            timer rules
     */
    public void setRules(final MyTimerRule newRules) {
        this.rules = newRules;
    }
}
