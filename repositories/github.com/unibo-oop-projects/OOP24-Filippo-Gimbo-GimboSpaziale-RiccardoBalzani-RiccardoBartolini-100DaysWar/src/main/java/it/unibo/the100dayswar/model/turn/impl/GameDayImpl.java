package it.unibo.the100dayswar.model.turn.impl;

import it.unibo.the100dayswar.commons.patterns.Observer;
import it.unibo.the100dayswar.commons.utilities.api.ResourceGenerator;
import it.unibo.the100dayswar.model.turn.api.GameDay;

import java.util.ArrayList;
import java.util.List;

/**
 * class that implement the interface GameDay.
 */
public class GameDayImpl implements GameDay {
    private static final long serialVersionUID = 1L;

    private static final int MAX_DAY = 100;
    private static final int AMOUNT_IN_A_DAY = 50;
    private static final int INITIAL_DAY = 1;
    private final List<Observer<ResourceGenerator>> observers;
    private int day;

    /**
     * Constructor for the GameDay.
      */
    public GameDayImpl() {
        this.day = INITIAL_DAY;
        this.observers = new ArrayList<>();
    }
    /**
     * increase the day.
     */
    @Override
    public void increaseDay() {
        if (this.day < MAX_DAY) {
            this.day++;
            notifyObservers();
        }
    }
    /**
     * return the current day.
     */
    @Override
    public int getCurrentDay() {
        return this.day;
    }
    /**
     * return the amount in a day.
     */
    @Override
    public int getAmount() {
        return AMOUNT_IN_A_DAY;
    }
    /**
     * return the maxDay in a Game.
     */
    @Override
    public int getMaxDay() {
        return MAX_DAY;
    }
    /**
     * notify the player.
     */
    @Override
    public void notifyObservers() {
        observers.forEach(this::notify);
    }
    /**
     * notify the observer.
     */
    @Override
    public void notify(final Observer<ResourceGenerator> observer) {
        observer.update(this);
    }
    /**
     * Add an observer.
     */
    @Override
    public void attach(final Observer<ResourceGenerator> observer) {
        observers.add(observer);
    }
    /**
     * Remove an observer.
     */
    @Override
    public void detach(final Observer<ResourceGenerator> observer) {
        observers.remove(observer);
    }
}
