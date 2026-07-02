package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.common.Pair;
import it.unibo.view.api.View;
import javafx.animation.Timeline;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.util.Duration;

/**
 * Class to manage a view of a window.
 */
public final class WindowsView implements View {
    private static final int FRAME_COUNT = 4;
    private static final int FRAME_WIDTH = 39; 
    private static final int FRAME_HEIGHT = 60; 
    private static final int ANIMATION_DURATION = 1000; 
    private final ImageView imageView;
    private final Image spriteSheet;
    private Timeline timeline;
    private int currentFrame;
    private boolean isFixed;
    /**
     * Constructor.
     * @param pos the position of the window.
     */
    public WindowsView(final Pair<Double, Double> pos) {
        spriteSheet = getSource("window");
        imageView = new ImageView();
        imageView.setFitHeight(FRAME_HEIGHT);
        imageView.setFitWidth(FRAME_WIDTH);
        this.imageView.setX(pos.getX());
        this.imageView.setY(pos.getY());
        this.isFixed = false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image getSource(final String name) {
        return new Image(getClass().getResourceAsStream("/" + name + ".png"));
    }

    /**
     * Method to change the animation of the fixing window.
     */
    public void fixAnimation() {
        if (timeline != null && timeline.getStatus() == Animation.Status.RUNNING) {
            return;
        }
        if (isFixed) {
            return; 
        }
        currentFrame = FRAME_COUNT - 1;
        Platform.runLater(() -> imageView.setImage(getFrame(currentFrame))); 

        timeline = new Timeline(new KeyFrame(Duration.millis(ANIMATION_DURATION / FRAME_COUNT), e -> updateFrame()));
        timeline.setCycleCount(FRAME_COUNT);
        timeline.setOnFinished(e -> {
            Platform.runLater(() -> imageView.setImage(getFrame(0)));
            isFixed = true;
        });
        timeline.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateFrame() {
        if (currentFrame >= 0) {
            Platform.runLater(() -> {
                imageView.setImage(getFrame(currentFrame));
                currentFrame--;
            });
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Image getFrame(final int index) {
        return new WritableImage(spriteSheet.getPixelReader(), index * FRAME_WIDTH, 0, FRAME_WIDTH, FRAME_HEIGHT);
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
     * Static view of a fixed window.
     * @return the image view of a fixed window.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView fixedwindows() {
        this.imageView.setImage(getFrame(0));
        isFixed = true;
        return this.imageView;
    }

    /**
     * Static view of a broken window.
     * @return the image view of a broken window.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public ImageView brokenWindow() {
        this.imageView.setImage(getFrame(FRAME_COUNT - 1));
        isFixed = false;
        return this.imageView;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void animate() {
    }
}
