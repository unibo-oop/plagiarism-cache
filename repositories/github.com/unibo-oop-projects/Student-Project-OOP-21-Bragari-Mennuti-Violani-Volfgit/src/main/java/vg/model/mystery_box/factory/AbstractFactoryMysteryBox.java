package vg.model.mystery_box.factory;

import vg.model.mystery_box.AbilityInTheBox;

/**
 * This is the abstract factory for the mystery box.
 */
public abstract class AbstractFactoryMysteryBox {
    /**
     * This is the abstract method for freeze time in the mystery box.
     * @return the ability freeze time in the box.
     */
    public abstract AbilityInTheBox createFreezeTime();
    /**
     * This is the abstract method for speed up in the mystery box.
     * @return the ability for speed up in the box.
     */
    public abstract AbilityInTheBox createSpeedUp();
    /**
     * This is the abstract method for score in the mystery box.
     * @return the ability for score in the box.
     */
    public abstract AbilityInTheBox createScore();
    /**
     * This is the abstract method to kill the mosquitoes in the mystery box.
     * @return the ability to kill the mosquitoes in the box.
     */
    public abstract AbilityInTheBox createKillMosquitoes();
}
