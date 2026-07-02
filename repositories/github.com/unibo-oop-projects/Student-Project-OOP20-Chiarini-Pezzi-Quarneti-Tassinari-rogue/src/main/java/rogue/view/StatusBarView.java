package rogue.view;

import rogue.model.creature.PlayerAttribute;
import rogue.model.items.armor.Armor;
import rogue.model.items.weapons.Weapon;

/**
 * An interface for controlling the game status bar display.
 */
public interface StatusBarView extends View {

    /**
     * Sets the life label.
     * @param attribute
     *          the {@link PlayerAttribute} to update
     * @param value
     *          the value to replace
     */
    void setLifeLabel(PlayerAttribute attribute, int value);

    /**
     * Sets the weapon label.
     * @param weapon
     *          the weapon currently in use
     */
    void setWeaponLabel(Weapon weapon);

    /**
     * Sets the weapon label.
     * @param armor
     *          the armor currently in use
     */
    void setArmorLabel(Armor armor);

}
