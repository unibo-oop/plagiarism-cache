package view;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Implementation of {@link DimensionCalculator}.
 *
 */
public class DimensionCalculatorImpl implements DimensionCalculator {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setImageViewDimension(final ImageView img, final Double percent, final Double size) {
        img.setStyle("-fx-scale-x : " + percent * size + ";-fx-scale-y : " + percent * size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNodeFontStyle(final GridPane grid, final Double percent, final Double size) {
        grid.getChildren().forEach(b -> {
            ((Node) b).setStyle(String.format("-fx-font-size: %dpx;", (int) (percent * size)));
        }); 
    }

}
