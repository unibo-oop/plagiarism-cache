package talisman.model.menu;

import java.util.Arrays;
import java.util.Locale;

/**
 * Used to store the informations for players that are ready to start a game.
 * 
 * @author Alberto Arduini
 *
 */
public class PlayerInfo {
    /**
     * The type of character chosen by the player.
     */
    public enum Character {
        /**
         * 
         */
        ASSASSIN("0"), DRUID("1"), DWARF("2"), ELF("3"), GHOUL("4");

        private final String imageName;

        Character(final String imageName) {
            this.imageName = imageName;
        }

        /**
         * Gets the name of the icon to show in the menu for this character.
         * 
         * @return the image name
         */
        public String getImageName() {
            return this.imageName;
        }

        /**
         * Gets the character index of this character.
         * 
         * @return the index
         */
        public int getIndex() {
            return Arrays.binarySearch(Character.values(), this);
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String toString() {
            return super.toString().toLowerCase(Locale.ENGLISH);
        }

        /**
         * Gets the number of characters.
         * 
         * @return the number of characters available
         */
        public static int getCount() {
            return Character.values().length;
        }
    }

    private final Character character;

    /**
     * Creates a new player info.
     * 
     * @param character the index of the character that the player has selected.
     */
    public PlayerInfo(final Character character) {
        this.character = character;
    }

    /**
     * Gets which character this player has selected.
     * 
     * @return the character index
     */
    public Character getCharacter() {
        return this.character;
    }
}
