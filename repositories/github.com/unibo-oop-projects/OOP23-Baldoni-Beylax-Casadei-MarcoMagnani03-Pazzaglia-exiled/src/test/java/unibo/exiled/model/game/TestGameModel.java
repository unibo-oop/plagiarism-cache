package unibo.exiled.model.game;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.Position;
import unibo.exiled.utilities.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

final class TestGameModel {

    private static int defaultMapSize;
    private static GameModel gameModel;

    @BeforeAll
    static void initializeConstants() {
        defaultMapSize = ConstantsAndResourceLoader.MAP_SIZE;
        gameModel = new GameModelImpl();
    }

    @Test
    void testConstructorAndInitialization() {
        assertNotNull(gameModel);
        assertEquals(defaultMapSize, gameModel.getMapModel().getSize());
        assertNotNull(gameModel.getCharacterModel().getPlayerPosition());
        assertNotNull(gameModel.getCharacterModel()
                .getCharacterFromPosition(gameModel.getCharacterModel().getPlayerPosition()));
        assertNotNull(gameModel.getMapModel().getCellType(gameModel.getCharacterModel().getPlayerPosition()));
        assertNotNull(gameModel.getItemsModel().getPlayerItems());
        assertEquals(1, gameModel.getCharacterModel().getPlayerLevel());
        assertNotNull(gameModel.getCharacterModel().getPlayerClass());
        assertNotNull(gameModel.getCharacterModel().getPlayerMoveSet());
    }

    @Test
    void testMovePlayer() {
        final Position positionBeforeMoving = gameModel.getCharacterModel().getPlayerPosition();
        gameModel.getCharacterModel().movePlayer(Direction.NORTH);
        assertEquals(Positions.sum(positionBeforeMoving, Direction.NORTH.getPosition()),
                gameModel.getCharacterModel().getPlayerPosition());
    }
}
