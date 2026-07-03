package maingame.menu.menu;

/**
 * Implementazione interfaccia SimpleFactoryMenu.
 *
 */
public class SimpleFactoryMenuImpl implements SimpleFactoryMenu {

    @Override
    public MenuImpl startMenu() {
        return new MenuImpl(Option.START, Option.COMMAND,
                Option.STATS, Option.EDITOR, Option.DIFF);
    }

    @Override
    public MenuImpl settingsMenu() {
        return new MenuImpl(Option.START, Option.COMMAND, Option.STATS, Option.RESTART);
    }

}
