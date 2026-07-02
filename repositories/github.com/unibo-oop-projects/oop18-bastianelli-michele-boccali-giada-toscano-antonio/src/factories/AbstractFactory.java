package factories;

import org.jbox2d.common.Vec2;

import controller.entities.Coin;
import controller.entities.Enemy;
import controller.entities.Platform;
import controller.entities.Player;
import enumerators.CoinType;
import enumerators.EnemyCharacter;
import enumerators.PlatformType;
import enumerators.PlayerCharacter;

/**
 * Abstract Factory to create all entities by calling the appropriate method.
 */
public final class AbstractFactory {

    private AbstractFactory() {
    }

    /**
     * Create a Player.
     * 
     * @param playerCharacter type of the player to create
     * @param position        where to create
     * @return a {@link Player} entity
     */
    public static Player createPlayer(final PlayerCharacter playerCharacter, final Vec2 position) {
        final FactoryPlayer fPlayer = new FactoryPlayer();
        return (Player) fPlayer.createEntity(playerCharacter, position);
    }

    /**
     * Create an Enemy.
     * 
     * @param enemyCharacter type of the enemy to create
     * @param position       where to create
     * @return a {@link Enemy} entity
     */
    public static Enemy createEnemy(final EnemyCharacter enemyCharacter, final Vec2 position) {
        final FactoryEnemy fEnemy = new FactoryEnemy();
        return (Enemy) fEnemy.createEntity(enemyCharacter, position);
    }

    /**
     * Create a Platform.
     * 
     * @param platformType type of the platform to create
     * @param position     where to create
     * @return a {@link Platform} entity
     */
    public static Platform createPlatform(final PlatformType platformType, final Vec2 position) {
        final FactoryPlatform fPlatform = new FactoryPlatform();
        return (Platform) fPlatform.createEntity(platformType, position);
    }

    /**
     * Create a Coin.
     * 
     * @param coinType type of coin to create
     * @param position where to create
     * @return a {@link Coin} entity
     */
    public static Coin createCoin(final CoinType coinType, final Vec2 position) {
        final FactoryCoin factoryCoin = new FactoryCoin();
        return (Coin) factoryCoin.createEntity(coinType, position);
    }
}
