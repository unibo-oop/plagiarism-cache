package ala.controllers;

public interface NormalLevelGeneratorController {
    /**
     * Create game loop.
     * 
     */
     void createGameLoop();
    /**
     * Method used in the CreateGameLoop method that moves objects in scene.
     * 
     */
    void moveObjectsInScene();

    /**
     * Method used in the CreateGameLoop method that make enemies attack.
     * 
     */
    void enemiesAttack();
}
