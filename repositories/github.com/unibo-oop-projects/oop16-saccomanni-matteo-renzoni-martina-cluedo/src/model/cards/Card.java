package model.cards;

import java.util.ArrayList;
import java.util.List;

import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.CardType;
import utilities.enumerations.WeaponCard;

/**
 * This is the interface of a generic card that could be a Character, a Room or
 * a Weapon. It's implemented by all the card enumerations.
 */
public interface Card {
    /**
     * Returns the type of a card.
     * 
     * @return the card type (Character, Room or Weapon)
     */
    CardType getCardType();

    /**
     * Return the path of the image of the card.
     * 
     * @return the path of the card.
     */
    String getImagePath();

    /**
     * Returns a list containing all the cards.
     * 
     * @return a list containing all the cards
     */
    static List<Card> getAllCards() {
        final List<Card> allCards = new ArrayList<>();
        allCards.addAll(CharacterCard.getCharacterCards());
        allCards.addAll(WeaponCard.getWeaponCards());
        allCards.addAll(RoomCard.getRoomCards());
        return allCards;
    }
}