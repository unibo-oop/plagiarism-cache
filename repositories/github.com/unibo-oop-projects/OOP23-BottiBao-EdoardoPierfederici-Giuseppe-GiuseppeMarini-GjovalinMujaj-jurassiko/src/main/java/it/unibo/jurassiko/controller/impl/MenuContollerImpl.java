package it.unibo.jurassiko.controller.impl;

import it.unibo.jurassiko.controller.api.MainController;
import it.unibo.jurassiko.controller.api.MenuController;

/**
 * Implementation of {@link MenuController} interface.
 */
public class MenuContollerImpl implements MenuController {

    private final MainController mainContr = new MainControllerImpl();

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        mainContr.openView();
        mainContr.startGameLoop();
    }
}
