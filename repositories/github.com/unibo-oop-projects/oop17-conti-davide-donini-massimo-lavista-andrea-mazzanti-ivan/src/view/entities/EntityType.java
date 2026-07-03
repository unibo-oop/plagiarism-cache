package view.entities;

import javafx.scene.image.Image;

/**
 * Enumeration used to classify game's entities.
 */
public enum EntityType {
    /**
     * All types of entities.
     */
    /**
     * spaceship.
     */
    SPACESHIP("spaceship.png"),
    /**
     * activeEnemy.
     */
    ACTIVE_ENEMY("activeEnemy.png"),
    /**
     * passiveEnemy.
     */
    AIM_ENEMY("aimEnemy.png"),
    /**
     * passiveEnemy.
     */
    PASSIVE_ENEMY("passiveEnemy.jpg"),
    /**
     * kamikaze.
     */
    KAMIKAZE("kamikaze.jpg"),
    /**
     * x3ShootEnemy.
     */
    X3_SHOOT_ENEMY("x3ShootEnemy.jpg"),
    /**
     * starShootEnemy.
     */
    STAR_SHOOT_ENEMY("starShootEnemy.png"),
    /**
     * enemyBoss.
     */
    ENEMY_BOSS("enemyBoss.png"),
    /**
     * lifePowerUp.
     */
    LIFE_POWER_UP("lifePowerUp.jpg"),
    /**
     * attackPowerUp.
     */
    ATTACK_POWER_UP("damagePowerUp.jpg"),
    /**
     * rateOfFirePowerUp.
     */
    RATE_OF_FIRE_POWER_UP("rateOfFirePowerUp.jpg"),
    /**
     * defensePowerUp.
     */
    DEFENSE_POWER_UP("defensePowerUp.jpg"),
    /**
     * Friendly bullet.
     */
    FIRENDLY_BULLET("bullet5.png"),
    /**
     * Generic enemy bullet.
     */
    GENERIC_ENEMY_BULLET("genericBullet.png"),
    /**
     * Aim enemy bullet.
     */
    AIM_ENEMY_BULLET("aimBullet.png"),
    /**
     * x3 shoot enemy bullet.
     */
    X3_ENEMY_BULLET("x3bulletshoot.png"),
    /**
     * Star shoot enemy bullet.
     */
    STAR_ENEMY_BULLET("starBullet.png");

    private static final String PATH = "/view/images/";
    private Image picture;

    /**
     * 
     * @param imageName,
     *            name of the image stored in the file system
     */
    EntityType(final String imageName) {
        this.picture = new Image(getClass().getResourceAsStream(PATH + imageName));
    }

    /**
     * 
     * @return the entity's picture
     */
    public Image getPicture() {
        return this.picture;
    }

}
