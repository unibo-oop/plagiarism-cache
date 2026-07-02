package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import hollowmen.model.facade.InformationDealer;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code TabbedDialog} abstract class will be extended from
 * those classes that need to have some JTabbedPanes.
 * 
 * @author Juls
 */
public abstract class TabbedDialog extends GridDialog {

	private static final long serialVersionUID = 6838744487705502656L;
	protected JTabbedPane tabbedPane = new JTabbedPane();
	protected JPanel headP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel chestP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel glovesP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel ringsP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel legsP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel bootsP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel weaponsP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel spellsP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel consumP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel inventoryP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	protected JPanel shopP = PanelBuilder.getBuilder()
								.layout(ROWS, COLUMNS, HGAP, VGAP)
								.bound(X, Y, WIDTH, HEIGHT)
								.build();
	
	public TabbedDialog(Frame frame) {
		super(frame);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		tabbedPane.setForeground(Color.WHITE);
		tabbedPane.setBounds(X, Y, WIDTH, HEIGHT);
		tabbedPane.setOpaque(false);
		this.addMouseListener(dialogL);
	}

	/**
	 * The {@code populateTab} method will populate the tab with the corresponding InformationDealer.
	 * @param c - collection of InofrmationDealer (= item)
	 * @param s - string representing the slot
	 * @param panel - the panel on which the item must be added
	 */
	protected abstract void populateTab(Collection<InformationDealer> c, String s, JPanel panel);

	/**
	 * The {@code setButton} state allows to determines the enable state of a couple
	 * of button, for instance, EQUIP & UNEQUIP.
	 * @param b1 - first button
	 * @param b2 - second button
	 */
	protected abstract void setButtonState(boolean b1, boolean b2);
	
	MouseListener dialogL = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			setButtonState(false, false);
		}
	};
}
