package javawulf.model.item;

import javawulf.model.Coordinate;

/**
 * Implementation of the Item Factory.
 */
public final class ItemFactoryImpl implements ItemFactory {

    @Override
    public AmuletPiece createAmuletPiece(final Coordinate position) {
        return new AmuletPiece(position);
    }

    @Override
    public Cure createCure(final Coordinate position) {
        return new Cure(position);
    }

    @Override
    public CureMax createCureMax(final Coordinate position) {
        return new CureMax(position);
    }

    @Override
    public ExtraHeart createExtraHeart(final Coordinate position) {
        return new ExtraHeart(position);
    }

    @Override
    public GreatSword createGreatSword(final Coordinate position) {
        return new GreatSword(position);
    }

    @Override
    public Shield createShield(final Coordinate position) {
        return new Shield(position);
    }

}
