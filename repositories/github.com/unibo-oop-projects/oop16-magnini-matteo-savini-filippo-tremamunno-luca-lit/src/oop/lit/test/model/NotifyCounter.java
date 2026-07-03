package oop.lit.test.model;

import oop.lit.util.Observer;

/**
 * An observer that counts how many times it is notified.
 */
public class NotifyCounter implements Observer {
    private int count;

    @Override
    public void notifyChange() {
        this.count++;
    }

    /**
     * @return
     *      how many time this was notified.
     */
    public int getCount() {
        return this.count;
    }
}
