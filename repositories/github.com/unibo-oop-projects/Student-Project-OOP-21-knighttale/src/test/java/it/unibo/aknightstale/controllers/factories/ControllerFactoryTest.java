package it.unibo.aknightstale.controllers.factories;

import it.unibo.aknightstale.controllers.interfaces.Controller;
import it.unibo.aknightstale.controllers.interfaces.MainMenuController;
import it.unibo.aknightstale.views.interfaces.MainMenuView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ControllerFactoryTest {

    @Test
    @DisplayName("Test createController()")
    void createController() {
        final var controller = Controller.of(MainMenuController.class).get();
        assertInstanceOf(MainMenuController.class, controller);
    }

    @Test
    @DisplayName("Test createController() with view")
    void createControllerWithView() {
        final var controller = Controller.of(MainMenuController.class, MainMenuView.class).get();
        assertInstanceOf(MainMenuController.class, controller);
    }
}
