package view.gameview;

import javafx.scene.image.ImageView;
import model.entitiesutil.Entity;

/**
 * Interface of a generic SpriteLoader which have the role to load and return
 * the correspondent {@link ImageView} if necessary.
 *
 */
public interface SpritesLoader {

    /**
     * Get an Entity it returns the corresponding sprite.
     * 
     * @param entity The entity of which you want the sprite.
     * @return The {@link ImageView} containing the sprite.
     */
    ImageView getSprite(Entity entity);
}
