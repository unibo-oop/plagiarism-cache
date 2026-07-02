package it.unibo.the100dayswar.model.unit.impl;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.soldier.api.Soldier;
import it.unibo.the100dayswar.model.soldier.impl.SoldierImpl;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerType;
import it.unibo.the100dayswar.model.tower.impl.TowerFactoryImpl;
import it.unibo.the100dayswar.model.unit.api.UnitFactory;

/**
 * Class that implements the UnitFactory interface.
 */
public class UnitFactoryImpl implements UnitFactory {
    /** 
     * {@inheritDoc}
     */
    @Override
    public Soldier createSoldier(final Player player) {
        return new SoldierImpl(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Tower createTower(final Player player, final TowerType type, final Cell position) {
        return new TowerFactoryImpl().buildTower(player, type, position);
    }
}
