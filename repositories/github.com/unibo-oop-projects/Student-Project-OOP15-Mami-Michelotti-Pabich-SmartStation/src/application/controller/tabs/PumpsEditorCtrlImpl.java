package application.controller.tabs;

import java.util.List;
import application.controller.MainController;
import application.model.buildables.pump.Pump;
import application.model.moneyManager.MovementType;
import application.model.services.Fuel;
import application.view.tabs.pumpsEditor.PumpsEditor;

/**
 * Class that implements the logic of the PumpsEditorController.
 */
public class PumpsEditorCtrlImpl implements PumpsEditorCtrl {

    private final MainController mainController;
    private PumpsEditor pumpsEditor;
    private Pump pumpSelected, pumpRepair;
    
    /**
     * Initialize the logic for PumpsEditorController.
     * @param mainCtrl reference for the main controller
     */
    public PumpsEditorCtrlImpl(final MainController mainCtrl) {
	this.mainController = mainCtrl;
    }

    @Override
    public void setView(final PumpsEditor pmpEd) {
	this.pumpsEditor = pmpEd;
    }

    @Override
    public void loadData(final List<Fuel> fuels, final List<Pump> pumps) {
	this.pumpsEditor.loadFuels(fuels);
	this.pumpsEditor.loadPumps(pumps);
    }

    @Override
    public void selectEdit() {
	if (this.pumpsEditor.getModifySelectedPump() != "") {
	    this.pumpSelected = this.mainController.getModel().getPumpManager().getPumpByName(this.pumpsEditor.getModifySelectedPump());
	    this.pumpsEditor.setModifyFuelType(this.pumpSelected.getName());
	    this.pumpsEditor.setModifySpeed(String.valueOf(this.pumpSelected.getSpeed()));
	    this.pumpsEditor.setModifyDurability(String.valueOf(this.pumpSelected.getDurability()));
	    this.pumpsEditor.setModifyPrice(String.valueOf(this.pumpSelected.getCost()));
	    this.pumpsEditor.setModifyRepairCost(String.valueOf(this.pumpSelected.getRepairCost()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Select the pump");
	}
    }

    @Override
    public void changePumpName() {
	final boolean isTake = this.isFree(this.pumpsEditor.getModifyFuelType());
	if (isTake) {
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpSelected.getName())
	                                                   .setName(this.pumpsEditor.getModifyFuelType());
	    this.pumpSelected.setName(this.pumpsEditor.getModifyFuelType());
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	}
    }

    @Override
    public void changeSpeed() {
	final boolean isNum = this.isNumber(this.pumpsEditor.getModifySpeed());
	if (isNum) {
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpSelected.getName())
	                                                   .setSpeed(Integer.parseInt(this.pumpsEditor.getModifySpeed()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeDurability() {
	final boolean isNum = this.isNumber(this.pumpsEditor.getModifyDurability());
	if (isNum) {
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpSelected.getName())
	                                                   .setMaxDurability(Integer.parseInt(this.pumpsEditor.getModifyDurability()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changePrice() {
	final boolean isNum = this.isNumber(this.pumpsEditor.getModifyPrice());
	if (isNum) {
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpSelected.getName())
	                                                   .setCost(Integer.parseInt(this.pumpsEditor.getModifyPrice()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeRepairCost() {
	final boolean isNum = this.isNumber(this.pumpsEditor.getModifyRepairCost());
	if (isNum) {
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpSelected.getName())
	                                                   .setRepairCost(Integer.parseInt(this.pumpsEditor.getModifyRepairCost()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void selectRepair() {
	if (this.pumpsEditor.getRepairSelectedPump() != "") {
	    this.pumpRepair = this.mainController.getModel().getPumpManager().getPumpByName(this.pumpsEditor.getRepairSelectedPump());
	    this.pumpsEditor.setRepairQuantities(String.valueOf(this.pumpRepair.getDurability()),
		                                 String.valueOf(this.pumpRepair.getMaxDurability()));
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Select the pump");
	}
    }

    @Override
    public void repair() {
	final int damage = this.pumpRepair.getMaxDurability() - this.pumpRepair.getDurability();
	
	if (damage != 0) {
	    final int repair = (int) ((damage * this.pumpsEditor.getRepairValue()) / 100);
	    this.mainController.getModel().getPumpManager().getPumpByName(this.pumpRepair.getName()).repair(repair);
	    
	    //adding the movement
	    this.mainController.getModel().getMoneyManager()
                                          .addMovement(MovementType.REPAIR, this.pumpRepair.getCost() * repair,
        	                                       "Repaying pump " + this.pumpRepair.getName());
	    
	    //load the balance for movements tab
	    this.mainController.getMovementsViewerController().loadBalance();
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of repair", "Pump is ok");
	}
    }

    @Override
    public void addPump() {
	final boolean isFre = this.isFree(this.pumpsEditor.getFuelType());
	final boolean speed = this.isNumber(this.pumpsEditor.getSpeed());
	final boolean durab = this.isNumber(this.pumpsEditor.getDurability());
	final boolean price = this.isNumber(this.pumpsEditor.getPrice());
	final boolean repCos = this.isNumber(this.pumpsEditor.getRepairCost());
	if (isFre && speed && durab && price && repCos) {
	    final Fuel fuel = this.mainController.getModel().getFuelManager().getFuel(this.pumpsEditor.getFuelType());
	    this.mainController.getModel().getPumpManager().addPump(Integer.parseInt(this.pumpsEditor.getDurability()),
		                                                    Integer.parseInt(this.pumpsEditor.getDurability()),
		                                                    Integer.parseInt(this.pumpsEditor.getPrice()),
		                                                    Integer.parseInt(this.pumpsEditor.getRepairCost()),
		                                                    this.pumpsEditor.getFuelType(), fuel,
		                                                    Integer.parseInt(this.pumpsEditor.getSpeed()));
	    
	    this.pumpsEditor.loadPumps(this.mainController.getModel().getPumpManager().getAllPumps());
	    
	    //adding the movement
	    this.mainController.getModel().getMoneyManager()
	                                  .addMovement(MovementType.BUILD, Integer.parseInt(this.pumpsEditor.getPrice()),
	                                	       "Adding pump " + this.pumpsEditor.getFuelType());
	    
	    //load the balance for movements tab
	    this.mainController.getMovementsViewerController().loadBalance();
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else if (!isFre) {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	} else if (!speed) {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!durab) {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!price) {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!repCos) {
	    this.pumpsEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void deletePump() {
	if (this.pumpSelected != null) {
	    this.mainController.getModel().getPumpManager().removePump(this.pumpSelected);
	    
	    this.pumpSelected = null;
	    this.pumpsEditor.setModifyFuelType("");
	    this.pumpsEditor.setModifySpeed("");
	    this.pumpsEditor.setModifyDurability("");
	    this.pumpsEditor.setModifyPrice("");
	    this.pumpsEditor.setModifyRepairCost("");
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.pumpsEditor.showInformationAlert("Error", "error of delete", "Select the pump");
	}
    }

    //control of name is already taken
    private boolean isFree(final String name) {
	for (Pump p : this.mainController.getModel().getPumpManager().getAllPumps()) {
	    if (p.getName().equals(name)) {
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
}
