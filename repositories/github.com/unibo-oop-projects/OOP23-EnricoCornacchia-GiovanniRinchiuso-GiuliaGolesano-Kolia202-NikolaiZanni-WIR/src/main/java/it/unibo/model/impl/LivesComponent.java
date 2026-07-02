package it.unibo.model.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.model.api.ComponentType;

/**
 * LivesComponent, it represents the lives of the entity.
 * This class manages the lives of an entity, allowing for lives to be reset,
 * stolen, and immortality to be toggled.
 */
public class LivesComponent extends AbstractComponent {

    private int lives;
    private boolean immortality;
    private final List<LivesChangeListener> listeners = new ArrayList<>();

    /**
     * Interface for listening to changes in lives.
     */
    public interface LivesChangeListener {
        /**
         * Method called when the number of lives changes.
         *
         * @param newLives the new number of lives
         */
        void onLivesChanged(int newLives);
    }

    /**
     * Adds a listener for changes in lives.
     *
     * @param listener the listener to add
     */
    public void addLivesChangeListener(final LivesChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Notifies all listeners of a change in lives.
     */
    private void notifyLivesChange() {
        for (final LivesChangeListener listener : listeners) {
            listener.onLivesChanged(this.lives);
        }
    }

    /**
     * Constructs a LivesComponent with default lives and immortality settings.
     */
    public LivesComponent() {
        this.lives = 3; // Set default lives directly in the constructor
        this.immortality = false;
        notifyLivesChange(); // Notify the initial state
    }

    /**
     * Resets the number of lives to the default value and disables immortality.
     */
    public final void resetLives() {
        this.lives = 3;
        immortality = false;
        notifyLivesChange();
    }

    /**
     * Decreases the number of lives by one if the entity is not immortal and has
     * lives left.
     */
    public void stealLives() {
        if (!immortality && getLives() > 0) {
            this.lives -= 1;
            notifyLivesChange();
        }
    }

    /**
     * Gets the current number of lives.
     *
     * @return the current number of lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Enables immortality, preventing lives from being stolen.
     */
    public void setImmortality() {
        this.immortality = true;
    }

    /**
     * Checks if the entity is immortal.
     *
     * @return true if the entity is immortal, false otherwise
     */
    public boolean isImmortality() {
        return this.immortality;
    }

    /**
     * Disables immortality, allowing lives to be stolen again.
     */
    public void setStopImmortality() {
        this.immortality = false;
    }

    /**
     * Gets the component type.
     *
     * @return the component type
     */
    @Override
    public ComponentType getComponent() {
        return ComponentType.LIFE;
    }
}
