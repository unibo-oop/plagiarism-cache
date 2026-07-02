package model.entities;

import enumerators.EntityType;
import enumerators.PlatformType;
import model.components.LifeImpl;
import model.physics.PhysicEntity;

public class PlatformModel extends AbstractEntityModel {

    private final static EntityType TYPE = EntityType.PLATFORM;
    private static final int DEFAULT_LIFE = 1;

    public PlatformModel(final PlatformType character, final PhysicEntity physicEntity) {
        super(TYPE, character, physicEntity);
    }

    /**
     * Add the default life value for a platform.
     */
    protected void addDefaultPlatformLife() {
        this.add(new LifeImpl(this, DEFAULT_LIFE, DEFAULT_LIFE, DEFAULT_LIFE));
    }
}
