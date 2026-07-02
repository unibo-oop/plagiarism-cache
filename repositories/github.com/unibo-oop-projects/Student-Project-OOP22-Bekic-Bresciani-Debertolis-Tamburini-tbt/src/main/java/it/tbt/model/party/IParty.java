package it.tbt.model.party;

import java.util.List;
import java.util.Map;

import it.tbt.model.entities.MovableEntity;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.items.Item;
import it.tbt.model.world.api.Room;
import javafx.util.Pair;

/**
 * Generic Party.
 */
public interface IParty extends MovableEntity {
    /**
     * Active party size.
     */
    int PARTY_SIZE = 4;
    /**
     * Set current room.
     * @param room
     */
    void setCurrentRoom(Room room);

    /**
     * Get current room.
     * @return room
     */
    Room getCurrentRoom();

    /**
     * Move party.
     * @param xv
     * @param yv
     */
    void move(int xv, int yv);

    /**
     * Get the party members.
     * @return list of allies
     */
    List<Ally> getMembers();

    /**
     * Add the given ally to the party.
     * @param ally
     * @return true if the ally has been added
     */
    boolean addMember(Ally ally);

    /**
     * Remove the given ally from the party.
     * @param ally
     * @return true if the ally has been removed
     */
    boolean removeMember(Ally ally);

    /**
     * Get the current amount of cash available to the party.
     * @return available cash
     */
    int getWallet();

    /**
     * Add/subtract the given amount to the wallet.
     * @param amount
     */
    void addCash(int amount);

    /**
     * Get the character inventory.
     * @return map of <item, count> representing the character's intentory
     */
    Map<Item, Integer> getInventory();

    /**
     * Add an item to the inventory.
     * @param item
     */
    void addItemToInventory(Item item);

    /**
     * Remove an item from the inventory.
     * @param item
     * @return true if the item was found and removed
     */
    boolean removeItemFromInventory(Item item);

    /**
     * Return the stored dialogue.
     * @return Pair < Speaker, Content >
     */
    Pair<String, String> getDialogue();

    /**
     * Set the stored dialogue.
     * @param  dialogue Pair < Speaker, Content >
     */
    void setDialogue(Pair<String, String> dialogue);
    /**
     * Set party members.
     * @param  members List < Ally >
     */
    void setMembers(List<Ally> members);
}
