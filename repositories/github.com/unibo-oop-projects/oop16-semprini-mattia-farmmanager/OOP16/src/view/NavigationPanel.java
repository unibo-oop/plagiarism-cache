package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class NavigationPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6617565163207060009L;
	
	private final JButton cultivationButton = new JButton("CAMPI E COLTIVAZIONI");
	private final JButton suppliesButton = new JButton("FORNITURE");
	private final JButton shiftButton = new JButton("TURNI E STIPENDI");
	private final JButton customer = new JButton("CLIENTI E FATTURE");
	private View view;
	
	public NavigationPanel(View v){
		this.setLayout(new FlowLayout());
		this.view = v;
		this.view.setTitle("Farm Management - Navigazione");
		this.cultivationButton.setPreferredSize(new Dimension(200, 100));
		this.cultivationButton.addActionListener(this);
		this.suppliesButton.setPreferredSize(new Dimension(200, 100));
		this.suppliesButton.addActionListener(this);
		this.shiftButton.setPreferredSize(new Dimension(200, 100));
		this.shiftButton.addActionListener(this);
		this.customer.setPreferredSize(new Dimension(200, 100));
		this.customer.addActionListener(this);
		this.add(this.cultivationButton);
		this.add(this.suppliesButton);
		this.add(this.shiftButton);
		this.add(this.customer);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if(isPressed == this.suppliesButton){
			this.view.remove(this);
			this.view.add(new SupplyPanel(this.view));
			this.view.pack();
		}
		if (isPressed == this.cultivationButton){
			this.view.remove(this);
			this.view.add(new CultivationPanel(this.view));
			this.view.pack();
		}
		if(isPressed == this.shiftButton){
			this.view.remove(this);
			this.view.add(new EmployeePanel(this.view));
			this.view.pack();
		}
		if(isPressed == this.customer){
			this.view.remove(this);
			this.view.add(new CustomersPanel(this.view));
			this.view.pack();
		}
	}
	
	

}
