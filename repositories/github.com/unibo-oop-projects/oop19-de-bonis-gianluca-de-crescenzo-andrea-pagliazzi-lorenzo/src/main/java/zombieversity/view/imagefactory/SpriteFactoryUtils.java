package zombieversity.view.imagefactory;

import zombieversity.model.entities.EntityType;

/**
 * 
 * Factory used to manage sprite creation.
 *
 */
public final class SpriteFactoryUtils {

    private SpriteFactoryUtils() {
    }

    /**
     * 
     * @param type the entity.
     * @return {@link Sprite}.
     */
    public static Sprite createSprite(final EntityType type) {
        return new SpriteImpl(type);
    }
}
