package view.map;

import java.util.Map;
import java.util.Optional;

import controller.map.MapController;
import controller.map.VariableCasePart;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import util.Coordinates;
import util.mapbuilder.GameMapBuilder;
import util.mapbuilder.GameMapBuilderFactory;
import view.SecondaryView;

/**
 * the view of the map.
 */
public class MapView implements SecondaryView {

    private MapController controller;
    private GameMapBuilderFactory<GridPane> gameMapBuilderFactory; // this factory produces map builders at each refresh
                                                                   // always
    // starting from the already generated terrain
    private GameMapBuilder<GridPane> gameMapToDraw; // this is a map builder generated at each refresh
    private boolean controllerSet;
    private boolean mapBuilt;
    private Optional<GridPane> updatedView;

    /**
     * sets the controller for this view.
     * 
     * @param controller the controller to set
     */
    public void setController(final MapController controller) {
        if (this.controllerSet) {
            throw new IllegalStateException();
        }
        this.controller = controller;
        this.controllerSet = true;
    }

    /** {@inheritDoc} **/
    @Override
    public void update() {
        if (!this.mapBuilt) {
            this.gameMapBuilderFactory = new ViewMapBuilderWithCacheFactoryFromBackground(this.controller.getTerrains(),
                    this.controller.getMapSize());
            this.mapBuilt = true;
        }
        final Map<Coordinates, VariableCasePart> map = this.controller.getActualMapState();
        this.gameMapToDraw = this.gameMapBuilderFactory.get();
        map.entrySet().stream().forEach(e -> {
            final VariableCasePart varCase = e.getValue();
            if (varCase.getBottom().isPresent()) {
                this.gameMapToDraw.setBottom(e.getKey(), varCase.getBottom().get());
            }
            if (varCase.getTop().isPresent()) {
                this.gameMapToDraw.setTop(e.getKey(), varCase.getTop().get());
            }
            if (varCase.getBorder().isPresent()) {
                this.gameMapToDraw.setBorder(e.getKey(), varCase.getBorder().get());
            }

        });
        this.updatedView = Optional.empty();
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalStateException if update() hasn't been called at least once
     **/
    @Override
    public Region get() {
        if (!this.mapBuilt) {
            throw new IllegalStateException(
                    "map needs to be built before being got, call update at least once before calling this method");
        }
        if (!this.updatedView.isPresent()) {
            this.updatedView = Optional.of(this.gameMapToDraw.build());
            updatedView.get().setBorder(new Border(
                    new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            updatedView.get().getChildren().forEach(c -> {
                c.setOnMouseClicked(e -> this.controller
                        .cellHit(new Coordinates(GridPane.getColumnIndex(c), GridPane.getRowIndex(c))));
            });
        }
        return this.updatedView.get();
    }

}
