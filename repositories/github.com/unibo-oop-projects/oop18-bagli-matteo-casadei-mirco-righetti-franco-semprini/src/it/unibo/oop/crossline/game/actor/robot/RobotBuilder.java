package it.unibo.oop.crossline.game.actor.robot;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.attributes.Physical;
import it.unibo.oop.crossline.game.weapon.Weapon;

public interface RobotBuilder {

    Robot build();

    RobotBuilder setAttackRange(float attackRange);

    RobotBuilder setHealth(float health);

    RobotBuilder setPosition(Vector2 position);

    RobotBuilder setTarget(Physical target);

    RobotBuilder setTeam(int team);

    RobotBuilder setWeapon(Weapon weapon);

    RobotBuilder setWorld(World world);

}
