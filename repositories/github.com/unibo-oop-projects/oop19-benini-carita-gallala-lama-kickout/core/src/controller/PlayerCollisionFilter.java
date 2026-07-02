package controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.ContactFilter;
import com.badlogic.gdx.physics.box2d.Fixture;

import model.Fighter;


/**
 * Filters out player collisions, so that their bodies can overlap
 * */
public class PlayerCollisionFilter implements ContactFilter {

	
	/**
	 * checks if two fixtures should repulse or be allowed to intersect without registering collisions
	 *
	 * @param fixtureA
	 *			the first fixture involved
	 * @param fixtureB
	 *			the second fixture involved
	 * @return false if the two fixtures belong to players
	 * */
	@Override
	public boolean shouldCollide(Fixture fixtureA, Fixture fixtureB) {
		Body b1 = fixtureA.getBody();
		Body b2 = fixtureB.getBody();
		if(b1.getUserData() instanceof Fighter && b2.getUserData() instanceof Fighter) {
				return false;
		}else{
			return true;
		}
	}

}