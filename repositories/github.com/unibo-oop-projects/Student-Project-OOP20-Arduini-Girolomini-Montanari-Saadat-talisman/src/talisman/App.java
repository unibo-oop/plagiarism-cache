package talisman;


import talisman.util.GameSetupUtil;
import talisman.view.menu.MainMenuWindow;


public final class App {
    private final MainMenuWindow mainMenu;

    private App() {
        this.mainMenu = new MainMenuWindow();
        GameSetupUtil.getSingleton().setClosedListener(this::showMainMenu);
    }

    private void run() {
        this.showMainMenu();
    }

    private void showMainMenu() {
        this.mainMenu.setVisible(true);
    }

    public static void main(final String[] args) {
        new App().run();
    }
}
