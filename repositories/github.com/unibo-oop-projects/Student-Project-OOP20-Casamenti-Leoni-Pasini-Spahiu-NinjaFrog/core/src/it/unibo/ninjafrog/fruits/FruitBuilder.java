package it.unibo.ninjafrog.fruits;

import it.unibo.ninjafrog.screens.PlayScreen;

/**
 * Definition of the FruitBulder interface. Creates a
 * {@link it.unibo.ninjafrog.fruits.FruitPowerUp FruitPowerUp}.
 */
public interface FruitBuilder {
    /**
     * Select the screen to use the object.
     * 
     * @param screen PlayScreen.
     * @return The builder object itself.
     */
    FruitBuilder selectScreen(PlayScreen screen);

    /**
     * Choose the spawn x position.
     * 
     * @param x float value.
     * @return The builder object itself.
     */
    FruitBuilder chooseXPosition(float x);

    /**
     * Choose the spawn y position.
     * 
     * @param y float value.
     * @return The builder object itself.
     */
    FruitBuilder chooseYPosition(float y);

    /**
     * Select the type of the fruit.
     * 
     * @param type FruitType enum of fruit type.
     * @return The builder object itself.
     */
    FruitBuilder selectFruitType(FruitType type);

    /**
     * Build method to be called when you have set the parameters.
     * 
     * @return FruitPowerUp a new FruitPowerUp object.
     */
    FruitPowerUp build();
}
