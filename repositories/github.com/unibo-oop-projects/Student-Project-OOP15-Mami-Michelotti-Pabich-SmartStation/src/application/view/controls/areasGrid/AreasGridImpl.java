package application.view.controls.areasGrid;

import java.io.IOException;
import java.util.List;

import application.model.buildables.area.Area;
import application.view.controls.areaViewer.AreaViewer;
import application.view.controls.areaViewer.AreaViewerImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/**
 * Implements the ReserveStatus interface and contain all the logic of a reservestatus management.
 * @author Marcin Pabich
 */
public class AreasGridImpl extends BorderPane implements AreasGrid {

    @FXML
    private GridPane grdAreas;

    /**
     * Main constructor for the ReserveStatus.
     */
    public AreasGridImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AreasGrid.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }



    @Override
    public void deleteArea(final AreaViewer area) {
        grdAreas.getChildren().remove(area);
    }

    @Override
    public void clearAllAreas() {
        grdAreas.getChildren().clear();       
    }



    @Override
    public void drawArea(final List<Area> areas) {
        this.clearAllAreas();     
        
        for (Area a : areas) {
            final AreaViewerImpl aViewer = new AreaViewerImpl(a.getAllPumps());
            GridPane.setRowIndex(aViewer, a.getXPosition());
            GridPane.setColumnIndex(aViewer, a.getYPosition());
       
            grdAreas.getChildren().add(aViewer);
        }
    }
    


}
