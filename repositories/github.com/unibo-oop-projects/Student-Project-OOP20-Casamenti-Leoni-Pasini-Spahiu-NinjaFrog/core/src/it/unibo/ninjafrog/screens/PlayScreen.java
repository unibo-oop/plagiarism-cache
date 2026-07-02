package it.unibo.ninjafrog.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.ninjafrog.game.utilities.Pair;
import it.unibo.ninjafrog.world.Collidable;

/**
 * Definition of the PlayScreen interface, which extends
 * {@link com.badlogic.gdx.Screen Screen}.
 */
public interface PlayScreen extends Screen {
    /**
     * Method to be called when a new orange has to be created.
     * 
     * @param position A {@link it.unibo.ninjafrog.game.utilities.Pair Pair}
     *                 containing X and Y values where the new object will be
     *                 created.
     */
    void spawnOrange(Pair<Float, Float> position);

    /**
     * Method to be called when a new melon has to be created.
     * 
     * @param position A {@link it.unibo.ninjafrog.game.utilities.Pair Pair}
     *                 containing X and Y values where the new object will be
     *                 created.
     */
    void spawnMelon(Pair<Float, Float> position);

    /**
     * Method to be called when a new cherry has to be created.
     * 
     * @param position A {@link it.unibo.ninjafrog.game.utilities.Pair Pair}
     *                 containing X and Y values where the new object will be
     *                 created.
     */
    void spawnCherry(Pair<Float, Float> position);

    /**
     * Setter of the double jump ability.
     * 
     * @param b True if you want to enable it, false otherwise.
     */
    void setDoubleJumpAbility(boolean b);

    /**
     * Method to be called in order to add a score to global score count.
     * 
     * @param entity a {@link it.unibo.ninjafrog.world.Collidable Collidable}
     *               entity, which defines a
     *               {@link it.unibo.ninjafrog.world.Collidable#getScore()
     *               getScore()} method.
     */
    void addScore(Collidable entity);

    /**
     * Add a new life to the player.
     */
    void addLife();

    /**
     * Remove a life from the player.
     */
    void removeLife();

    /**
     * Set the WinScreen.
     */
    void setWinScreen();

    /**
     * Set the GameOverScreen.
     */
    void setGameOverScreen();

    /**
     * Set the MenuScreen.
     */
    void setMenuScreen();

    /**
     * Getter of the ninja X-axis position in the world.
     * 
     * @return The Ninja X-axis float value.
     */
    float getNinjaXPosition();

    /**
     * Getter of the TiledMap.
     * 
     * @return The {@link com.badlogic.gdx.maps.tiled.TiledMap TiledMap} used by the
     *         PlayScreen.
     */
    TiledMap getMap();

    /**
     * Getter of the World.
     * 
     * @return The {@link com.badlogic.gdx.physics.box2d.World World} used by the
     *         PlayScreen.
     */
    World getWorld();

    /**
     * Getter of the TextureAtlas.
     * 
     * @return The {@link com.badlogic.gdx.graphics.g2d.TextureAtlas TextureAtlas}
     *         used by the PlayScreen.
     */
    TextureAtlas getAtlas();
}
