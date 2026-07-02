package it.unibo.oop.bounce.ball;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface BallView {

 void setBall(Ball ball);

 float getStateTimer();

 void update(float dt);

 void draw(Batch batch);


}
