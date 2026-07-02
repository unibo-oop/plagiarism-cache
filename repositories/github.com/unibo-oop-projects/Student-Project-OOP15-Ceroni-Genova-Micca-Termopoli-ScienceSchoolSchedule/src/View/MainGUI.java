package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import Controller.Reservation;

/**
 * The main GUI that contains all the principal panels.
 * The frame size is get from the ScreenSize.
 * 
 * @author Anna Termopoli
 * 
 *         Modify by Galya Genova, Massimiliano Micca
 * 
 */
public class MainGUI implements MainGUIInterface {

	private JFrame frame = new JFrame("Science School Schedule");
	private PanelButton panelButton = new PanelButton(this);
	private PanelTable panelTable = new PanelTable();

	public MainGUI() {

		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final int width = (int) screenSize.getWidth();
		final int height = (int) screenSize.getHeight() - 50;

		this.frame.setSize(width, height);
		this.frame.add(panelButton.getPanelButton(), BorderLayout.WEST);
		this.frame.add(panelTable.getPanelTable());
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setResizable(false);
		this.frame.setVisible(true);
		this.frame.repaint();

	}

	public void update(Reservation s, Integer row, Integer colum) {
		this.panelTable.setNew(s, row, colum);
	}

	public void removeRes(Integer row, Integer colum) {
		this.panelTable.remove(row, colum);
	}

	public void resetTable() {
		this.panelTable = new PanelTable();
	}

}
