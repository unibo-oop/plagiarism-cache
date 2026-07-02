package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;

public interface Movement {
	
	Body getBody();
	
	void update(float dt);
	
	void draw(Batch batch);
	
	void setBlockBall();

}
