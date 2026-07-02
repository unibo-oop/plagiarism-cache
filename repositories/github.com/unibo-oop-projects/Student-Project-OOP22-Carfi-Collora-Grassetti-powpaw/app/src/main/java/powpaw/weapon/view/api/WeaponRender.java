package powpaw.weapon.view.api;

import java.util.List;

import javafx.scene.image.ImageView;
import powpaw.map.model.impl.BlockImpl;
import powpaw.weapon.model.api.Weapon;

/**
 * Interface for the WeaponRenderImpl class that implement WeaponRender
 * interface and is responsible for rendering the weapon sprite image based on
 * the current
 * weapon and its position.
 * 
 * @author Giacomo Grassetti
 */

public interface WeaponRender {

    /**
     * Setter for the terrains.
     * 
     * @param terrains An ArrayList of BlockImpl representing the terrains in a
     *                 game.
     */
    void setTerrains(List<BlockImpl> terrains);

    /**
     * Getter for an ArrayList of BlockImpl representing terrains.
     * 
     * @return An ArrayList of BlockImpl
     */
    List<BlockImpl> getTerrains();

    /**
     * Getter for an ImageView representing a weapon sprite.
     * 
     * @return An ImageView
     */
    ImageView getWeaponSprite();

    /**
     * Setter for the Weapon and updates its sprite image.
     * 
     * @param weapon The weapon to set
     */
    void setWeapon(Weapon weapon);

    /**
     * Method for updates the position, size, image, and visibility of a weapon
     * sprite.
     */
    void render();

}
