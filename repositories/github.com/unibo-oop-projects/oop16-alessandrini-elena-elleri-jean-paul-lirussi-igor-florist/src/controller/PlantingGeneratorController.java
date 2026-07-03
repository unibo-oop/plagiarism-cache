package controller;

import java.time.LocalDate;
import model.Plant;
import model.PlantImpl;

/**
 * This interface represents the {@link Planting} generator controller used for 
 * create a new {@link Planting} and add it to {@link PlantingManager}.
 * 
 */
public interface PlantingGeneratorController {

    /**
     * This method update the last selected {@link Plant}.
     * 
     * @param pos
     *       integer number represents the {@link Plant} selected.
     */
    void updatePlant(int pos);

    /**
     * This method update {@link Planting}.
     * 
     */
    void updatePlanting();

    /**
     * This method update the last selected {@link Localdate}.
     * 
     * @param from
     *      {@link Localdate} from
     */
    void updateDate(LocalDate from);

    /**
     * This method update the last selected {@link Employee}.
     * 
     * @param pos
     *      of the {@link Employee}
     */
    void updateEmployee(int pos);

    /**
     * This method confirm all, create a new {@link Planting} and exit.
     * 
     */
    void confirm();
}
