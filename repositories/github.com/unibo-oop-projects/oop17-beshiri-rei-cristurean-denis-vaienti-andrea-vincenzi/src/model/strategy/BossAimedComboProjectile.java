package model.strategy;

import static model.animated.EntityStats.BOSS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import model.animated.Bullet;
import model.animated.BulletImpl;
import model.hitbox.CircleHitBox;
import model.hitbox.HitBox;
import model.utility.ModelUtility;
import model.utility.ProportionUtility;
import utility.ImageType;

/**
 * 
 * Class that represent boss combo projectile.
 *
 */
public class BossAimedComboProjectile implements ProjectileType {

    private final int bulletNum;

    /**
     * Constructor for this class.
     * 
     * @param n
     *            number of bullets.
     */
    public BossAimedComboProjectile(final int n) {
        bulletNum = n;
    }

    /**
     * Method used to shot collection of bullet shot by the boss.
     */
    @Override
    public Collection<Bullet> shoot(final HitBox sender, final double range, final double vel,
            final ImageType bulletImg, final int damage, final double radius) {
        final List<Bullet> bullets = new ArrayList<>();
        final int upperBullets = bulletNum % 2 == 0 ? bulletNum / 2 : bulletNum / 2 + 1;
        final int downBullets = bulletNum - upperBullets;
        final double deltaUp = ((ProportionUtility.getHeight() - BOSS.getEntityRadius()) / 2
                - upperBullets * radius * 2) / upperBullets;
        final double deltaDown = ((ProportionUtility.getHeight() - BOSS.getEntityRadius()) / 2
                - downBullets * radius * 2) / downBullets;
        bullets.addAll(IntStream.range(0, upperBullets)
                .mapToObj(x -> new CircleHitBox(sender.getX() - 2 * BOSS.getEntityRadius(),
                        deltaUp * (x + 1) + radius * 2 * x, radius))
                .map(x -> new BulletImpl(x, vel,
                        new BulletMovement(Math.toDegrees(Math.atan2(ModelUtility.getPlayerHitBox().getY() - x.getY(),
                                ModelUtility.getPlayerHitBox().getX() - x.getX()))),
                        range, bulletImg, damage))
                .collect(Collectors.toList()));

        bullets.addAll(IntStream.range(0, downBullets)
                .mapToObj(x -> new CircleHitBox(sender.getX() - 2 * BOSS.getEntityRadius(),
                        sender.getY() + deltaDown * x + radius * 2 * x, radius))
                .map(x -> new BulletImpl(x, vel,
                        new BulletMovement(Math.toDegrees(Math.atan2(ModelUtility.getPlayerHitBox().getY() - x.getY(),
                                ModelUtility.getPlayerHitBox().getX() - x.getX()))),
                        range, bulletImg, damage))
                .collect(Collectors.toList()));
        return bullets;
    }
}
