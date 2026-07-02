package it.unibo.ninjafrog.enemies;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import it.unibo.ninjafrog.game.utilities.GameConst;
import it.unibo.ninjafrog.screens.PlayScreen;

public class RinoModelImpl implements DynamicEnemyModel {

    private static final float RESTITUTION = 1f;
    private static final int NUMBER_OF_VERTICE_OF_THE_HEAD = 4;
    private static final int HEAD_VERTICE_DOWN_Y = 7;
    private static final int HEAD_VERTICE_DOWN_X = 5;
    private static final int HEAD_VERTICE_UP_Y = 10;
    private static final int HEAD_VERTICE_UP_X = 6;
    private static final int GRAVITY = -2;
    private static final int SPEED = -1;
    private static final int DISTANCE_FOR_ACTIVE_ENEMIES = 224;
    private final World world;
    private final PlayScreen screen;
    private Body body;
    private final Vector2 velocity;
    private static final int CIRCCLE_RADIUS = 7;
    private static final int RINO_SCORE = 150;
    private float stateTime;
    private boolean setToDestroy;
    private boolean destroyed;
    private boolean runningLeft;
    private final EnemyController controller;

    /**
     * public constructor of the RinoModel.
     * 
     * @param screen     the playscreen
     * @param controller the EnemyController
     */

    public RinoModelImpl(final PlayScreen screen, final EnemyControllerImpl controller) {
        this.world = screen.getWorld();
        this.screen = screen;
        this.controller = controller;
        velocity = new Vector2(SPEED, GRAVITY);
        stateTime = 0;
        this.setToDestroy = false;
        this.destroyed = false;
        this.runningLeft = true;
    }

    @Override
    public final void defineEnemy() {
        final BodyDef bdef = new BodyDef();
        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        final PolygonShape head = new PolygonShape();
        final Vector2[] vertice = new Vector2[NUMBER_OF_VERTICE_OF_THE_HEAD];
        createBody(bdef);
        fixtureBodyDefinition(fdef, shape);
        createFixture(fdef);
        fixtureHeadDefinition(vertice, head, fdef);
        createFixture(fdef);
    }

    @Override
    public final void update(final float dt) {
        stateTime += dt;
        if (!this.destroyed && controller.getX(this) < this.screen.getNinjaXPosition()
                + DISTANCE_FOR_ACTIVE_ENEMIES / GameConst.PPM) {
            body.setActive(true);
        }
        if (setToDestroy && !this.destroyed) {
            this.destroyed = true;
            world.destroyBody(body);
            controller.setDeathRegion(this);
            stateTime = 0;
        } else if (!this.destroyed) {
            body.setLinearVelocity(velocity);
            controller.updateView(this, this.body, dt);
        }
    }

    @Override
    public final void reverseVelocity(final boolean x, final boolean y) {
        if (x) {
            velocity.x = -velocity.x;
        }
        if (y) {
            velocity.y = -velocity.y;
        }
    }

    @Override
    public final boolean isSetToDestroy() {
        return this.setToDestroy;
    }

    @Override
    public final void collide() {
        screen.addScore(this);
        setToDestroy = true;
    }

    @Override
    public final boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public final float getStateTime() {
        return this.stateTime;
    }

    @Override
    public final boolean isRunningLeft() {
        return this.runningLeft;
    }

    @Override
    public final void setRunningLeft(final boolean b) {
        this.runningLeft = b;
    }

    @Override
    public final int getScore() {
        return RINO_SCORE;
    }

    private void createBody(final BodyDef bdef) {
        bdef.position.set(controller.getX(this), controller.getY(this));
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        body.setActive(false);
    }

    private void fixtureBodyDefinition(final FixtureDef fdef, final CircleShape shape) {
        shape.setRadius(RinoModelImpl.CIRCCLE_RADIUS / GameConst.PPM);
        fdef.filter.categoryBits = GameConst.RINO;
        fdef.filter.maskBits = GameConst.GROUND | GameConst.BRICK | GameConst.NINJA | GameConst.RINO | GameConst.TURTLE
                | GameConst.GROUND_OBJECT | GameConst.FRUITBOX;
        fdef.shape = shape;
    }

    private void createFixture(final FixtureDef fdef) {
        body.createFixture(fdef).setUserData(this);
    }

    private void fixtureHeadDefinition(final Vector2[] vertice, final PolygonShape head, final FixtureDef fdef) {
        vertice[0] = new Vector2(-HEAD_VERTICE_UP_X, HEAD_VERTICE_UP_Y).scl(1 / GameConst.PPM);
        vertice[1] = new Vector2(+HEAD_VERTICE_UP_X, HEAD_VERTICE_UP_Y).scl(1 / GameConst.PPM);
        vertice[2] = new Vector2(-HEAD_VERTICE_DOWN_X, HEAD_VERTICE_DOWN_Y).scl(1 / GameConst.PPM);
        vertice[3] = new Vector2(+HEAD_VERTICE_DOWN_X, HEAD_VERTICE_DOWN_Y).scl(1 / GameConst.PPM);
        head.set(vertice);
        fdef.shape = head;
        fdef.restitution = RESTITUTION;
        fdef.filter.categoryBits = GameConst.RINO_HEAD;
    }
}
