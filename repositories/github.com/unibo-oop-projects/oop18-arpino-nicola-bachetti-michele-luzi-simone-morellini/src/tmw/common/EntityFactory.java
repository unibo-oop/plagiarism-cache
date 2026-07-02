package tmw.common;

import tmw.model.entities.BossEntity;
import tmw.model.entities.BulletEntity;
import tmw.model.entities.Enemy;
import tmw.model.entities.MilkEntity;

/**
 * for the creation of the entity of game.
 */
public interface EntityFactory {

    /**
     * This method is to be called when the game is resized to update this
     * information in the factory.
     * 
     * @param newFieldSize - the new resolution of the game
     */
    void setSize(Dim2D newFieldSize);

    /**
     * Create and return a new instance of the main character.
     * 
     * @param pos - the initial position of the main character as a {@link P2d}
     * @param vel - the initial velocity for the main character as a {@link V2d}
     * @return a new instance of the main character
     */
    MilkEntity createMilk(P2d pos, V2d vel);

    /**
     * Create and returns a new instance of the enemy Abbraccio.
     * 
     * @param pos - the initial position of the enemy as a {@link P2d}
     * @param vel - the initial velocity of the enemy as a {@link V2d}
     * @return a new instance of the enemy Abbraccio
     */
    Enemy createAbbraccio(P2d pos, V2d vel);

    /**
     * Create and returns a new instance of the enemy Stelle.
     * 
     * @param pos - the initial position of the enemy as a {@link P2d}
     * @param vel - the initial velocity of the enemy as a {@link V2d}
     * @return a new instance of the enemy Stelle
     */
    Enemy createStelle(P2d pos, V2d vel);

    /**
     * Create and returns a new instance of an enemy bullet, meaning a bullet with
     * default damage.
     * 
     * @param pos - the initial position of the bullet as a {@link P2d}
     * @param vel - the velocity of the bullet as a {@link V2d}
     * @return a new instance of a bullet
     */
    BulletEntity createEnemyBullet(P2d pos, V2d vel);

    /**
     * Create and returns a new instance of a bullet with a personalized value for
     * the damage.
     * 
     * @param pos    - the initial position of the bullet as a {@link P2d}
     * @param vel    - the velocity of the bullet as a {@link V2d}
     * @param damage - the damage value of the bullet
     * @return a new instance of a bullet with a personalized damage
     */
    BulletEntity createCharacterBullet(P2d pos, V2d vel, int damage);

    /**
     * Create and returns a new instance of the boss.
     * 
     * @param pos - the initial position of the boss as a {@link P2d}
     * @param vel - the velocity of the boss as a {@link V2d}
     * @return a new instance of a bullet with a personalized damage
     */
    BossEntity createBoss(P2d pos, V2d vel);
}
