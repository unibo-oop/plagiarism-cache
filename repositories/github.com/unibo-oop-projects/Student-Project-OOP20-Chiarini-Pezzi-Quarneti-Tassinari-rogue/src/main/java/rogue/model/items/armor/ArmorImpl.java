package rogue.model.items.armor;

import rogue.model.creature.Player;

/** 
 * Represents an implementation for a game {@link Armor}.
 */
public final class ArmorImpl implements Armor {

    private final ArmorType armor;
    private int ac;

    /**
     * Builds a new {@link ArmorImpl}.
     * @param armor
     *          the armor type
     */
    public ArmorImpl(final ArmorType armor) {
        this.armor = armor;
        this.ac = this.armor.getDefaultAC();
    }

    @Override
    public int getAC() {
        return this.ac;
    }

    @Override
    public ArmorType getArmorType() {
        return this.armor;
    }

    /**
     * Equip the player with this armor.
     * @param player
     *          the player who wants to wear this armor
     */
    @Override
    public boolean use(final Player player) {
        player.getEquipment().setArmor(this);
        return true;
    }

    private void updateAC(final int amount) {
        this.ac = this.ac + amount > 0 ? this.ac + amount : 0;
    }

    @Override
    public void increaseAC(final int amount) {
        this.updateAC(amount);
    }

    @Override
    public void decreaseAC(final int amount) {
        this.updateAC(-amount);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((armor == null) ? 0 : armor.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArmorImpl)) {
            return false;
        }
        final ArmorImpl other = (ArmorImpl) obj;
        return armor == other.armor;
    }

    @Override
    public String toString() {
        return this.armor.getName() + " (" + this.ac + ")";
    }

}
