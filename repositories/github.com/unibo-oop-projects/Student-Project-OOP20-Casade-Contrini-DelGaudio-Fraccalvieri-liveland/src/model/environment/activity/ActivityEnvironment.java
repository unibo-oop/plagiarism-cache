package model.environment.activity;

import java.util.List;

import model.activity.Fair;
import model.activity.Profit;
import view.model.activity.ActivityAlreadyPresentException;
import view.model.activity.ViewActivityImpl;

public interface ActivityEnvironment {

    /**
     * It adds the new activity (set by the user) to the corresponding 
     * activity list, either FairList or ProfitList.
     * @param activity The ViewActivityImpl that needs to be added in the environment
     * @throws ActivityAlreadyPresentException if the activity trying to be 
     * added is already present in the environment
     */
    void activityInsertion(ViewActivityImpl activity) throws ActivityAlreadyPresentException;

    /**
     * Resets each activity list.
     */
    void resetActivity();

    /**
     * @return a list containing Fair objects set in the environment
     */
    List<Fair> getFairList();

    /**
     * @return a list containing Profit objects set in the environment
     */
    List<Profit> getProfitList();

    /**
     * @return a list containing each activity set, including both 
     * Fair and Profit objects
     */
    List<ViewActivityImpl> getActivityList();

}
