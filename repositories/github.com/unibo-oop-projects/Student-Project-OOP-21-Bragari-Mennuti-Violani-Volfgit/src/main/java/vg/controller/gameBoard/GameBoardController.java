package vg.controller.gameBoard;

import javafx.collections.ObservableList;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import vg.model.entity.dynamicEntity.DynamicEntity;
import vg.model.entity.dynamicEntity.player.Player;
import vg.utils.V2D;
import vg.view.player.PlayerViewController;

import java.util.List;
import java.util.Set;

/**
 * View controller of game board. It change view and is a bridge between main logic controller and controller of view.
 */
public interface GameBoardController {
    /**
     * Return game area panel dimension
     * @return Dimension of game area
     */
    Dimension2D getGameAreaDimension();

    /**
     * @return game area containing all visual game nodes.
     */
    Pane getGameArea();

    /**
     * Add new node to game area to be showed.
     * @param node JavaFX node to be showed in game
     */
    void addInGameArea(Node node);

    /**
     * Retrieve all nodes contained in game area pane.
     * @return list of game-area's nodes
     */
    ObservableList<Node> getGameAreaNode();

    /**
     * Update player view setting new position, show shield if active and show tail.
     * @param position {@link V2D} vector of current position of player
     * @param shieldActive boolean, to show or hide player's shield
     * @param tail list of {@link V2D} positions of player's tail
     */
    void updatePlayer(V2D position, boolean shieldActive, List<V2D> tail);

    /**
     * Initialize structure used to show and keep graphics entity.
     */
    void initMapView();

    /**
     * Update position of mosquitoes.
     * @param mosquitoes Set of {@link DynamicEntity}
     */
    void updateMosquitoesPosition(Set<DynamicEntity> mosquitoes);

    /**
     * Update boss position.
     * @param bossPos {@link V2D} new boss position
     */
    void updateBossPosition(V2D bossPos);

    /**
     * When border are updated and tail has created new border call this method to update border draw.
     * Are required only vertex of borders.
     * @param vertexBorder list of {@link V2D} vertex of borders
     */
    void updateBorders(List<V2D> vertexBorder);

    /**
     * Update remaining player's life indicator.
     * @param life int that represent player's life. admitted value from 0 to 6.
     */
    void updateLifeCounter(int life);

    /**
     * Update percentage indicator of map area cropped by player.
     * @param percentage percentage value from 0.0 to 100.0
     */
    void updatePercentage(double percentage);

    /**
     * Set current round number.
     * @param round int current round level
     */
    void setRound(int round);

    /**
     * Update currente player's score.
     * @param score player's score
     */
    void updateScoreText(int score);

    /**
     * Update high score label.
     * @param highScore high score has been done by a player
     */
    void setHighScoreText(int highScore);

    /**
     * Update shield countdown.
     * @param time double indicating remaining time of player's shield
     */
    void updateShieldTime(double time);
    /**
     * Get Player view.
     *
     * @return Player.
     */
    PlayerViewController getPlayer();
}
