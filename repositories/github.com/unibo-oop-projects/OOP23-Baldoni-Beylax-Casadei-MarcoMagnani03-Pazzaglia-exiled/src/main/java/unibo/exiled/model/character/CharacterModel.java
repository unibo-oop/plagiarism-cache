package unibo.exiled.model.character;

import java.util.Optional;
import java.util.Set;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.character.enemy.EnemyCollection;
import unibo.exiled.model.character.player.Player;
import unibo.exiled.model.move.MagicMove;
import unibo.exiled.model.move.MoveSet;
import unibo.exiled.utilities.Direction;
import unibo.exiled.utilities.ElementalType;
import unibo.exiled.utilities.Position;

import javax.annotation.concurrent.Immutable;

/**
 * The model representing a character in the game, the player or an enemy.
 */
@Immutable
public interface CharacterModel {
    /**
     * Moves the player in the selected direction.
     *
     * @param dir The direction where to move the player.
     */
    void movePlayer(Direction dir);

    /**
     * Moves the enemies in random directions.
     */
    void moveEnemies();

    /**
     * Returns the player.
     *
     * @return the player.
     */
    Optional<Player> getPlayer();

    /**
     * Returns the enemies.
     *
     * @return the enemies.
     */
    Optional<EnemyCollection> getEnemies();

    /**
     * Gets the evaluated attribute of the player.
     *
     * @param id The attribute identifier to get.
     * @return A double representing the evaluated value of the attribute.
     */
    double getPlayerAttributeOf(AttributeIdentifier id);

    /**
     * Gets the level of the player.
     *
     * @return An integer representing the level of the player.
     */
    int getPlayerLevel();

    /**
     * Gets the elemental class of the player.
     *
     * @return The elemental class of the player.
     */
    ElementalType getPlayerClass();

    /**
     * Gets the player move set.
     *
     * @return The move set.
     */
    MoveSet getPlayerMoveSet();

    /**
     * Gets the position of the player.
     *
     * @return The position of the player.
     */
    Position getPlayerPosition();

    /**
     * Gets the character in the selected cell.
     *
     * @param pos The cell where to search for the character.
     * @return An optional containing the character if found, empty otherwise.
     */
    Optional<GameCharacter> getCharacterFromPosition(Position pos);

    /**
     * Sets the elemental class of the player.
     *
     * @param playerClass The ElementalType representing the new elemental class of
     *                    the player.
     */
    void assignPlayerClass(ElementalType playerClass);

    /**
     * Retrieves the current experience of the player.
     *
     * @return The current experience of the player.
     */
    int getPlayerCurrentExperience();

    /**
     * Retrieves the experience cap of the player.
     *
     * @return The experience cap of the player.
     */
    int getPlayerExperienceCap();

    /**
     * Returns if the player has a move to learn, that couldn't learn because his move set was
     * at max capacity.
     * 
     * @return if the player has a move to learn.
     */
    boolean needsPlayerToChangeMove();

    /**
     * Adds experience to the player.
     *
     * @param amount the experience the player gained killing an enemy.
     */
    void addPlayerExperience(int amount);

    /**
     * Removes the enemy in the position.
     *
     * @param pos the position of the enemy to remove.
     */
    void removeEnemyFromPosition(Position pos);

    /**
     * Gets a set of every magic move in the game.
     *
     * @return A set of MagicMoves.
     */
    Set<MagicMove> getMagicMoves();

}
