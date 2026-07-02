package unibo.exiled.model.map;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.utilities.Position;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * The implementation of the model of the map.
 */
public final class MapModelImpl implements MapModel {
    private final GameMap map;
    private final GameModel model;

    /**
     * The constructor of the map manager.
     *
     * @param model The game model.
     */
    public MapModelImpl(final GameModel model) {
        this.map = new GameMapImpl(ConstantsAndResourceLoader.MAP_SIZE);
        this.model = model;
    }

    @Override
    public boolean isInBoundaries(final Position position) {
        return this.map.getCellStates().containsKey(position);
    }

    @Override
    public int getSize() {
        return this.map.getSize();
    }

    @Override
    public CellType getCellType(final Position position) {
        if (this.map.getCellStates().containsKey(position)) {
            return this.map.getCellStates().get(position);
        } else {
            throw new NoSuchElementException("The specified position isn't a cell.");
        }
    }

    @Override
    public Position getCornerOfType(final CellType type) {
        return this.map.getCornerPositionOfElement(type);
    }


    @Override
    public boolean isCornerOfMap(final Position position) {
        final int x = position.x();
        final int y = position.y();
        return x == 0 && y == 0
                || x == getSize() && y == getSize()
                || x == 0 && y == getSize()
                || y == 0 && x == getSize();
    }

    @Override
    public Optional<GameCharacter> getCharacterFromPosition(final Position pos) {
        return this.model.getCharacterModel().getCharacterFromPosition(pos);
    }
}
