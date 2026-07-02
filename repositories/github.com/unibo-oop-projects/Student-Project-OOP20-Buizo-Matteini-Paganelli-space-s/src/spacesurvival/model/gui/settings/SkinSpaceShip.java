package spacesurvival.model.gui.settings;

import spacesurvival.utilities.path.animation.AnimationShip;


import java.util.List;

/**
 * Enumeration for skin of spaceShip.
 */
public enum SkinSpaceShip {
    /**
     * Special skin.
     */
    SPECIAL(AnimationShip.SPECIAL0, AnimationShip.LIST_SHIP1),

    /**
     * Standard skin.
     */
    STANDARD(AnimationShip.STANDARD0, AnimationShip.LIST_SHIP2),

    /**
     * Deluxe skin.
     */
    DELUXE(AnimationShip.DELUXE0, AnimationShip.LIST_SHIP3),

    /**
     * Normal skin.
     */
    NORMAL(AnimationShip.NORMAL0, AnimationShip.LIST_SHIP4),

    /**
     * Atomic skin.
     */
    ATOMIC(AnimationShip.ATOMIC0, AnimationShip.LIST_SHIP5);

    private final String skin;
    private final List<String> animation;

    /**
     * Implement enumeration of skin with image and animation.
     * @param skin for image.
     * @param animation for game.
     */
    SkinSpaceShip(final String skin, final List<String> animation) {
        this.skin = skin;
        this.animation = animation;
    }

    /**
     * Get path of skin.
     * @return path for image.
     */
    public String getSkin() {
        return this.skin;
    }

    /**
     * Get animation.
     * @return List String a list of animation.
     */
    public List<String> getAnimation() {
        return this.animation;
    }
}
