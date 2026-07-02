package application.controller.tabs;

import java.util.ArrayList;
import java.util.List;
import application.controller.MainController;
import application.model.buildables.area.Area;
import application.model.buildables.pump.Pump;
import application.view.tabs.stationEditor.StationEditor;

/**
 * Class that implements the logic of the StationEditorController.
 */
public class StationEditorCtrlImpl implements StationEditorCtrl {

    private final MainController mainController;
    private StationEditor stationEditor;
    private int x, y;
    
    /**
     * Initialize the logic for StationEditorController.
     * @param mainCtrl reference for the main controller
     */
    public StationEditorCtrlImpl(final MainController mainCtrl) {
        this.mainController = mainCtrl;
    }

    @Override
    public void setView(final StationEditor stationEdtr) {
        this.stationEditor = stationEdtr;
    }

    @Override
    public void loadData(final int xCoord, final int yCoord, final List<Pump> pumps, final List<Area> areas) {
	this.stationEditor.loadCoordinates(xCoord, yCoord);
	this.stationEditor.loadPumps(pumps);
	this.stationEditor.refreshGrid(areas);
    }

    @Override
    public void selectionConfirm() {
	final boolean numX = this.isNumber(this.stationEditor.getModifyX());
	final boolean numY = this.isNumber(this.stationEditor.getModifyY());
	
	if (numX && numY) {
	    this.stationEditor.hideModifyErrorMessage();
	    this.x = Integer.parseInt(this.stationEditor.getModifyX());
	    this.y = Integer.parseInt(this.stationEditor.getModifyY());
	    final boolean free = this.areaFree(this.x, this.y);
	    
	    if (!free) {
		this.stationEditor.hideModifyErrorMessage();
		this.stationEditor.showModifyngPanel();
		this.stationEditor.showDetailsPanel();
		
		final List<String> list = new ArrayList<>();
		for (Pump p : this.mainController.getModel().getAreaManager().getArea(this.x, this.y).getAllPumps()) {
		    list.add(p.getName());
		}
		this.stationEditor.setModifyXChange(String.valueOf(this.mainController.getModel()
			                                  .getAreaManager().getArea(this.x, this.y).getXPosition()));
		this.stationEditor.setModifyYChange(String.valueOf(this.mainController.getModel()
			                                  .getAreaManager().getArea(this.x, this.y).getYPosition()));
		this.stationEditor.setModifyPumps(list);
	    } else {
		this.stationEditor.showModifyErrorMessage("Area not found");
		this.stationEditor.hideDetailsPanel();
	    }
	} else {
	    this.stationEditor.showModifyErrorMessage("Select area");
	}
    }

    @Override
    public void changePosition() {
	final boolean numX = this.isNumber(this.stationEditor.getModifyChangeX());
	final boolean numY = this.isNumber(this.stationEditor.getModifyChangeY());
	
	if (numX && numY) {
	    final int tempX = Integer.parseInt(this.stationEditor.getModifyChangeX());
	    final int tempY = Integer.parseInt(this.stationEditor.getModifyChangeY());
	    final boolean free = this.areaFree(tempX, tempY);
	    
	    if (free) {
		this.stationEditor.hideModifyCoordsMessage();
		this.mainController.getModel().getAreaManager().getArea(this.x, this.y).setPosition(tempX, tempY);
		this.x = tempX;
		this.y = tempY;
		
		//refresh areas
		this.stationEditor.refreshGrid(this.mainController.getModel().getAreaManager().getAllAreas());
		this.mainController.getOverviewController().loadData(this.mainController.getModel().getAreaManager().getAllAreas());
	    } else {
		this.stationEditor.showModifyCoordsMessage("Area arleady occupied");
	    }
	} else {
	    this.stationEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void confirmPumps() {
	final List<String> list = new ArrayList<>();
	list.addAll(this.stationEditor.getModifyPumps());
	
	this.mainController.getModel().getAreaManager().getArea(this.x, this.y).removeAllPumps();
	for (Pump p : this.mainController.getModel().getPumpManager().getAllPumps()) {
	    for (int i = 0; i < list.size(); i++) {
		if (p.getName().equals(list.get(i))) {
		    this.mainController.getModel().getAreaManager().getArea(this.x, this.y).addPump(p);
		}
	    }
	}
	
	//refresh areas
	this.stationEditor.refreshGrid(this.mainController.getModel().getAreaManager().getAllAreas());
	this.mainController.getOverviewController().loadData(this.mainController.getModel().getAreaManager().getAllAreas());
    }

    @Override
    public void insertArea() {
	final boolean numX = this.isNumber(this.stationEditor.getXCoords());
	final boolean numY = this.isNumber(this.stationEditor.getYCoords());
	
	if (numX && numY) {
	    final int tempX = Integer.parseInt(this.stationEditor.getXCoords());
	    final int tempY = Integer.parseInt(this.stationEditor.getYCoords());
	    final boolean free = this.areaFree(tempX, tempY);
	    
	    if (free) {
		final List<Pump> list = new ArrayList<>();
		for (Pump p : this.mainController.getModel().getPumpManager().getAllPumps()) {
		    for (int i = 0; i < this.stationEditor.getPumps().size() - 1; i++) {
			if (p.getName().equals(this.stationEditor.getPumps().get(i))) {
			    list.add(p);
			}
		    }
	        }
		this.mainController.getModel().getAreaManager().addArea(tempX, tempY, list);
		
		//refresh areas
		this.stationEditor.refreshGrid(this.mainController.getModel().getAreaManager().getAllAreas());
		this.mainController.getOverviewController().loadData(this.mainController.getModel().getAreaManager().getAllAreas());
	    } else {
		this.stationEditor.showModifyCoordsMessage("Area arleady occupied");
	    }
	} else {
	    this.stationEditor.showInformationAlert("Error", "error of load", "Insert a number");
	}
    }

    @Override
    public void removeArea() {
	final boolean isX = this.isNumber(String.valueOf(this.x));
	final boolean isY = this.isNumber(String.valueOf(this.y));
	if (isX && isY) {
	    this.mainController.getModel().getAreaManager().removeArea(this.x, this.y);
	    
	    //refresh areas
	    this.stationEditor.refreshGrid(this.mainController.getModel().getAreaManager().getAllAreas());
	    this.mainController.getOverviewController().loadData(this.mainController.getModel().getAreaManager().getAllAreas());
	} else {
	    this.stationEditor.showInformationAlert("Error", "error of load", "Select area");
	}
    }

    @Override
    public void switchPanel() {
        if (this.stationEditor.isAddingPanelVisible()) {
            this.stationEditor.showModifyngPanel();
            this.stationEditor.changeButtonText("Switch to add panel");
        } else {
            this.stationEditor.showAddingPanel();
            this.stationEditor.changeButtonText("Switch to modify panel");
        }
    }

    //search if area(x, y) is free
    private boolean areaFree(final int xCoord, final int yCoord) {
	for (Area a : this.mainController.getModel().getAreaManager().getAllAreas()) {
	    if (a.getXPosition() == xCoord && a.getYPosition() == yCoord) {
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
