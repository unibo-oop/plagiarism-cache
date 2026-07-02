package it.tbt.model.entities.items.factories;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import it.tbt.controller.resources.ConfigManager;
import it.tbt.model.entities.items.Weapon;

/**
 * Singleton weapons factory.
 * Reads tbt/entities/weapons.json
 */
public final class WeaponFactory implements ItemFactory<Weapon> {
    private final Set<Weapon> items;

    /**
     * Inner class for singleton instance.
     */
    private static class LazyFactory {
        private static final WeaponFactory FACTORY = new WeaponFactory();
    }

    private WeaponFactory() {
        items = new HashSet<>();

        // load potions
        final Optional<Weapon[]> optItems = ConfigManager.parseJsonConfig(
            "tbt/entities/weapons.json",
            Weapon[].class
        );
        if (optItems.isEmpty()) {
            throw new IllegalStateException("Failed loading weapons.json");
        } else {
            for (final Weapon t : optItems.get()) {
                items.add(t);
            }
        }
    }

    /**
     * Get singleton factory.
     * @return WeaponFactory
     */
    public static WeaponFactory getInstance() {
        return LazyFactory.FACTORY;
    }

    /**
     * Returns a set of items, singletone.
     * @return Set of weapons
     */
    @Override
    public Set<Weapon> getItems() {
        return Set.copyOf(items);
    }
}
