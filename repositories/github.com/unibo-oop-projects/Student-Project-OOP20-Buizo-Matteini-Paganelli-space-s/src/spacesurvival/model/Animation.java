package spacesurvival.model;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import spacesurvival.utilities.Delay;
import spacesurvival.utilities.ThreadUtils;

/**
 * Implement an animation model that extends a thread.
 */
public class Animation extends Thread {
    private List<String> listPath;
    private boolean isAnimating;
    private boolean isPause;
    private EngineImage body;

    /**
     * Create animation from engineImage.
     * @param body is engineImage.
     */
    public Animation(final EngineImage body) {
        this.listPath = new ArrayList<>();
        this.isAnimating = true;
        this.isPause = false;
        this.body = body;
    }

    /**
     * Get engineImage of animation.
     * @return EngineImage is a engine for image.
     */
    public EngineImage getBody() {
        return this.body;
    }

    /**
     * Set engineImage of animation.
     * @param body is a new engineImage for image.
     */
    public void setBody(final EngineImage body) {
        this.body = body;
    }

    /**
     * Return a list of the paths of the animation images.
     * 
     * @return a list of string indicating the paths
     */
    public List<String> getListPath() {
        return this.listPath;
    }

    /**
     * Set the passed paths list to the current paths list.
     * 
     * @param listPath a list of animation image paths
     */
    public void setListPath(final List<String> listPath) {
        this.listPath = listPath;
    }

    /**
     * Return true if the animation is active.
     * 
     * @return a boolean true if the animation is active
     */
    public boolean isAnimating() {
        return this.isAnimating;
    }

    /**
     * Set the animating attribute.
     * 
     * @param isAnimating boolean
     */
    public void setAnimating(final boolean isAnimating) {
        this.isAnimating = isAnimating;
    }

    /**
     * Return true if the animation is in pause.
     * 
     * @return a boolean true if the animation is in pause
     */
    public boolean isPause() {
        return this.isPause;
    }

    /**
     * Set the pause attribute.
     * 
     * @param isPause boolean
     */
    public void setPause(final boolean isPause) {
        this.isPause = isPause;
    }

    /**
     * Return the current image.
     * 
     * @return the current image
     */
    public Image getImage() {
        return this.body.getImage();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        int index = 0;

        while (this.isAnimating) {
            if (!this.listPath.isEmpty() && !this.isPause) {
                index = index + 1 > this.listPath.size() ? 0 : index;
                this.body.setPath(this.listPath.get(index++));
            }
            ThreadUtils.sleep(Delay.ANIMATIONS);
        }
    }

    /**
     * Wait for the next frame of the animation.
     * 
     * @param current current time
     */
   public void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < EngineLoop.FPS) {
            ThreadUtils.sleep(EngineLoop.FPS - dt);
        }
    }

   /**
    * Return a string description of the animation.
    * 
    * @return a describing string
    */
    @Override
    public String toString() {
        return "Animation [listPath=" + this.listPath + ", isAnimating=" + this.isAnimating + ", isPause=" + this.isPause + ", body="
                + this.body + "]";
    }
}
