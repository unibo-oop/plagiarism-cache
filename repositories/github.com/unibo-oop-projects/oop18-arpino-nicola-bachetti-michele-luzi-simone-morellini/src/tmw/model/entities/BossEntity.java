package tmw.model.entities;

/**
 * This interface is implemented by the boss of the game.
 */
public interface BossEntity extends Enemy {

    /**
     * This method is called to know if the boss is ready for charging the main
     * character.
     * 
     * @return true if the boss is ready to charge, false otherwise.
     */
    boolean readyForSpecialAttack();

    /**
     * This method is called to know if the boss is charging the main character.
     * 
     * @return true if the boss is charging, false otherwise
     */
    boolean isUsingTheSpecialAttack();

    /**
     * This method is called when the boss start charging.
     */
    void startSpecialAttack();

    /**
     * This method is called when the boss has to stop charging.
     */
    void stopSpecialAttack();
}
