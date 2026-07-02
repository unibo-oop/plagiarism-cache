package model.entities;

import enumerators.EntityType;
import enumerators.SpecificType;
import model.components.CoinValueImpl;
import model.components.LifeImpl;
import model.physics.PhysicEntity;

public class CoinModel extends AbstractEntityModel {

    private static final EntityType TYPE = EntityType.COIN;
    private static final int DEFAULT_COIN_VALUE = 1;
    private static final int DEFAULT_LIFE = 1;

    public CoinModel(final SpecificType type, final PhysicEntity physicEntity) {
        super(TYPE, type, physicEntity);
    }

    /**
     * Add the default coin value for a coin.
     */
    protected void addDefaultCoinValue() {
        this.add(new CoinValueImpl(DEFAULT_COIN_VALUE));
    }

    /**
     * Add the default life for a coin.
     */
    protected void addDefaultLife() {
        this.add(new LifeImpl(this, DEFAULT_LIFE, DEFAULT_LIFE, DEFAULT_LIFE));
    }

}
