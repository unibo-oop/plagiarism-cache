package powpaw;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import powpaw.map.model.impl.BlockFactory;
import powpaw.map.model.impl.BlockImpl;
import powpaw.map.model.impl.CreateMap;

class GameMapTest {
    private static final double DEBUG_POSITION_1 = 200;
    private static final double DEBUG_POSITION_2 = 250;

    @Test
    void checkTerrainTest() {
        final CreateMap map = new CreateMap();
        final List<BlockImpl> terrainTest = map.getTerrains();
        terrainTest.add(BlockFactory.createBlock(0, 0));
        assertEquals(terrainTest.size(), map.getTerrains().size());

    }

    @Test
    void checkBlockPositionTest() {
        final int x = 400;
        final BlockImpl blockOne = BlockFactory.createBlock(DEBUG_POSITION_1, DEBUG_POSITION_1);
        blockOne.setX(x);
        final BlockImpl blockTwo = BlockFactory.createBlock(DEBUG_POSITION_2, DEBUG_POSITION_1);
        blockTwo.setX(x);
        assertEquals(blockOne.getPosition(), blockTwo.getPosition());
    }

}
