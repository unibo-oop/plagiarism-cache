package controller.sceneManager;

import java.util.Set;

import com.almasb.fxgl.core.math.Vec2;

import javafx.scene.image.ImageView;
import model.Entity;
import view.GameMap;

/**
*
*/
public class SceneManager {

    private final GameMap gameMap;
    private final Set<Entity> entities;

    /**
     * Constructor.
     *
     * @param gameMap the game map
     */
    public SceneManager(final GameMap gameMap) {
        this.gameMap = gameMap;
        this.entities = this.gameMap.getActiveEntities();
    }

    /**
     * Game update.
     *
     * @param deltaTime tic update
     */
    public void update(long deltaTime) {
        //this.updateBackground();
        this.entities.forEach(entity -> updateEntityPosition(entity, deltaTime));
    }

    /**
     * Entity position update.
     * @param entity entity to update
     * @param deltaTime tic update
     */
    private void updateEntityPosition(final Entity entity, final long deltaTime) {
        if (entity.isAlive()) {
            entity.move(deltaTime);
            final Vec2 position = entity.getPosition();
            ImageView image = (ImageView) entity.getNode();
            image.getImage().getHeight();
            entity.getNode().relocate(position.x - image.getImage().getWidth() / 2,
                    position.y - image.getImage().getHeight() / 2);
            entity.getNode().setRotate(entity.getAngle());
        } else {
            try {
                this.gameMap.removeEntity(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    // TODO DA CANCELLARE
    /**
     * Background update.
     */
    /*
     * private void updateBackground() { for (final Node image :
     * this.gameMap.getBackground()) { image.setLayoutY(image.getLayoutY() +
     * EnumInt.FOUR.getValue()); if (image.getLayoutY() >=
     * this.gameMap.getHeight().intValue()) {
     * image.setLayoutY(-this.gameMap.getHeight().intValue()); } } }
     */

}
