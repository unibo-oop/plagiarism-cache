package model.entities;

import java.util.Random;

import model.ai.BossAI;
import model.ai.StandardAI;
import model.hitbox.HitboxImpl;
import model.hitbox.Hitbox;
import model.hitbox.HitboxCircle;
import model.hitbox.HitboxRectangle;
import model.map.StandardRoom;
import model.strategies.RandomMovement;
import model.strategies.ShootCross;
import model.strategies.ShootTheTarget;
import model.strategies.Stactionary;

/**
 * 
 * Constructs all the type of enemies.
 *
 */
public final class Enemies {

    private Enemies() {
    }

    /**
     * Constructs a new enemy of the type provided.
     * 
     * @param e
     *            The type of enemy.
     * @return The enemy associated with the type provided.
     */
    public static Enemy getEnemy(final CharacterType e) {
        switch (e) {
        case TURRET:
            return getTurret();
        case TANK:
            return getTank();
        case BOSS:
            return getFinalBoss();
        default:
            return null;
        }
    }

    private static Hitbox getRandomPosition() {
        final HitboxRectangle hr = StandardRoom.getRoomSpace();
        final Random r = new Random();
        return new HitboxImpl(hr.getX() + r.nextDouble() * hr.getWidth(), hr.getY() + r.nextDouble() * hr.getHeight());
    }

    private static Enemy getTurret() {
        final Hitbox position = getRandomPosition();
        final CharacterType e = CharacterType.TURRET;
        final BulletType b = BulletType.BULLET_TURRET;
        return new Enemy(new HitboxCircle(position.getX(), position.getY(), e.getRadius()),
                new StandardAI(new Stactionary(), new ShootTheTarget()), e.getSteps(), e.getDamage(), e.getLife(),
                e.getKnockbackDelay(), e.getFireRate(), b.getDamage(), b.getRange(), b.getSteps());
    }

    private static Enemy getTank() {
        final Hitbox position = getRandomPosition();
        final CharacterType e = CharacterType.TANK;
        final BulletType b = BulletType.BULLET_TANK;
        return new Enemy(new HitboxCircle(position.getX(), position.getY(), e.getRadius()),
                new StandardAI(new RandomMovement(90), new ShootCross()), e.getSteps(), e.getDamage(), e.getLife(),
                e.getKnockbackDelay(), e.getFireRate(), b.getDamage(), b.getRange(), b.getSteps());
    }

    private static Enemy getFinalBoss() {
        final HitboxRectangle position = StandardRoom.getRoomSpace();
        final double x = position.getX() + position.getWidth() / 2;
        final double y = position.getY() + position.getHeight() / 2;
        final CharacterType e = CharacterType.BOSS;
        final BulletType b = BulletType.BULLET_BOSS;

        return new Enemy(new HitboxCircle(x, y, e.getRadius()), new BossAI(), e.getSteps(), e.getDamage(), e.getLife(),
                e.getKnockbackDelay(), e.getFireRate(), b.getDamage(), b.getRange(), b.getSteps());
    }

}
