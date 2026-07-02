package it.unibo.view.impl;

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
import javafx.util.Duration;

/**
 * Abstract class that implements the common functionality for entity views.
 */
public abstract class BaseBirdCakeView implements View {
    private static final int FRAME_COUNT = 2;
    private static final int ANIMATION_DURATION = 1000;
    private final Entity entity;
    private final ImageView imageView;
    private Image sprite;
    private final Timeline timeline;
    private int currentFrame;
    private static final String JUSTIFICATION = "We need the original object";

    /**
     * Constructor.
     * 
     * @param entity the entity to be represented
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = JUSTIFICATION)
    public BaseBirdCakeView(final Entity entity) {
        this.entity = entity;
        this.imageView = new ImageView();
        initializeImageView();
        this.timeline = new Timeline(
                new KeyFrame(
                        Duration.millis(ANIMATION_DURATION / FRAME_COUNT),
                        e -> updateFrame()));
        this.timeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Initializes the image view based on the entity's hitbox.
     */
    private void initializeImageView() {
        this.imageView.setFitHeight(
                ((HitboxComponent) this.entity.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getHeight());
        this.imageView.setFitWidth(
                ((HitboxComponent) this.entity.getTheComponent(ComponentType.HITBOX).get()).getHitbox().getWidth());
        this.imageView.setX(this.entity.getPosition().getX());
        this.imageView.setY(this.entity.getPosition().getY());
    }

    /**
     * Initializes the sprite after construction.
     * 
     * @param spriteName the name of the sprite image
     */
    protected void initializeSprite(final String spriteName) {
        this.sprite = getSource(spriteName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animate() {
        this.imageView.setX(this.entity.getPosition().getX());
        this.imageView.setY(this.entity.getPosition().getY());
        if (this.timeline.getStatus() != Animation.Status.RUNNING) {
            this.timeline.play();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(BaseBirdCakeView.class.getResourceAsStream("/" + name + ".png"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void updateFrame() {
        imageView.setImage(getFrame(currentFrame));
        currentFrame = (currentFrame + 1) % FRAME_COUNT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        final int frameWidth = (int) this.sprite.getWidth() / FRAME_COUNT;
        final int frameHeight = (int) this.sprite.getHeight();
        final int x = index * frameWidth;
        return new WritableImage(this.sprite.getPixelReader(), x, 0, frameWidth, frameHeight);
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
     * Returns the entity.
     *
     * @return the entity
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = JUSTIFICATION)
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * Returns the sprite image.
     *
     * @return the sprite image
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = JUSTIFICATION)
    public Image getSprite() {
        return this.sprite;
    }
}
