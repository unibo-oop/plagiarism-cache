package hollowmen.view.juls;

import hollowmen.enumerators.InputMenu;
import hollowmen.view.SingletonFrame;
import hollowmen.view.juls.dialog.ClassChoice;
import hollowmen.view.juls.dialog.DifficultyMenu;
import hollowmen.view.juls.dialog.HelpMenu;
import hollowmen.view.juls.dialog.PauseMenu;

/**
 * The {@code BasicMenuImpl} class represents the concrete
 * implementation of {@link BasicMenu}. It allows to draw "basic" menus on screen.
 * @author Juls
 */
public class BasicMenuImpl implements BasicMenu {

	public BasicMenuImpl() {}
	
	@Override
	public void drawBasicMenu(InputMenu name) {
		switch (name) {
		
		case MAIN:
			new MainMenu();
			break;
			
		case CLASS:
			new ClassChoice(SingletonFrame.getInstance());
			break;
			
		case DIFFICULTY:
			new DifficultyMenu(SingletonFrame.getInstance());
			break;
			
		case HELP:
			new HelpMenu(SingletonFrame.getInstance());
			break;
			
		case PAUSE:
			new PauseMenu(SingletonFrame.getInstance());
			break;
			
		default:
			break;
		
		}	
	}
}
