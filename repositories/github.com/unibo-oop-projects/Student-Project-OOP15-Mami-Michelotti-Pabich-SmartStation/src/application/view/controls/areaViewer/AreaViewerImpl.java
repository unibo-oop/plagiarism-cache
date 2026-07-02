package application.view.controls.areaViewer;

import java.io.IOException;
import java.util.List;

import application.model.buildables.pump.Pump;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * Implements the area interface and contain all the logic of an area.
 * @author Marcin Pabich
 */
public class AreaViewerImpl extends BorderPane implements AreaViewer {

    @FXML
    private Double defaultSize, defaultSpacing;
    
    @FXML
    private VBox pumpContainer;
	
    @FXML
    private Rectangle car;

	
	
	
    /**
     * Main constructor for the Area.
     */
    public AreaViewerImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AreaViewer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    /**
     * Secondary constructor of the AreaViewer that initialize pumps.
     * @param pumps List of pumps
     */
    public AreaViewerImpl(final List<Pump> pumps) {
        this();        
        this.addPumps(pumps);
    }
    
    /**
     * Private method, permit to create a colored rectangle
     * @param color Fill of the rectangle
     * @return Colored, sized and alligned rectangle ready to use
     */
    private Rectangle createRectangle(final Color color) {
        Rectangle rectangle = new Rectangle();
        
        rectangle.setFill(color);
        rectangle.setWidth(defaultSize);
        rectangle.setHeight(defaultSize);       
        
        BorderPane.setAlignment(rectangle, Pos.CENTER);
            
        return rectangle;
    }

    @Override
    public void addPumps(final List<Pump> pumps) {
        this.removePumps();

        for (Pump pump : pumps) {
            pumpContainer.getChildren().add(createRectangle(pump.getType().getColor()));
        }     
    }

    @Override
    public void removePumps() {
        pumpContainer.getChildren().clear();
    }

    @Override
    public void setOccupied() {
	this.car.setVisible(true);
	this.car.setFill(Color.YELLOW);
    }

    @Override
    public void setFree() {
	this.car.setVisible(false);
    }
	
}
