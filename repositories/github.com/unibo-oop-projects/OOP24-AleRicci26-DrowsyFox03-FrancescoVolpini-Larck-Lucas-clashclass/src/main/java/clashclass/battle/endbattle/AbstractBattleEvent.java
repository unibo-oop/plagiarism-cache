package clashclass.battle.endbattle;

import clashclass.battle.manager.BattleManagerController;
import clashclass.ecs.GameObject;


import java.util.Optional;

/**
 * Abstract class for battle events that can end a battle.
 * This class provides a base implementation for ending battles
 * and should be extended by concrete battle event classes.
 */
public abstract class AbstractBattleEvent implements BattleEvent {
    private final BattleManagerController battleManagerController;

    /**
     * Construct the battle event.
     *
     * @param battleManagerController the battle manager controller
     */
        public AbstractBattleEvent(final BattleManagerController battleManagerController) {
        this.battleManagerController = battleManagerController;
    }

    /**
     * Protected method to end the battle.
     * This method is called by concrete implementations to perform
     * the actual battle ending logic.
     *
     * @param gameObject the game object associated with the battle
     */
    protected void endBattleInternal(final GameObject gameObject) {
        // Perform battle ending logic
        if (Optional.ofNullable(gameObject).isPresent()) {
            gameObject.destroy();
        }

        // Additional battle ending logic can be added here
        // such as updating game state, showing results, etc.
        if (this.battleManagerController != null) {
            this.battleManagerController.endBattle();
        }
    }

    /**
     * {@inheritDoc}
     * This method should be implemented by concrete subclasses to define
     * when and how to end the battle.
     */
    @Override
    public abstract void endBattle();
}
