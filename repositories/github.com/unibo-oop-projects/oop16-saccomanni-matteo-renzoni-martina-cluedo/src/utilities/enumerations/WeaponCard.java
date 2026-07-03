package utilities.enumerations;

import java.util.Arrays;
import java.util.List;

import model.cards.Card;

/**
 * Enumeration of the weapons cards.
 */
public enum WeaponCard implements Card {

    /**
     * Candlestick card.
     */
    CANDLESTICK("Candlestick"),

    /**
     * Ice Pick card.
     */
    ICEPICK("Ice Pick"),

    /**
     * Poison card.
     */
    POISON("Poison"),

    /**
     * Poker card.
     */
    POKER("Poker"),

    /**
     * Revolver card.
     */
    REVOLVER("Revolver"),

    /**
     * Shears card.
     */
    SHEARS("Shears");

    private final String name;

    WeaponCard(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public CardType getCardType() {
        return CardType.WEAPON;
    }

    /**
     * Returns the path of the room weapon image.
     * 
     * @return the path of the room weapon image
     */
    public String getImagePath() {
        final String pathim = "/images/weapons/" + this.name.replace('.', ' ') + ".png";
        return pathim.replaceAll(" ", "");
    }

    /**
     * Returns all weapon cards.
     * 
     * @return a list of weapon cards.
     */
    public static List<WeaponCard> getWeaponCards() {
        return Arrays.asList(WeaponCard.values());
    }
}