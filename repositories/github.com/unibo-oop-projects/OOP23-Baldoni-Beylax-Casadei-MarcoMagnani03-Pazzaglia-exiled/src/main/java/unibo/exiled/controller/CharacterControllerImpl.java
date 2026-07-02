package unibo.exiled.controller;

import unibo.exiled.model.character.CharacterModel;
import unibo.exiled.model.character.GameCharacter;
import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.boss.BossEnemy;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.Moves;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;

import javax.annotation.concurrent.Immutable;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Implementation of the CharacterController interface.
 */
@Immutable
public final class CharacterControllerImpl implements CharacterController {

    private static final String EXCEPTION_POSITION_MISSING_MESSAGE = "The position doesn't contain a character.";
    private final CharacterModel model;

    /**
     * Constructor for the CharacterControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public CharacterControllerImpl(final CharacterModel model) {
        this.model = model;
    }

    @Override
    public List<String> getImagePathOfCharacter(final String folderPath, final String name) {
        final String loweredName = name.toLowerCase(Locale.ROOT);
        return List.of(
                folderPath,
                "/" + loweredName + "_up",
                "/" + loweredName + "_down",
                "/" + loweredName + "_left",
                "/" + loweredName + "_right");
    }

    @Override
    public double getPlayerHealth() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTH);
    }

    @Override
    public double getPlayerHealthCap() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.HEALTHCAP);
    }

    @Override
    public int getPlayerLevel() {
        return this.model.getPlayerLevel();
    }

    @Override
    public int getPlayerCurrentExperience() {
        return this.model.getPlayerCurrentExperience();
    }

    @Override
    public void addPlayerExperience(final int amount) {
        this.model.addPlayerExperience(amount);
    }

    @Override
    public int getPlayerExperienceCap() {
        return this.model.getPlayerExperienceCap();
    }

    @Override
    public String getPlayerClassName() {
        return this.model.getPlayerClass().getName();
    }

    @Override
    public void move(final Direction direction) {
        this.model.movePlayer(direction);
        this.model.moveEnemies();

    }

    @Override
    public Position getPlayerPosition() {
        return this.model.getPlayerPosition();
    }

    @Override
    public List<String> getMagicMoveNames() {
        return this.model.getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public String getMagicMoveDescription(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().getDescription();
        } else {
            return "";
        }
    }

    @Override
    public double getMagicMoveDamage(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().getPower();
        } else {
            throw new IllegalArgumentException("The move doesn't exists");
        }
    }

    @Override
    public ElementalType getMagicMoveElementalType(final String moveName) {
        final Optional<MagicMove> move = this.model.getMagicMoves().stream()
                .filter(m -> m.name().equals(moveName))
                .findFirst();
        if (move.isPresent()) {
            return move.get().getType();
        } else {
            throw new IllegalArgumentException("The move doesn't exists");
        }
    }

    @Override
    public List<String> getPlayerMoveSet() {
        return this.model.getPlayerMoveSet().getMagicMoves().stream().map(MagicMove::name).toList();
    }

    @Override
    public void assignPlayerClass(final ElementalType playerClass) {
        this.model.assignPlayerClass(playerClass);
    }

    @Override
    public boolean getIfCharacterInPositionIsMoving(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().spriteIsMoving();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealth();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public double getCharacterHealthCapFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getHealthCap();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public String getCharacterClassNameFromPosition(final Position position) {
        final Optional<GameCharacter> gottenCharacter = this.model
                .getCharacterFromPosition(position);
        if (gottenCharacter.isPresent()) {
            return gottenCharacter.get().getType().getName();
        } else {
            throw new IllegalArgumentException(EXCEPTION_POSITION_MISSING_MESSAGE);
        }
    }

    @Override
    public void removeEnemyFromPosition(final Position position) {
        this.model.removeEnemyFromPosition(position);
    }

    @Override
    public boolean checkWin() {
        if (model.getEnemies().isPresent()) {
            return model.getEnemies().get().getEnemies().stream().noneMatch(e -> e instanceof BossEnemy);
        }
        return true;
    }

    @Override
    public String getNewMove() {
        return this.model.getPlayer().flatMap(Player::getNewMove).get().name();
    }

    @Override
    public void changeMove(final String oldMove, final String newMove) {
        this.model.getPlayer().get().changeMove(Moves.getMoveByName(oldMove), Moves.getMoveByName(newMove));
    }

    @Override
    public double getPlayerAttack() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.ATTACK);
    }

    @Override
    public double getPlayerDefense() {
        return this.model.getPlayerAttributeOf(AttributeIdentifier.DEFENSE);
    }

    @Override
    public String getPlayerExceedingMoveName() {
        return this.model.getPlayer().get().getExceedingMagicMove().get().name();
    }
}
