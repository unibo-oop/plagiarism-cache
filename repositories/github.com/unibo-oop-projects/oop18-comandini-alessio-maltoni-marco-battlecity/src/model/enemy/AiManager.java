package model.enemy;

/**
 * An object that orchestrate all the Artificial Intelligences in the game.
 *
 */
public interface AiManager {
    /**
     * Start all the artificial intelligences.
     */
    void startAll();

    /**
     * Stop all the artificial intelligences.
     */
    void stopAll();

    /**
     * ask to all the Artificial intelligences to generate commands.
     */
    void generateAiCommands();

    /**
     * delete all artificial intelligences when a new level is occurred.
     */
    void resetAll();

    /**
     * Create a new Artificial Intelligences with a specific Algorithm.
     * 
     * @param aiCommandGenerator an algorithm that manage the generation of new
     *                           command
     * @return a new enemy AI
     */
    EnemyAi getNewEnemyAI(AiCommandGenerator aiCommandGenerator);

}
