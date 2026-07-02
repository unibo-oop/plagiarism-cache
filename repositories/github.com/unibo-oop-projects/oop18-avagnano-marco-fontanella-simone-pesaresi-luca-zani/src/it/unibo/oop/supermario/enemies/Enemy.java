package it.unibo.oop.supermario.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import it.unibo.oop.supermario.character.Mario;
import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.gamemanager.GameManager;
import it.unibo.oop.supermario.screens.PlayScreen;

/**
 * This class implements enemies' functions.
 */
public abstract class Enemy extends RigidBody {

    /** Score when enemy is hit by mario. */
    public static final int KILLED_SCORE = 1500;

    /** Score when enemy is hit with fire by mario. */
    public static final int FIRED_SCORE = 2000;

    /** The gravity of enemies when they falling down. */
    public static final int GRAVITY = -2;

    /** The walking velocity if enemies in horizontal directions. */
    public static final int NORMAL_SPEED = -1;

    /** Coordinates x and y axis of enemy's head. */
    private static final int X1 = -5, X2 = -3, Y1 = 8, Y2 = 3;

    /** Elasticity of enemies. */
    private static final float ELASTICITY_GOOMBA = 0.5f, 
            ELASTICITY_KOOPA = 1.5f;

    /** Manage velocity and directions of enemies. */
    private Vector2 velocity;

    /** Check if game is finished. */
    private boolean finishGame;

    /** Specifies one of two enemies. */
    public enum Enemies {
        /** Goomba enemy. */
        GOOMBA,
        /** Koopa enemy. */
        KOOPA;
    }

    /**
     * Constructor for Enemy.
     *
     * @param screen the PlayScreen of the game
     * @param x the x-axis
     * @param y the y-axis
     */
    public Enemy(final PlayScreen screen, final float x, final float y) {
        super(screen, x, y);
        setVelocity(NORMAL_SPEED, GRAVITY);
        getBody().setActive(false);
    }

    /**
     * Create the body of relative enemy.
     * 
     * @param enemy could be Goomba or Koopa.
     */
    public void createBody(final Enemies enemy) {
        final BodyDef bdef = new BodyDef();
        bdef.position.set(getPosition().x, getPosition().y);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = getWorld().createBody(bdef);

        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        shape.setRadius(RADIUS);
        fdef.filter.categoryBits = GameManager.ENEMY_BIT;
        fdef.filter.maskBits = GameManager.GROUND_BIT 
                | GameManager.COIN_BIT 
                | GameManager.BRICK_BIT 
                | GameManager.ENEMY_BIT 
                | GameManager.PIPE_BIT 
                | GameManager.POWER_UP 
                | GameManager.MARIO_BIT
                | GameManager.FEET_BIT;
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        //Create the Head here:
        final PolygonShape head = new PolygonShape();
        Vector2[] vertice = new Vector2[4];
        vertice[0] = new Vector2(X1, Y1).scl(1 / GameManager.PPM);
        vertice[1] = new Vector2(-1 * X1, Y1).scl(1 / GameManager.PPM);
        vertice[2] = new Vector2(X2, Y2).scl(1 / GameManager.PPM);
        vertice[3] = new Vector2(-1 * X2, Y2).scl(1 / GameManager.PPM);
        head.set(vertice);
        fdef.shape = head;
        if (enemy.equals(Enemies.GOOMBA)) {
            fdef.restitution = ELASTICITY_GOOMBA;
        } else {
            fdef.restitution = ELASTICITY_KOOPA;
        }
        fdef.filter.categoryBits = GameManager.ENEMY_HEAD_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    /** Updates enemies' state synchronized by time. 
     * 
     * @param dt the delta time value of the game
     * */
    public abstract void update(float dt);

    /** Checks the state where enemy is hit on head by Mario. 
     * 
     * @param mario contains all informations of class Mario.
     * */
    public abstract void hitOnHead(Mario mario);

    /** Checks the state where enemy is hit by another enemy. 
     * 
     * @param enemy contains all informations of class Enemy.
     * */
    public abstract void hitByEnemy(Enemy enemy);

    /** Checks the state where enemy is hit by fire. */
    public abstract void hitByFire();


    /** When game finished stop animations of enemies. 
     * 
     * @return finishGame the condition of the game.
     * */
    public boolean stateEnemies() {
        return this.finishGame;
    }

    /** 
     * Set to stop enemies. 
     */
    public void stopEnemies() {
        this.finishGame = true;
    }

    /** Inverts the direction of enemies. 
     * 
     * @param x the x-axis
     * @param y the y-axis
     * */
    public void reverseVelocity(final boolean x, final boolean y) {
        if (x) {
            setVelocity(-getVelocity().x, getVelocity().y);
        }
        if (y) {
            setVelocity(getVelocity().x, -getVelocity().y);
        }
    }

    /** Getter for velocity. 
     * @return the value
     * */
    public Vector2 getVelocity() {
        return velocity;
    }

    /** Setter for velocity. 
     * @param velocity the vector to set
     * */
    public void setVelocity(final Vector2 velocity) {
        this.velocity = velocity;
    }

    /**
     * Set the velocity in horizontal direction.
     * 
     * @param x direction x.
     */
    public void setVelocity(final float x) {
        this.velocity.x = x;
    }

    /**
     * Set the velocity in both directions.
     * 
     * @param x horizontal direction
     * @param y vertical direction
     */
    public void setVelocity(final float x, final float y) {
        this.velocity = new Vector2(x, y);
    }
}
