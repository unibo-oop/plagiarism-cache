package model.square;

import java.util.Optional;
import model.entity.Entity;

public interface Square {
    
    /**
     * 
     * @return an optional with the entity(cell or stone) if it's present in the square, or null
     */
    Optional<Entity> getEntity();
    /**
     * create a new cell (dead or alive) in that square
     * @param entity
     */
    void setEntity(Optional<Entity> entity);
}
