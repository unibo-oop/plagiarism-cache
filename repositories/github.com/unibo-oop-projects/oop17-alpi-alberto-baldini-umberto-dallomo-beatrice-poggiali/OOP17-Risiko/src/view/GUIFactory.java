package view;

import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import view.gamemap.WorldGUI;
/**
 * Use of the factory method pattern to creating the graphical interface
 * 
 */
public interface GUIFactory {
	/**
	 * 
	 * @return the main frame
	 */
	JFrame getFrame();
	/**
	 * 
	 * @param The panel containing hud
	 */
	void addPlayerGUI(JPanel panel);
	/**
	 * 
	 * @param The panel containing possible combinations of bonus cards
	 */
	void addCardsGUI(JPanel panel);
	/**
	 * 
	 * @param The panel containing the world map
	 */
	void addMap(JPanel panel);
}
