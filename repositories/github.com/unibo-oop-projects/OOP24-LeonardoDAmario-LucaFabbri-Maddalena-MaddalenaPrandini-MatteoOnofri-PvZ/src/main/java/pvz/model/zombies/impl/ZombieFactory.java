package pvz.model.zombies.impl;

import pvz.utilities.Position;
import java.util.Locale;

/**
 * Factory class for creating zombie instances based on type.
 */
public final class ZombieFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private ZombieFactory() {
    }

    /**
     * Creates a zombie of the specified type at the given position.
     *
     * @param type the type of zombie to create ("basic", "fast", "strong", "beast").
     * @param position the initial position of the zombie.
     * @return the created {@link AbstractZombie} instance.
     * @throws IllegalArgumentException if the type is not supported.
     */
    public static AbstractZombie createZombie(final String type, final Position position) {
        return switch (type.toLowerCase(Locale.ROOT)) {
            case "basic" -> new BasicZombie(position);
            case "fast" -> new FastZombie(position);
            case "strong" -> new StrongZombie(position);
            case "beast" -> new BeastZombie(position);
            default -> throw new IllegalArgumentException("Unsupported zombie type: " + type);
        };
    }
}
