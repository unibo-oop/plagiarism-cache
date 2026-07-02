package it.unibo.ninjafrog.frog;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface FrogView {

    /**
     * update the view.
     * 
     * @param dt delta of time;
     */
    void update(float dt);

    /**
     * draw the frog in the Playscreen.
     * 
     * @param batch the batch.
     */
    void draw(Batch batch);

    /**
     * @return true if the run is running right.
     */
    boolean isRunningRight();

}
