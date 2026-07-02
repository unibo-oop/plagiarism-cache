package spacesurvival.model.gameobject;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.util.List;
import spacesurvival.model.common.P2d;
import spacesurvival.model.Animation;
import spacesurvival.model.EngineImage;
import spacesurvival.model.World;
import spacesurvival.model.collision.Collideable;
import spacesurvival.model.collision.bounding.BoundingBox;
import spacesurvival.model.collision.eventgenerator.EventComponent;

/**
 * A game object with essential functionalities, including collisions, position and animations.
 */
public abstract class GameObject implements Collideable {
    private AffineTransform transform;
    private BoundingBox boundingBox;
    private EventComponent eventComponent;
    private final Animation mainAnimation;
    private final Animation effectAnimation;

    public GameObject(final EngineImage engineImage, final P2d position, final BoundingBox bb,
            final EventComponent eventComponent) {
        this.mainAnimation = new Animation(engineImage);
        this.effectAnimation = new Animation(EngineImage.getTransparentEngineImage(engineImage));
        this.boundingBox = bb;
        this.eventComponent = eventComponent;
        this.transform = new AffineTransform();
        this.transform.translate(position.getX(), position.getY());
        this.boundingBox.setTransform(this.transform);
        this.startThreadsAnimations();
    }

    /**
     * Start the threads for animations.
     */
    private void startThreadsAnimations() {
        this.mainAnimation.start();
        this.effectAnimation.start();
    }

    /**
     * Pause or resume all animations of the object.
     * @param isPause true will stop them, false will resume them
     */
    public void setPauseAnimation(final boolean isPause) {
        this.mainAnimation.setPause(isPause);
        this.effectAnimation.setPause(isPause);
    }

    /**
     * Stop all animations of the object and relative threads.
     */
    public void stopAnimation() {
        this.mainAnimation.setAnimating(false);
        this.effectAnimation.setAnimating(false);
    }

    /**
     * @return current image of main animation
     */
    public Image getImgBody() {
        return this.mainAnimation.getImage();
    }

    /**
     * 
     * @return current image of efect animation
     */
    public Image getImgEffect() {
        return this.effectAnimation.getImage();
    }

    /**
     * @return the object AffineTransform
     */
    public AffineTransform getTransform() {
        return this.transform;
    }

    /**
     * Place the object in the given position, sets AffineTransform and BoundingBox
     * referring to the position. This method doesn't maintain the current rotation of the object.
     * 
     * @param position the position where to place the object
     */
    public void setTransformFromPosition(final P2d position) {
        this.transform = new AffineTransform();
        this.transform.translate(position.getX(), position.getY());
        this.boundingBox.setTransform(this.transform);
    }

    /**
     * Sets a new AffineTransform and a new BoundingBox.
     * 
     * @param transform the new transform to set
     */
    public void setTransform(final AffineTransform transform) {
        this.transform.setTransform(transform);
        this.boundingBox.setTransform(transform);
    }

    /**
     * @return the object engine image
     */
    public EngineImage getEngineImage() {
        return this.mainAnimation.getBody();
    }

    /**
     * @return the object engine image
     */
    public EngineImage getEngineEffect() {
        return this.effectAnimation.getBody();
    }

    /**
     * @return the current object position.
     */
    public P2d getPosition() {
        return new P2d(this.transform.getTranslateX(), this.getTransform().getTranslateY());
    }

    /**
     * @return the current object bounding box.
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    /**
     * Sets a new bounding box to the object.
     * 
     * @param boundingBox the bounding box to set
     */
    public void setBoundingBox(final BoundingBox boundingBox) {
          this.boundingBox = boundingBox;
    }

    /**
     * Sets a new EngineImage to the object.
     * 
     * @param engineImage
     */
    public void setEngineImage(final EngineImage engineImage) {
        this.mainAnimation.setBody(engineImage);
    }

    /**
     * @return the object event component.
     */
    public EventComponent getEventComponent() {
        return eventComponent;
    }

    /**
     * Sets a new event component to the object.
     * 
     * @param eventComponent the new event component to set
     */
    public void setEventComponent(final EventComponent eventComponent) {
        this.eventComponent = eventComponent;
    }

    /**
     * Check and update all events affecting game object.
     * 
     * @param world the world which contains each game object
     */
    public void updateEvents(final World world) {
        eventComponent.update(this, world);
    }

    /**
     * @return the object size
     */
    public Dimension getSize() {
        return this.mainAnimation.getBody().getSize();
    }

    /**
     * @return the object width
     */
    public double getWidth() {
        return this.getEngineImage().getSize().getWidth();
    }

    /**
     * @return the object height
     */
    public double getHeight() {
        return this.getEngineImage().getSize().getHeight();
    }

    /**
     * @return the list of path images for the main animation
     */
    public List<String> getAnimation() {
        return this.mainAnimation.getListPath();
    }

    /**
     * Sets the main animation of the object.
     * 
     * @param animation the new animation
     */
    public void setMainAnimation(final List<String> animation) {
        this.mainAnimation.setListPath(animation);
    }

    /**
     * Sets the effect animation of the object.
     * 
     * @param animation the new animation
     */
    public void setEffectAnimation(final List<String> animation) {
        this.effectAnimation.setListPath(animation);
    }

    /**
     * Return a description of game object.
     */
    @Override
    public String toString() {
        return "GameObject [transform=" + transform + ", boundingBox=" + boundingBox + ", eventComponent="
                + eventComponent + ", mainAnimation=" + mainAnimation + ", effectAnimation=" + effectAnimation + "]";
    }

}
