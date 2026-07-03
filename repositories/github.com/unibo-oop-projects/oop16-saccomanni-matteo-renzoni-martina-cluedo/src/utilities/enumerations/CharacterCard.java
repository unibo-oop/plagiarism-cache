package utilities.enumerations;

import java.util.Arrays;
import java.util.List;

import model.cards.Card;

/**
 * Enumeration of the characters cards.
 */
public enum CharacterCard implements Card {

    /**
     * Reverend Green card.
     */
    GREEN("Reverend Green"),

    /**
     * Mrs. Peacock card.
     */
    PEACOCK("Mrs. Peacock"),

    /**
     * Professor Plum card.
     */
    PLUM("Professor Plum"),

    /**
     * Miss Scarlett card.
     */
    SCARLETT("Miss Scarlett"),

    /**
     * Colonel Mustard card.
     */
    MUSTARD("Colonel Mustard"),

    /**
     * Mrs. White card.
     */
    WHITE("Mrs. White");

    private final String name;

    CharacterCard(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public CardType getCardType() {
        return CardType.CHARACTER;
    }

    /**
     * Returns the path of the character card image.
     * 
     * @return the path of the character card image
     */
    public String getImagePath() {
        final String pathIm = "/images/characters/" + this.name.replace('.', ' ') + ".png";
        return pathIm.replaceAll(" ", "");
    }

    /**
     * Returns the pawn associated to the character.
     * 
     * @return the path of the pawn image
     */
    public String getPawnPath() {
        final String pathPawn = "/images/pawns/" + this.name.replace('.', ' ') + "pawn.png";
        return pathPawn.replaceAll(" ", "");
    }

    /**
     * Returns all character cards.
     * 
     * @return a list of character cards
     */
    public static List<CharacterCard> getCharacterCards() {
        return Arrays.asList(CharacterCard.values());
    }
}