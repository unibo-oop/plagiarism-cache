package it.unibo.oop.crossline.game.actor.player;

import com.badlogic.gdx.math.Vector2;

import it.unibo.oop.crossline.game.actor.Actor;
import it.unibo.oop.crossline.game.attributes.Armed;
import it.unibo.oop.crossline.game.weapon.Weapon;
import it.unibo.oop.crossline.io.InputController;

public interface Player extends Actor, Armed {

    void move(Vector2 direction);

    void jump();

    void shoot();

    void setWeapon(Weapon weapon);

    boolean getJumpState();

    void setJumpState(boolean jumping);

}
