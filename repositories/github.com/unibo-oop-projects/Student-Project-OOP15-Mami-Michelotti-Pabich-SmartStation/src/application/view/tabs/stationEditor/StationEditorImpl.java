package application.view.tabs.stationEditor;

import java.util.ArrayList;
import java.util.List;

import application.ExitStatus;
import application.Main;
import application.controller.tabs.StationEditorCtrl;
import application.model.buildables.area.Area;
import application.model.buildables.pump.Pump;
import application.view.controls.areasGrid.AreasGridImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;


/**
 * Implements the StationEditor interface and contains the logic of StationEditor.
 * @author Marcin Pabich
 */
public class StationEditorImpl extends BorderPane implements StationEditor {
 
    @FXML
    private AreasGridImpl argAreasGrid;
    
    @FXML
    private Button btnChangeView;
    
    @FXML
    private VBox vbxModifyArea, vbxAddArea, vbxAreaDetails, vbxModifySelection;
    
    
    //Controls for adding an area
    @FXML
    private TextField txfXCoords, txfYCoords, txfPrice;
    
    @FXML
    private ComboBox<String> cmbPump1, cmbPump2, cmbPump3, cmbPump4;
    
    
    //Controls for modifying an area
    @FXML
    private ComboBox<String> cmbModifyXCoords, cmbModifyYCoords;
    
    @FXML
    private TextField txfModifyXChange, txfModifyYChange, txfModifyPrice;
    
    @FXML
    private ComboBox<String> cmbModifyPump1, cmbModifyPump2, cmbModifyPump3, cmbModifyPump4;
	
    
    //Error messages
    @FXML
    private Label lblAddError, lblModifyError, lblChangeCoordsError;
    
    
    //The controller
    private StationEditorCtrl controller;
	
    
    /**
     * Constructor for the StationEditor that loads the content.
     */
    public StationEditorImpl() {
        //Load the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StationEditor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);          
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "StationEditor.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);       
        }      
        
        //Set max visible rows
        this.cmbModifyXCoords.setVisibleRowCount(4);
        this.cmbModifyYCoords.setVisibleRowCount(4);
        
        this.cmbModifyPump1.setVisibleRowCount(4);
        this.cmbModifyPump2.setVisibleRowCount(4);
        this.cmbModifyPump3.setVisibleRowCount(4);
        this.cmbModifyPump4.setVisibleRowCount(4);
        
