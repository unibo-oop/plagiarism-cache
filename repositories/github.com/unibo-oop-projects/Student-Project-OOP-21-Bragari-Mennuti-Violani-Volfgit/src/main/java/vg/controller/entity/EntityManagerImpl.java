package vg.controller.entity;

import vg.controller.entity.boss.BossController;
import vg.controller.entity.boss.StaticFactoryBossController;
import vg.controller.entity.mystery_box.MysteryBoxController;
import vg.controller.entity.mystery_box.manager.MysteryBoxManager;
import vg.controller.entity.mystery_box.manager.MysteryBoxManagerImpl;
import vg.controller.gameBoard.GameBoardController;
import vg.model.Stage;
import vg.sound.manager.SoundManager;
import vg.utils.V2D;

import java.util.List;

/**
 * This class manages the entities of the game.
 */
public class EntityManagerImpl implements EntityManager {
    private final MysteryBoxManager mysteryBoxManager;
    private BossController boss;
    private SoundManager soundManager;
    public EntityManagerImpl() {
        this.mysteryBoxManager = new MysteryBoxManagerImpl();
        this.boss = StaticFactoryBossController.createRound1();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void initializeRound(final GameBoardController gameController) {
        this.mysteryBoxManager.initializeRound(gameController);
        this.boss.setInParentNode(gameController);
    }

    @Override
    public void initializeNewRound(final GameBoardController gameController) {
        gameController.getGameAreaNode().clear();
        this.mysteryBoxManager.initializeRound(gameController);
        this.boss = StaticFactoryBossController.createRound1();
        this.boss.setInParentNode(gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MysteryBoxController> getMysteryBoxList() {
        return this.mysteryBoxManager.getMysteryBoxList();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BossController getBoss() {
        return this.boss;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void countingTimeMysteryBox(final long elapsedTime, final Stage<V2D> stage) {
        this.mysteryBoxManager.updateTimeBlinking(elapsedTime);
        this.mysteryBoxManager.showPickUpMysteryBox(elapsedTime);
        this.mysteryBoxManager.updateTimeIfAbilityActive(elapsedTime, stage);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void checkMysteryBoxOnBorder(final Stage<V2D> stage, final GameBoardController gameController) {
        this.mysteryBoxManager.checkMysteryBoxOnBorder(stage, gameController, this.soundManager);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void moveEntityBoss(final long elapsedTime) {
        this.boss.move();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setSoundManager(final SoundManager soundManager) {
        this.soundManager = soundManager;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateAnimation() {
        this.boss.updateAnimation();
        this.mysteryBoxManager.updateAnimation();
    }
}
