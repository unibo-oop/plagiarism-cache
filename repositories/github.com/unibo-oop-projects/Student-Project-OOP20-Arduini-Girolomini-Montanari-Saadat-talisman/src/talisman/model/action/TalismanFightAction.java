package talisman.model.action;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import talisman.Controllers;
import talisman.controller.battle.BattleController;
import talisman.controller.battle.BattleControllerImpl;

import talisman.model.battle.BattleModel;
import talisman.model.battle.BattleModelImpl;
import talisman.model.battle.EnemyInfos;
import talisman.model.battle.EnemyModel;
import talisman.model.character.CharacterModel;

import talisman.view.BattleWindow;

/**
 * A action that makes the player battle with an enemy.
 * 
 * @author Alberto Arduini
 *
 */
public class TalismanFightAction  extends TalismanActionImpl {
    private static final long serialVersionUID = 4451903715879720847L;
    private static final String DESCRIPTION_FORMAT = "You have to fight a %s";
    private final int enemy;

    /**
     * Creates a new fight action.
     * 
     * @param enemy the index of the enemy in the enemies list to fight
     */
    public TalismanFightAction(final int enemy) {
        this.enemy = enemy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return String.format(TalismanFightAction.DESCRIPTION_FORMAT, EnemyInfos.getEnemyByIndex(enemy).getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void apply() {
        BattleModel battleModel;
        CharacterModel characterModel = Controllers.getCharactersController().getCurrentPlayer().getCurrentCharacter();
        EnemyModel enemyModel = EnemyInfos.getEnemyByIndex(enemy);
        if (EnemyInfos.hasStrength(enemy)) {
            battleModel = new BattleModelImpl(characterModel.getStrength(), enemyModel.getStrength());
        } else {
            battleModel = new BattleModelImpl(characterModel.getCraft(), enemyModel.getCraft());
        }
        BattleController battleController = new BattleControllerImpl(characterModel, enemyModel, battleModel);
        final BattleWindow window = new BattleWindow(battleController);
        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(final WindowEvent e) {
                TalismanFightAction.this.actionEnded();
            }
        });
    }

    /**
     * Gets the enemy to fight.
     * 
     * @return the index of the enemy in the enemies list
     */
    public int getEntityIndex() {
        return this.enemy;
    }
}