        this.cmbPump1.setVisibleRowCount(4);
        this.cmbPump2.setVisibleRowCount(4);
        this.cmbPump3.setVisibleRowCount(4);
        this.cmbPump4.setVisibleRowCount(4);     
    }


    
    //Set the controller    
    @Override
    public void setController(final StationEditorCtrl ctrl) {
        this.controller = ctrl;      
    }
    
    
    
    //Loading methods   
    @Override
    public void loadCoordinates(final int x, final int y) {      
        //Adding as many rows as the area are (x)
        for (int i = 0; i < x; i++) {
            cmbModifyXCoords.getItems().add(String.valueOf(i));
        }
        
        //Adding as many rows as the area are (y)
        for (int i = 0; i < y; i++) {
            cmbModifyYCoords.getItems().add(String.valueOf(i));
        }
    }
      
    @Override
    public void loadPumps(final List<Pump> pumps) {  
        this.cmbPump1.getItems().clear(); 
        this.cmbPump2.getItems().clear(); 
        this.cmbPump3.getItems().clear(); 
        this.cmbPump4.getItems().clear(); 
        
        this.cmbModifyPump1.getItems().clear();
        this.cmbModifyPump2.getItems().clear();
        this.cmbModifyPump3.getItems().clear();
        this.cmbModifyPump4.getItems().clear();
        
        this.cmbModifyPump1.getItems().add("-");
        this.cmbModifyPump2.getItems().add("-");
        this.cmbModifyPump3.getItems().add("-");
        this.cmbModifyPump4.getItems().add("-");
        
        //For each pump found add it to every combobox
        pumps.stream().forEach(p -> {
            this.cmbPump1.getItems().add(p.getName());
            this.cmbPump2.getItems().add(p.getName());
            this.cmbPump3.getItems().add(p.getName());
            this.cmbPump4.getItems().add(p.getName());        
            
            this.cmbModifyPump1.getItems().add(p.getName());
            this.cmbModifyPump2.getItems().add(p.getName());
            this.cmbModifyPump3.getItems().add(p.getName());
            this.cmbModifyPump4.getItems().add(p.getName());
        });
    }

    @Override
    public void refreshGrid(final List<Area> areas) {
        argAreasGrid.drawArea(areas);
    }

    
    
    //Show / hide panels    
    @Override
    public void showModifyngPanel() {
        this.vbxModifyArea.setVisible(true);
        this.vbxAddArea.setVisible(false);
    }
    
    @Override
    public void hideModifyingPanel() {
        this.vbxModifyArea.setVisible(false);
    }
    
    @Override
    public void showDetailsPanel() {
        this.vbxAreaDetails.setVisible(true);
    }

    @Override
    public void hideDetailsPanel() {
        this.vbxAreaDetails.setVisible(false);
    }  
    
    @Override
    public void showAddingPanel() {
        this.vbxAddArea.setVisible(true);
        this.vbxModifyArea.setVisible(false);
    }
    
    @Override
    public void hideAddingPanel() {
        this.vbxAddArea.setVisible(false);
    }
    
    @Override
    public void enableSelectArea() {
        this.vbxModifySelection.setDisable(false);
    }

    @Override
    public void disableSelectArea() {
        this.vbxModifySelection.setDisable(true);
    }
    
    @Override
    public boolean isAddingPanelVisible() {
        return this.vbxAddArea.isVisible();
    }

    @Override
    public boolean isModifyingPanelVisible() {
        return this.vbxModifyArea.isVisible();
    }
    
    @Override
    public boolean isDetailsPanelVisible() {
        return this.vbxAreaDetails.isVisible();
    }
    
    @Override
    public boolean isSelectAreaEnabled() {
        return !this.vbxModifySelection.isDisabled();
    }
    
    
    @Override
    public void changeButtonText(final String s) {
        this.btnChangeView.setText(s);
    }
     


    //Adding new area  
    @Override
    public String getXCoords() {
        return this.txfXCoords.getText();
    }

    @Override
    public String getYCoords() {
        return this.txfYCoords.getText();
    }

    @Override
    public List<String> getPumps() {
        List<String> tmpPumps = new ArrayList<>();
        
        tmpPumps.add(this.cmbPump1.getValue());
        tmpPumps.add(this.cmbPump2.getValue());
        tmpPumps.add(this.cmbPump3.getValue());
        tmpPumps.add(this.cmbPump4.getValue());
        
        return tmpPumps;
    }

    
    
    //Modifying area
    @Override
    public String getModifyX() {
        return this.cmbModifyXCoords.getValue();
    }

    @Override
    public String getModifyY() {
        return this.cmbModifyYCoords.getValue();
    }

    @Override
    public String getModifyChangeX() {
        return this.txfModifyXChange.getText();
    }
    
    @Override
    public void setModifyXChange(final String x) {
        this.txfModifyXChange.setText(x);
    }

    @Override
    public String getModifyChangeY() {
        return this.txfModifyYChange.getText();
    }
    
    @Override
    public void setModifyYChange(final String y) {
        this.txfModifyYChange.setText(y);
    }

    @Override
    public List<String> getModifyPumps() {
        List<String> tmpPumps = new ArrayList<>();
        
        tmpPumps.add(this.cmbModifyPump1.getValue());
        tmpPumps.add(this.cmbModifyPump2.getValue());
        tmpPumps.add(this.cmbModifyPump3.getValue());
        tmpPumps.add(this.cmbModifyPump4.getValue());
        
        return tmpPumps;
    }
  
    @Override
    public void setModifyPumps(final List<String> pumps) {
        if (pumps.size() >= 1) {
            this.cmbModifyPump1.setValue(pumps.get(0));
        }
        
        if (pumps.size() >= 2) {
            this.cmbModifyPump2.setValue(pumps.get(1));
        }
        
        if (pumps.size() >= 3) {
            this.cmbModifyPump3.setValue(pumps.get(2));
        }
        
        if (pumps.size() == 4) {
            this.cmbModifyPump4.setValue(pumps.get(3));
        }
    }
    
    

    //Error messages   
    @Override
    public void showAddErrorMessage(final String s) {
        this.lblAddError.setVisible(true);
        this.lblAddError.setText(s);
    }

    @Override
    public void hideAddErrorMessage() {
        this.lblAddError.setVisible(false);
    }

    @Override
    public void showModifyErrorMessage(final String s) {
        this.lblModifyError.setVisible(true);
        this.lblModifyError.setText(s);
    }

    @Override
    public void hideModifyErrorMessage() {
        this.lblModifyError.setVisible(false);
    }

    @Override
    public void showModifyCoordsMessage(final String s) {
        this.lblChangeCoordsError.setVisible(true);
        this.lblChangeCoordsError.setText(s);
    }

    @Override
    public void hideModifyCoordsMessage() {
        this.lblChangeCoordsError.setVisible(false);
    }


    
    //Event Handlers
    //Modifying
    @FXML
    private void btnOK_click(final MouseEvent e) {
        this.controller.selectionConfirm();
    }   
    
    @FXML
    private void btnChangePosition_click(final MouseEvent e) {
        this.controller.changePosition();
    }
    
    @FXML
    private void btnConfirmPumps_click(final MouseEvent e) {
        this.controller.confirmPumps();
    }
    
    @FXML
    private void btnRemoveArea_click(final MouseEvent e) {
        this.controller.removeArea();
    }
     
    //Adding
    @FXML
    private void btnInsertArea_click(final MouseEvent e) {
        this.controller.insertArea();
    }
     
    //Switching between panels
    @FXML
    private void btnSwtich_click(final MouseEvent e) {
        this.controller.switchPanel();
    }
  
}
