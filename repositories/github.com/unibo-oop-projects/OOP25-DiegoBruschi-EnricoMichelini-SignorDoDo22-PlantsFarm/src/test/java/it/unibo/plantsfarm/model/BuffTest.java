package it.unibo.plantsfarm.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Rectangle;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.controller.garden.SpawningBuffsController;
import it.unibo.plantsfarm.model.garden.Buff;
import it.unibo.plantsfarm.model.garden.Buff.Type;
import it.unibo.plantsfarm.model.tiles.TileMap;

final class BuffTest {

    private static final int BUFF_SIZE = 48;
    private static final int LEGAL_POSITION_X = 144;
    private static final int LEGAL_POSITION_Y = 144;

    private static final String FILEPATH = "/maps/map.txt";

    @Test
    void testCreateBuff() {
        final Rectangle position = new Rectangle(0, 0, BUFF_SIZE, BUFF_SIZE);
        final Buff buff = new Buff(position);
        assertEquals(buff.getBuffType(), Type.OMNI_BUFF);
        assertEquals(buff.getBuffPosition(), position);
    }

    @Test
    void testCheckPosition() {
        final TileMap map = new TileMap();
        map.loadMap(FILEPATH);

        final SpawningBuffsController buffsController = new SpawningBuffsController(map);

        final Rectangle illegalPosition = new Rectangle(0, 0, BUFF_SIZE, BUFF_SIZE);
        assertFalse(buffsController.verifyPosUpgrade(illegalPosition.getX(), illegalPosition.getY()));

        final Rectangle legalPosition = new Rectangle(LEGAL_POSITION_X, LEGAL_POSITION_Y, BUFF_SIZE, BUFF_SIZE);
        assertTrue(buffsController.verifyPosUpgrade(legalPosition.getX(), legalPosition.getY()));
    }
}
