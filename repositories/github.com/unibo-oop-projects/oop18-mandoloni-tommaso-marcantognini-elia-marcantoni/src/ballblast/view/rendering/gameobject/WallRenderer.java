package ballblast.view.rendering.gameobject;

import ballblast.model.gameobjects.Wall;
import ballblast.model.levels.Boundary;
import ballblast.view.imageloader.ImagePath;
import ballblast.view.rendering.Sprite;

/**
 * Represents a Renderer class for {@link Wall}.
 */
public class WallRenderer extends GameObjectRenderer<Wall> {
    /**
     * Creates a new {@link Wall} Renderer given its {@link Wall} GameObject.
     * 
     * @param sprite     The {@link Sprite} used to render.
     * @param gameObject The {@link Wall} GameObject.
     */
    public WallRenderer(final Sprite sprite, final Wall gameObject) {
        super(sprite, gameObject);
        try {
            sprite.setGameObjectWidth(gameObject.getWidth());
            sprite.setGameObjectHeight(gameObject.getHeight());
            sprite.setGameObjectPosition(gameObject.getPosition());
            if (Boundary.isFloor(gameObject.getPosition())) {
                sprite.setSource(ImagePath.WALL_FLOOR);
            } else if (Boundary.isRoof(gameObject.getPosition())) {
                sprite.setSource(ImagePath.WALL_ROOF);
            } else if (Boundary.isLeft(gameObject.getPosition()) || Boundary.isRight(gameObject.getPosition())) {
                sprite.setSource(ImagePath.WALL_VERTICAL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
