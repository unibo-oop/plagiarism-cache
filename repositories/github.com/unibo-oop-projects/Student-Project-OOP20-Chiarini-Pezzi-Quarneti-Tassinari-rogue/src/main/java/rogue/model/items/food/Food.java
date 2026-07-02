package rogue.model.items.food;

import rogue.model.items.Item;

/**
 * An interface modeling a game's food.
 *
 */
public interface Food extends Item {

    /**
     * Get the food's starvation value.
     * @return the food's starvation value.
     */
    int getStarvationValue();

    /**
     * Get the food' FoodType.
     * @return food's FoodType.
     */
    FoodType getFood();
}
