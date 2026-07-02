package controllers.collision.collisionsPlayer;

import model.gameObject.GameObject;
import model.player.Player;

public interface CollisionsPlayerInterface {

    /**
     * @param obj1
     * @param obj2
     * 
     *             method to check enemy's collisions
     */
    void collisionsInGame(Player obj1, GameObject obj2);

}
