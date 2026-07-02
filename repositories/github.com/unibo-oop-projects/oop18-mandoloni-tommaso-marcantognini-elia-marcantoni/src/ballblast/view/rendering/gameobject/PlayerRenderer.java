package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;
import ballblast.model.gameobjects.Player;
import ballblast.view.imageloader.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * A renderer for {@link Player} GameObject.
 */
public class PlayerRenderer extends GameObjectRenderer<Player> {
    /**
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Player} GameObject
     */
    public PlayerRenderer(final Sprite sprite, final Player gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
            sprite.setSource(ImagePath.PLAYER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void render() {
        this.getSprite().render();
    }
}
