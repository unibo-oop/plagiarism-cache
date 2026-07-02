package ala.controllers;

public interface BossLevelPattern {
    /**
     * it manage the program in sixtieth of second. Then execute every operation in this time.
     * 
     */
     void createGameLoop();

    /**
     * every kind of collision with Lucifer is spotted and managed by this method.
     * 
     */
     void checkLuciferCollisions();

    /**
     * every kind of collision with the boss is spotted and managed by this method.
     * 
     */
    void checkBossCollisions();
}
