package model.entities;

/**
 * Enumeration that represents how the hero attacks.
 */
public enum AttackType {

    /**
     * Represent an Entity that cannot attack.
     */
    NONE(Constants.DEFAULT_NONE_TEXT, Constants.DEFAULT_NONE_VALUE),
    /**
     * Represent an entity that attacks with sword.
     */
    SWORD(Constants.DEFAULT_SWORD_TEXT, Constants.DEFAULT_SWORD_VALUE),
    /**
     * Represent an entity that attacks with polearm.
     */
    POLEARM(Constants.DEFAULT_POLEARM_TEXT, Constants.DEFAULT_POLEARM_VALUE),
    /**
     * Represent an entity that attacks with bow.
     */
    BOW(Constants.DEFAULT_BOW_TEXT, Constants.DEFAULT_BOW_VALUE);

    private final int range;
    private String text;

    AttackType(final String text, final int range) {
        this.text = text;
        this.range = range;
    }

    /**
     * Returns the enumerator corresponding to the integer passed.
     * 
     * @param x
     *            the integer passed
     * @return the enumerator value associated to the integer value
     */
    public static AttackType fromInteger(final int x) {
        switch (x) {
        case Constants.DEFAULT_NONE_VALUE:
            return AttackType.NONE;
        case Constants.DEFAULT_SWORD_VALUE:
            return AttackType.SWORD;
        case Constants.DEFAULT_POLEARM_VALUE:
            return AttackType.POLEARM;
        case Constants.DEFAULT_BOW_VALUE:
            return AttackType.BOW;
        default:
            return AttackType.NONE;
        }
    }

    /**
     * Getter for the attack range.
     * 
     * @return the range
     */
    public int getRange() {
        return this.range;
    }

    @Override
    public String toString() {
        return this.text + "(" + Integer.toString(this.range) + ")";
    }

    public static class Constants {
        /**
         * 
         */
        public static final int DEFAULT_NONE_VALUE = 0;
        /**
         * 
         */
        public static final int DEFAULT_SWORD_VALUE = 1;
        /**
         * 
         */
        public static final int DEFAULT_POLEARM_VALUE = 2;
        /**
         * 
         */
        public static final int DEFAULT_BOW_VALUE = 3;
        /**
         * 
         */
        public static final String DEFAULT_NONE_TEXT = "None";
        /**
         * 
         */
        public static final String DEFAULT_SWORD_TEXT = "Sword";
        /**
         * 
         */
        public static final String DEFAULT_POLEARM_TEXT = "Polearm";
        /**
         * 
         */
        public static final String DEFAULT_BOW_TEXT = "Bow";
    }
}
