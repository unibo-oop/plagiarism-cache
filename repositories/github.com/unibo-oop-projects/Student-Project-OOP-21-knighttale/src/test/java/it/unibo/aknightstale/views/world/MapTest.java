package it.unibo.aknightstale.views.world;

import it.unibo.aknightstale.controllers.interfaces.MapController;
import it.unibo.aknightstale.views.BaseViewTest;
import it.unibo.aknightstale.views.interfaces.MapView;
import it.unibo.aknightstale.views.map.CrossableTile;
import it.unibo.aknightstale.views.map.SolidTile;
import javafx.stage.Stage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

@ExtendWith(ApplicationExtension.class)
class MapTest extends BaseViewTest<MapController, MapView> {

    protected MapTest() {
        super(MapView.class, MapController.class);
    }

    @Start
    @Override
    public void start(final Stage stage) {
        super.start(stage);
    }

    @Test
    @DisplayName("Check Tiles Type")
    void checkTilesType() {
        final var mapView = getView();
        Assertions.assertThat(mapView.getFloor().getClass()).isEqualTo(CrossableTile.class);
        Assertions.assertThat(mapView.getTree().getClass()).isEqualTo(SolidTile.class);
    }

    @Test
    @DisplayName("Check Tiles")
    void checkTiles() {
        final var mapView = getView();
        Assertions.assertThat(mapView.getTiles().size() > 0);
    }



}
