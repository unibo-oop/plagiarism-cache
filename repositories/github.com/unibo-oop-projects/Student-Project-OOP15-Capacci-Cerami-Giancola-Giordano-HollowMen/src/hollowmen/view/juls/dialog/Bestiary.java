package hollowmen.view.juls.dialog;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code Bestiary} class draws on screen a menu that contains
 * information about the enemy met.
 * 
 * @author Juls
 */
public class Bestiary extends GridDialog {

	private static final long serialVersionUID = 9026903745675122006L;
	private IconButton button;
	private JLabel label = new JLabel();
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 0, 0, 0)
							.bound(150, 450, 150, 58)
							.addTo(close)
							.build();

	public Bestiary(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		super.addTitle(title);
		this.populateBestiary(collection);
		this.add(buttonC);
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			}
		});
		label.setBounds(420, 100, 115, 115);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(label);
		statsBox.setBounds(420, 240, 280, 100);
		this.add(statsBox);
		desc.setBounds(420, 365, 280, 60);
		this.add(desc);
		this.setVisible(true);
	}

	/**
	 * The {@code populateBestiary} method fills the Bestiary menu with the enemies met.
	 * @param c - the collection of enemies met
	 */
	private void populateBestiary(Collection<InformationDealer> c) {
		c.stream()	
		.forEach(x -> {
			icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
			button = new IconButton(icon);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					desc.setText(description = x.getDescription());
					icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
					label.setIcon(showMobPortrait((ImageIcon) icon));
					statsBox.setText(showStats(x.getStat()));	
				}
			});
			gridPanel.add(button);
		});
	}
	
	private void loadImages() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("bestiary"));
	}
}
