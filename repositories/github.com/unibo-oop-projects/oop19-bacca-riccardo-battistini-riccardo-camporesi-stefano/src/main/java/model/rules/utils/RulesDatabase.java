package model.rules.utils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import model.ScenarioImpl;
import model.observer.Observer;
import model.observer.Subject;

public class RulesDatabase implements Subject {

    private final Set<Observer> observers;
    private ScenarioImpl scenario;


    public RulesDatabase() {
        this.observers = new HashSet<>();
    }

    /**
     * To update current scenario instance.
     * @param scenario the scenario instance to be passed to observers
     */
    public final void modifyScenarioInstance(final ScenarioImpl scenario) {
        this.scenario = scenario;
        this.notifyObservers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void register(final Observer obj) {
        this.observers.add(Optional.of(obj).orElseThrow());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void unregister(final Observer obj) {
        this.observers.remove(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void notifyObservers() {
        this.observers.forEach(x -> x.update(this.scenario));
    }
}
