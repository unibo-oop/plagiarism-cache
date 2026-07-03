package utilities.enumerations;

import java.util.Arrays;
import java.util.List;

import model.cards.Card;

/**
 * Enumeration of the room cards.
 */
public enum RoomCard implements Card {

    /**
     * Studio card.
     */
    STUDIO("Studio"),

    /**
     * Kitchen card.
     */
    KITCHEN("Kitchen"),

    /**
     * Trophy Hall card.
     */
    TROPHYHALL("Trophy Hall"),

    /**
     * Greenhouse card.
     */
    GREENHOUSE("Greenhouse"),

    /**
     * Lounge card.
     */
    LOUNGE("Lounge"),

    /**
     * Bedroom card.
     */
    BEDROOM("Bedroom"),

    /**
     * Entrance card.
     */
    ENTRANCE("Entrance"),

    /**
     * Library card.
     */
    LIBRARY("Library"),

    /**
     * Billiards room card.
     */
    BILLIARDS("Billiards");

    private final String name;

    RoomCard(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public CardType getCardType() {
        return CardType.ROOM;
    }

    /**
     * Returns the path of the room card image.
     * 
     * @return the path of the room card image
     */
    public String getImagePath() {
        final String pathim = "/images/rooms/" + this.name.replace('.', ' ') + ".png";
        return pathim.replaceAll(" ", "");
    }

    /**
     * Returns all room cards.
     * 
     * @return a list of room cards.
     */
    public static List<RoomCard> getRoomCards() {
        return Arrays.asList(RoomCard.values());
    }
}