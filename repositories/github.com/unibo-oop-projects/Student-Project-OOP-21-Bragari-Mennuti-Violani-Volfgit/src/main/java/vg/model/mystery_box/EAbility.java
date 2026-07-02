package vg.model.mystery_box;

import vg.utils.path.PathImageMysteryBox;

/**
 * This enum represents the different abilities that can be in the box.
 */
public enum EAbility {
    /**
     * Score ability.
     */
    SCORE(PathImageMysteryBox.COIN_SCORE),
    /**
     * Time ability.
     */
    FREEZE_TIME(PathImageMysteryBox.COIN_TIME),
    /**
     * Speed ability.
     */
    SPEED_UP(PathImageMysteryBox.COIN_SPEED),
    /**
     * Kill mosquitoes ability.
     */
    KILL_ALL_MOSQUITOES(PathImageMysteryBox.COIN_KILL_ALL_MOQUETOES);
    private final String pathReveled;
    EAbility(final String pathReveled) {
        this.pathReveled = pathReveled;
    }
    /**
     * This method is used to get the path of the ability.
     * @return the path of the ability.
     */
    public String getPathReveled() {
        return pathReveled;
    }
    /**
     * This method is used to randomize the ability.
     * @return the ability.
     */
    public static EAbility randomAll()  {
        return values()[(int) (Math.random() * values().length)];
    }
}
