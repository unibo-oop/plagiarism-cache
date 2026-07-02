package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.Entity;
import it.unibo.utilities.Constants;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class that implement the view of a brick.
 */
public final class BrickView implements View {
    private static final int ANIMATION_DURATION = 16; 
    private final ImageView imageView;
    private final Image spriteSheet;
    private final Entity brick;
    private Timeline timeline;
    private static final String JUSTIFICATION = "We need the original object";
    /**
     * Constructor.
     * @param brick
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = JUSTIFICATION)
    public BrickView(final Entity brick) {
        spriteSheet = getSource("brick");
        this.brick = brick;
        imageView = new ImageView();
        imageView.setFitHeight(Constants.Brick.BRICK_HEIGHT);
        imageView.setFitWidth(Constants.Brick.BRICK_WIDTH);
        this.imageView.setX(brick.getPosition().getX());
        this.imageView.setY(brick.getPosition().getY());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }
    /**
     * @{InheritDoc}
     */
    @Override
    public void animate() {
        this.imageView.setX(this.brick.getPosition().getX());
        this.imageView.setY(this.brick.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.timeline = new Timeline(
                    new KeyFrame(Duration.millis(ANIMATION_DURATION), e -> {
                    }));
            this.timeline.setCycleCount(1);
            this.timeline.setOnFinished(e -> this.imageView.setImage(this.spriteSheet));
            this.timeline.play();
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        return new WritableImage(this.spriteSheet.getPixelReader(),
                                 index * ((int) this.spriteSheet.getWidth()), 0, 
                                 (int) this.spriteSheet.getWidth(), (int) this.spriteSheet.getHeight());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = JUSTIFICATION)
    public ImageView getImageView() {
        return this.imageView;
    }
    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = JUSTIFICATION)
    public Image getImage() {
        return this.spriteSheet;
    }
    /**
     * Getter for the brick.
     * @return the brick.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = JUSTIFICATION)
    public Entity getBrick() {
        return this.brick;
    }
}
