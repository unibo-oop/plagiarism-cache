package model.game;
import java.util.List;

import javafx.util.Pair;
import model.achievements.Achievement;
import model.buildingscounter.BuildingsCounter;
import model.construction.Construction;
import model.gamemap.GameMap;
import model.resources.Resource;

/**
 * This interface models the main actor of the game.
 */
public interface Game {

    /**
     * This method returns the current GameMap.
     * @return GameMap
     *          The game map.
     */
    GameMap getMap();

    /**
     * This method returns the total amount of all type of resources.
     * @return Resource
     *          the resources.
     */
    List<Resource> getResources();

    /**
     * This method creates a new Building on the Map.
     * @param build
     *          The Construction to be placed on the Map.
     * @param position
     *          Where build is going to be constructed.
     * @return
     *          true:   if building can be built
     *          false:  if building can't be built
     */
    boolean createBuilding(Construction build, Pair<Integer, Integer> position);

    /**
     * This method demolish an existing Building.
     * @param build
     *          The building to be demolished.
     */
    void demolishBuilding(Construction build);

    /**
     * This method returns the daily incomes of all type of resources.
     * @return List<Integer>
     *          the daily incomes.
     */
    List<Integer> getDailyIncomes();

    /**
     * This method returns the daily costs of all type of resources.
     * @return List<Integer>
     *          the daily costs.
     */
    List<Integer> getDailyCosts();

    /**
     * This method returns the current population of the city.
     * @return Resource
     *          the current population.
     */
    Resource getCityPopulation();

    /**
     * This method returns how many days have passed in the current Game.
     * @return Integer
     *          how many days have passed in the Game.
     */
    Integer getGameDays();
    /**
     * 
     * @return
     *          the Buildings Counter.
     */
    BuildingsCounter getBuildingsCounter();
    /**
     * 
     * @return
     *          the list of all achievements 
     */
    List<Achievement> getAchievements();
}
