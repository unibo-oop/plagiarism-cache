package bzzbomber.view.menu;

import java.awt.event.KeyListener;

import javax.swing.ImageIcon;

import bzzbomber.controller.Controller;
import bzzbomber.model.ResetType;
import bzzbomber.view.Music;
import bzzbomber.view.hud.HudPanel;

/**
 * This is the interface for the @ViewManagerImpl .
 *
 */
public interface ViewManager {

    /**
     * This method'll used to show @MainMenu .
     */
    void showMainMenu();

    /**
     * This method'll used to show @Game .
     * 
     */
    void showGame();

    /**
     * This method'll used to show @StatisticMenuView .
     */
    void showStatisticMenu();

    /**
     * This method'll used to show @SelectAvatarMenu .
     */
    void showAvatarMenu();

    /**
     * This method'll used to show @InformationMenuView .
     */
    void showInformationMenu();

    /**
     * This method'll used to show @PreferencesMenuView .
     */
    void showPreferencesMenu();

    /**
     * This method'll used to show @WinMenuView .
     */
    void showWinMenu();

    /**
     * This method set controller.
     * 
     * @param c
     *            controller
     */
    void setController(Controller c);

    /**
     * This method setKeyListener .
     * 
     * @param l
     *            key listener
     */
    void setKeyListenerOnCurrentView(KeyListener l);

    /**
     * This method draw gameView .
     */
    void draw();

    /**
     * This method reset current view .
     * 
     * @param rt
     *            is the type of reset.
     */
    void reset(ResetType rt);

    /**
     * This method get @HudPanel .
     * 
     * @return @HudPanel .
     */
    HudPanel getHudPanel();

    /**
     * This method get @Music .
     * 
     * @return @Music .
     */
    Music getMusicClass();

    /**
     * This method set AvatarIcon .
     * 
     * @param avatarIcon
     *            The icon of avatar .
     */
    void setAvatarIcon(ImageIcon avatarIcon);
}
