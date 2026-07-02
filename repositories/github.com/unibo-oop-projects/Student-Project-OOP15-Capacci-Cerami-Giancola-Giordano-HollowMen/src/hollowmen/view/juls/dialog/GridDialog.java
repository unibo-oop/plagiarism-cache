package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.util.Map;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

import hollowmen.controller.ViewObserver;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.ViewImpl;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code GridDialog} abstract class will be extended from
 * those classes that need to create panels with
 * grid-organized buttons.
 * @author Juls
 *
 */
public abstract class GridDialog extends MenuDialog {

	private static final long serialVersionUID = -7697502946815508802L;
	protected static final int ROWS = 5;
	protected static final int COLUMNS = 1;
	protected static final int HGAP = 3;
	protected static final int VGAP = 3;
	protected static final int X = 50;
	protected static final int Y = 100;
	protected static final int WIDTH = 300;
	protected static final int HEIGHT = 320;
	protected JTextArea statsBox = new JTextArea();
	protected JTextArea desc = new JTextArea();
	protected JTextArea amount = new JTextArea();
	private ImageIcon portrait;
	protected IconButton button;
	protected String nameF, description;
	protected int quantity;
	protected Icon icon;
	protected ViewObserver observer;
	protected ViewImpl view;
	protected Optional<Map<String, Double>> stats;
	private InformationDealer lastItem;
	protected PaintedButton close = new PaintedButton("CLOSE");
	protected JScrollBar scroll = new JScrollBar();
	protected JPanel gridPanel = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();

	public GridDialog(Frame frame) {
		super(frame);
		gridPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		prepareArea(statsBox);
		prepareArea(desc);
		prepareArea(amount);
		
		this.add(gridPanel);
	}
	
	/**
	 * The {@code showStats} method shows on screen information about 
	 * what has been clicked before.
	 * @return - stats
	 */
	protected String showStats(Optional<Map<String, Double>> map) {
		String stats;
		stats = map.get().entrySet().toString();
		return stats;
	}
	
	/**
	 * This method is used to set the InformationDealer on which 
	 * some action (equip/unequip/buy/sell...) will be done.
	 * @param lastItem
	 */
	protected void setLastItem(InformationDealer lastItem) {
		this.lastItem = lastItem;
	}
	
	/**
	 * The method return the last InformationDealer clicked.
	 * @return lastItem
	 */
	protected InformationDealer getLastItem() {
		return lastItem;
	}
	
	/**
	 * This method draws a bigger representation of the image passed as parameter.
	 * @param image
	 */
	protected ImageIcon showImage(ImageIcon image) {
		Image i = image.getImage();
		Image scaled = i.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		portrait = new ImageIcon(scaled);
		return portrait;
	}
	
	/**
	 * This method draws the image passed as parameter in its real dimension.
	 * @param image
	 */
	protected ImageIcon showMobPortrait(ImageIcon image) {
		return this.portrait = image;
	}
	
	/**
	 * This method sets the preferences for a custom version of JTextArea. <br>
	 * The preferences are: <br>
	 * - opacity (false) <br>
	 * - edit (false)<br>
	 * - line wrap (true) <br>
	 * @param t - the JTextArea to custom
	 */
	private JTextArea prepareArea(JTextArea t) {
		t.setEditable(false);
		t.setLineWrap(true);
		t.setOpaque(false);
		t.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		return t;
	}
}
