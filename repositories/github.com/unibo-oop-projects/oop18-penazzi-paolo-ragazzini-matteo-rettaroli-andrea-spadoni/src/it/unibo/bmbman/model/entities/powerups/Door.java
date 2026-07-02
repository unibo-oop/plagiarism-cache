package it.unibo.bmbman.model.entities.powerups;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.entities.HeroImpl;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Model the door to reach to win the game.
 */
public class Door extends AbstractPowerupEntity {
    /**
     * Construct the door in the world.
     */
    public Door() {
        super(new Position(TerrainFactoryImpl.DOOR_POSITION.getX() / ScreenToolUtils.SCALE, 
                           TerrainFactoryImpl.DOOR_POSITION.getY() / ScreenToolUtils.SCALE), 
                           new Dimension(TerrainFactoryImpl.CELL_DIMENSION, TerrainFactoryImpl.CELL_DIMENSION), true);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public final void powerupEffect(final HeroImpl hero) {
        hero.checkWin();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove() {
        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void removeEffect(final HeroImpl hero) {
    }

}
