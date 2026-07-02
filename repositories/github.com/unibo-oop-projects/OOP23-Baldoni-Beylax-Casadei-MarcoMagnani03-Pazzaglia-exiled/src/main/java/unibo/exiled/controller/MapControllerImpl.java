package unibo.exiled.controller;

import java.util.Optional;

import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.enemy.Enemy;
import unibo.exiled.model.map.CellType;
import unibo.exiled.model.map.MapModel;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.Position;

/**
 * Implementation of the MapController interface.
 */
public final class MapControllerImpl implements MapController {
    private final MapModel model;

    /**
     * The constructor of the game main controller.
     *
     * @param model The game model to manage the game.
     */
    public MapControllerImpl(final MapModel model) {
        this.model = model;
    }

    @Override
    public int getMapSize() {
        return model.getSize();
    }

    @Override
    public CellType getCellType(final Position position) {
        return model.getCellType(position);
    }


    @Override
    public boolean isEnemyInCell(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        return gottenCharacter.filter(gameCharacter -> gameCharacter instanceof Enemy).isPresent();
    }

    @Override
    public String getNameOfCharacterInPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = model.getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getName();
        } else {
            return "";
        }
    }

    @Override
    public Direction getLastDirectionOfCharacterInPosition(final Position position) {
        return model.getCharacterFromPosition(position).get().getLastDirection();
    }
}
