package model.entities;

import java.util.List;

import model.common.Dimension;
import model.common.DimensionImpl;
import model.entities.tankcomponents.TankComponent;

public interface Tank extends GameEntity {
    Dimension DEFAULT_TANK_DIMENSION = new DimensionImpl(1.6, 1.6);

    void shoot();

    List<TankComponent> getComponents();

    Tank attach(TankComponent component);

}
