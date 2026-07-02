package model.player;

import java.util.*;
import model.character.Character;
import utilities.Utilities;

/**
 * Allows to properly build Players.
 * Note that there is no way to build a Player with less than 1 attempt or 2 characters to play with,
 * in that case an IllegalArgumentException will be thrown.
 */
public class PlayerBuilder {

    private Integer attempts;
    private Set<Character> characters = new HashSet<>();
    private boolean hasBuilt;

    /**
     * Allows to set the number of Attempts.
     * @throws IllegalArgumentException
     *                      in case of null argument or
     *                      in case the attempts are less than the minimum to play (i.e. 1)
     * @throws IllegalStateException
     *                      in case it has already been built
     * @param attempts the attempts
     * @return the PlayerBuilder
     */
    public PlayerBuilder setAttempts(final Integer attempts) {
        this.check();
        Utilities.requireNonNull(attempts);
        if (attempts < 1) {
            throw new IllegalArgumentException("At least one attempt");
        }
        this.attempts = attempts;
        return this;
    }

    /**
     * Allows to set the Characters to play with.
     * @throws IllegalArgumentException
     *                      in case of null argument or
     *                      in case the number of Characters is less than the minimum to play (i.e. 2)
     * @throws IllegalStateException
     *                      in case it has already been built
     * @param characters the characters
     * @return the PlayerBuilder
     */
    public PlayerBuilder setCharacters(final Set<Character> characters) {
        Utilities.requireNonNull(characters);
        if (characters.size() < 2) {
            throw new IllegalArgumentException("At least two characters");
        }
        this.characters = characters;
        return this;
    }

    /**
     * Allows to build the Player, it can be called just once.
     * @throws IllegalStateException
     *                      in case it has already been built or
     *                      in case the fields have not been set
     * @return the Player
     */
    public Player build() {
        this.check();
        if (attempts == null || characters.isEmpty()) {
            throw new IllegalStateException("Fields have not been set");
        }
        hasBuilt = true;
        return new PlayerImpl(characters, attempts);
    }

    private void check() {
        if (hasBuilt) {
            throw new IllegalStateException("Has already been built");
        }
    }

}
