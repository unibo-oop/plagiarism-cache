package talisman.controller.battle;

import java.util.ArrayList;
import java.util.List;

import talisman.Controllers;
import talisman.controller.board.TalismanBoardController;
import talisman.controller.character.CharactersController;
import talisman.model.battle.EnemyModel;
import talisman.model.character.CharacterModel;
import talisman.model.character.CharacterModelImpl;
import talisman.model.character.PlayerModelImpl;
import talisman.model.character.defaultCharacters.CharacterType;
import talisman.model.character.defaultCharacters.TalismanCharacterFactory;

/**
 * The implementation of a MVC controller for the character's death.
 * 
 * @author Alice Girolomini
 *
 */
public class DeathControllerImpl implements DeathController {
    private final CharactersController characterController;
    private final TalismanBoardController boardController;
    private final CharacterModel character;

    /**
     * Creates a new the death controller.
     * 
     * @param character - the character to check
     */
    public DeathControllerImpl(final CharacterModel character) {
        this.characterController = Controllers.getCharactersController();
        this.boardController = Controllers.getBoardController();
        this.character = character;
    }

    /**
     * Checks whether the character's life is ended.
     * 
     * @return true if the character's life is zero
     */
    private boolean checkHealth() {
        return (this.character.getHealth() == 0);
    }

    /**
     * Finds the player associated to the character.
     * 
     * @return the player's index
     */
    private int findPlayer() {
        List<Integer> indexes = new ArrayList<>(this.boardController.getCurrentCharacterOpponents());
        List<PlayerModelImpl> players = this.characterController.getPlayers();
        for (int i = 0; i < indexes.size(); i++) {
            if (players.get(indexes.get(i)).getCurrentCharacter().equals(character)) {
                return indexes.get(i);
            }
        }
        return this.characterController.getCurrentPlayer().getIndex();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean death() {
        if (this.character.getClass().equals(EnemyModel.class)) {
            return false;
        }
        if (checkHealth()) {
            int index = findPlayer();
            resetCharacterInfo((CharacterModelImpl) this.character, index);
            resetCharacterPosition(index);
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCharacterInfo(final CharacterModelImpl character, final int index) {
        CharacterType type = character.getType();
        switch (type) {
        case ASSASSIN:
            this.characterController.getPlayers().get(index).setCurrentCharacter(TalismanCharacterFactory.createAssassinCharacter());
            break;
        case DRUID:
            this.characterController.getPlayers().get(index).setCurrentCharacter(TalismanCharacterFactory.createDruidCharacter());
            break;
        case DWARF:
            this.characterController.getPlayers().get(index).setCurrentCharacter(TalismanCharacterFactory.createDwarfCharacter());
            break;
        case ELF:
            this.characterController.getPlayers().get(index).setCurrentCharacter(TalismanCharacterFactory.createElfCharacter());
            break;
        case GHOUL:
            this.characterController.getPlayers().get(index).setCurrentCharacter(TalismanCharacterFactory.createGhoulCharacter());
            break;
        default:

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetCharacterPosition(final int index) {
        if (index >= 0) {
            this.boardController.moveCharacterSection(index, 0, 0);
        }
    }

}
