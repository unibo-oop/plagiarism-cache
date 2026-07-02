package it.unibo.model.worldconstructor.worldgenerator.impl;

import java.util.LinkedList;
import java.util.List;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.worldconstructor.data.Difficult;
import it.unibo.model.worldconstructor.worldgenerator.api.Observer;
import it.unibo.model.worldconstructor.worldgenerator.api.Subject;
import it.unibo.model.worldconstructor.worldgenerator.api.WorldDifficult;

/**
 * Implementation of WorldDifficult.
 * Manages difficulty levels by observing the player's height (Alien) and
 * notifying observers when a new difficulty threshold is reached.
 */
public class WorldDifficultImpl implements WorldDifficult, Subject, AltitudeObserver {

    /**
     * List of observers to notify when the difficulty changes.
     */
    private final List<Observer> observers;

    /**
     * The current difficulty level.
     */
    private Difficult difficult;

    /**
     * List of available difficulty levels.
     */
    private final List<Difficult> difficultList;

    /**
     * The current height of the player (Alien).
     */
    private double height;

    /**
     * Constructs a new WorldDifficultImpl.
     * 
     * @param difficultList the list of available difficulty levels
     */
    public WorldDifficultImpl(final List<Difficult> difficultList) {
        this.observers = new LinkedList<>();
        this.difficultList = List.copyOf(difficultList);
        this.difficult = this.difficultList.getFirst();
        this.height = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createDifficult(final double ht) {
        this.height += ht;
        if (this.height > difficult.height()) {
            difficultList.stream().forEach(d -> {
                if (this.height < d.height()) {
                    this.difficult = d;
                    notifyObservers(d);
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean registerObserver(final Observer o) {
        return this.observers.add(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeObserver(final Observer o) {
        return this.observers.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers(final Difficult dif) {
        for (final var o : observers) {
            o.update(dif);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double delta) {
        this.createDifficult(delta);
    }

}
