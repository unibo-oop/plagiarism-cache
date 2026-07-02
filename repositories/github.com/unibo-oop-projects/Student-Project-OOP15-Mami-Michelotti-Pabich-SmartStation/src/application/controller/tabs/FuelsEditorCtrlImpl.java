package application.controller.tabs;

import java.util.List;
import application.controller.MainController;
import application.model.buildables.pump.Pump;
import application.model.buildables.reserve.Reserve;
import application.model.moneyManager.MovementType;
import application.model.services.Fuel;
import application.view.tabs.fuelsEditor.FuelsEditor;
import javafx.scene.paint.Color;

/**
 * Class that implements the logic of FuelsEditorController.
 */
public class FuelsEditorCtrlImpl implements FuelsEditorCtrl {

    private final MainController mainController;
    private FuelsEditor fuelsEditor;
    private Fuel fuel;
    
    /**
     * Initialize the reference of controller.
     * @param mainCtrl reference of the main controller
     */
    public FuelsEditorCtrlImpl(final MainController mainCtrl) {
	this.mainController = mainCtrl;
    }

    @Override
    public void setView(final FuelsEditor flsEd) {
	this.fuelsEditor = flsEd;
    }

    @Override
    public void loadData(final List<Fuel> fuels) {
	this.fuelsEditor.loadFuels(fuels);
    }

    @Override
    public void select() {
	if (this.fuelsEditor.getSelectedFuel() != "") {
	    this.fuel = this.mainController.getModel().getFuelManager().getFuel(this.fuelsEditor.getSelectedFuel());
	    this.fuelsEditor.setModifyName(this.fuel.getName());
	    this.fuelsEditor.setModifyPrice(String.valueOf(this.fuel.getPrice()));
	    this.fuelsEditor.setModifyWhoesalePrice(String.valueOf(this.fuel.getWholeSalePrice()));
	    this.fuelsEditor.setModifyColor(String.valueOf(this.fuel.getColor()));
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Select the fuel");
	}
    }

    @Override
    public void changeName() {
	final boolean isFree = this.isFree(this.fuelsEditor.getModifyName());
	if (isFree) {
	    this.mainController.getModel().getFuelManager().getFuel(this.fuel.getName())
	                                                    .setName(this.fuelsEditor.getModifyName());
	    this.fuel.setName(this.fuelsEditor.getModifyName());
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	}
    }

    @Override
    public void changePrice() {
	final boolean isNum = this.isNumber(this.fuelsEditor.getModifyPrice());
	if (isNum) {
	    this.mainController.getModel().getFuelManager().getFuel(this.fuel.getName())
	                                  .setPrice(Integer.parseInt(this.fuelsEditor.getModifyPrice()));
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeWPrice() {
	final boolean isNum = this.isNumber(this.fuelsEditor.getModifyWhoesalePrice());
	if (isNum) {
	    this.mainController.getModel().getFuelManager().getFuel(this.fuel.getName())
	                                  .setWholeSalePrice(Integer.parseInt(this.fuelsEditor.getModifyWhoesalePrice()));
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeColor() {
	final boolean isColor = this.isColor(this.fuelsEditor.getModifyColor());
	if (isColor) {
	    this.mainController.getModel().getFuelManager().getFuel(this.fuel.getName())
	                                  .setColor(Color.valueOf(this.fuelsEditor.getModifyColor()));
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a color");
	}
    }

    @Override
    public void addFuel() {
	final boolean isFre = this.isFree(this.fuelsEditor.getFuelName());
	final boolean isPri = this.isNumber(this.fuelsEditor.getFuelPrice());
	final boolean isWPr = this.isNumber(this.fuelsEditor.getFuelWhoesalePrice());
	final boolean isCol = this.isColor(this.fuelsEditor.getFuelColor());
	
	if (isFre && isPri && isWPr && isCol) {
	    this.mainController.getModel().getFuelManager().addFuel(this.fuelsEditor.getFuelName(),
		                                                    Integer.parseInt(this.fuelsEditor.getFuelPrice()),
		                                                    Integer.parseInt(this.fuelsEditor.getFuelWhoesalePrice()),
		                                                    Color.valueOf(this.fuelsEditor.getFuelColor()));
	    this.fuelsEditor.loadFuels(this.mainController.getModel().getFuelManager().getAllFuels());
	    
	    //adding the movement
	    this.mainController.getModel().getMoneyManager()
	                                  .addMovement(MovementType.BUILD,
		                                       Integer.parseInt(this.fuelsEditor.getFuelPrice()),
		                                       "Adding fuel " + this.fuelsEditor.getFuelName());
	    
	    //load the balance for movements tab
	    this.mainController.getMovementsViewerController().loadBalance();
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	    
	} else if (!isFre) {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	} else if (!isPri) {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!isWPr) {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!isCol) {
	    this.fuelsEditor.showInformationAlert("Error", "error of load", "Insert a color");
	}
    }

    @Override
    public void deleteFuel() {
	if (this.fuel != null) {
	    for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	        if (r.getType().getName().equals(this.fuel.getName())) {
		    this.mainController.getModel().getReserveManager().removeReserve(r);
	        }
	    }
	    
	    for (Pump p : this.mainController.getModel().getPumpManager().getAllPumps()) {
	        if (p.getType().getName().equals(this.fuel.getName())) {
		    this.mainController.getModel().getPumpManager().removePump(p);
	        }
	    }
	    
	    this.mainController.getModel().getFuelManager().removeFuel(this.fuel.getName());
	    
	    this.fuel = null;
	    this.fuelsEditor.setModifyName("");
	    this.fuelsEditor.setModifyPrice("");
	    this.fuelsEditor.setModifyWhoesalePrice("");
	    this.fuelsEditor.setModifyColor("");
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.fuelsEditor.showInformationAlert("Error", "error of delete", "Select the fuel");
	}
    }

    //control of name is already taken
    private boolean isFree(final String name) {
	for (Fuel f : this.mainController.getModel().getFuelManager().getAllFuels()) {
	    if (f.getName().equals(name)) {
		return false;
	    }
	}
	return true;
    }

    //control if numbers is right
    private boolean isNumber(final String str) {
	try {
	    Integer.parseInt(str);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }

    //control if color is right
    private boolean isColor(final String str) {
	try {
	    Color.valueOf(str);
	    return true;
	} catch (Exception e) {
	    return false;
	}
    }
}
