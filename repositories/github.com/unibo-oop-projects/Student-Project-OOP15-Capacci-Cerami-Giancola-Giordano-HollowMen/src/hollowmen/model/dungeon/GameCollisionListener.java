package hollowmen.model.dungeon;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.contacts.Contact;

import hollowmen.model.Attack;
import hollowmen.model.Enemy;
import hollowmen.model.Hero;
import hollowmen.model.Interactable;
import hollowmen.model.roomentity.EnemyAbs;
import hollowmen.model.roomentity.ActorAbs;
import hollowmen.model.utils.Algorithms;

/**
 * This class is used to determinate what to do when two
 * {@link Fixture}s collide
 * @author pigio
 *
 */
public class GameCollisionListener implements ContactListener{

	@Override
	public void beginContact(Contact contact) {
		int typeA = contact.getFixtureA().getFilterData().categoryBits;
		int typeB = contact.getFixtureB().getFilterData().categoryBits;
		
		if(typeA == FilterType.GROUND.getValue() && typeB == FilterType.JUMP.getValue()) {
			((ActorAbs) contact.getFixtureB().getBody().getUserData()).addGroundContact();
		}
		if(typeB == FilterType.GROUND.getValue() && typeA == FilterType.JUMP.getValue()) {
			((ActorAbs) contact.getFixtureA().getBody().getUserData()).addGroundContact();
		}
			
		if(typeA == FilterType.INTERACTABLE.getValue() && typeB == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureA().getBody().getUserData()).setInteractAllowed(true);
		}
		if(typeB == FilterType.INTERACTABLE.getValue() && typeA == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureB().getBody().getUserData()).setInteractAllowed(true);
		}
		
		if(typeA == FilterType.WALL.getValue() && (typeB == FilterType.ENEMY.getValue() 
				|| typeB == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			((EnemyAbs)contact.getFixtureB().getBody().getUserData()).setHitWall(true);
		}
		if(typeB == FilterType.WALL.getValue() && (typeA == FilterType.ENEMY.getValue() 
				|| typeA == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			((EnemyAbs)contact.getFixtureA().getBody().getUserData()).setHitWall(true);
		}
		
		if(typeA == FilterType.HEROATTACK.getValue()
				&& (typeB == FilterType.ENEMY.getValue() 
				|| typeB == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			Algorithms.combat((Attack)contact.getFixtureA().getBody().getUserData(), 
					(Enemy) contact.getFixtureB().getBody().getUserData());
		}
		if(typeB == FilterType.HEROATTACK.getValue()
				&& (typeA == FilterType.ENEMY.getValue() 
				|| typeA == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			Algorithms.combat((Attack)contact.getFixtureB().getBody().getUserData(), 
					(Enemy) contact.getFixtureA().getBody().getUserData());
		}
		
		if(typeA == FilterType.HERO.getValue() && (typeB == FilterType.ENEMY.getValue() 
				|| typeB == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			Algorithms.combat((Enemy)contact.getFixtureB().getBody().getUserData(), 
					(Hero) contact.getFixtureA().getBody().getUserData());
			}
		
		if(typeB == FilterType.HERO.getValue() && (typeA == FilterType.ENEMY.getValue() 
				|| typeA == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			Algorithms.combat((Enemy)contact.getFixtureA().getBody().getUserData(), 
					(Hero) contact.getFixtureB().getBody().getUserData());
		}
	}





	@Override
	public void endContact(Contact contact) {
		int typeA = contact.getFixtureA().getFilterData().categoryBits;
		int typeB = contact.getFixtureB().getFilterData().categoryBits;
		
		if(typeA == FilterType.INTERACTABLE.getValue() && typeB == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureA().getBody().getUserData()).setInteractAllowed(false);
		}
		if(typeB == FilterType.INTERACTABLE.getValue() && typeA == FilterType.HERO.getValue()) {
			((Interactable) contact.getFixtureB().getBody().getUserData()).setInteractAllowed(true);
		}
		
		if(typeA == FilterType.GROUND.getValue() && typeB == FilterType.JUMP.getValue()) {
			((ActorAbs) contact.getFixtureB().getBody().getUserData()).removeGroundContact();
		}
		if(typeB == FilterType.GROUND.getValue() && typeA == FilterType.JUMP.getValue()) {
			((ActorAbs) contact.getFixtureA().getBody().getUserData()).removeGroundContact();
		}
		
		if(typeA == FilterType.WALL.getValue() && (typeB == FilterType.ENEMY.getValue() 
				|| typeB == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			((EnemyAbs)contact.getFixtureB().getBody().getUserData()).setHitWall(false);
		}
		if(typeB == FilterType.WALL.getValue() && (typeA == FilterType.ENEMY.getValue() 
				|| typeA == FilterType.ENEMY.getValue() + FilterType.FLY.getValue())) {
			((EnemyAbs)contact.getFixtureA().getBody().getUserData()).setHitWall(false);

		}

	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

}
