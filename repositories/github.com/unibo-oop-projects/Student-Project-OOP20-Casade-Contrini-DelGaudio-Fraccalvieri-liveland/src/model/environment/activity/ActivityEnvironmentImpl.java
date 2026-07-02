package model.environment.activity;

import java.util.ArrayList;
import java.util.List;

import model.activity.Fair;
import model.activity.Profit;
import view.model.activity.ActivityAlreadyPresentException;
import view.model.activity.ActivityType;
import view.model.activity.ViewActivityImpl;

public final class ActivityEnvironmentImpl implements ActivityEnvironment {

    private final List<Fair> fairList;
    private final List<Profit> profitList;
    private final List<ViewActivityImpl> activityList;
    private ActivityType actType;

    public ActivityEnvironmentImpl() {
        this.fairList = new ArrayList<>();
        this.profitList = new ArrayList<>();
        this.activityList = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void activityInsertion(final ViewActivityImpl activity) throws ActivityAlreadyPresentException {
        if (this.activityList.stream().filter(a -> a.getName().equals(activity.getName()))
                                .filter(a -> a.getCapacity().equals(activity.getCapacity()))
                                .filter(a -> a.getMaxPrice().equals(activity.getMaxPrice()))
                                .filter(a -> a.getMinPrice().equals(activity.getMinPrice()))
                                .filter(a -> a.getActivityType().equals(activity.getActivityType()))
                                .count() == 1) {
            throw new ActivityAlreadyPresentException();
        } else {
            this.activityList.add(activity);
            this.actType = activity.getActivityType();

            switch (actType) {
                case BABYFAIR:
                    fairList.add(new Fair(activity.getName(), activity.getCapacity().orElse(0), actType));
                    break;
                case FAIR:
                    fairList.add(new Fair(activity.getName(), activity.getCapacity().orElse(0), actType));
                    break;
                case REST:
                    profitList.add(new Profit(activity.getName(), activity.getMinPrice().orElse(0), 
                            activity.getMaxPrice().orElse(0), actType));
                    break;
                case SHOP:
                    profitList.add(new Profit(activity.getName(), activity.getMinPrice().orElse(0),
                            activity.getMaxPrice().orElse(0), actType));
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetActivity() {
        this.fairList.clear();
        this.profitList.clear();
        this.activityList.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Fair> getFairList() {
        return this.fairList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Profit> getProfitList() {
        return this.profitList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ViewActivityImpl> getActivityList() {
        return this.activityList;
    }

}
