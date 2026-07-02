package application.controller.tabs;

import java.util.List;
import application.controller.MainController;
import application.model.buildables.reserve.Reserve;
import application.model.moneyManager.MovementType;
import application.model.services.Fuel;
import application.view.tabs.reservesEditor.ReservesEditor;

/**
 * Class that implements the logic of the ReservesEditorController.
 */
public class ReservesEditorCtrlImpl implements ReservesEditorCtrl {

    private final MainController mainController;
    private ReservesEditor reservesEditor;
    private Reserve reserve, reserveRefill, reserveRepair;
    
    /**
     * Initialize the logic for ReservesEditorController.
     * @param mainCtrl reference for the main controller
     */
    public ReservesEditorCtrlImpl(final MainController mainCtrl) {
	this.mainController = mainCtrl;
    }

    @Override
    public void setView(final ReservesEditor rsvEdr) {
	this.reservesEditor = rsvEdr;
    }

    @Override
    public void loadData(final List<Fuel> fuels, final List<Reserve> reserves) {
	this.reservesEditor.loadFuels(fuels);
	this.reservesEditor.loadReserves(reserves);
    }

    @Override
    public void selectEdit() {
	for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	    if (r.getType().getName().equals(this.reservesEditor.getModifyReserve())) {
		this.reserve = r;
		this.reservesEditor.setModifyFuel(r.getType().getName());
		this.reservesEditor.setModifyCapacity(String.valueOf(r.getCapacity()));
		this.reservesEditor.setModifyPrice(String.valueOf(r.getCost()));
		this.reservesEditor.setModifyDurability(String.valueOf(r.getDurability()));
		this.reservesEditor.setModifyRepairCost(String.valueOf(r.getRepairCost()));
	    }
	}
    }

    @Override
    public void changeFuelType() {
	final boolean isFuel = this.isFree(this.reservesEditor.getModifyFuel());
	if (isFuel) {
	    this.mainController.getModel().getReserveManager().getReserve(this.reserve.getType())
	                                  .setType(this.mainController.getModel().getFuelManager()
	                                	                      .getFuel(this.reservesEditor.getModifyFuel()));
	    this.reserve = this.mainController.getModel().getReserveManager()
		                              .getReserve(this.mainController.getModel().getFuelManager()
		                        	              .getFuel(this.reservesEditor.getModifyFuel()));
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	}
    }

    @Override
    public void changeCapacity() {
	final boolean isNum = this.isNumber(this.reservesEditor.getModifyCapacity());
	if (isNum) {
	    this.mainController.getModel().getReserveManager().getReserve(this.reserve.getType())
	                                  .setCapacity(Integer.parseInt(this.reservesEditor.getModifyCapacity()));
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeRepairCost() {
	final boolean isNum = this.isNumber(this.reservesEditor.getModifyRepairCost());
	if (isNum) {
	    this.mainController.getModel().getReserveManager().getReserve(this.reserve.getType())
	                                  .setRepairCost(Integer.parseInt(this.reservesEditor.getModifyRepairCost()));
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changePrice() {
	final boolean isNum = this.isNumber(this.reservesEditor.getModifyPrice());
	if (isNum) {
	    this.mainController.getModel().getReserveManager().getReserve(this.reserve.getType())
	                                  .setCost(Integer.parseInt(this.reservesEditor.getModifyPrice()));
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void changeDurability() {
	final boolean isNum = this.isNumber(this.reservesEditor.getModifyDurability());
	if (isNum) {
	    this.mainController.getModel().getReserveManager().getReserve(this.reserve.getType())
	                                  .setMaxDurability(Integer.parseInt(this.reservesEditor.getModifyDurability()));
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void selectRefill() {
	for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	    if (r.getType().getName().equals(this.reservesEditor.getRefillReserve())) {
		this.reserveRefill = r;
		this.reservesEditor.setRefillQuantities(String.valueOf(this.reserveRefill.getRemaining()),
			                                String.valueOf(this.reserveRefill.getCapacity()));
	    }
	}
    }

    @Override
    public void refill() {
	final boolean isNum = this.isNumber(this.reservesEditor.getRefill());
	if (isNum) {
	    if (this.reserveRefill.getRemaining() < this.reserveRefill.getCapacity()) {
		this.mainController.getModel().getReserveManager().getReserve(this.reserveRefill.getType())
	                                                      	  .refill(Integer.parseInt(this.reservesEditor.getRefill()));
		//Adding the movement
		this.mainController.getModel().getMoneyManager()
                                              .addMovement(MovementType.REFILL,
                                        	           this.reserveRefill.getCost() * Integer.parseInt(this.reservesEditor.getRefill()),
                                        	           "Refilling the riserve " + this.reserveRefill.getType().getName());
		
		//load the balance for movements tab
		this.mainController.getMovementsViewerController().loadBalance();
	    } else {
		this.reservesEditor.showInformationAlert("Error", "error of load", "Capacity is full");
	    }
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void selectRepair() {
	for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	    if (r.getType().getName().equals(this.reservesEditor.getRepairReserve())) {
		this.reserveRepair = r;
		this.reservesEditor.setRepairQuantities(String.valueOf(r.getDurability()),
			                                String.valueOf(r.getMaxDurability()));
	    }
	}
    }

    @Override
    public void repair() {
	if (this.reserveRepair != null) {
	    final int damage = this.reserveRepair.getMaxDurability() - this.reserveRepair.getDurability();
	    
	    if (damage != 0) {
		final int repair = (int) ((damage * this.reservesEditor.getRepairValue()) / 100);
		this.mainController.getModel().getReserveManager().getReserve(this.reserveRepair.getType()).repair(repair);
		
		//Adding the movement
		this.mainController.getModel().getMoneyManager()
	                              	      .addMovement(MovementType.REPAIR, this.reserveRepair.getCost() * repair,
	                        	                   "Repaying reserve " + this.reserveRepair.getType().getName());
		
		//load the balance for movements tab
		this.mainController.getMovementsViewerController().loadBalance();
	    } else {
		this.reservesEditor.showInformationAlert("Error", "error of repair", "Reserve is ok");
	    }
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of repair", "Select the reserve for repair");
	}
    }

    @Override
    public void addReserve() {
	final boolean isFuel = this.isFree(this.reservesEditor.getFuel());
	final boolean isCap = this.isNumber(this.reservesEditor.getCapacity());
	final boolean isPri = this.isNumber(this.reservesEditor.getPrice());
	final boolean isDur = this.isNumber(this.reservesEditor.getDurability());
	final boolean isRCos = this.isNumber(this.reservesEditor.getRepairCost());
	if (isFuel && isCap && isPri && isDur && isRCos) {
	    final Fuel fuel = this.mainController.getModel().getFuelManager().getFuel(this.reservesEditor.getFuel());
	    this.mainController.getModel().getReserveManager().addReserve(Integer.parseInt(this.reservesEditor.getDurability()),
		                                                          Integer.parseInt(this.reservesEditor.getDurability()),
		                                                          Integer.parseInt(this.reservesEditor.getPrice()),
		                                                          Integer.parseInt(this.reservesEditor.getRepairCost()), fuel,
		                                                          Integer.parseInt(this.reservesEditor.getCapacity()),
		                                                          Integer.parseInt(this.reservesEditor.getCapacity()));
	    
	    this.reservesEditor.loadReserves(this.mainController.getModel().getReserveManager().getAllReserves());
	    
	    //Adding the movement
	    this.mainController.getModel().getMoneyManager()
	                                  .addMovement(MovementType.BUILD,
		                                       Integer.parseInt(this.reservesEditor.getPrice()),
		                                       "Adding reserve " + this.reservesEditor.getFuel());
	    
	    //load the balance for movements tab
	    this.mainController.getMovementsViewerController().loadBalance();
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else if (!isFuel) {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Name is already taken");
	} else if (!isCap) {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!isPri) {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!isDur) {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	} else if (!isRCos) {
	    this.reservesEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void deleteReserve() {
	if (this.reserve != null) {
	    this.mainController.getModel().getReserveManager().removeReserve(this.reserve);
	    
	    this.reserve = null;
	    this.reservesEditor.setModifyFuel("");
	    this.reservesEditor.setModifyCapacity("");
	    this.reservesEditor.setModifyPrice("");
	    this.reservesEditor.setModifyDurability("");
	    this.reservesEditor.setModifyRepairCost("");
	    
	    //reconfiguration of tabs
	    this.mainController.reconfiguration();
	} else {
	    this.reservesEditor.showInformationAlert("Error", "error of delete", "Select the reserve");
	}
    }

    //control of name is already taken
    private boolean isFree(final String name) {
	for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	    if (r.getType().getName().equals(name)) {
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
