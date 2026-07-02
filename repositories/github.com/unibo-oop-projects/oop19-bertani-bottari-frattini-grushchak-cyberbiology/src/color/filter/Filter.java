package color.filter;

import java.awt.Color;
import java.util.Optional;

import model.entity.Entity;

/**
 * Graphic interface that allows to calculate the colors of the entities in the world 
 * according to the type of entities and their characteristics.
 * 
 */
public interface Filter {

    /**
     * Calculates the color of any entity based on its type and characteristics.
     * stone-> dark grey
     * dead cell -> light grey
     * cell standard-> returns color according to the chosen filter and its characteristics
     * @param square the entity of which you want to know the color
     * @return the color of entity
     * @throws IllegalArgumentException if the cell type is not one of the existing types 
     */
    Color getColor(Optional<Entity> square);

}
