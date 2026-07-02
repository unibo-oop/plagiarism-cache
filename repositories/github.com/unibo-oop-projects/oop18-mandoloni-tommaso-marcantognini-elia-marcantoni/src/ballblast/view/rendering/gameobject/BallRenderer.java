package ballblast.view.rendering.gameobject;

import java.io.FileNotFoundException;
import ballblast.model.gameobjects.Ball;
import ballblast.view.imageloader.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * 
 * A renderer for {@link Ball} GameObject.
 * 
 */
public class BallRenderer extends GameObjectRenderer<Ball> {
    /**
     * @param sprite     the {@link Sprite} used to render.
     * @param gameObject the {@link Ball} GameObject.
     */
    public BallRenderer(final Sprite sprite, final Ball gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
            sprite.setSource(ImagePath.BALL);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
