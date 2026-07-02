package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import it.unibo.oop.bounce.manager.State;


public interface Ball {

Body getBody();

State getState();

boolean isBig();

boolean isSmall();

boolean isDead();

void setBig();

void setSmall();

void bigBall();

void smallBall();

void big();

void small();

void jump();

void movement(float direction);

boolean isRolling();

void update(float dt);

Vector2 getPosition();

void setRespawnX(float x);

void setRespawnY(float y);

}
