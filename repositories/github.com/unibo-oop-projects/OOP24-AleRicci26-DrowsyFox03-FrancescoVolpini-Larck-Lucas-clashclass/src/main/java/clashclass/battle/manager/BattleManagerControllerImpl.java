package clashclass.battle.manager;

import clashclass.ai.behaviourtree.BehaviourTree;
import clashclass.commons.Vector2D;
import clashclass.ecs.GameObject;
import clashclass.elements.troops.TroopType;
import clashclass.gamestate.GameStateManager;
import clashclass.village.Village;


import java.util.Set;

/**
 * Represents a {@link BattleManagerController} implementation.
 */
public class BattleManagerControllerImpl implements BattleManagerController {
    private final BattleManagerModel model;
    private final BattleManagerView view;

    /**
     * Constructs the controller.
     *
     * @param model the model
     * @param view the view
     */
        public BattleManagerControllerImpl(final BattleManagerModel model, final BattleManagerView view) {
        this.model = model;
        this.model.buildBattleReport(view.buildBattleReportView());
        this.model.setController(this);

        this.view = view;
        this.view.setController(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameStateManager(final GameStateManager gameStateManager) {
        this.model.setGameStateManager(gameStateManager);

        final var battleVillage = this.model.getBattleVillage();
        final var gameEngine = this.model.getGameStateManager().getGameEngine();

        battleVillage.getGroundObjects().forEach(x -> {
            gameEngine.addGameObject(x);
            x.getComponentOfType(BehaviourTree.class).ifPresent(BehaviourTree::start);
        });
        battleVillage.getGameObjects().forEach(x -> {
            gameEngine.addGameObject(x);
            x.getComponentOfType(BehaviourTree.class).ifPresent(BehaviourTree::start);
        });

        this.view.setArmyCampTroops(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearScene() {
        this.model.clearScene();
        this.view.clearScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endBattle() {
        this.view.endBattle(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCurrentSelectedTroop(final TroopType troop) {
        this.model.setCurrentSelectedTroop(troop);
        this.view.updateArmyCampTroopsCount(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createTroop(final Vector2D position) {
        this.model.createTroop(position);
        this.view.updateArmyCampTroopsCount(this.model);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<GameObject> getActiveTroops() {
        return this.model.getActiveTroops();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Village getBattleVillage() {
        return this.model.getBattleVillage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateVillageState(final GameObject destroyedBuilding) {
        this.model.updateVillageState(destroyedBuilding);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateTroopsState(final GameObject destroyedTroop) {
        this.model.updateTroopsState(destroyedTroop);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBattleTimeFinished() {
        return this.model.isBattleTimeFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean areAllTroopsDead() {
        return this.model.areAllTroopsDead();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long getBattleRemainingTime() {
        return this.model.getBattleRemainingTime();
    }
}
