package application.view.tabs.overview;

import java.util.List;

import application.ExitStatus;
import application.Main;
import application.controller.tabs.OverviewCtrl;
import application.model.buildables.area.Area;
import application.view.controls.areasGrid.AreasGridImpl;
import application.view.controls.reserveStatus.ReserveStatusImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Implenets the Overview interface and contains all the logic for the overview tab.
 * @author Marcin Pabich
 */
public class OverviewImpl extends BorderPane implements Overview {

    @FXML
    private VBox vbxReserves;
    
    @FXML
    private AreasGridImpl areasGrid;
    
    @FXML 
    private ListView<String> lsvLog;
     
    //Left here for future releases
    @SuppressWarnings("unused")
    private OverviewCtrl controller;
    
	
    /**
     * Constructor for the overview that loads the content.
     */
    public OverviewImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
     

        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "Overview.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);
        }    
    }

    
    
    //Controller setter
    @Override
    public void setController(final OverviewCtrl ctrl) {
	this.controller = ctrl;
    }

    
    
    //Reserve status
    @Override
    public void addReserveStatus(final String name, final String price, final String remain, final String max, final Double progress) {
	this.vbxReserves.getChildren().add(new ReserveStatusImpl(name, price, remain, max, progress));
    }
    
    @Override
    public void refreshReserveStatus(final String name, final String price, final String remain, final String max, final Double progress) {
        ReserveStatusImpl rsv = this.searchReserve(name);
        
        if (rsv != null) {
            rsv.setPrice(price);
            rsv.setRemain(remain);
            rsv.setMaxReserve(max);
            rsv.setProgress(progress);
        }        
    }
    
    @Override
    public void refreshReserveStatus(final String name, final String remain, final Double progress) {
        ReserveStatusImpl rsv = this.searchReserve(name);
       
        if (rsv != null) {
            rsv.setRemain(remain);
            rsv.setProgress(progress);
        }   
    }

    @Override
    public void removeReserves() {
        this.vbxReserves.getChildren().clear();
    }


    
    //List management
    @Override
    public void addElementToList(final String element) {
        this.lsvLog.getItems().add(element);
    }

    @Override
    public void clearList() {
        this.lsvLog.getItems().clear();
    }
    
    
    
    //Grid management
    @Override
    public void refreshGrid(final List<Area> areas) {
        this.areasGrid.drawArea(areas);
    }

  
    
    //Private methods
    /**
     * Search for the reserve
     * @param name name of the reserve
     * @return the ReserveStatusImpl if found or null otherwise
     */
    private ReserveStatusImpl searchReserve(final String name) {
        for (Node node : this.vbxReserves.getChildren()) {
            if (node.getClass().equals(ReserveStatusImpl.class)) {
                ReserveStatusImpl rsv = (ReserveStatusImpl) node;
                if (rsv.getFuelName().equals(name)) {
                    return rsv;
                }
            }         
        }       
        return null;
    }





}
