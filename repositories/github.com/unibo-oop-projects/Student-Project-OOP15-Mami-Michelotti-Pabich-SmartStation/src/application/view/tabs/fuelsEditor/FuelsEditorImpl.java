package application.view.tabs.fuelsEditor;

import java.util.List;

import application.ExitStatus;
import application.Main;
import application.controller.tabs.FuelsEditorCtrl;
import application.model.services.Fuel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class FuelsEditorImpl extends BorderPane implements FuelsEditor {

    //Controller
    private FuelsEditorCtrl controller;
    
    //EDITING
    @FXML
    private ComboBox<String> cmbFuels;
    
    @FXML
    private Label lblEditTitle;
    
    @FXML
    private TextField txfEditName, txfEditPrice, txfEditWPrice, txfEditColor;
    
    @FXML
    private VBox vbxModifyingPanel;
    
   
    //ADDING
    @FXML
    private TextField txfFuelName, txfFuelPrice, txfFuelWPrice, txfFuelColor;
    
	
    /**
     * Constructor for the StationEditor that loads the content.
     */
    public FuelsEditorImpl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FuelsEditor.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
     
        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            ExitStatus.showErrorDialog("FXML Loading Exception", "FuelsEditor.fxml could not be loaded", "Exception message: " + exception.getMessage());
            Main.close(ExitStatus.FXMLLoadingExcp);
        }
        
        this.cmbFuels.setVisibleRowCount(4);             
    }

    

    //Controller setter
    @Override
    public void setController(final FuelsEditorCtrl ctrl) {
        this.controller = ctrl;
    }
    
    
    
    //Loading
    @Override
    public void loadFuels(final List<Fuel> fuels) {
        this.cmbFuels.getItems().clear();       
        fuels.forEach(f -> this.cmbFuels.getItems().add(f.getName()));      
    }
    
    
    
    //Show/hide panel
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
    
    
    
    //Modifying
    @Override
    public String getSelectedFuel() {
        return this.cmbFuels.getValue();
    }

    @Override
    public String getModifyName() {
        return this.txfEditName.getText();
    }

    @Override
    public String getModifyPrice() {
        return this.txfEditPrice.getText();
    }

    @Override
    public String getModifyWhoesalePrice() {
        return this.txfEditWPrice.getText();
    }

    @Override
    public String getModifyColor() {
        return this.txfEditColor.getText();
    }

    @Override
    public void setModifyPanelVisibility(final boolean visibility) {
        this.vbxModifyingPanel.setVisible(visibility);
    }

    @Override
    public void setModifyName(final String name) {
        this.txfEditName.setText(name);
    }

    @Override
    public void setModifyPrice(final String price) {
        this.txfEditPrice.setText(price);
    }

    @Override
    public void setModifyWhoesalePrice(final String wprice) {
        this.txfEditWPrice.setText(wprice);
    }

    @Override
    public void setModifyColor(final String color) {
        this.txfEditColor.setText(color);
    }
    
    
    
    //Adding fuel
    @Override
    public String getFuelName() {
        return this.txfFuelName.getText();
    }

    @Override
    public String getFuelPrice() {
        return this.txfFuelPrice.getText();
    }

    @Override
    public String getFuelWhoesalePrice() {
        return this.txfFuelWPrice.getText();
    }

    @Override
    public String getFuelColor() {
        return this.txfFuelColor.getText();
    }

    
    
    //Event handlers
    
    //Modifying
    @FXML
    private void btnSelect_click(final MouseEvent e) {
        this.controller.select();
    }
    
    @FXML
    private void btnChangeName_click(final MouseEvent e) {
        this.controller.changeName();
    }
    
    @FXML
    private void btnChangePrice_click(final MouseEvent e) {
        this.controller.changePrice();
    }
    
    @FXML
    private void btnChangeWPrice_click(final MouseEvent e) {
        this.controller.changeWPrice();
    }
    
    @FXML
    private void btnChangeColor_click(final MouseEvent e) {
        this.controller.changeColor();
    }
    
    @FXML
    private void btnDelete_click(final MouseEvent e) {
        this.controller.deleteFuel();
    }
    
    //Adding
    @FXML
    private void btnAddFuel_click(final MouseEvent e) {
        this.controller.addFuel();
    }
    
}
