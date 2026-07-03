package factory;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.scene.FXGLMenu;
import com.almasb.fxgl.scene.SceneFactory;
import com.almasb.fxgl.scene.menu.MenuType;

import launcher.Menu;






/**
 *
 *  @author Daniele
 *
 *	 this class declares GameMenu and MainMenu
 *
 */
public class MenuFactory extends SceneFactory {





	/**
	 * constructor class
     *
     * @param app the GameApplication app we need to launch from menu
     * @return new MainMenu
     *
	 */
	@Override
	public FXGLMenu newMainMenu(GameApplication app) {

		return new Menu(app, MenuType.MAIN_MENU);
	}


   /**
    * constructor class
    *
    *  @param app the GameApplication app we need to launch from menu
    *  @return new GameMenu
    */
    @Override
	public FXGLMenu newGameMenu(GameApplication app) {

		return new Menu(app, MenuType.GAME_MENU);
	}

}