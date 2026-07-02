package model.collision;

import java.util.List;

import model.Model;
import model.entity.DynamicEntity;
import model.player.Player;
/**
 * 
 * Interface that manages all the collisions with the Player.
 *
 */
public interface CollisionManager {

    /**
     * Checks all the collisions with the player.
     * @param pl the Player
     * @param objects the actual list of Entity
     * @param model the Model
     */
    void playerCollidesWidth(Player pl, List<DynamicEntity> objects, Model model);

}
