package it.tbt.controller.modelmanager;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.entities.characters.Ally;
import it.tbt.model.entities.characters.Status;

import it.tbt.model.entities.items.Armor;
import it.tbt.model.entities.items.Potion;
import it.tbt.model.entities.items.Weapon;
import it.tbt.model.entities.items.Antidote;
import it.tbt.model.entities.items.Item;
import it.tbt.model.entities.items.Consumable;
import it.tbt.model.entities.items.Equipement;
import it.tbt.model.party.IParty;
import it.tbt.model.statechange.StateObserver;
import it.tbt.model.statechange.StateTrigger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static it.tbt.model.party.IParty.PARTY_SIZE;

/**
 * The {@code InventoryStateImpl} class represents the implementation of the {@link InventoryState} interface.
 * It manages the state of the inventory in the application's controller.
 * <p>
 * This class provides methods to access information about party members, inventory phase, inventory items,
 * and perform actions related to the inventory state.
 * </p>
 */
public class InventoryStateImpl implements InventoryState, StateTrigger {
    private static final int NOT_SELECTED = -1;
    private final IParty party;
    private InventoryPhase phase;
    private boolean itemLocked;
    private int membersCounter;
    private int memberSelected;
    private int itemCounter;
    private int itemSelected;
    private StateObserver stateObserver;

