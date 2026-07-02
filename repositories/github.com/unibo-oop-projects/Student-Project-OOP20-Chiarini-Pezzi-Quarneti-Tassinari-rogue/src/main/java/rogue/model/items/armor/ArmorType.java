package rogue.model.items.armor;

/**
 * Represents an enumeration for declaring armor types.
 * 
 * The first field keeps track the armor's AC.
 * The second field keeps track the armor's name.
 */
public enum ArmorType {

    /**
     * Leather armor.
     */
    LEATHER(8, "Leather"),
    /**
     * Ring mail.
     */
    RING_MAIL(7, "Ring mail"), 
    /**
     * Studded leather.
     */
    STUDDED_LEATHER(7, "Studded Leather"), 
    /**
     * Scale mail.
     */
    SCALE_MAIL(6, "Scale mail"), 
    /**
     * Chain mail.
     */
    CHAIN_MAIL(5, "Chain mail"),
    /**
     * Splint mail.
     */
    SPLINT_MAIL(4, "Splint mail"),
    /** 
     * Banded mail.
     */
    BANDED_MAIL(4, "Banded mail"), 
    /** 
     * Plate mail.
     */
    PLATE_MAIL(3, "Plate mail");

    /**
     * A lower AC gives a better chance to avoid damage.
     */
    private final int ac;
    private final String name;

    ArmorType(final int ac, final String name) {
        this.ac = ac;
        this.name = name;
    }

    /**
     * @return the armor's AC default value.
     */
    protected int getDefaultAC() {
        return this.ac;
    }

    /**
     * @return a String representing the weapon name
     */
    protected String getName() {
        return this.name;
    }

}
