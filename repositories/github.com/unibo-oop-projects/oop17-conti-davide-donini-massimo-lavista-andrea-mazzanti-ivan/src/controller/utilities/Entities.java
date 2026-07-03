package controller.utilities;

import model.decorator.EnemyDecorator;
import model.decorator.KamikazeEnemy;
import model.decorator.StarShootEnemy;
import model.decorator.X3ShootEnemy;
import model.entities.ActiveEnemy;
import model.entities.AimEnemy;
import model.entities.Bullet;
import model.entities.Enemy;
import model.entities.Entity;
import model.entities.Spaceship;
import model.entities.boss.EnemyBoss;
import model.entities.powerup.PowerUp;
import view.entities.EntityType;
import view.entities.ViewEntity;
import view.entities.ViewEntityImpl;

/**
 * Class with methods used to operate on entities in the controller.
 */
public final class Entities {

    private Entities() {
    }

    /**
     * 
     * @param entity
     *            model's entity
     * @return the model's entity converted into view's entity
     */
    public static ViewEntity convertEntityForView(final Entity entity) {
        EntityType type;
        // spaceship
        if (entity instanceof Spaceship) {
            type = EntityType.SPACESHIP;
        } else {
            // powers ups
            if (entity instanceof PowerUp) {
                switch (((PowerUp) entity).getType()) {
                case LIFE:
                    type = EntityType.LIFE_POWER_UP;
                    break;
                case DAMAGE:
                    type = EntityType.ATTACK_POWER_UP;
                    break;
                case RATE_OF_FIRE:
                    type = EntityType.RATE_OF_FIRE_POWER_UP;
                    break;
                default:
                    type = EntityType.DEFENSE_POWER_UP;
                    break;
                }
            } else {
                // enemies
                if (entity instanceof Enemy) {
                    final Enemy enemy;
                    if (entity instanceof EnemyDecorator && !(entity instanceof KamikazeEnemy)) {
                        enemy = ((EnemyDecorator) entity).getDecoratedEnemy();
                    } else {
                        enemy = (Enemy) entity;
                    }
                    if (enemy instanceof KamikazeEnemy) {
                        type = EntityType.KAMIKAZE;
                    } else {
                        if (enemy instanceof StarShootEnemy) {
                            type = EntityType.STAR_SHOOT_ENEMY;
                        } else {
                            if (enemy instanceof X3ShootEnemy) {
                                type = EntityType.X3_SHOOT_ENEMY;
                            } else {
                                if (enemy instanceof EnemyBoss) {
                                    type = EntityType.ENEMY_BOSS;
                                } else {
                                    if (enemy instanceof AimEnemy) {
                                        type = EntityType.AIM_ENEMY;
                                    } else {
                                        if (enemy instanceof ActiveEnemy) {
                                            type = EntityType.ACTIVE_ENEMY;
                                        } else {
                                            type = EntityType.PASSIVE_ENEMY;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    // bullets
                    switch (((Bullet) entity).getBulletType()) {

                    case FRIENDLY:
                        type = EntityType.FIRENDLY_BULLET;
                        break;
                    case AIM_ENEMY:
                        type = EntityType.AIM_ENEMY_BULLET;
                        break;
                    case X3_ENEMY:
                        type = EntityType.X3_ENEMY_BULLET;
                        break;
                    case STAR_ENEMY:
                        type = EntityType.STAR_ENEMY_BULLET;
                        break;
                    default:
                        type = EntityType.GENERIC_ENEMY_BULLET;
                        break;
                    }
                }
            }
        }
        return new ViewEntityImpl(entity.getShape(), type);
    }
}
