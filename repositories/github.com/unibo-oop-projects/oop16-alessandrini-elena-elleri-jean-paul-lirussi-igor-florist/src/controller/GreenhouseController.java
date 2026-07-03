package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import model.Plant;
import model.PlantImpl;
import model.Planting;
import model.PlantingImpl;
import model.PlantingManager;
import view.GreenhouseView;

/**
 * An interface that connect the {@link GreenhouseView} and the {@link Greenhouse}.
 *
 */
public interface GreenhouseController {

    /**
     * This getter return a List of {@link Plant}.
     * 
     * @return List of {@link Plant}
     */
    List<PlantImpl> getAllPlant();

    /**
     * This method sets selected dates.
     * 
     * @param date
     *            {@link Date}
     *
     */
    void selectDate(Date date);

    /**
     * This method controls click on a button that represents a {@link PlantImpl}.
     * It's used for plant a {@link PlantImpl}.
     * 
     * @param pos
     *          int that represent the position
     */
    void selectPlant(int pos);

    /**
     * This method add to the model a new planted {@link Plant}.
     * 
     * @param p
     *          {@link Planting} to add
     * 
     */
    void addAPlant(PlantingImpl p);

    /**
     * This method remove a {@link Planting} from model.
     * 
     * @param p
     *             {@link Planting} to remove 
     * 
     */
    void removePlant(PlantingImpl p);

    /**
     * This getter return a List of all planted {@link Plant} informations.
     * 
     * @return List<String>
     *          that represents a list of {@link Plant} informations
     */
    List<String> getAllPlantAsString();

    /**
     * This method return true if {@link Greenhouse} is Full.
     * 
     * @return true or false
     */
    boolean isGreenhouseFull();

    /**
     * This method returns the {@link GreenhouseView}.
     * 
     * @return {@link GreenhouseView}
     */
    GreenhouseView getGreenhouseView();

    /**
     * This method delete all {@link Planting}.
     * 
     */
    void clearAll();

    /**
     * This method return the {@link GreenhouseManager}.
     * 
     * @return {@link GreenhouseManager}
     */
    PlantingManager getPlantingManager();

    /**
     * This method open new frame with all the {@link Plant} that have to pick
     * in a passed Date.
     * 
     * @param date to check
     */
    void getPlantsToPickInDate(LocalDate date);
}
