package javawulf.model.item;

import javawulf.model.Coordinate;

/**
 * Factory for creating items.
 */
public interface ItemFactory {

    /**
     * Creates an amulet piece.
     * 
     * @param position the position of the amulet piece when created
     * @return the amulet piece
     */
    AmuletPiece createAmuletPiece(Coordinate position);

    /**
     * Creates a cure potion.
     * 
     * @param position the position of the cure when created
     * @return the cure
     */
    Cure createCure(Coordinate position);

    /**
     * Creates a cure max potion.
     * 
     * @param position the position of the cure max when created
     * @return the cure max
     */
    CureMax createCureMax(Coordinate position);

    /**
     * Creates an extra heart.
     * 
     * @param position the position of the extra heart when created
     * @return the extra heart
     */
    ExtraHeart createExtraHeart(Coordinate position);

    /**
     * Creates a greatsword.
     * 
     * @param position the position of the great sword when created
     * @return the great sword
     */
    GreatSword createGreatSword(Coordinate position);

    /**
     * Creates a shield.
     * 
     * @param position the position of the shield when created
     * @return the shield
     */
    Shield createShield(Coordinate position);

}
