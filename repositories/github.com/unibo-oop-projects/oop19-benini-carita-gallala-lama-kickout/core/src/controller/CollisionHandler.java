package  controller;
import com.badlogic.gdx.physics.box2d.*;

import model.Fighter;
import model.Trap;
import model.Attack;

/**
 * Inspects the various Contact objects representing collisions between Fixtures
 * and changes the state of the associated Entities accordingly
 * */
public class CollisionHandler implements ContactListener{

	/**{@inheritDoc}
	 * */
    @Override
    public void beginContact(Contact contact) {
    	Body b1 = contact.getFixtureA().getBody();
    	Body b2 = contact.getFixtureB().getBody();
    	Object o1 = b1.getUserData();
    	Object o2 = b2.getUserData();
    	

        if (o1 instanceof Trap && o2 instanceof Fighter || o2 instanceof Trap && o1 instanceof Fighter) {
            if (o1 instanceof Trap) {
                Trap temp = (Trap) o1;
                temp.trap1(b2);
            } else {
                Trap temp = (Trap) o2;
                temp.trap1(b1);
            }
        }
        
        if ((o1 instanceof Fighter && o2 instanceof Attack)  || (o1 instanceof Attack && o2 instanceof Fighter)) {
        	Fighter f;
        	Attack a;
            if (o1 instanceof Attack) {
            	a = (Attack) o1;
            	f = (Fighter) o2;
            } else {
            	a = (Attack) o2;
            	f = (Fighter) o1;
            }
        	a.trigger(f);
        }
    }

	@Override
	public void endContact(Contact contact) {
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		
	}

}