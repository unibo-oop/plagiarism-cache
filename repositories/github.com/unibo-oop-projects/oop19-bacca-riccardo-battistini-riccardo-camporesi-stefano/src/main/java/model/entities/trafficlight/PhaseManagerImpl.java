package model.entities.trafficlight;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Concrete implementation of the PhaseManager.
 *
 * @param <T> the object that represents a phase
 */
public class PhaseManagerImpl<T> implements PhaseManager<T> {

    private final Queue<Pair<T, Integer>> phaseQueue;
    private final Set<TrafficLightObserver> observers;
    private int timer;
    private Pair<T, Integer> currentPhase;

    /**
     * Creates a new phaseManager with the given list.
     *
     * @param list the list of phases to be managed
     */
    public PhaseManagerImpl(final List<Pair<T, Integer>> list) {
        Objects.requireNonNull(list);
        this.phaseQueue = new LinkedList<Pair<T, Integer>>(list);
        this.observers = new HashSet<>();
        this.initialize();
        this.timer = this.currentPhase.getValue();
    }

    private void initialize() {
        this.currentPhase = this.phaseQueue.poll();
        this.phaseQueue.add(this.currentPhase);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.timer--;

        if (this.timer == 0) {
            this.initialize();
            this.timer = this.currentPhase.getValue();
            this.notifyObservers();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attach(final TrafficLightObserver obs) {
        Objects.requireNonNull(obs);
        this.observers.add(obs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detach(final TrafficLightObserver obs) {
        Objects.requireNonNull(obs);
        this.observers.remove(obs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifyObservers() {
        this.observers.forEach(x -> x.updatePhase());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getCurrentPhase() {
        return currentPhase.getKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentPhaseDuration() {
        return timer;
    }
}
