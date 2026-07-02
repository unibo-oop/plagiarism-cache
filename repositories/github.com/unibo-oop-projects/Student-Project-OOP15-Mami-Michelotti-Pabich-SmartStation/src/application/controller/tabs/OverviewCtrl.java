package application.controller.tabs;

import java.util.List;
import application.model.buildables.area.Area;
import application.view.tabs.overview.Overview;

/**
 * Interface that contains all methods needed to manage overview controller.
 * @author Matteo Michelotti
 */
public interface OverviewCtrl {

    /**
     * Set the view of the controller.
     * @param overview the view for the class
     */
    void setView(Overview overview);
    
    /**
     * Load configuration for overview tab.
     * @param areas list of area
     */
    void loadData(List<Area> areas);
}
