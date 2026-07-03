package breakout.model.entities;

import java.util.EnumSet;
import java.util.function.Function;

/**
 * Enum for the different types of bricks. It defines life and point value of a
 * particular category of bricks.
 *
 */

public enum BrickType {

    /**
     * Classic brick types.
     */
    ONE_CLASSIC(1, BrickStructure.SIMPLE),
    /**
     * 
     */
    THREE_CLASSIC(3, BrickStructure.SIMPLE),
    /**
     * 
     */
    FIVE_CLASSIC(5, BrickStructure.SIMPLE), 
    /**
     * 
     */
    SEVEN_CLASSIC(7, BrickStructure.SIMPLE),

    /**
     * Advanced brick types.
     */
    UNBREAKABLE_ADVANCED(0, BrickStructure.UNBREAKABLE), 
    /**
     * 
     */
    SIMPLE_ADVANCED(5, BrickStructure.SIMPLE), 
    /**
     * 
     */
    HARD_ADVANCED(12, BrickStructure.HARD);

    private int value;
    private BrickStructure structure;

    /**
     * Defines a brick type.
     * 
     * @param value
     *            point given when the brick is destroyed
     * @param type
     *            a structure defined in the enum {@link BrickStructure}
     */
    BrickType(final int value, final BrickStructure structure) {
        this.value = value;
        this.structure = structure;
    }

    /**
     * @return the score given when the brick is destroyed
     */
    public int getValue() {
        return this.value;
    }

    /**
     * @return the initial life of the brick
     */
    public int getStartingLife() {
        return this.structure.getLife();
    }

    /**
     * @return a function to determine how much life this brick loose when hit
     */
    public Function<Integer, Integer> decrementLife() {
        return this.structure.getDecrementFunction();
    }

    /**
     * @return the brick structur that "implement" this brick
     */
    public BrickStructure getStructure() {
        return this.structure;
    }

    /**
     * @return all the classic bricks of this enum
     */
    public static EnumSet<BrickType> getClassicBricks() {
        return EnumSet.of(BrickType.ONE_CLASSIC, BrickType.THREE_CLASSIC, BrickType.FIVE_CLASSIC,
                BrickType.SEVEN_CLASSIC);
    }

    /**
     * @return all the advanced bricks of this enum
     */
    public static EnumSet<BrickType> getAdvancedBricks() {
        return EnumSet.of(BrickType.SIMPLE_ADVANCED, BrickType.HARD_ADVANCED, BrickType.UNBREAKABLE_ADVANCED);

    }

}
