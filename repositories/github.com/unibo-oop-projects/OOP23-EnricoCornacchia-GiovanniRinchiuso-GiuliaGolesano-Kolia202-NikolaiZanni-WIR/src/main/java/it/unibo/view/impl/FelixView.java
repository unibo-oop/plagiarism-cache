package it.unibo.view.impl;

import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.api.ComponentType;
import it.unibo.model.api.Entity;
import it.unibo.model.impl.HitboxComponent;
import it.unibo.view.api.View;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 * Class responsible for the view of Felix.
 */
public final class FelixView implements View {

    private static final int FRAME_COUNT = 4;
    private static final int ANIMATION_DURATION = 1000;
    private final ImageView imageView;
    private final Image spriteLeft;
    private final Image spriteRight;
    private Image sprite;
    private final Entity felix;
    private Timeline timeline;
    private int currentFrame;

    /**
     * Builder for the Felix view.
     * 
     * @param felix the Felix entity.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public FelixView(final Entity felix) {
        this.felix = felix;
        this.imageView = new ImageView();
        this.spriteLeft = getSource("felix_movement_left");
        this.spriteRight = getSource("felix_movement_right");
        this.sprite = this.spriteRight;
        this.imageView.setFitHeight(((HitboxComponent) this.felix.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getHeight());
        this.imageView.setFitWidth(((HitboxComponent) this.felix.getTheComponent(ComponentType.HITBOX).get())
                                                                 .getHitbox().getWidth());
        this.imageView.setX(this.felix.getPosition().getX());
        this.imageView.setY(this.felix.getPosition().getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animate() {
        this.imageView.setX(this.felix.getPosition().getX());
        this.imageView.setY(this.felix.getPosition().getY());
        if (timeline == null || timeline.getStatus() != Animation.Status.RUNNING) {
            this.currentFrame = 0;
            this.sprite = getImage();
            this.timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
            this.timeline.setCycleCount(FRAME_COUNT);
            this.timeline.setOnFinished(e -> imageView.setImage(getFrame(0)));
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
        return new WritableImage(this.sprite.getPixelReader(),
                                 index * ((int) this.sprite.getWidth()) / FRAME_COUNT, 0, 
                                 ((int) this.sprite.getWidth()) / FRAME_COUNT, (int) this.sprite.getHeight());
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
     * Returns the standing Felix image view.
     *
     * @return The standing Felix image view.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView getStandingFelix() {
        this.imageView.setImage(getFrame(0));
        return this.imageView;
    }

    /**
     * Returns the appropriate sprite based on the last input received.
     *
     * @return The sprite to be displayed.
     */
    private Image getImage() {
        final List<KeyCode> inputs = this.felix.getGamePerformance().getInputs();
        return inputs.get(inputs.size() - 1) == KeyCode.D ? this.spriteRight : this.spriteLeft;
    }
}
