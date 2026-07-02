package ballblast.view.rendering.gameobject;

import ballblast.model.powerups.AbstractPower;
import ballblast.model.powerups.PowerTypes;
import ballblast.view.imageloader.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * A renderer for {@link AbstractPower} GameObject.
 */
public class PowerUpRenderer extends GameObjectRenderer<AbstractPower> {
    /**
     * 
     * @param sprite     the {@link Sprite} used to render.
     * @param gameObject the {@link AbstractPower} GameObject.
     */
    public PowerUpRenderer(final Sprite sprite, final AbstractPower gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
            if (gameObject.getPowerType().equals(PowerTypes.DOUBLEFIRE)) {
                sprite.setSource(ImagePath.POWERUP_DOUBLEFIRE);
            } else if (gameObject.getPowerType().equals(PowerTypes.SPEED)) {
                sprite.setSource(ImagePath.POWERUP_SPEED);
            } else if (gameObject.getPowerType().equals(PowerTypes.SHIELD)) {
                sprite.setSource(ImagePath.POWERUP_SHIELD);
            }
            if (gameObject.isActive()) {
                sprite.setGameObjectPosition(sprite.getSourceTopLeftCorner());
                sprite.setGameObjectWidth(gameObject.getWidth() * 2);
                sprite.setGameObjectHeight(gameObject.getHeight() * 2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
