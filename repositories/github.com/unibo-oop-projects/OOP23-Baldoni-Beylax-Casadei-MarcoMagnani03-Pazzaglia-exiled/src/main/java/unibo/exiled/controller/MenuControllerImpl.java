package unibo.exiled.controller;

import java.util.List;

import unibo.exiled.model.menu.MenuItem;
import unibo.exiled.model.menu.MenuModel;
import unibo.exiled.model.menu.MenuModelImpl;

/**
 * The menu controller implementation.
 */
public final class MenuControllerImpl implements MenuController {
    private final MenuModel menuModel;

    /**
     * The constructor of the implementation of the menu controller.
     */
    public MenuControllerImpl() {
        this.menuModel = new MenuModelImpl();
    }

    @Override
    public List<MenuItem> getNewGameMenuItems() {
        return this.menuModel.getNewGameMenu().getMenuItems();
    }

    @Override
    public List<MenuItem> getInGameMenuItems() {
        return this.menuModel.getInGameMenu().getMenuItems();
    }
}
