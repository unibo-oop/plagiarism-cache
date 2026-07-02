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
import javax.swing.JButton;

import hollowmen.enumerators.InputCommand;
import hollowmen.enumerators.InputMenu;
import hollowmen.enumerators.Values;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.UtilitySingleton;
import hollowmen.view.juls.buttons.IconButton;
import hollowmen.view.juls.buttons.PaintedButton;
import hollowmen.view.juls.panel.PanelBuilder;

/**
 * The {@code Shop} class represents the Shop Menu drawable on screen.
 * It allows the player to buy/sell items.
 * @author Juls
 */
public class Shop extends TabbedDialog {

	private static final long serialVersionUID = -1975340404777455747L;
	private PaintedButton buy = new PaintedButton("BUY");
	private PaintedButton sell = new PaintedButton("SELL");
	private JLabel label = new JLabel();
	private JLabel gold = new JLabel("GOLD:   " + String.valueOf(Values.GOLD.getValue()));
	private JPanel buttonC = PanelBuilder.getBuilder()
							.layout(1, 3, 30, 0)
							.bound(90, 450, 520, 58)
							.addTo(buy)
							.addTo(sell)
							.addTo(close)
							.build();

	public Shop(Frame frame, Collection<InformationDealer> collection) {
		super(frame);
		this.loadImages();
		super.addTitle(title);
		this.add(buttonC);
		this.setButtonState(false, false);
		super.addMouseListener(dialogL);
		
		buy.addActionListener(listener);
		sell.addActionListener(listener);
		close.addActionListener(listener);

		this.populateTab(collection, "inventory", inventoryP);
		this.populateShopTab(collection, "shop", shopP);
		
		gold.setBounds(420, 50, 100, 50);
		gold.setForeground(Color.BLACK);
		this.add(gold);
		
		label.setBounds(450, 100, 100, 100);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.add(label);
		
		amount.setBounds(420, 230, 70, 20);
		this.add(amount);
		
		desc.setBounds(420, 360, 280, 60);
		this.add(desc);
		
		statsBox.setBounds(420, 280, 280, 50);
		this.add(statsBox);
		
		this.add(tabbedPane);
		this.setVisible(true);
	}
	
	ActionListener listener = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			name = ((JButton) e.getSource()).getText();
			if(name.equals("BUY")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputCommand.BUY, getLastItem());
				dispose();
			} else if (name.equals("SELL")) {
				UtilitySingleton.getInstance().getObserver().addInput(InputCommand.SELL, getLastItem());
				dispose();
			} else {
				UtilitySingleton.getInstance().getObserver().addInput(InputMenu.RESUME);
				dispose();
			}
		}
	};

	@Override
	protected void populateTab(Collection<InformationDealer> c, String tab, JPanel panel) {
		c.stream()	
		.filter(x -> x.getState().equals("UNEQUIPPED"))
		.forEach(x -> {
			icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
			button = new IconButton(icon);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					desc.setText(x.getDescription());
					icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
					setButtonState(false, true);
					setLastItem(x);
					label.setIcon(showImage((ImageIcon) icon));
					statsBox.setText(showStats(x.getStat()));
					amount.setText("Amount:  " + x.getAmount());
				}
			});
			panel.add(button);
		});
		tabbedPane.addTab(tab, panel);
	}

	/**
	 * The method populates the shop tab and sets the availability of the buttons.
	 * @param c
	 * @param tab
	 * @param panel
	 */
	protected void populateShopTab(Collection<InformationDealer> c, String tab, JPanel panel) {
		c.stream()	
		.filter(x -> x.getState().equals("BUYABLE"))
		.forEach(x -> {
			icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
			button = new IconButton(icon);
			button.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					desc.setText(x.getDescription());
					icon = UtilitySingleton.getInstance().getStorage().get(x.getName());
					setButtonState(true, false);
					setLastItem(x);
					label.setIcon(showImage((ImageIcon) icon));
					statsBox.setText(showStats(x.getStat()));
					amount.setText("Amount:  X");
				}
			});
			panel.add(button);
		});
		tabbedPane.addTab(tab, panel);
	}

	@Override
	protected void setButtonState(boolean b, boolean s) {
		buy.setEnabled(b);
		sell.setEnabled(s);	
	}
	
	private void loadImages() {
		title.setIcon(UtilitySingleton.getInstance().getStorage().get("shop"));
	}
}
