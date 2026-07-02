package it.unibo.oop.crossline.game.actor.robot;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import it.unibo.oop.crossline.game.attributes.Physical;
import it.unibo.oop.crossline.game.weapon.Weapon;

public class RobotBuilderImpl implements RobotBuilder {

    private static final float DEFAULT_HEALTH = 100f;
    private static final float DEFAULT_ATTACK_RANGE = 10f;
    private static final Vector2 DEFAULT_POSITION = Vector2.Zero;
    private static final int DEFAULT_TEAM = 1;

    private float health = DEFAULT_HEALTH;
    private float attackRange = DEFAULT_ATTACK_RANGE;
    private Vector2 position = DEFAULT_POSITION;
    private Physical target;
    private int team = DEFAULT_TEAM;
    private Weapon weapon;
    private World world;

    @Override
    public final Robot build() {
        if (target == null) {
            throw new IllegalArgumentException();
        }
        if (weapon == null) {
            throw new IllegalArgumentException();
        }
        if (world == null) {
            throw new IllegalArgumentException();
        }
        return new RobotImpl(attackRange, health, position, target, team, weapon, world);
    }

    @Override
    public final RobotBuilder setAttackRange(final float attackRange) {
        this.attackRange = attackRange;
        return this;
    }

    @Override
    public final RobotBuilder setHealth(final float health) {
        this.health = health;
        return this;
    }

    @Override
    public final RobotBuilder setPosition(final Vector2 position) {
        this.position = position;
        return this;
    }

    @Override
    public final RobotBuilder setTarget(final Physical target) {
        this.target = target;
        return this;
    }

    @Override
    public final RobotBuilder setTeam(final int team) {
        this.team = team;
        return this;
    }

    @Override
    public final RobotBuilder setWeapon(final Weapon weapon) {
        this.weapon = weapon;
        return this;
    }

    @Override
    public final RobotBuilder setWorld(final World world) {
        this.world = world;
        return this;
    }

}
