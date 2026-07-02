package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.utilities.Movements;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class responsible for the view of Ralph.
 */
public final class RalphView implements View {

    private static final int FRAME_COUNT_DX = 3;
    private static final int FRAME_COUNT_SX = 2;
    private static final int ANIMATION_DURATION = 1000;
    private final Entity ralph;
    private final ImageView imageView;
    private final Image spriteLeft;
    private final Image spriteRight;
    private Image sprite;
    private Timeline timeline;
    private int currentFrame;
    private Movements lastMovement = Movements.RIGHT;

    /**
     * Builder for the Ralph view.
     * 
     * @param ralph the Ralph entity.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public RalphView(final Entity ralph) {
        this.ralph = ralph;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("ralph_sx");
        this.spriteRight = getSource("ralph_dx");
        this.sprite = this.spriteRight;
        this.imageView.setFitHeight(((HitboxComponent) this.ralph.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getHeight());
        this.imageView.setFitWidth(((HitboxComponent) this.ralph.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getWidth());
        this.imageView.setX(this.ralph.getPosition().getX());
        this.imageView.setY(this.ralph.getPosition().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animate() {
        this.imageView.setX(this.ralph.getPosition().getX());
        this.imageView.setY(this.ralph.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.currentFrame = 0;
            this.sprite = getImage();
            lastMovement = this.ralph.getLastPosition().getX() < this.ralph.getPosition().getX()
                          ? Movements.RIGHT : Movements.LEFT;
            final int frameCount = lastMovement.equals(Movements.RIGHT) ? FRAME_COUNT_DX : FRAME_COUNT_SX;
            this.timeline = new Timeline(
                            new KeyFrame(Duration.millis((double) ANIMATION_DURATION / frameCount), e -> updateFrame()));
            this.timeline.setCycleCount(frameCount);
            this.timeline.setOnFinished(e -> this.imageView.setImage(getFrame(0)));
            this.timeline.play();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        final int frameCount = lastMovement.equals(Movements.RIGHT) ? FRAME_COUNT_DX : FRAME_COUNT_SX;
        return new WritableImage(this.sprite.getPixelReader(),
                                 index * ((int) this.sprite.getWidth()) / frameCount, 0, 
                                 ((int) this.sprite.getWidth()) / frameCount, (int) this.sprite.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView getImageView() {
        return this.imageView;
    }

    /**
     * Returns the standing Ralph image view.
     *
     * @return The standing Ralph image view.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView getStandingRalph() {
        this.sprite = this.spriteRight;
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }
 
    /**
     * Returns the image of the character based on the last movement.
     * 
     * @return the image of the character based on the last movement.
     */
    private Image getImage() {
        this.lastMovement = this.ralph.getLastPosition().getX() < this.ralph.getPosition().getX()
                                               ? Movements.RIGHT : Movements.LEFT;
        return this.lastMovement.equals(Movements.RIGHT) ? this.spriteRight : this.spriteLeft;
    }
}
