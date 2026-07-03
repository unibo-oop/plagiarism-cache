package model.cards;

import java.io.Serializable;
import java.util.Set;

import utilities.enumerations.CharacterCard;
import utilities.enumerations.RoomCard;
import utilities.enumerations.WeaponCard;

/**
 * This interface represents a solution: a triple of cards composed of 1
 * CharacterCard, 1 RoomCard, 1 WeaponCard.
 */
public interface Solution extends Serializable {
    /**
     * Returns the character card.
     * 
     * @return the character card
     */
    CharacterCard getCharacter();

    /**
     * Returns the weapon card.
     * 
     * @return the weapon card
     */
    WeaponCard getWeapon();

    /**
     * Returns the room card.
     * 
     * @return the room card
     */
    RoomCard getRoom();

    /**
     * Returns all the cards of the solution.
     * 
     * @return the cards of the solution
     */
    Set<Card> getCards();
}