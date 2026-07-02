package ala.controllers;

import java.util.List;

public interface ParadiseShootingEnemiesAbilitiesController {
    /**
     * Method that makes paradise enemies stop and shoot arrows consequently.
     * @param dynamicObjectsInScene
     */
    void fire(List<DynamicGameObjectController> dynamicObjectsInScene);
}
