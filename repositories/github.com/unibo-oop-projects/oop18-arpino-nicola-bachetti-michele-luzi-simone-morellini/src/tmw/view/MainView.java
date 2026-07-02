package tmw.view;

import javafx.geometry.Dimension2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.Rec2D;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.GameEntity;
import tmw.model.objects.BaseGameObject;

/**
 * Main view interface. The mainView allows to render gameEntities. It's the
 * only one class which knows how to render gameObjects/gameEntites and knows
 * about "textures". It should be the main view of the game
 * 
 * @version 1.4
 *
 */
public interface MainView {

    /**
     * Closes the game.
     */
    void exit();

    /**
     * Updates automatically game actual resolution. This method should be called
     * when game screen resolution changes. For example when user maximizes game
     * window.
     */
    void updateGameResolution();

    /**
     * Renders an object in a box that has a dimension and a position.
     * 
     * @param objImage image to render
     * @param box      box to render in
     */
    void render(Image objImage, Rec2D box);

    /**
     * Renders a baseGameObject.
     * 
     * @param obj {@link BaseGameObject} gameObj to render
     */
    void render(BaseGameObject obj);

    /**
     * Render an item in the view through its controller.
     * 
     * @param itemController item controller
     */
    void render(AbstractItemController itemController);

    /**
     * Renders a gameEntity.
     * 
     * @param entity   - the entity to render
     * @param boundary - the boundary of the entity
     */
    void render(GameEntity entity, Rec2D boundary);

    /**
     * Renders an object in the view that has a different image depending by
     * direction he's following.
     * 
     * @param entity    {@link GameEntity} the entity to be rendered
     * @param direction {@link EntityDirection} the direction that the entity is
     *                  following, used to understand which image use
     * @param boundary  {@link Rectangle2D} the boundary of the entity
     */
    void render(GameEntity entity, EntityDirection direction, Rec2D boundary);

    /**
     * Getter for stage.
     * 
     * @return current {@link Stage}
     */
    Stage getStage();

    /**
     * Getter for canvas.
     * 
     * @return current {@link Canvas}
     */
    Canvas getCanvas();

    /**
     * Sets the new canvas.
     * 
     * @param canvas {@link Canvas} new canvas
     */
    void setCanvas(Canvas canvas);

    /**
     * Getter for default gameResolution.
     * 
     * @return {@link Dimension2D} game-resolution
     */
     Dim2D getDefaultGameResolution();

    /**
     * Getter for actual game resolution, note that this may change.
     * 
     * @return {@link Dimension2D} actual game resolution
     */
     Dim2D getActualGameResolution();

    /**
     * Setter for the game resolution.
     * 
     * @param width  stage weight
     * @param height stage height
     */
    void setGameResolution(double width, double height);
}