    /**
     * Constructs a new {@code InventoryStateImpl} with the given party.
     *
     * @param party The party associated with the inventory state.
     * @throws IllegalArgumentException if the party is null
     */
    @SuppressFBWarnings(
            value = { "EI2" },
            justification = "The Component needs to access the exact instance of the Party the game is using."
    )
    public InventoryStateImpl(final IParty party) {
        if (party == null) {
            throw new IllegalArgumentException("Party cannot be null");
        }
        this.party = party;
        this.phase = InventoryPhase.INVENTORY;

        itemCounter = 0;
        membersCounter = 0;
        itemSelected = NOT_SELECTED;
        memberSelected = NOT_SELECTED;
        if (party.getInventory().size() == 0) {
            this.phase = InventoryPhase.MEMBERS;
            itemLocked = true;
        } else {
            itemLocked = false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Ally> getPartyMembers() {
        return this.party.getMembers();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InventoryPhase getPhase() {
        return phase;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Item, Integer> getInventory() {
        return this.party.getInventory();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void previousElement() {
        switch (this.getPhase()) {
            case INVENTORY -> {
                if (this.itemSelected == NOT_SELECTED) {
                    this.itemCounter = (this.itemCounter - 1) < 0
                            ? this.party.getInventory().size() - 1 : this.itemCounter - 1;
                } else {
                    this.membersCounter = (this.membersCounter - 1) < 0
                            ? this.party.getMembers().size() - 1 : this.membersCounter - 1;
                }
            }
            case MEMBERS -> {
                this.membersCounter = (this.membersCounter - 1) < 0
                    ? this.party.getMembers().size() - 1 : this.membersCounter - 1;
            }
            default -> { }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextElement() {
        switch (this.getPhase()) {
            case INVENTORY -> {
                if (this.itemSelected == NOT_SELECTED) {
                    this.itemCounter = (this.itemCounter + 1) % this.getInventory().size();
                } else {
                    this.membersCounter = (this.membersCounter + 1) % this.getPartyMembers().size();
                }
            }
            case MEMBERS -> {
                this.membersCounter = (this.membersCounter + 1) % this.getPartyMembers().size();
            }
            default -> { }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performAction() {
        switch (this.getPhase()) {
            case INVENTORY -> {
                if (this.itemSelected == NOT_SELECTED) {
                    this.itemSelected = this.itemCounter;
                } else {
                    final List<Item> items = new ArrayList<>(this.getInventory().keySet());
                    if (items.get(this.itemSelected) instanceof Consumable) {
                        if (items.get(this.itemSelected) instanceof Potion
                                && this.getPartyMembers().get(this.membersCounter).getMaxHealth()
                                != this.getPartyMembers().get(this.membersCounter).getHealth()) {
                            this.getPartyMembers().get(this.membersCounter).setHealth(
                                    this.getPartyMembers().get(this.membersCounter).getHealth()
                                    + ((Potion) items.get(this.itemSelected)).getHealPower()
                            );
                            if (this.getPartyMembers().get(this.membersCounter).getHealth()
                                    > this.getPartyMembers().get(this.membersCounter).getMaxHealth()) {
                                this.getPartyMembers().get(this.membersCounter).setHealth(
                                        this.getPartyMembers().get(this.membersCounter).getMaxHealth()
                                );
                            }
                            party.removeItemFromInventory(items.get(this.itemSelected));
                        }
                        if (items.get(this.itemSelected) instanceof Antidote
                                && this.getPartyMembers().get(this.membersCounter).removeStatus(Status.POISONED)) {
                            party.removeItemFromInventory(items.get(this.itemSelected));
                        }
                    }
                    if (items.get(this.itemSelected) instanceof Equipement) {
                        if (items.get(this.itemSelected) instanceof Armor) {
                            Armor old = null;
                            if (this.getPartyMembers().get(this.membersCounter).getArmor().isPresent()) {
                                old = this.getPartyMembers().get(this.membersCounter).getArmor().get();
                            }
                            this.getPartyMembers().get(this.membersCounter).equipeArmor((Armor) items.get(this.itemSelected));
                            party.removeItemFromInventory(items.get(this.itemSelected));
                            if (old != null) {
                                party.addItemToInventory(old);
                            }
                        }
                        if (items.get(this.itemSelected) instanceof Weapon) {
                            Weapon old = null;
                            if (this.getPartyMembers().get(this.membersCounter).getWeapon().isPresent()) {
                                old = this.getPartyMembers().get(this.membersCounter).getWeapon().get();
                            }
                            this.getPartyMembers().get(this.membersCounter).equipeWeapon((Weapon) items.get(this.itemSelected));
                            party.removeItemFromInventory(items.get(this.itemSelected));
                            if (old != null) {
                                party.addItemToInventory(old);
                            }
                        }
                    }
                    this.itemSelected = NOT_SELECTED;
                    this.itemCounter = 0;
                    if (party.getInventory().size() == 0) {
                        this.phase = InventoryPhase.MEMBERS;
                        this.itemLocked = true;
                    }

                }
            }
            case MEMBERS -> {
                if (this.memberSelected == NOT_SELECTED) {
                    this.memberSelected = this.membersCounter;
                } else {
                    if (this.memberSelected != this.membersCounter) {
                        final ArrayList<Ally> temp = new ArrayList<>(this.getPartyMembers());
                        Collections.swap(temp, this.membersCounter, this.memberSelected);
                        this.party.setMembers(temp);
                    }
                    this.memberSelected = NOT_SELECTED;
                }
            }
            default -> { }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextPhase() {
        if (this.itemSelected == NOT_SELECTED && this.memberSelected == NOT_SELECTED) {
            this.phase = this.phase.next();
            if (itemLocked && this.phase == InventoryPhase.INVENTORY) {
                this.phase = this.phase.next();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPartySize() {
        return PARTY_SIZE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void previousPhase() {
        if (this.itemSelected == NOT_SELECTED && this.memberSelected == NOT_SELECTED) {
            this.phase = this.phase.previous();
            if (itemLocked && this.phase == InventoryPhase.INVENTORY) {
                this.phase = this.phase.previous();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToExplore() {
        if (this.stateObserver != null) {
            this.stateObserver.onExplore();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemSelected() {
        return this.itemSelected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getItemFocus() {
        return this.itemCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAllySelected() {
        return this.memberSelected;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getAllyFocused() {
        return this.membersCounter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStateObserver(final StateObserver stateObserver) {
        this.stateObserver = stateObserver;
    }
}
