package clashclass.battle.troopdeath;

import clashclass.commons.HealthComponent;
import clashclass.ecs.AbstractComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a {@link TroopDeathObservable} implementation.
 */
public class TroopDeathObservableImpl extends AbstractComponent implements TroopDeathObservable {
    private final Set<TroopDeathObserver> observers = new HashSet<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public void addObserver(final TroopDeathObserver observer) {
        this.observers.add(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeObserver(final TroopDeathObserver observer) {
        this.observers.remove(observer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final float deltaTime) {
        final var healthComponent = this.getGameObject().getComponentOfType(HealthComponent.class)
                .orElseThrow(() -> new RuntimeException("Health component not found"));

        if (healthComponent.isDead()) {
            this.getGameObject().destroy();
            this.observers.forEach(x -> x.notifyDeath(this.getGameObject()));
        }
    }
}
