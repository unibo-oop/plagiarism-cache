package unibo.exiled.model.menu;

/**
 * The implementation of the menu model.
 */
public final class MenuModelImpl implements MenuModel {
    private final Menu inGameMenu;
    private final Menu newGameMenu;

    /**
     * The constructor of the implementation of the model of the menu.
     */
    public MenuModelImpl() {
        this.inGameMenu = new MenuImpl();
        this.newGameMenu = new MenuImpl();

        this.inGameMenu.addMenuItem(new MenuItem("RESUME", Command.CLOSE_MENU));
        this.inGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));

        this.newGameMenu.addMenuItem(new MenuItem("NEW GAME", Command.NEW_GAME));
        this.newGameMenu.addMenuItem(new MenuItem("QUIT", Command.QUIT));
    }

    @Override
    public Menu getNewGameMenu() {
        return this.newGameMenu;
    }

    @Override
    public Menu getInGameMenu() {
        return this.inGameMenu;
    }
}
