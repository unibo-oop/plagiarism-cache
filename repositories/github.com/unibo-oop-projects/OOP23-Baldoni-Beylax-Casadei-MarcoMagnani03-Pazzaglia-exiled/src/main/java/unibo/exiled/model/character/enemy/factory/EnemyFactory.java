package unibo.exiled.model.character.enemy.factory;

import unibo.exiled.model.character.enemy.Enemy;

/**
 * The EnemyFactory interface provides methods for creating different types of enemies.
 */
public interface EnemyFactory {
    /**
     * Creates a goblin enemy.
     *
     * @return The created goblin enemy.
     */
    Enemy createGoblin();

    /**
     * Creates a brutus enemy.
     *
     * @return The created brutus enemy.
     */
    Enemy createBrutus();

    /**
     * Creates a water Boss.
     *
     * @return A water enemy that drops one of the crystals to end the game.
     */
    Enemy createWaterBoss();

    /**
     * Creates a fire boss.
     *
     * @return A fire enemy that drops one of the crystals to end the game.
     */
    Enemy createFireBoss();

    /**
     * Creates a bolt boss.
     *
     * @return A bolt enemy that drops one of the crystals to end the game.
     */
    Enemy createBoltBoss();

    /**
     * Creates a grass boss.
     *
     * @return A grass enemy that drops one of the crystals to end the game.
     */
    Enemy createGrassBoss();

    /**
     * Creates a random enemy.
     *
     * @return A random enemy in the factory.
     */
    Enemy createRandom();

    /**
     * Creates a Whirler.
     *
     * @return A whirler enemy.
     */
    Enemy createWhirler();

    /**
     * Creates a Wave Breaker.
     *
     * @return A WaveBreaker enemy.
     */
    Enemy createWaveBreaker();

    /**
     * Creates an AquaShade enemy.
     *
     * @return An AquaShade enemy.
     */
    Enemy createAquaShade();

    /**
     * Creates a "Magnetaldo" enemy.
     *
     * @return An Enemy implementation of Mangnetaldo.
     */
    Enemy createMagnetaldo();

    /**
     * Creates a "Leafy" enemy.
     *
     * @return An Enemy implementation of Leafy
     */
    Enemy createLeafy();
}
