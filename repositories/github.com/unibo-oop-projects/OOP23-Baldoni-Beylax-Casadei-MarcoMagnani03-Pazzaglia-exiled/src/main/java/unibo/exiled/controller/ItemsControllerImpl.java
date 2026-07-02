package unibo.exiled.controller;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import unibo.exiled.model.item.Item;
import unibo.exiled.model.item.UsableItem;
import unibo.exiled.model.item.utilities.ItemsContainer;
import unibo.exiled.model.item.utilities.ItemType;
import unibo.exiled.model.item.ItemsModel;

/**
 * Implementation of the ItemsController interface.
 */
public final class ItemsControllerImpl implements ItemsController {

    private final ItemsModel model;

    /**
     * Constructor for the ItemsControllerImpl.
     *
     * @param model The game model to manage the game.
     */
    public ItemsControllerImpl(final ItemsModel model) {
        this.model = Objects.requireNonNull(model);
    }

    @Override
    public Map<String, Integer> getItems() {
        return this.model.getPlayerItems();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return this.model.getItemDescription(itemName);
    }

    @Override
    public double getItemValor(final String itemName) {
        return this.model.getItemValor(itemName);
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return this.model.getItemType(itemName);
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        return this.model.getItemBoostedAttributeName(itemName);
    }

    @Override
    public void useItem(final String itemName) {
        this.model.useItem(itemName);
    }

    @Override
    public boolean isItemUsable(final String itemName) {
        final Optional<Item> gottenItem = ItemsContainer.getItemByName(itemName);
        return gottenItem.filter(item -> item instanceof UsableItem).isPresent();
    }
}
