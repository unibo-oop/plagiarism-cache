package it.tbt.model.menu.impl;

import it.tbt.model.fight.api.FightModel;
import it.tbt.model.menu.api.MenuItem;
import it.tbt.model.shop.Shop;
import it.tbt.model.statechange.StateObserver;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class MenuModelImplTest {

    private static final String NAME_1 = "Item 1";
    private static final String NAME_2 = "Item 2";
    private static final String TEST_MENU = "Test Menu";
    @Test
    void testGetTitle() {
        // Create a sample list of menu items
        final List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton(NAME_1));
        items.add(new MenuQuitGameButton(NAME_2));

        // Create a MenuModelImpl instance
        final MenuModelImpl menuModel = new MenuModelImpl(TEST_MENU, items);

        // Assert that the title is correct
        assertEquals(TEST_MENU, menuModel.getTitle());
        assertEquals(items, menuModel.getItems());
    }

    @Test
    void testGetFocus() {
        // Create a sample list of menu items
        final List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton(NAME_1));
        items.add(new MenuQuitGameButton(NAME_2));

        // Create a MenuModelImpl instance
        final MenuModelImpl menuModel = new MenuModelImpl(TEST_MENU, items);

        // Assert that the initial focus is 0
        assertEquals(0, menuModel.getFocus());

        // Set the focus to a different value
        menuModel.setFocus(1);

        // Assert that the focus has been updated
        assertEquals(1, menuModel.getFocus());
    }

    @Test
    void testGetItems() {
        // Create a sample list of menu items
        final List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton(NAME_1));
        items.add(new MenuQuitGameButton(NAME_2));


        // Create a MenuModelImpl instance
        final MenuModelImpl menuModel = new MenuModelImpl(TEST_MENU, items);

        // Get the menu items from the menu model
        final List<MenuItem> retrievedItems = menuModel.getItems();

        // Assert that the retrieved items match the original items
        assertIterableEquals(items, retrievedItems);

    }

    @Test
    void testTriggerExplore() {
        // Create a sample list of menu items
        final List<MenuItem> items = new ArrayList<>();
        items.add(new MenuNewGameButton(NAME_1));
        items.add(new MenuQuitGameButton(NAME_2));

        // Create a MenuModelImpl instance
        final MenuModelImpl menuModel = new MenuModelImpl(TEST_MENU, items);

        // Create a mock StateObserver
        final StateObserverMock observerMock = new StateObserverMock();

        // Set the mock StateObserver on the menu model
        menuModel.setStateObserver(observerMock);


        menuModel.triggerExplore();

        // Assert that the onExplore() method of the mock observer has been called
        assertTrue(observerMock.isExploreCalled());
    }

    // A mock implementation of the StateObserver interface for testing purposes
    private static class StateObserverMock implements StateObserver {
        private boolean exploreCalled;

        @Override
        public void onExplore() {
            exploreCalled = true;
        }

        @Override
        public void onMenu() {

        }

        @Override
        public void onPause() {

        }

        @Override
        public void onFight(final FightModel fightModel) {

        }

        @Override
        public void onInventory() {

        }

        @Override
        public void onShop(final Shop shop) {

        }

        @Override
        public void onEnding(final String message) {

        }

        public boolean isExploreCalled() {
            return exploreCalled;
        }
    }
}
