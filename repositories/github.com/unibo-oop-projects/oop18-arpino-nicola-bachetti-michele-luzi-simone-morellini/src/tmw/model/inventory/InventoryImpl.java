package tmw.model.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import tmw.model.item.Item;

/**
 * Class to manage the items that the character carries in the inventory and to
 * add new item collected in the game map.
 * <p>
 * Implementation of {@link tmw.model.inventory.Inventory}
 */
public class InventoryImpl implements Inventory {

    private final List<Optional<Item>> items;
    private static final int MAX_SLOT = 5;

    /**
     * The constructor of InventoryImpl which initializes empty slot.
     */
    public InventoryImpl() {
        this.items = new ArrayList<>(MAX_SLOT);
        for (int i = 0; i < MAX_SLOT; i++) {
            items.add(Optional.empty());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFull() {
        return this.items.stream().filter(e -> !e.isPresent()).count() > 0 ? false : true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(final Item item) {
        for (int i = 0; i < MAX_SLOT; i++) {
            if (!items.get(i).isPresent()) {
                items.set(i, Optional.of(item));
                return;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeItem(final int index) {
        items.set(index, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Item> getItem(final int index) {
        if (index < MAX_SLOT) {
            return items.get(index);
        }
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Optional<Item>> getAll() {
        return items;
    }

}

