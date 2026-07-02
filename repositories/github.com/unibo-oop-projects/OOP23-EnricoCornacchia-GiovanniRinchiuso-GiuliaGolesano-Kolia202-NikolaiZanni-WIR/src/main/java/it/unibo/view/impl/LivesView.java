package it.unibo.view.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.model.impl.LivesComponent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * LivesView represents the view for displaying the player's remaining lives.
 * This class can be extended to customize the lives display.
 */
public final class LivesView extends StackPane {
    private final LivesComponent livesComponent;
    private final HBox livesContainer;
    private static final int WIDTH = 40;
    private static final int HEIGHT = 40;
    private static final int SPACING = 5;
    /**
     * Constructs a LivesView with the given LivesComponent.
     *
     * @param livesComponent the LivesComponent to use for lives data
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP2", justification = "We need the original object")
    public LivesView(final LivesComponent livesComponent) {
        this.livesComponent = livesComponent;
        this.livesContainer = new HBox();
        this.getChildren().add(livesContainer);
        updateLivesLabel();
        this.livesComponent.addLivesChangeListener(newLives -> updateLivesLabel());
    }

    private void updateLivesLabel() {
        final int actualLives = livesComponent.getLives();
        livesContainer.getChildren().clear();
        for (int i = 0; i < actualLives; i++) {
            final ImageView lifeImage = new ImageView(new Image("life.png"));
            lifeImage.setFitWidth(WIDTH);
            lifeImage.setFitHeight(HEIGHT);
            livesContainer.getChildren().add(lifeImage);
        }
        livesContainer.setSpacing(SPACING);
    }
}
