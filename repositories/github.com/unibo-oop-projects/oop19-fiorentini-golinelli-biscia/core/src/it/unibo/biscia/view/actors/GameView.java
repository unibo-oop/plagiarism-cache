package it.unibo.biscia.view.actors;

import it.unibo.biscia.core.Entity;
import it.unibo.biscia.core.Level;
import it.unibo.biscia.events.StateObserver;
import it.unibo.biscia.view.View;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;

/**
 * Takes care of only the "visible" part of the gameplay. This does not
 * comprehend the status bar but only {@link Entity} and their respective
 * {@link EntityActor}. This uses {@link EntityCrew} as child of an
 * {@link Stage} to accomplish its objective. This is usually used as a
 * component for building a {@link View}.
 * 
 * @see GameView
 * @see GameViewImpl
 */
public interface GameView extends Disposable {

    /**
     * The used {@link Stage} with all its children.
     * 
     * @return The {@link Stage}
     */
    Stage getStage();

    /**
     * The {@link EntityCrew} with all its children.
     * 
     * @see EntityActor
     * 
     * @return The {@link EntityCrew}
     */
    EntityCrew getEntityCrew();

    /**
     * Utility method to move the position of the gameplay visible action. @{code x}
     * and @{code y} refers to the upper-left corner of the visible gameplay.
     * 
     * @param x Horizontal shift
     * @param y Vertical shift
     */
    void setPosition(float x, float y);

    /**
     * Called when there is a new level and {@link Entity} and thei respective
     * {@link EntityActor} should be displayed on screen. This method is usually
     * called on {@link StateObserver#newLevel(Level)}.
     * 
     * @param level The {@link Level}.
     */
    void newLevel(Level level);

}
