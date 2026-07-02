package application.view.tabs.reservesEditor;

import java.util.List;

import application.ExitStatus;
import application.Main;
import application.controller.tabs.ReservesEditorCtrl;
import application.model.buildables.reserve.Reserve;
import application.model.services.Fuel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * Implements the StationEditor interface and contains the logic of StationEditor.
 * @author Marcin Pabich
 */
public class ReservesEditorImpl extends BorderPane implements ReservesEditor {
	 
    private ReservesEditorCtrl controller; 
    
    
    //Comboboxes
    @FXML
    private ComboBox<String> cmbReservesEdit, cmbReserveRefill, cmbReserveRepair;
    
    @FXML
    private ComboBox<String> cmbEditFuelType, cmbFuelType;
    
    
    //Texfields
    @FXML
    private TextField txfEditCapacity, txfEditPrice, txfEditDurability, txfEditRepairCost, txfRefill;
    
    @FXML
    private TextField txfCapacity, txfPrice, txfDurability, txfRepairCost;
    
    
    //Slider
    @FXML
    private Slider sldRepair; 
    
    //Significant buttons   
    @FXML
    private Button btnRepair;
    
    //Modifying section
    @FXML
    private VBox vbxModifyingPanel;
    
    //Labels
    @FXML
    private Label lblRepair, lblRefill;
	
    
    /**
     * Constructor for the StationEditor that loads the content.
     */
    public ReservesEditorImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReservesEditor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
     
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "ReservesEditor.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);
        }
        
        this.cmbEditFuelType.setVisibleRowCount(4);
        this.cmbFuelType.setVisibleRowCount(4);
        this.cmbReserveRefill.setVisibleRowCount(4);
        this.cmbReserveRepair.setVisibleRowCount(4);
        this.cmbReservesEdit.setVisibleRowCount(4);     
    }

    //Controller
    @Override
    public void setController(final ReservesEditorCtrl ctrl) {
	this.controller = ctrl;
    }
 
    
    
    //Loading methods
    @Override
    public void loadReserves(final List<Reserve> reserves) {        
        this.cmbReserveRefill.getItems().clear();
        this.cmbReserveRepair.getItems().clear();
        this.cmbReservesEdit.getItems().clear();
        
        reserves.forEach(r -> {
            this.cmbReserveRefill.getItems().add(r.getType().getName());
            this.cmbReserveRepair.getItems().add(r.getType().getName());
            this.cmbReservesEdit.getItems().add(r.getType().getName());
        });  
    }

    @Override
    public void loadFuels(final List<Fuel> fuels) {
        this.cmbFuelType.getItems().clear();
        this.cmbEditFuelType.getItems().clear();
        
        fuels.forEach(f -> {
            this.cmbFuelType.getItems().add(f.getName());
            this.cmbEditFuelType.getItems().add(f.getName());
        });
    }
    
    
    
    //Show/hide panels
    @Override
    public void showEditingPanel() {
        this.vbxModifyingPanel.setVisible(true);
    }

    @Override
    public void hideEditingPanel() {
        this.vbxModifyingPanel.setVisible(false);
    }

    @Override
    public boolean isEditingPanelVisible() {
        return this.vbxModifyingPanel.isVisible();
    }
    

    
    //Getters/setters for modifying
    @Override
    public String getModifyReserve() {
        return this.cmbReservesEdit.getValue();
    }

    @Override
    public String getModifyFuel() {
        return this.cmbEditFuelType.getValue();
    }

    @Override
    public void setModifyFuel(final String fuel) {
        this.cmbEditFuelType.setValue(fuel);
    }

    @Override
    public String getModifyCapacity() {
        return this.txfEditCapacity.getText();
    }

    @Override
    public void setModifyCapacity(final String capacity) {
        this.txfEditCapacity.setText(capacity);
    }

    @Override
    public String getModifyPrice() {
        return this.txfEditPrice.getText();
    }

    @Override
    public void setModifyPrice(final String price) {
        this.txfEditPrice.setText(price);
    }

    @Override
    public String getModifyDurability() {
        return this.txfEditDurability.getText();
    }

    @Override
    public void setModifyDurability(final String durability) {
        this.txfEditDurability.setText(durability);
    }

    @Override
    public String getModifyRepairCost() {
        return this.txfEditRepairCost.getText();
    }

    @Override
    public void setModifyRepairCost(final String repairCost) {
        this.txfEditRepairCost.setText(repairCost);
    }

    
    
    //Getters for refill
    @Override
    public String getRefillReserve() {
        return this.cmbReserveRefill.getValue();
    }
    
    @Override
    public String getRefill() {
        return this.txfRefill.getText();
    }
    
    @Override
    public void setRefillQuantities(final String current, final String max) {
        this.lblRefill.setText(current + "/" + max);
    }

    
    //Getters/setters for repair
    @Override
    public Double getRepairValue() {
        return this.sldRepair.getValue();
    }

    @Override
    public void setRepairValue(final String value) {
        this.btnRepair.setText("Repair (" + value.toString() + ")");
    }
    
    @Override
    public void setRepairQuantities(final String current, final String max) {
        this.lblRepair.setText(current + "/" + max);
    }
    
    
    
    //Adding methods
    @Override
    public String getFuel() {
        return this.cmbFuelType.getValue();
    }

    @Override
    public String getCapacity() {
        return this.txfCapacity.getText();
    }

    @Override
    public String getPrice() {
        return this.txfPrice.getText();
    }

    @Override
    public String getDurability() {
        return this.txfDurability.getText();
    }

    @Override
    public String getRepairCost() {
        return this.txfRepairCost.getText();
    }    

    
    
    //Event handlers
    
    //Modifying
    @FXML
    private void btnSelectEdit_click(final MouseEvent e) {
        this.controller.selectEdit();
    }
    
    @FXML
    private void btnChangeFuelType_click(final MouseEvent e) {
        this.controller.changeFuelType();
    }
    @FXML
    private void btnChangeCapacity_click(final MouseEvent e) {
        this.controller.changeCapacity();
    } 
    
    @FXML
    private void btnChangeRepairCost_click(final MouseEvent e) {
        this.controller.changeRepairCost();
    }
        
    @FXML
    private void btnChangePrice_click(final MouseEvent e) {
        this.controller.changePrice();
    }
    
    @FXML
    private void btnChangeDurability_click(final MouseEvent e) {
        this.controller.changeDurability();
    }
    
    @FXML
    private void btnDelete_click(final MouseEvent e) {
        this.controller.deleteReserve();
    }
     
    //Refill  
    @FXML
    private void btnSelectRefill_click(final MouseEvent e) {
        this.controller.selectRefill();
    }
    
    @FXML
    private void btnRefill_click(final MouseEvent e) {
        this.controller.refill();
    }
     
    //Repair
    @Override
    public String getRepairReserve() {
        return this.cmbReserveRepair.getValue();
    }
    
    @FXML
    private void btnSelectRepair_click(final MouseEvent e) {
        this.controller.selectRepair();
    }
    
    @FXML
    private void btnRepair_click(final MouseEvent e) {
        this.controller.repair();
    }  
        
    //Adding
    @FXML
    private void btnAddReserve_click(final MouseEvent e) {
        this.controller.addReserve();
    }
}
