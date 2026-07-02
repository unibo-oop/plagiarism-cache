package view.entities;

import org.jbox2d.common.Vec2;

import javafx.scene.image.ImageView;

/**
 * EntityView represent the view of the game entities and contain all the
 * variables used to visualise the entity.
 * 
 */
public interface EntityView {

    /**
     * @return the {@link ImageView} that represents the player into the game.
     */
    ImageView getImage();

    /**
     * @return the node position
     */
    Vec2 getViewPosition();

    /**
     * @param preserveRatio used to preserve the image ratio width x height.
     */
    void fitViewToDimension(boolean preserveRatio);

    /**
     * Update the node position by getting the entity's position from the physic
     * world, according to the camera position of the view.
     */
    void updateView();

    /**
     * remove the node from the visible view.
     */
    void remove();

    /**
     * add the node into the visible view.
     */
    void show();
}
