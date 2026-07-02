package it.unibo.oop.bounce.obstacles;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;


public interface TypeDefiner {
	
	Body getBody();
	void setCategoryFilter(int filterId);
	Fixture getFixture();
}
