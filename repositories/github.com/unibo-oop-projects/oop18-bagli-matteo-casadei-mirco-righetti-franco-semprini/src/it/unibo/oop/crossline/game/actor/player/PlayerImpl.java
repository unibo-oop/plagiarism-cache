package it.unibo.oop.crossline.game.actor.player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.oop.crossline.game.weapon.Weapon;
import it.unibo.oop.crossline.io.InputController;

/**
 * PlayerImpl class.
 */
public class PlayerImpl implements Player {

    private static final float MAX_VELOCITY = 4f;
    private static final float MAX_JUMP = 10;
    private static final float CIRCLE_RADIUS = 0.3f;
    private static final float FRICTION = 15;

    private float health = 1;
    private boolean isJumping;
    private boolean shouldShoot;
    private final Body player;
    private boolean queuedForDestruction;
    private Weapon weapon;

    public final boolean isShouldShoot() {
        return shouldShoot;
    }

    public final void setShouldShoot(boolean shouldShoot) {
        this.shouldShoot = shouldShoot;
    }


    /**
     * Constructor for player.
     *
     * @param world    the world
     * @param position the position
     */
    public PlayerImpl(final World world, final Vector2 position) {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(position);
        player = world.createBody(bodyDef);
        player.setUserData(this);

        final Shape circle = new CircleShape();
        circle.setRadius(CIRCLE_RADIUS);

        final FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.friction = FRICTION;
        fixtureDef.shape = circle;

        player.createFixture(fixtureDef);
        circle.dispose();
    }

    /**
     * This function return the team value of the player
     * @return value of the team
     */
    @Override
    public final int getTeam() {
        return 0;
    }

    /**
     * This function return the health of the player
     * @return health of the player
     */
    @Override
    public final float getHealth() {
        return health;
    }

    /**
     * This function apply taken damages to the player health and check if his health is zero
     * @param amount of damage taken
     */
    @Override
    public final void applyDamage(final float damage) {
        health = Math.max(health - damage, 0);
        if (health == 0) {
            queueForDestruction();
        }
    }

    /**
     * This function return the body of the player
     * 
     * @return body
     */
    @Override
    public final Body getBody() {
        return player;
    }

    @Override
    public final boolean isQueuedForDestruction() {
        return queuedForDestruction;
    }

    @Override
    public final void queueForDestruction() {
        queuedForDestruction = true;
    }

    @Override
    public final void update() {
        /*
         * move(); shoot();
         */
    }

    /**
     * This function return the weapon which player is using
     * 
     * @return weapon
     */
    @Override
    public final Weapon getWeapon() {
        return this.weapon;
    }

    /**
     * This function permit the player to move in the x axis
     * 
     * @param the direction where the player have to move
     */
    @Override
    public final void move(final Vector2 direction) {

//      if (player.linVelLoc.isZero() && player.getLinearVelocity().len() < MAX_VELOCITY)
//      {
//              player.applyLinearImpulse(direction, player.getWorldCenter(), true);
//      }

        final Vector2 linearVelocity = player.getLinearVelocity().cpy();
        direction.scl(MAX_VELOCITY);
        linearVelocity.x = direction.x;
        player.setLinearVelocity(linearVelocity);
        // player.setTransform(player.getPosition().add(direction),0);
        // player.setTransform(player.getPosition().scl(direction), 0);
        // player.applyLinearImpulse(direction, player.getWorldCenter(), true);
    }

    /**
     * This function permit the player to jump
     */
    @Override
    public final void jump() {
        if (!isJumping) {
            player.applyLinearImpulse(new Vector2(0, MAX_JUMP), player.getWorldCenter(), true);
            isJumping = true;
        }
    }

    /**
     * This function check if the player can shoot
     */
    @Override
    public final void shoot() {
        if (weapon.canShoot() && shouldShoot) {
            weapon.shoot();
        }
        shouldShoot = false;
    }

    /**
     * This function take from input the weapon and set it to the player
     * 
     * @param weapon
     */
    @Override
    public final void setWeapon(final Weapon weapon) {
        this.weapon = weapon;
    }

    
    /**
     * This function check if the player is jumping
     * 
     * @return jumping state
     */
    @Override
    public final boolean getJumpState() {
        return this.isJumping;
    }

    /**
     * This function set if the player is jumping
     * 
     * @param jumping state
     */
    @Override
    public final void setJumpState(final boolean isJumping) {
        this.isJumping = isJumping;
    }

    public static float getCircleRadius() {
        return CIRCLE_RADIUS;
    }
}
