package it.tbt.controller.modelmanager.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.tbt.model.entities.items.Item;
import it.tbt.model.shop.Shop;

/**
 * Shop state class.
 */
public class ShopStateImpl implements ShopState {
    private final Shop shop;
    private int focusParty;
    private int focusShop;
    private boolean selector; // true = party
    private final List<ShopItem> partyItems;
    private final List<ShopItem> shopItems;

    /**
     * Default constructor.
     * @param shop
     */
    @SuppressFBWarnings(
        value = "EI2",
        justification = "ShopStateImpl needs to access the exact instance of the current Shop."
    )
    public ShopStateImpl(final Shop shop) {
        this.shop = shop;
        partyItems = new ArrayList<>(
            shop.getPartyItems().entrySet().stream().map(
                (Map.Entry<Item, Integer> item) -> {
                    return new ShopItem(
                        item.getKey().getName(),
                        item.getValue(), // item count
                        item.getKey().getValue()
                    );

                }
            ).collect(Collectors.toList())
        );
        shopItems = new ArrayList<>(
            shop.getShopInventory().entrySet().stream().map(
                (Map.Entry<Item, Integer> item) -> {
                    return new ShopItem(
                        item.getKey().getName(),
                        item.getValue(), // item count
                        item.getKey().getValue()
                    );

                }
            ).collect(Collectors.toList())
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextElement() {
        if (selector) {
            focusParty = (focusParty + 1) % partyItems.size();
        } else {
            focusShop = (focusShop + 1) % shopItems.size();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void previousElement() {
        if (selector) {
            focusParty = (focusParty - 1) < 0 ? partyItems.size() - 1 : focusParty - 1;
        } else {
            focusShop = (focusShop - 1) < 0 ? shopItems.size() - 1 : focusShop - 1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPartyFocus() {
        return focusParty;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShopFocus() {
        return focusShop;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPartyListFocused() {
        return selector;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToExplore() {
        shop.goToExplore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getPartyItems() {
        return List.copyOf(partyItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShopItem> getShopItems() {
        return List.copyOf(shopItems);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPartyWallet() {
        return shop.getPartyWallet();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getShopWallet() {
        return shop.getShopWallet();
    }

    private int searchItem(final String name, final List<ShopItem> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        if (selector) {
            // party sell to shop
            if (!partyItems.isEmpty() && partyItems.get(focusParty).getCount() > 0) {
                final ShopItem item = partyItems.get(focusParty);
                if (!shop.buy(item.getName())) {
                    final int index = searchItem(item.getName(), shopItems);
                    if (index >= 0) {
                        shopItems.get(index).incCount();
                    } else {
                        shopItems.add(new ShopItem(item));
                    }
                    partyItems.get(focusParty).decCount();
                    if (partyItems.get(focusParty).getCount() <= 0) {
                        partyItems.remove(focusParty);
                    }
                    // reposition the focus
                    if (partyItems.size() <= focusParty) {
                        focusParty = partyItems.size() - 1;
                    }
                }
            }
        } else {
            // party buy from shop
            if (!shopItems.isEmpty() && shopItems.get(focusShop).getCount() > 0) {
                final ShopItem item = shopItems.get(focusShop);
                if (!shop.sell(item.getName())) {
                    final int index = searchItem(item.getName(), partyItems);
                    if (index >= 0) {
                        partyItems.get(index).incCount();
                    } else {
                        partyItems.add(new ShopItem(item));
                    }
                    shopItems.get(focusShop).decCount();
                    if (shopItems.get(focusShop).getCount() <= 0) {
                        shopItems.remove(focusShop);
                    }
                    // reposition the focus
                    if (shopItems.size() <= focusShop) {
                        focusShop = shopItems.size() - 1;
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void toggleList() {
        selector = !selector;
    }
}
