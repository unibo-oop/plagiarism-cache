package model.achievements;

import java.util.List;
import java.util.Map;

import model.buildingscounter.BuildingsCounter;
import model.construction.ConstructionType;
import model.demand.Demand;
import model.game.Game;
import model.observer.Observer;
import model.resources.Resource;
import model.resources.ResourceType;

/**
 * This class is used to create and handle an Achievement.
 */
public class AchievementImpl implements Achievement, Observer {

    private final String name;
    private final Demand request;
    private boolean status;
    private final AchievementType type;

    /**
     * The constructor of an Achievement.
     * @param name
     *          the name of the Achievement
     * @param request
     *          the items requested to unlock the Achievement
     * @param type
     *          the type of the Achievement
     */
    public AchievementImpl(final String name, final Demand request, final AchievementType type) {
        this.name = name;
        this.request = request;
        this.type = type;
        this.status = false;
    }

    /*
     * (non-Javadoc)
     * @see model.objectives.Achievement#getType()
     */
    @Override
    public AchievementType getType() {
        return this.type;
    }

    /*
     * (non-Javadoc)
     * @see model.objectives.Achievement#getName()
     */
    @Override
    public String getName() {
        return this.name;
    }

    /*
     * (non-Javadoc)
     * @see model.objectives.Achievement#isUnlock()
     */
    @Override
    public boolean isUnlock() {
        return this.status;
    }

    /*
     * (non-Javadoc)
     * @see model.observer.Observer#update(model.game.Game)
     */
    @Override
    public void update(final Game game) {
        switch (this.type) {
        case RESOURCES:
            if (checkResources(game.getResources(), game.getCityPopulation())) {
                unlock();
            }
            break;
        case BUILDINGS:
            if (checkBuildings(game.getBuildingsCounter())) {
                unlock();
            }
            break;
        case BUILDINGS_AND_RESOURCES:
            if (checkResources(game.getResources(), game.getCityPopulation()) 
                    && checkBuildings(game.getBuildingsCounter())) {
                unlock();
            }
            break;
        default:
            break;
        }
    }

    /**
     * This method unlock the Achievement.
     */
    private void unlock() {
        this.status = true;
    }

    /**
     * This method checks if the required building are present.
     * @param counter
     *          the Buildings counter
     * @return
     *          true:   if the demands are met
     *          false:  if not
     */
    private boolean checkBuildings(final BuildingsCounter counter) {
        for (final Map.Entry<ConstructionType, Integer> demand : this.request.getBuildings().get().entrySet()) {
            if (counter.getNumberOf(demand.getKey()) != demand.getValue()) {
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the required quantities of the resources have been reached.
     * @param resources
     *          List of city's resources (money, water, energy)
     * @param population
     *          city population
     * @return
     *          true:   if the demands are met
     *          false:  if not
     */
    private boolean checkResources(final List<Resource> resources, final Resource population) {
        if (this.request.getResources().get().containsKey(ResourceType.PEOPLE)
                && this.request.getResources().get().get(ResourceType.PEOPLE).compareTo(population.getValue()) > 0) {
            return false;
        } else if (this.request.getResources().get().containsKey(ResourceType.MONEY)
                && this.request.getResources().get().get(ResourceType.MONEY) > resources.get(0).getValue()) {
                    return false;
        } else if (this.request.getResources().get().containsKey(ResourceType.WATER)
                && this.request.getResources().get().get(ResourceType.WATER) > resources.get(1).getValue()) {
            return false;
        } else if (this.request.getResources().get().containsKey(ResourceType.ENERGY)
                && this.request.getResources().get().get(ResourceType.ENERGY) > resources.get(2).getValue()) {
            return false;
        }
        return true;
    }
}
