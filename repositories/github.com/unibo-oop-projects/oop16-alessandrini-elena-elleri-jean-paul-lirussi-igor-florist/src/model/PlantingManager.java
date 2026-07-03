package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * the interface of the class which manages the plantings.
 */
public interface PlantingManager extends Serializable {

    /**
     * gets the whole condition of the program.
     * @return the map where every Plant is connected to a "list" of his plantings never repeated
     */
    Map<PlantImpl, Set<PlantingImpl>> getAll();

    /**
     * add a planting.
     * @param pos the position of the plant
     * @param planting the new planting
     * @return if it's OK the operation
     */
    boolean addPlanting(PlantImpl pos, PlantingImpl planting);

    /**
     * remove a planting.
     * @param pos the position of the plant
     * @param planting the planting to delete
     * @return if it's OK the operation
     */
    boolean removePlanting(PlantImpl pos, PlantingImpl planting);

    /**
     * remove a plant.
     * @param pos the plant
     * @return if it's OK the operation
     */
    boolean removePlant(PlantImpl pos);

    /**
     * checks if a planting can be made in a period.
     * @param plant the plant chosen
     * @param from the date the planting starts
     * @param to the date the planting ends
     * @return true if a planting can be done in that period
     */
   boolean isPlantingAvaiable(PlantImpl plant, LocalDate from, LocalDate to);

   /**
    * checks if a planting can be made in a period.
    * @param plant the plant chosen
    * @param from the date the planting starts
    * @param period the period the planting exists 
    * @return true if a planting can be done in that period
    */
  boolean isPlantingAvaiablePeriod(PlantImpl plant, LocalDate from, Period period);

  /**
   * the map of the plants to pick today, the final date is today.
   * @return plants to pickup today
   */
  Map<PlantImpl, Set<PlantingImpl>> getSummaryToPickToday();

  /**
   * the map of the plants to pick today, the final date is today.
   * @param date the date the plants need to be picked
   * @return plants to pickup today
   */
  List<PlantingImpl> getSummaryToPickDate(LocalDate date);

  /**
   * the list of plants in the map.
   * @return all plants in the map
   */
  List<PlantImpl> getPlantsInMap();

  /**
   * gets the number of keys (plants) in the map.
   * @return the number of keys
   */
  int getNumFlowersinthemap();


  /**
   * 
   * @return if the map is empty.
   */
  boolean isMapEmpty();

}
