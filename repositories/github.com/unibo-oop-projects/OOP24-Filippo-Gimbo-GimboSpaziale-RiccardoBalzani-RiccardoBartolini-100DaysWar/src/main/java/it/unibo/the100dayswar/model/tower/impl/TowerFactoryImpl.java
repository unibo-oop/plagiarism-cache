package it.unibo.the100dayswar.model.tower.impl;

import java.util.Map;
import java.util.function.BiFunction;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.player.api.Player;
import it.unibo.the100dayswar.model.tower.api.Tower;
import it.unibo.the100dayswar.model.tower.api.TowerFactory;
import it.unibo.the100dayswar.model.tower.api.TowerType;

/**
 * Class that implements a factory pattern for all differents kind of towers.
 */
public class TowerFactoryImpl implements TowerFactory {
    private static final Logger LOGGER = Logger.getLogger(TowerFactoryImpl.class.getName());
    /**
     * Contains the tower type associated with its constructor.
     */
    private static final Map<TowerType, BiFunction<Player, Cell, Tower>> TOWER_CREATORS = Map.of(
        TowerType.BASIC, BasicTowerImpl::new,
        TowerType.ADVANCED, AdvancedTowerImpl::new
    );

    /**
     * {@inheritDoc}
     */
    @Override
    public Tower buildTower(final Player owner, final TowerType towerType, final Cell position) {
        if (owner == null || towerType == null || position == null) {
            LOGGER.log(Level.SEVERE, "Unknow Tower Type " + towerType, new NullPointerException());
            throw new IllegalArgumentException("All arguments must be non null");
        }

        final BiFunction<Player, Cell, Tower> towerConstructor = TOWER_CREATORS.get(towerType);

        return towerConstructor.apply(owner, position);
    } 
}
