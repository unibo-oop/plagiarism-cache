package hollowmen.view.juls.dialog;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code Inventory} class draws on screen the relative menu.
 * 
 * @author Juls
 */
public class Inventory extends TabbedDialog {

	private static final long serialVersionUID = 1157519982974148320L;
	private JLabel body = new JLabel();
	private ImageIcon s1 = UtilitySingleton.getInstance().getStorage().get("spell1");
	private ImageIcon s2 = UtilitySingleton.getInstance().getStorage().get("spell2");
	private ImageIcon s3 = UtilitySingleton.getInstance().getStorage().get("spell3");

	private PaintedButton equip = new PaintedButton("EQUIP"); //dentro buttonC
	private PaintedButton unequip = new PaintedButton("UNEQUIP"); //dentro buttonC
	private IconButton head = new IconButton(); //dentro bodyPNorth
	private IconButton chest = new IconButton(); //dentro bodyPNorth
	private IconButton gloves = new IconButton(); //dentro bodyPCenter
	private IconButton rings = new IconButton(); //dentro bodyPCenter
	private IconButton legs = new IconButton(); //dentro bodyPCenter
	private IconButton boots = new IconButton(); //dentro bodyPSouth
	private IconButton weapon = new IconButton(); //dentro pEast
	private IconButton spell1 = new IconButton(s1); //dentro pEast
	private IconButton spell2 = new IconButton(s2); //dentro pEast
	private IconButton spell3 = new IconButton(s3); //dentro pEast
	
	private JPanel buttonC = PanelBuilder.getBuilder()
								.layout(1, 3, 30, 0)
								.bound(90, 460, 520, 58)
								.addTo(equip)
								.addTo(unequip)
								.addTo(close)
								.build();
	private JPanel bodyPNorth = PanelBuilder.getBuilder()
								.layout(2, 0, 0, 20)
								.bound(465, 50, 50, 120)
								.addTo(head)
								.addTo(chest)
								.build();
	private JPanel bodyPCenter = PanelBuilder.getBuilder()
								.layout(1, 3, 10, 0)
								.bound(405, 190, 170, 50)
								.addTo(gloves)
								.addTo(legs)
								.addTo(rings)
								.build();
	private JPanel bodyPSouth = PanelBuilder.getBuilder()
								.layout(1, 0, 0, 0)
								.bound(430, 310, 50, 50)
								.addTo(boots)
								.build();
	private JPanel pEast = PanelBuilder.getBuilder()
								.layout(4, 0, 0, 10)
								.bound(610, 50, 50, 230)
								.addTo(weapon)
								.addTo(spell1)
								.addTo(spell2)
								.addTo(spell3)
								.build();

	public Inventory(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		super.addTitle(title);
		body.setBounds(420, 40, 130, 350);
		this.add(body);
		statsBox.setBounds(550, 300, 140, 50);
		this.add(statsBox);
		this.add(buttonC);
		this.add(bodyPNorth);
		this.add(bodyPCenter);
		this.add(bodyPSouth);
		this.add(pEast);
		this.setButtonState(false, false);
		this.add(tabbedPane);	
		
		equip.addActionListener(paintedL);		
		unequip.addActionListener(paintedL);
		close.addActionListener(paintedL);
		super.addMouseListener(dialogL);

		this.populateTab(collection, "head", headP);
		this.populateTab(collection, "chest", chestP);
		this.populateTab(collection, "gloves", glovesP);
		this.populateTab(collection, "rings", ringsP);
		this.populateTab(collection, "legs", legsP);
		this.populateTab(collection, "boots", bootsP);
		this.populateTab(collection, "weapon", weaponsP);
		this.populateTab(collection, "spells", spellsP);
		this.populateTab(collection, "consumables", consumP);
		
		this.populateBody(collection, "head", head);
		this.populateBody(collection, "chest", chest);
		this.populateBody(collection, "gloves", gloves);
		this.populateBody(collection, "rings", rings);
		this.populateBody(collection, "legs", legs);
		this.populateBody(collection, "boots", boots);
		this.populateBody(collection, "weapon", weapon);
		
		this.setVisible(true);
	}
	
	/**
	 * The {@code populateTab} method fills the different tabs of the menu with the proper items.
	 */
	protected void populateTab(Collection<InformationDealer> c, String slot, JPanel panel) {
		c.stream()
		.filter(x -> x.getSlot().equals(slot))
		.filter(x -> x.getState().equals("UNEQUIPPED"))
		.forEach(x -> {
			icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
			button = new IconButton(icon);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
					setButtonState(true, false);
					setLastItem(x);
					statsBox.setText(showStats(x.getStat()));	
				}
			});
			panel.add(button);
		});
		tabbedPane.addTab(slot, panel);
	}
	
	/**
	 * The {@code populateBody} method fills the body-puppet with the items equipped.
	 */
	private void populateBody(Collection<InformationDealer> c, String slot, IconButton button) {
		c.stream()
		.filter(x -> x.getState().equals("EQUIPPED"))
		.filter(x -> x.getSlot().equals(slot))
		.forEach(x -> {
			icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
			button.setIcon(icon);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
					setButtonState(false, true);
					statsBox.setText(showStats(x.getStat()));
					setLastItem(x);
				}
			});
		});
	}
		
	protected void setButtonState(boolean eq, boolean un) {
		equip.setEnabled(eq);
		unequip.setEnabled(un);
	}

	private void loadImages() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("inventoryT"));
		body.setIcon(UtilitySingleton.getInstance().getStorage().get("bodyTemplate"));
	}
	
	ActionListener paintedL = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("EQUIP")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputCommand.EQUIP, getLastItem());
				dispose();
			} else if (name.equals("UNEQUIP")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputCommand.UNEQUIP, getLastItem());
				dispose();
			} else {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			}
		}
	};
}