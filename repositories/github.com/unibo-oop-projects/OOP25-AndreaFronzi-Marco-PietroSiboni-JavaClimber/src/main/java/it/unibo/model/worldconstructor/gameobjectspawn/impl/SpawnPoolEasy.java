package it.unibo.model.worldconstructor.gameobjectspawn.impl;

import it.unibo.model.score.api.ScoreManager;
import it.unibo.model.worldconstructor.gameobjectspawn.api.AbstractSpawnPool;
import it.unibo.model.worldconstructor.gameobjectspawn.platformspawn.impl.PairImpl;

/**
 * Implementation of PlatformPool for the "Easy" difficulty level.
 * Defines the probabilities and types of objects that spawn in easy mode.
 */
public class SpawnPoolEasy extends AbstractSpawnPool {

    /**
     * Constructs the easy platform pool and initializes the object lists.
     * 
     * @param width the width of the game world
     * @param height the height of the game world
     * @param scoreManager the score manager to be used for the factory add-on
     */
    public SpawnPoolEasy(final double width, final double height, final ScoreManager scoreManager) {
        super(width, height, scoreManager);

        initializeStaticPlatformPool();
        initializeMovingPlatformPool();
        initializeOnTouchPlatformPool();
        initializeMonsterPool();
        initializeGadgetPool();
        initializeMoneyPool();
    }

    private void initializeStaticPlatformPool() {
        this.staticPlatformPool.add(new PairImpl<>(1.0, this.director::normalPlatform));
    }

    private void initializeMovingPlatformPool() {
        this.movingPlatformPool.add(new PairImpl<>(1.0, this.director::movingPlatform));
    }

    private void initializeOnTouchPlatformPool() {
        this.onTouchPlatformPool.add(new PairImpl<>(1.0, this.director::movingOnTouchPlatform));
    }

    private void initializeMonsterPool() {
        this.monsterPool.add(new PairImpl<>(1.0, this.factoryAddOn::createEnemy));
    }

    private void initializeGadgetPool() {
        this.gadgetPool.add(new PairImpl<>(1.0, this.factoryAddOn::createElycap));
    }

    private void initializeMoneyPool() {
        this.moneyPool.add(new PairImpl<>(1.0, this.factoryAddOn::createCoin));
    }

}
