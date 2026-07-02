package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.controllers.entity.CharacterController;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;
import it.unibo.aknightstale.views.interfaces.MapView;

import java.util.List;

/**
 * This interface manages the game world and everything that happens inside it, such as the spawn of entities,
 * or draw the game world, etc... .
 */
public interface MapController extends Controller<MapView> {

    /**
     * The constant NUM_COL.
     */
    int NUM_COL = 48;
    /**
     * The constant NUM_ROW.
     */
    int NUM_ROW = 27;

    /**
     * Draw map.
     */
    void drawMap();

    /**
     * Update the size of the tiles when the size of Windows changes.
     */
    void updateScreenSize();

    /**
     * Draw the enemies in the game world.
     */
    void drawEnemies();

    /**
     * Reposition all the entities when window's size change.
     */
    void repositionEntities();

    /**
     * Draw player in the game world.
     */
    void drawPlayer();

    /**
     * Update the entities and check if the game is over.
     */
    void update();

    /**
     * Gets the player.
     *
     * @return the player
     */
    CharacterController<? extends Character, ? extends AnimatedEntityView> getPlayer();

    /**
     * Makes the player stop in the game world.
     */
    void setIdlePlayer();

    /**
     * Update player.
     *
     * @param direction the new player's direction.
     */
    void updatePlayer(Direction direction);

    /**
     * Makes the player attack.
     */
    void playerAttack();

    /**
     * Makes enemies move in a new direction.
     */
    void moveEnemies();

    /**
     * Get the enemies in the map.
     */
    List<CharacterController<? super Character, ? super AnimatedEntityView>> getEnemies();

    /**
     * set a spawn position to entity.
     */
    void setSpawnPosition(CharacterController<? super Character, ? super AnimatedEntityView> entity);

    /**
     * Switch to main menu.
     */
    void returnToMainMenu();
}

