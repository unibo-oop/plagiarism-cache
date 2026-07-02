package it.tbt.model.party;

import it.tbt.model.statechange.StateTrigger;
import it.tbt.model.world.interaction.InteractionComponent;
import it.tbt.model.world.interaction.InteractionTrigger;
import it.tbt.model.world.interaction.PartyInteractionComponent;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Inventory;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.MovableEntityImpl;
import it.tbt.model.world.api.Room;
import it.tbt.model.statechange.StateObserver;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;
import java.util.Map;

/**
 * The {@code Party} class represents a party implementation.
 * It represents a group of allies and provides functionality related to the party.
 */
public class Party extends MovableEntityImpl implements IParty, InteractionTrigger, StateTrigger {
    private List<Ally> members;
    private Optional<Room> currentRoom;
    private int wallet;
    private final Inventory inventory;
    private StateObserver stateObserver;
    private final InteractionComponent interactionComponent = new PartyInteractionComponent(this);
    private Pair<String, String> dialogue;

    /**
     * Constructs a new {@code Party} without any party members.
     *
     * @param name   the name of the party
     * @param x      the x-coordinate of the party's position
     * @param y      the y-coordinate of the party's position
     * @param width  the width of the party
     * @param height the height of the party
     */
    public Party(final String name, final int x, final int y, final int width, final int height) {
        super(name, x, y, width, height);
        this.members = new ArrayList<>();
        this.inventory = new Inventory();
        this.currentRoom = Optional.empty();
    }

    /**
     * Constructs a new {@code Party} with the specified collection of allies.
     *
     * @param name   the name of the party
     * @param x      the x-coordinate of the party's position
     * @param y      the y-coordinate of the party's position
     * @param width  the width of the party
     * @param height the height of the party
     * @param c      the collection of allies
     */
    public Party(final String name, final int x, final int y, final int width, final int height, final Collection<Ally> c) {
        super(name, x, y, width, height);
        this.members = new ArrayList<>(c);
        this.inventory = new Inventory();
    }

    /**
     * Sets the current room for the party.
     *
     * @param room the current room
     */
    @Override
    public void setCurrentRoom(final Room room) {
        this.currentRoom = Optional.of(room);
        this.stateObserver.onExplore();
    }

    /**
     * Gets the current room of the party.
     *
     * @return the current room
     */
    @Override
    public Room getCurrentRoom() {
        if (this.currentRoom.isEmpty()) {
            throw new IllegalStateException("Player has no room assigned to it.");
        }
        return this.currentRoom.get();
    }

    /**
     * Moves the party by the specified distances.
     *
     * @param xv the distance to move along the x-axis
     * @param yv the distance to move along the y-axis
     */
    @Override
    public void move(final int xv, final int yv) {
        if (this.getCurrentRoom().isValidCoordinates(xv + getX(), yv + getY(), getWidth(), getHeight())) {
            setX(getX() + xv);
            setY(getY() + yv);
        }
    }

    /**
     * Gets the list of party members.
     *
     * @return the list of allies
     */
    @Override
    public List<Ally> getMembers() {
        return List.copyOf(members);
    }

    /**
     * Adds the given ally to the party.
     *
     * @param ally the ally to add
     * @return true if the ally has been added successfully
     */
    @Override
    public boolean addMember(final Ally ally) {
        return members.add(ally);
    }

    /**
     * Removes the given ally from the party.
     *
     * @param ally the ally to remove
     * @return true if the ally has been removed successfully
     */
    @Override
    public boolean removeMember(final Ally ally) {
        return members.remove(ally);
    }

    /**
     * Gets the current amount of cash available to the party.
     *
     * @return the available cash
     */
    @Override
    public int getWallet() {
        return wallet;
    }

    /**
     * Adds or subtracts the given amount to the wallet.
     *
     * @param amount the amount to add or subtract
     */
    @Override
    public void addCash(final int amount) {
        wallet += amount;
    }

    /**
     * Triggers the interaction logic of the interaction component.
     */
    @Override
    public void triggerInteraction() {
        this.interactionComponent.interactLogic();
    }

    /**
     * Sets the state observer for the party.
     *
     * @param stateObserver the state observer
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }

    /**
     * Gets the character's inventory.
     *
     * @return a map representing the character's inventory (item, count)
     */
    @Override
    public Map<Item, Integer> getInventory() {
        return inventory.getItems();
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item to add
     */
    @Override
    public void addItemToInventory(final Item item) {
        inventory.addItem(item);
    }

    /**
     * Removes an item from the inventory.
     *
     * @param item the item to remove
     * @return true if the item was found and removed successfully
     */
    @Override
    public boolean removeItemFromInventory(final Item item) {
        return inventory.removeItem(item);
    }

    /**
     * Gets the current dialogue of the party.
     *
     * @return the dialogue as a pair of strings (speaker, content)
     */
    @Override
    public Pair<String, String> getDialogue() {
        return dialogue;
    }

    /**
     * Sets the dialogue for the party.
     *
     * @param dialogue the dialogue as a pair of strings (speaker, content)
     */
    @Override
    public void setDialogue(final Pair<String, String> dialogue) {
        this.dialogue = dialogue;
    }

    /**
     * Sets the members of the party.
     *
     * @param members the list of members
     */
    @Override
    public void setMembers(final List<Ally> members) {
        this.members = List.copyOf(members);
    }
}
