package bzzbomber.view.menu;

import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import bzzbomber.controller.Controller;
import bzzbomber.model.ResetType;
import bzzbomber.view.GamePlayView;
import bzzbomber.view.Music;
import bzzbomber.view.hud.HudPanel;

/**
 * Implementations of the Menu Manager. This class has the reference of all the
 * menus except for the statistics menu that is created every time new to
 * prevent bugs.
 */
public final class ViewManagerImpl implements ViewManager {

    private Controller controller;
    private final InformationMenuView informationMenu;
    private final PreferencesMenuView preferencesMenu;
    private final MainMenuView mainMenu;
    private StatisticMenuView statisticMenu;
    private SelectAvatarMenuView avatarMenu;
    private GamePlayView gameView;

    /**
     * Constructor of ViewManagerImpl.
     */
    public ViewManagerImpl() {
        this.controller = null;
        this.mainMenu = new MainMenuView(this);
        this.informationMenu = new InformationMenuView();
        this.preferencesMenu = new PreferencesMenuView();
    }

    @Override
    public void showMainMenu() {
        this.mainMenu.show();
    }

    @Override
    public void showGame() {
        this.gameView.show();

    }

    @Override
    public void showStatisticMenu() {
        this.statisticMenu.show();
    }

    @Override
    public void showAvatarMenu() {
        this.avatarMenu.show();
    }

    @Override
    public void showPreferencesMenu() {
        this.preferencesMenu.show();

    }

    @Override
    public void showInformationMenu() {
        this.informationMenu.show();
    }

    @Override
    public void showWinMenu() {
        final WinMenuView winMenu;
        this.gameView.hide();
        winMenu = new WinMenuView(this.controller);
        winMenu.show();
        this.getMusicClass().musicWin();
    }

    @Override
    public void setController(final Controller c) {
        this.controller = c;
        this.avatarMenu = new SelectAvatarMenuView(this.controller);
        this.statisticMenu = new StatisticMenuView(this.controller);
        this.gameView = new GamePlayView(this.controller);
    }

    @Override
    public void draw() {
        this.gameView.draw();
    }

    @Override
    public void setKeyListenerOnCurrentView(final KeyListener l) {
        this.gameView.addKeyListener(l);
    }

    @Override
    public void reset(final ResetType rt) {
        if (rt.equals(ResetType.TOTAL)) {
            this.showMainMenu();
        }
        this.gameView.reset(rt);
    }

    @Override
    public HudPanel getHudPanel() {
        return this.gameView.getHudPanel();
    }

    @Override
    public Music getMusicClass() {
        return this.preferencesMenu.getMusicClass();
    }

    @Override
    public void setAvatarIcon(final ImageIcon avatarIcon) {
        this.getHudPanel().setAvatarIcon(avatarIcon);
    }
}
