package it.tbt.model.entities.items.factories;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.tbt.controller.resources.ConfigManager;
import it.tbt.model.entities.items.Potion;

/**
 * Singleton potions factory.
 * Reads tbt/entities/potions.json
 */
public final class PotionFactory implements ItemFactory<Potion> {
    private final Set<Potion> items;

    /**
     * Inner class for singleton instance.
     */
    private static class LazyFactory {
        private static final PotionFactory FACTORY = new PotionFactory();
    }

    private PotionFactory() {
        items = new HashSet<>();

        // load potions
        final Optional<Potion[]> optItems = ConfigManager.parseJsonConfig(
            "tbt/entities/potions.json",
            Potion[].class
        );
        if (optItems.isEmpty()) {
            throw new IllegalStateException("Failed loading potions.json");
        } else {
            for (final Potion t : optItems.get()) {
                items.add(t);
            }
        }
    }

    /**
     * Get singleton factory.
     * @return PotionFactory
     */
    public static PotionFactory getInstance() {
        return LazyFactory.FACTORY;
    }

    /**
     * Returns a set of items, singletone.
     * @return Set of potions
     */
    @Override
    public Set<Potion> getItems() {
        return Set.copyOf(items);
    }
}
