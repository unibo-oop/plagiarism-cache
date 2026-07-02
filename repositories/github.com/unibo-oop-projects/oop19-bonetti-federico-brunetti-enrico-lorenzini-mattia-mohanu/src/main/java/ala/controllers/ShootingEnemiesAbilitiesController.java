package ala.controllers;

import java.util.List;

public interface ShootingEnemiesAbilitiesController {

    /**
     * Shooting enemies firing method.
     * 
     * @param x
     *        Lucifer x position
     * @param y
     *        Lucifer y position
     * @param dynamicObjectsInScene
     */
    void fire(double x, double y, List<DynamicGameObjectController> dynamicObjectsInScene);
}
