package it.tbt.model.menu.impl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuFactoryTest {

    @Test
    void testGetMainMenu() {
        // Get the main menu from the MenuFactory
        final MenuModelImpl mainMenu = MenuFactory.getMainMenu();

        // Assert that the menu title is correct
        assertEquals("Main Menu", mainMenu.getTitle());

        // Assert that the menu contains the expected number of items
        assertEquals(2, mainMenu.getItems().size());

        // Assert that the menu items are of the correct type
        assertTrue(mainMenu.getItems().get(0) instanceof MenuNewGameButton);
        assertTrue(mainMenu.getItems().get(1) instanceof MenuQuitGameButton);
    }

    @Test
    void testGetPauseMenu() {
        // Get the pause menu from the MenuFactory
        final MenuModelImpl pauseMenu = MenuFactory.getPauseMenu();

        // Assert that the menu title is correct
        assertEquals("Pause", pauseMenu.getTitle());

        // Assert that the menu contains the expected number of items
        assertEquals(2, pauseMenu.getItems().size());

        // Assert that the menu items are of the correct type
        assertTrue(pauseMenu.getItems().get(0) instanceof MenuNewGameButton);
        assertTrue(pauseMenu.getItems().get(1) instanceof MenuQuitGameButton);
    }
}
