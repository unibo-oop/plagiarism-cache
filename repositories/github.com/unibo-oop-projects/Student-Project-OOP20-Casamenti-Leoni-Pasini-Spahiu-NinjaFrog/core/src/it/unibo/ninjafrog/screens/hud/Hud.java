package it.unibo.ninjafrog.screens.hud;

import com.badlogic.gdx.scenes.scene2d.Stage;

public interface Hud {
    /**
     * add points to the hud.
     * 
     * @param value ,the points to add
     */
    void addScore(int value);

    /**
     * @return the game's score.
     */
    Integer getScore();

    /**
     * add one life to the hud.
     */
    void addLife();

    /**
     * remove one life from the hud.
     */
    void removeLife();

    /**
     * @return the stage.
     */
    Stage getStage();

    /**
     * @return true if the timer is over
     */
    boolean isTimerOn();

    /**
     * @param dt delta of time;
     */
    void update(float dt);
}
