package hollowmen.view.juls;

import hollowmen.enumerators.InputMenu;

/**
 * The interface {@code BasicMenu} allows to draw 
 * really simple game menus.
 * 
 * For example, Main Menu, Class Choice Menu or
 * Help Menu.
 * 
 * @author Juls
 */
public interface BasicMenu {
	/**
	 * This method is used to draw those menus that does not need
	 * anything but name.
	 * 
	 * @param name - the name of the menu that must be drawn
	 */
	public void drawBasicMenu(InputMenu name);

}