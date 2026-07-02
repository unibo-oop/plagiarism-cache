package it.unibo.oop.crossline.game.wave;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import it.unibo.oop.crossline.game.actor.player.Player;
import it.unibo.oop.crossline.game.actor.robot.Robot;
import it.unibo.oop.crossline.game.actor.robot.RobotBuilder;
import it.unibo.oop.crossline.game.actor.robot.RobotBuilderImpl;
import it.unibo.oop.crossline.game.bullet.BulletBuilder;
import it.unibo.oop.crossline.game.bullet.BulletBuilderImpl;
import it.unibo.oop.crossline.game.weapon.Weapon;
import it.unibo.oop.crossline.game.weapon.WeaponImpl;

/**
 *  This is the class manages all the logic behind a game wave.
 */
public class WaveImpl implements Observer, Wave {

    private static final float BASE_BULLET_DAMAGE = 10f;
    private static final float BASE_BULLET_SPEED = 2f;
    private static final int ROBOT_DISTANCE_MAX = 25;
    private static final int ROBOT_DISTANCE_MIN = 15;
    private static final long SHOT_DELAY = 3000;

    private final float difficulty;
    private final List<Robot> robots = new ArrayList<>();;

    /**
     * Initialize the wave.
     * @param player the main player
     * @param difficulty the wave difficulty
     */
    public WaveImpl(final Player player, final float difficulty) {
        this.difficulty = difficulty;
        final RobotBuilder robotFactory = new RobotBuilderImpl();
        final int robotCount = (int) (difficulty * 1.5);
        final long shotDelay = (long) ((SHOT_DELAY / 100) * (100 - difficulty * 5));
        final World world = player.getBody().getWorld();
        final BulletBuilder bulletBuilder = new BulletBuilderImpl()
                .setDamage(BASE_BULLET_DAMAGE * difficulty)
                .setSpeed(BASE_BULLET_SPEED * difficulty);
        for (int i = 0; i < robotCount; i++) {
            final Weapon weapon = new WeaponImpl(shotDelay, bulletBuilder);
            final Vector2 direction = new Vector2((float) MathUtils.random(-1f, 1f), (float) MathUtils.random(0f, 1f)).nor();
            final Vector2 playerPosition = player.getBody().getPosition().cpy();
            final int distanceHorizontal = MathUtils.random(ROBOT_DISTANCE_MIN, ROBOT_DISTANCE_MAX);
            final int distanceVertical = MathUtils.random(ROBOT_DISTANCE_MIN, ROBOT_DISTANCE_MAX);
            final float attackRange = (10 + difficulty);
            final Vector2 distance = new Vector2(distanceHorizontal, distanceVertical);
            final Vector2 relativePosition = distance.scl(direction);
            final Vector2 robotPosition = playerPosition.add(relativePosition);
            final Robot robot = robotFactory.setAttackRange(attackRange)
                                            .setPosition(robotPosition)
                                            .setTarget(player)
                                            .setWeapon(weapon)
                                            .setWorld(world)
                                            .build();
            robot.addObserver(this);
            robots.add(robot);
            weapon.setOwner(robot);
        }
    }

    @Override
    public final void update(final Observable observable, final Object object) {
        if (observable instanceof Robot) {
            final Robot robot = (Robot) observable;
            robots.remove(robot);
        }
    }

    @Override
    public final List<Robot> getRobots() {
        return robots;
    }

    @Override
    public final float getDifficulty() {
        return difficulty;
    }

    @Override
    public final boolean hasEnded() {
        return robots.isEmpty();
    }

}
