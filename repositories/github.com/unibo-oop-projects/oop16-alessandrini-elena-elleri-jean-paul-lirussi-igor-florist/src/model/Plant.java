package model;

import java.awt.Image;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

/**
 * a class that contains every kind of flower.
 *
 */
public interface Plant extends Serializable {

    /**
     * @return the type of the flower. 
     */
    String getName(); 

    /**
     * @return the color of the flower.
     */
    Color getColor();

    /**
     * @return the description of the flower.
     */
    String getDescription();

    /**
     * @return the irrigation of the flower 0-1.
     */
    boolean isWatered();

    /**
     * @param watered 
     * the irrigation.
     */
    void setWatered(boolean watered);

    /**
     * @return the condition of the flower.
     */
    Condition getCondition();

    /**
     * @param condizione
     * in which state is the plant
     */
    void setCondition(Condition condizione);

    /**
     * @return the date of the sowing.
     */
    LocalDate getDate();

    /**
     * @param data the date of the sowing.
     */
    void setDate(LocalDate data);

    /**
     * @return the "age" of the flower.
     */
    Period getAge();

    /**
     * @return the time to blossom
     */
    Period getBloomigTime();

    /**
     * @return the image of the flower.
     */
    Image getImage();

    /**
     * method for the controller.
     * @return the plant. 
     */
    PlantImpl getPlant(); 
}
