package tmw.view.level;

import java.util.List;
import java.util.Optional;
import javafx.geometry.Dimension2D;
import javafx.scene.Camera;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import tmw.common.CharacterStates;
import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.Rec2D;
import tmw.controller.item.AbstractItemController;
import tmw.model.entities.GameEntity;
import tmw.view.MainView;

/**
 * Interface for a generic view counterpart of a level. Allows to operate on
 * view aspects of a level such as get the whole scene.
 * 
 * @version 1.2
 *
 */
public interface GameLevelView {

    /**
     * Getter for camera in this level.
     * 
     * @return {@link Camera} camera reference
     */
    Camera getCamera();

    /**
     * Getter for hud in this level.
     * 
     * @return {@link Optional<Parent>} represents hud
     */
    Optional<Parent> getHud();

    /**
     * Allows to set for this level such hud.
     * 
     * @param hud {@link Parent} hud to set
     */
    void setHud(Parent hud);

    /**
     * Set background image.
     * 
     * @param img {@link Image} image to set
     */
    void setBackground(Image img);

    /**
     * Getter for this level scene.
     * 
     * @return {@link Scene} level scene
     */
    Scene getLevelScene();

    /**
     * Getter for main game resolution.
     * 
     * @return {@link Dimension2D} game resolution
     */
    Dim2D getGameRes();

    /**
     * Allows to render entities with one image on screen by calling.
     * 
     * @param entity   - the entity to render
     * @param boundary - the entity's boundary
     */
    void render(GameEntity entity, Rec2D boundary);

    /**
     * Allows to render entities whom images depends on which direction is following
     * on screen by calling.
     * 
     * @param entity    - the entity to render
     * @param direction - the direction that the entity is following, used to
     *                  understand which image use
     * @param boundary  - the entity's boundary
     */
    void render(GameEntity entity, EntityDirection direction, Rec2D boundary);

    /**
     * Allows to render items.
     * 
     * @param itemController abstract item controller
     */
    void render(AbstractItemController itemController);

    /**
     * This method resets the stack view by clearing previous frames and by
     * displaying again background.
     */
    void resetViewStack();

    /**
     * Getter for main view of game.
     * 
     * @return {@link MainView} view of game
     */
    MainView getMainView();

    /**
     * Getter for command list.
     * 
     * @return {@link CharacterStates} list of states represents commands
     */
    List<CharacterStates> getCommandList();
}
