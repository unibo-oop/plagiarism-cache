package unibo.exiled.model.item;

import java.util.Map;
import java.util.stream.Collectors;

import unibo.exiled.model.character.attributes.AttributeIdentifier;
import unibo.exiled.model.game.GameModel;
import unibo.exiled.model.item.utilities.ItemType;

/**
 * The implementation of the model of the items.
 */
public final class ItemsModelImpl implements ItemsModel {
    private final GameModel model;

    /**
     * The constructor of the model of the Items.
     *
     * @param model
     */
    public ItemsModelImpl(final GameModel model) {
        this.model = model;
    }

    @Override
    public Map<String, Integer> getPlayerItems() {
        return this.model.getCharacterModel().getPlayer().get().getInventory().getItems()
                .entrySet()
                .stream()
                .collect(Collectors.toUnmodifiableMap(entry -> entry.getKey().getName(), Map.Entry::getValue));
    }

    private Item getItem(final String itemName) {
        return this.model.getCharacterModel().getPlayer().get().getInventory().getItems()
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey().getName().equals(itemName))
                .findFirst().get().getKey();
    }

    @Override
    public String getItemDescription(final String itemName) {
        return getItem(itemName).getDescription();
    }

    @Override
    public double getItemValor(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof UsableItem usableItem) {
            return usableItem.getAmount();
        }
        return 0;
    }

    @Override
    public ItemType getItemType(final String itemName) {
        return getItem(itemName).getType();
    }

    @Override
    public String getItemBoostedAttributeName(final String itemName) {
        final Item selectedItem = getItem(itemName);
        if (selectedItem instanceof PowerUpItem powerUpItem) {
            return powerUpItem.getBoostedAttribute().getName();
        } else if (selectedItem instanceof HealingItem) {
            return AttributeIdentifier.HEALTH.getName();
        }
        return "";
    }

    @Override
    public void useItem(final String item) {
        final Item selectedItem = getItem(item);
        if (selectedItem instanceof UsableItem usableItem) {
            this.model.getCharacterModel().getPlayer().get().useItem(usableItem);
        } else {
            throw new IllegalArgumentException("Trying to use an unusable item.");
        }
    }

}
