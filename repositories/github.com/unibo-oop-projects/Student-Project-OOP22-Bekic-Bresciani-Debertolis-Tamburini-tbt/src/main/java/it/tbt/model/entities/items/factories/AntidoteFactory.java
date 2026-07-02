package it.tbt.model.entities.items.factories;

import java.util.Optional;
import java.util.Set;

import it.tbt.controller.resources.ConfigManager;
import it.tbt.model.entities.items.Antidote;

/**
 * Singleton antidote factory.
 * Reads tbt/entities/antidote.json
 */
public final class AntidoteFactory implements ItemFactory<Antidote> {
    private final Antidote antidote;

    /**
     * Inner class for singleton instance.
     */
    private static class LazyFactory {
        private static final AntidoteFactory FACTORY = new AntidoteFactory();
    }

    private AntidoteFactory() {
        // load antidote
        final Optional<Antidote> optItems = ConfigManager.parseJsonConfig(
            "tbt/entities/antidote.json",
            Antidote.class
        );
        if (optItems.isEmpty()) {
            throw new IllegalStateException("Failed loading antidote.json");
        } else {
            antidote = optItems.get();
        }
    }

    /**
     * Get singleton factory.
     * @return AntidoteFactory
     */
    public static AntidoteFactory getInstance() {
        return LazyFactory.FACTORY;
    }

    /**
     * Get antidote.
     * @return antidote
     */
    public Antidote getAntidote() {
        return antidote;
    }

    /**
     * Returns a set of items, singletone.
     * Prefere the use of getAntidote()
     * @return Set of a single antidote
     */
    @Override
    public Set<Antidote> getItems() {
        return Set.of(antidote);
    }
}
