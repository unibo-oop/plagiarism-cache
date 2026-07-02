package application.controller.tabs;

import java.util.List;
import application.controller.MainController;
import application.model.buildables.area.Area;
import application.model.buildables.reserve.Reserve;
import application.view.tabs.overview.Overview;

/**
 * Class that implements the logic of the OverviewController.
 */
public class OverviewCtrlImpl implements OverviewCtrl {

    private final MainController mainController;
    private Overview overview;
    
    /**
     * Initialize the logic for OverviewController.
     * @param mainCtrl reference for the main controller
     */
    public OverviewCtrlImpl(final MainController mainCtrl) {
	this.mainController = mainCtrl;
    }

    @Override
    public void setView(final Overview ovrv) {
	this.overview = ovrv;
    }

    @Override
    public void loadData(final List<Area> areas) {
	this.overview.refreshGrid(areas);
	
	for (Reserve r : this.mainController.getModel().getReserveManager().getAllReserves()) {
	    double progress = (double) r.getRemaining() / r.getCapacity();
	    
	    this.overview.addReserveStatus(r.getType().getName(), String.valueOf(r.getCost()),
		                           String.valueOf(r.getRemaining()), String.valueOf(r.getCapacity()), progress);
	}
    }
}
