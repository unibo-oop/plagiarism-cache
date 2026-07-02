package model.mod;

import model.obstacle.GameObject;
import model.obstacle.GameObjectImpl.GameObjectType;
import model.obstacle.ObstacleBaseType;

/**
 *
 */
public class ModEntity implements ModObstacle {

    private final ModType modType;
    private final GameObject base;

    /**
     * Constructor for the ModEntity.
     * @param base The underlying GameObject.
     * @param type The type of the mod.
     */
    public ModEntity(final GameObject base, final ModType type) {
        this.modType = type;
        this.base = base;
    }

    @Override
    public double getCenter() {
        return base.getCenter();
    }

    @Override
    public void setCenter(final double newCenter) {
        //mods do not move
    }

    /**/
    @Override
    public GameObjectType getGameObjectType() {
        return base.getGameObjectType();
    }

    /**/
    @Override
    public double getWidth() {
        return base.getWidth();
    }

    /**/
    @Override
    public ObstacleBaseType getBaseType() {
        return base.getBaseType();
    }

    /**/
    @Override
    public ModType getModType() {
        return this.modType;
    }

}
