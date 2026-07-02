package model.handler;

import java.util.List;

import model.Portal;
import model.gameObject.GameObject;
import model.player.Player;

public interface HandlerInterface {

    /**
     * @return gameObject list
     * 
     *         getter gameObject list
     */
    List<GameObject> getGameObjectList();

    /**
     * @param toRemove
     * 
     *                 method to remove a gameObject from the list
     */
    void removeGameObject(GameObject toRemove);

    /**
     * @param toAdd
     * 
     *              method to add a gameObject into the gameObject list
     */
    void addGameObject(GameObject toAdd);

    /**
     * @return player gameObj
     * 
     *         getter Player object from the gameObject list
     */
    Player getPlayer();

    /**
     * @return enemies list gameObj
     * 
     *         getter Enemies list object from the gameObject list
     */
    List<GameObject> getEnemyList();

    /**
     * @return wall list gameObj
     * 
     *         getter wall list object from the gameObject list
     */
    List<GameObject> getWallList();

    /**
     * @return staticObject list
     * 
     *         getter staticObject list from the gameObject list
     */
    List<GameObject> getOtherList();

    /**
     * 
     * @return portalList list
     * 
     *         getter portal list from the gameObject list
     */
    List<Portal> getPortalList();

}
