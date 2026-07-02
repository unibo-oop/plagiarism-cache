package net.pokemonbt.view;

import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

/**
 * Letterbox component used for resizing.
 */
public final class LetterboxPane extends StackPane {

    private static final double DESIGN_WIDTH = 1200;
    private static final double DESIGN_HEIGHT = 900;

    private final Pane contentPane;

    /**
     * Costructor for the LetterboxPane custom JavaFX component.
     * 
     * @param view the component to be set inside the LetterboxPane.
     */
    public LetterboxPane(final Parent view) {
        this.setStyle("-fx-background-color: black;");

        this.contentPane = new Pane();

        contentPane.setPrefSize(DESIGN_WIDTH, DESIGN_HEIGHT);
        contentPane.setMinSize(DESIGN_WIDTH, DESIGN_HEIGHT);
        contentPane.setMaxSize(DESIGN_WIDTH, DESIGN_HEIGHT);

        contentPane.setClip(new Rectangle(DESIGN_WIDTH, DESIGN_HEIGHT));

        if (view != null) {
            contentPane.getChildren().add(view);
        }

        this.getChildren().add(contentPane);

        this.widthProperty().addListener((obs, oldVal, newVal) -> resize());
        this.heightProperty().addListener((obs, oldVal, newVal) -> resize());
    }

    /**
     * Changes the view inside the LetterboxPane.
     * 
     * @param newView the new View Component to be set in the LetterboxPane.
     */
    public void setView(final Parent newView) {
        contentPane.getChildren().setAll(newView);
    }

    /**
     * Resizes the scene following the design with and height.
     */
    private void resize() {
        final double currentWidth = getWidth();
        final double currentHeight = getHeight();

        if (currentWidth == 0 || currentHeight == 0) {
            return;
        }

        final double scaleX = currentWidth / DESIGN_WIDTH;
        final double scaleY = currentHeight / DESIGN_HEIGHT;

        final double scaleFactor = Math.min(scaleX, scaleY);

        contentPane.setScaleX(scaleFactor);
        contentPane.setScaleY(scaleFactor);

    }
}
