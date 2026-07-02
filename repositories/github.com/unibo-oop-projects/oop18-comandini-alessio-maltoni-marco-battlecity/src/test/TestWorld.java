package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

import enums.Sprite;
import model.World;
import model.common.PositionImpl;
import model.entities.Block;
import model.entities.BlockImpl;
import model.entities.TankImpl;

class TestWorld {
    private static final int BLOCKS = 10;
    private final World world = new World();

    @Test
    void testOnePlayer() {
        world.setup(Stream.of(new TankImpl(Sprite.PLAYER_TANK_GREEN)).collect(Collectors.toList()),
                Stream.generate(() -> new BlockImpl(Sprite.BLOCK_BRICK, new PositionImpl(), Block.Type.WALL))
                        .limit(BLOCKS).collect(Collectors.toList()));
        assertEquals(BLOCKS + 1, world.getWorldEntity().size());
    }

    @Test
    void testTwoPlayers() {
        world.setup(
                Stream.of(new TankImpl(Sprite.PLAYER_TANK_GREEN), new TankImpl(Sprite.PLAYER_TANK_YELLOW))
                        .collect(Collectors.toList()),
                Stream.generate(() -> new BlockImpl(Sprite.BLOCK_BRICK, new PositionImpl(), Block.Type.WALL))
                        .limit(BLOCKS).collect(Collectors.toList()));
        assertEquals(BLOCKS + 2, world.getWorldEntity().size());
    }

    @Test
    void testGetAddEnemy() {
        world.setup(Stream.of(new TankImpl(Sprite.PLAYER_TANK_GREEN)).collect(Collectors.toList()),
                Stream.generate(() -> new BlockImpl(Sprite.BLOCK_BRICK, new PositionImpl(), Block.Type.WALL))
                        .limit(BLOCKS).collect(Collectors.toList()));
        assertEquals(0, world.getEnemy().size());
        world.addEnemy(new TankImpl(Sprite.ENEMY_ARMOR_TANK));
        assertEquals(1, world.getEnemy().size());
    }

    @Test
    void testUpdateState() {
        world.updateState();
    }

}
