package it.unibo.biscia.core;

import it.unibo.biscia.core.Level.LevelManaged;

import java.util.List;
import java.util.Optional;

/**
 * Is an entity of board, wall or biscia.
 *
 */
public interface Entity {

    /**
     * Get the ordered list of cells that compose the entity.
     * 
     * @return a list of cells
     */
    List<Cell> getCells();

    /**
     * define the type of entity.
     * 
     * @return the type of entity
     */
    EntityType getType();

    /**
     * Entity with addictional method from internal management.
     *
     */
    interface EntityManaged extends Entity {

        LevelManaged getLevel();

        boolean removeCell(int index);

        int removeFromCell(int index);

        /**
         * add to Entity methods for perform movements.
         *
         */
        interface Movable extends EntityManaged {
            /**
             * calling for move object.
             * 
             * @return true if movement is performed
             */
            boolean move();

            /**
             * get the direction for next movement.
             * 
             * @return the direction to move at next move
             */
            Optional<Direction> getDirection();

            /**
             * add to Movable methods for set direction of movement.
             *
             */
            interface Directable extends Movable {
                void setDirection(Optional<Direction> direction);
            }

        }

        /**
         * add to Entity the methods for eat food.
         *
         */
        interface Eater extends EntityManaged {
            int INCREMENT_FOR_ENERGY = 3;

            /**
             * calling for eat energy food.
             * 
             * @param energy energy supplies from food,
             */
            void eat(int energy);

            /**
             * add a cell and reduce energy.
             * 
             * @param cell cell to add
             */
            boolean grow();

        }

        /**
         * add to Entity the quantity of energy it provides.
         *
         */
        interface Eatable extends EntityManaged {
            int MAX_ENERGY = 9;

            /**
             * The energy gained eating the food.
             * 
             * @return an integer value
             */
            int getEnergy();
        }

    }

}
