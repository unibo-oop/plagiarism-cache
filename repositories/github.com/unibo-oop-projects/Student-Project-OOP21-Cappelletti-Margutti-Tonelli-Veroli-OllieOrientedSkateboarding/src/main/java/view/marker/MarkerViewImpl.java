package view.marker;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.marker.Marker;
import model.marker.MarkerManager;

/**
 * 
 * Implementation of {@link MarkerView}.
 *
 */
public class MarkerViewImpl implements MarkerView {

    private static final int TEXT_FONT_SIZE = 30;
    private static final int TEXT_PADDING_X = 80;
    private static final int TEXT_PADDING_Y = 20;
    private final Pane pane;
    private final MarkerManager markerManager;

    /**
     * Creates a new MarkerViewImpl.
     * @param pane the {@link Pane}.
     * @param markerManager the {@link MarkerManager}.
     */
    public MarkerViewImpl(final Pane pane, final MarkerManager markerManager) {
        super();
        this.pane = pane;
        this.markerManager = markerManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void render() {
        this.markerManager.getMarkers().forEach(op -> {
            this.renderMarker(op.get());
        });
    }

    /**
     * Renders a single {@link Marker} adding a text notice if marker has it. 
     * @param marker the {@link Marker} to render.
     */
    private void renderMarker(final Marker marker) {
        final ImageView image = new ImageView(marker.getImage());
        image.setX(marker.getX() - marker.getDimension().getWidth());
        image.setY(marker.getY());
        this.pane.getChildren().add(image);

        if (!marker.getText().isEmpty()) {
            final Text tx = new Text(marker.getText());
            tx.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, TEXT_FONT_SIZE));
            tx.setX(marker.getX() - TEXT_PADDING_X);
            tx.setY(marker.getY() + TEXT_PADDING_Y);
            this.pane.getChildren().add(tx);
        }

    }

}
