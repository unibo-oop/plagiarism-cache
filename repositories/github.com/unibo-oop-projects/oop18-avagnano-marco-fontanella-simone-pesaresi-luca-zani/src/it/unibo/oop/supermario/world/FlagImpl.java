package it.unibo.oop.supermario.world;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import it.unibo.oop.supermario.character.RigidBody;
import it.unibo.oop.supermario.screens.PlayScreen;
/**Flag class.
 */
public class FlagImpl extends RigidBody implements Flag {
    private boolean flagFalling;
    /**Flag constructor.
     * 
     * @param playScreen playscreen object
     * @param x x object's coordinate
     * @param y y object's 
     * */
    public FlagImpl(final PlayScreen playScreen, final float x, final float y) {
        super(playScreen, x, y);
        flagFalling = false;
    }

    @Override
    protected final void defBody() {
        final BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(getPosition().x, getPosition().y);
        b2body = getWorld().createBody(bodyDef);
        b2body.setActive(true);
        CircleShape shape = new CircleShape(); 
        FixtureDef fdef = new FixtureDef();
        fdef = new FixtureDef();
        shape = new CircleShape(); 
        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);
    }

    /**Method to set the flag falling.
     *@param flagBool set if flag is falling 
     */
    public void setFlagFalling(final boolean flagBool) {
        flagFalling = flagBool;
    }
    /**Return if flag is falling.
     * @return flag state
     */
    public boolean isFlagFalling() {
        return flagFalling;
    }
}

