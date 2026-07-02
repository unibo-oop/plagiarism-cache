package model.enemy;

import enums.Sprite;
import model.entities.Tank;
import model.entities.TankImpl;
import model.entities.tankcomponents.ArmorComponent;
import model.entities.tankcomponents.BulletComponent;
import model.entities.tankcomponents.SpeedBulletComponent;
import model.entities.tankcomponents.SpeedComponent;
/**
 * A factory that create new enemy tank by a given type. 
 *
 */
public final class EnemyFactory {

    private EnemyFactory() {
    }

    /**
     * A method that from a given enemy type return a tank with the enemy
     * characteristics.
     * 
     * @param enemyType the type of the enemy to create
     * @return the tank create with the enemy characteristics attacched with an an
     *         AI
     */
    public static Tank getEnemy(final Enemy enemyType) {
        final Tank tank = new TankImpl(Sprite.ENEMY_NORMAL_TANK);
        switch (enemyType) {
        case ARMORED:
            initializeArmored(tank);
            break;

        case FAST:
            initializeFast(tank);
            break;

        case POWER:
            initializePower(tank);

            break;

        default:
            break;
        }
        return tank.attach(new BulletComponent());
    }

    private static void initializePower(final Tank tank) {
        tank.attach(new SpeedBulletComponent());
        tank.setSprite(Sprite.ENEMY_POWER_TANK);

    }

    private static void initializeFast(final Tank tank) {
        tank.attach(new SpeedComponent(tank));
        tank.setSprite(Sprite.ENEMY_FAST_TANK);
    }

    private static void initializeArmored(final Tank tank) {
        tank.attach(new ArmorComponent()).attach(new ArmorComponent()).attach(new ArmorComponent());
        tank.setSprite(Sprite.ENEMY_ARMOR_TANK);
    }

}
