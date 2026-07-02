package it.tbt.model.entities.items.factories;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.tbt.controller.resources.ConfigManager;
import it.tbt.model.entities.items.Armor;

/**
 * Singleton armors factory.
 * Reads tbt/entities/armors.json
 */
public final class ArmorFactory implements ItemFactory<Armor> {
    private final Set<Armor> items;

    /**
     * Inner class for singleton instance.
     */
    private static class LazyFactory {
        private static final ArmorFactory FACTORY = new ArmorFactory();
    }

    private ArmorFactory() {
        items = new HashSet<>();

        // load potions
        final Optional<Armor[]> optItems = ConfigManager.parseJsonConfig(
            "tbt/entities/armors.json",
            Armor[].class
        );
        if (optItems.isEmpty()) {
            throw new IllegalStateException("Failed loading armors.json");
        } else {
            for (final Armor t : optItems.get()) {
                items.add(t);
            }
        }
    }

    /**
     * Get singleton factory.
     * @return ArmorFactory
     */
    public static ArmorFactory getInstance() {
        return LazyFactory.FACTORY;
    }

    /**
     * Returns a set of items, singletone.
     * @return Set of armors
     */
    @Override
    public Set<Armor> getItems() {
        return Set.copyOf(items);
    }
}
