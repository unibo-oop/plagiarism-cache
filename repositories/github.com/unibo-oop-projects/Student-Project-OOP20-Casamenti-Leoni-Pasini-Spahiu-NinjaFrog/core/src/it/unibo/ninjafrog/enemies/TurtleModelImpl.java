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

public class TurtleModelImpl implements StaticEnemyModel {
    private static final int GRAVITY = 0;
    private static final int SPEED = 0;
    private static final int HEAD_VERICE_DOWN_Y = 7;
    private static final int HEAD_VERICE_DOWN_X = 5;
    private static final int HEAD_VERTICE_UP_Y = 10;
    private static final int HEAD_VERTICE_UP_X = 7;
    private final World world;
    private final PlayScreen screen;
    private Body body;
    private final Vector2 velocity;
    private static final int CIRCLE_RADIUS = 7;
    private static final int TURTLE_SCORE = 200;
    private float stateTime;
    private boolean setToDestroy;
    private boolean destroyed;
    private final EnemyController controller;

    /**
     * public constructor of the TurtleModel.
     * 
     * @param screen     the playscreen
     * @param controller the EnemyController
     */

    public TurtleModelImpl(final PlayScreen screen, final EnemyControllerImpl controller) {
        this.world = screen.getWorld();
        this.screen = screen;
        this.controller = controller;
        velocity = new Vector2(SPEED, GRAVITY);
        stateTime = 0;
        this.destroyed = false;
        this.setToDestroy = false;
    }

    @Override
    public final void defineEnemy() {
        final BodyDef bdef = new BodyDef();
        final FixtureDef fdef = new FixtureDef();
        final CircleShape shape = new CircleShape();
        final PolygonShape head = new PolygonShape();
        final Vector2[] vertice = new Vector2[4];
        createBody(bdef);
        fixtureBodyDefinition(fdef, shape);
        createFixture(fdef);
        fixtureHeadDefinition(fdef, head, vertice);
        createFixture(fdef);

    }

    private void fixtureHeadDefinition(final FixtureDef fdef, final PolygonShape head, final Vector2[] vertice) {
        vertice[0] = new Vector2(-HEAD_VERTICE_UP_X, HEAD_VERTICE_UP_Y).scl(1 / GameConst.PPM);
        vertice[1] = new Vector2(+HEAD_VERTICE_UP_X, HEAD_VERTICE_UP_Y).scl(1 / GameConst.PPM);
        vertice[2] = new Vector2(-HEAD_VERICE_DOWN_X, HEAD_VERICE_DOWN_Y).scl(1 / GameConst.PPM);
        vertice[3] = new Vector2(HEAD_VERICE_DOWN_X, HEAD_VERICE_DOWN_Y).scl(1 / GameConst.PPM);
        head.set(vertice);
        fdef.shape = head;
        fdef.restitution = 1f;
        fdef.filter.categoryBits = GameConst.TURTLE_HEAD;
    }

    private void createFixture(final FixtureDef fdef) {
        body.createFixture(fdef).setUserData(this);
    }

    private void fixtureBodyDefinition(final FixtureDef fdef, final CircleShape shape) {
        shape.setRadius(TurtleModelImpl.CIRCLE_RADIUS / GameConst.PPM);
        fdef.filter.categoryBits = GameConst.TURTLE;
        fdef.filter.maskBits = GameConst.GROUND | GameConst.BRICK | GameConst.RINO | GameConst.TURTLE
                | GameConst.GROUND_OBJECT | GameConst.NINJA | GameConst.FRUITBOX;
        fdef.shape = shape;
    }

    private void createBody(final BodyDef bdef) {
        bdef.position.set(controller.getX(this), controller.getY(this));
        bdef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bdef);
        body.setActive(true);
    }

    @Override
    public final void update(final float dt) {
        stateTime += dt;
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
    public final float getStateTime() {
        return this.stateTime;
    }

    @Override
    public final boolean isSetToDestroy() {
        return this.setToDestroy;
    }

    @Override
    public final boolean isDestroyed() {
        return this.destroyed;
    }

    @Override
    public final void collide() {
        screen.addScore(this);
        setToDestroy = true;
    }

    @Override
    public final int getScore() {
        return TURTLE_SCORE;
    }

}
