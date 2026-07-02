package it.unibo.oop.supermario.world;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.screens.PlayScreen;
/**Class that models coin object. 
 */
public class Coin extends RigidBody {
    /**Coin constructor. 
     * @param playScreen Playscreen object
     * @param x x object's coordinate
     * @param y y object's coordinate
     * */
    public Coin(final PlayScreen playScreen, final float x, final float y) {
        super(playScreen, x, y);
    }

    @Override
    protected final void defBody() {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getPosition().x, getPosition().y);
        b2body = getWorld().createBody(bodyDef);
        b2body.setActive(false);
        final CircleShape shape = new CircleShape(); 
        final FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }
}
