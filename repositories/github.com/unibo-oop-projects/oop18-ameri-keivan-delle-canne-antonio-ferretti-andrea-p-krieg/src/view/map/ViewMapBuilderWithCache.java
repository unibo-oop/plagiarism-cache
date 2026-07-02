package view.map;

import java.util.Map;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.util.Pair;
import util.Coordinates;
import util.image.ImageFactory;
import util.image.ImageFactoryWithCache;
import util.mapbuilder.AbstractGameMapBuilderUsingCaseBuilders;

/**
 * package protected.
 */
class ViewMapBuilderWithCache extends AbstractGameMapBuilderUsingCaseBuilders<GridPane, StackPane> {

    private boolean built;
    private static final ImageFactory IMAGE_FACTORY = new ImageFactoryWithCache();

    ViewMapBuilderWithCache(final Map<Coordinates, Image> base, final Pair<Integer, Integer> size) {
        super(size, (Coordinates cords) -> new StackPaneCaseBuilder(base.get(cords), IMAGE_FACTORY));
    }

    /** {@inheritDoc} **/
    @Override
    public GridPane build() {
        if (this.built) {
            throw new IllegalStateException("build method can't be called twice");
        }
        this.built = true;
        final GridPane result = new GridPane();
        for (int i = 0; i < super.getSize().getKey(); i++) {
            for (int j = 0; j < super.getSize().getValue(); j++) {
                final int x = i;
                final int y = j;
                final Coordinates cords = new Coordinates(x, y);
                final StackPane cell = super.getMap().get(cords).build();
                cell.prefWidthProperty().bind(result.widthProperty().divide(super.getSize().getKey()));
                cell.prefHeightProperty().bind(result.heightProperty().divide(super.getSize().getValue()));
                result.add(cell, x, y);
            }
        }
        return result;
    }
}
