package view.dialog;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Implementations.Pair;
import model.Implementations.State;
import model.Interfaces.CultivationModel;

public class AddInvoiceDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel dataPanel;
	private JPanel editPanel;
	private ArrayList<cultivationPanel> productPanel;
	private JComboBox<Integer> day;
	private JComboBox<Integer> Month;
	private JComboBox<Integer> year;
	private JButton addProductBtn;
	private JButton addBtn;
	private String customer;

	public AddInvoiceDialog(String customer) {
		this.setLayout(new GridLayout(0, 1));
		this.customer = customer;

		this.productPanel = new ArrayList<>();

		this.editPanel = new JPanel(new GridLayout(0, 2));
		this.editPanel.add(new JLabel("Data"));
		this.dataPanel = new JPanel(new GridLayout(0, 3));
		this.day = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });
		this.dataPanel.add(this.day);
		this.Month = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		this.dataPanel.add(this.Month);
		this.year = new JComboBox<>(new Integer[] { 2017, 2018, 2019, 2020, 2021 });
		this.dataPanel.add(this.year);
		this.editPanel.add(this.dataPanel);
		this.add(this.editPanel);
		this.addProductBtn = new JButton("Aggiungi Prodotto");
		this.addProductBtn.addActionListener(this);
		this.add(addProductBtn);

		this.addBtn = new JButton("Aggiungi Fattura");
		this.addBtn.addActionListener(this);
		this.add(this.addBtn);
		this.pack();
	}

	/**
	 * this inner class create a new panel used to add a new product
	 * @author sempr
	 *
	 */
	private class cultivationPanel extends JPanel {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private JComboBox<String> cultivation;
		private JTextField weight;
		private JTextField price;

		public cultivationPanel() {
			this.setLayout(new GridLayout(0, 2));

			this.add(new JLabel("Coltivazione"));
			this.cultivation = new JComboBox<>();
			this.add(this.cultivation);

			this.add(new JLabel("Peso"));
			this.weight = new JTextField(5);
			this.add(this.weight);
			this.add(new JLabel("Prezzo/kg"));
			this.price = new JTextField(5);
			this.add(this.price);
			this.prepareCombo();

		}

		private void prepareCombo() {
			for (CultivationModel cm : Controller.getController().getCultivations()) {
				if (cm.getState() == State.HARVESTING) {
					StringBuilder sb = new StringBuilder();
					sb.append(cm.getField().getFieldID());
					sb.append(">");
					sb.append(cm.getSupply().getSupply());
					sb.append(">");
					sb.append(cm.getSupply().getPlant().getName());
					this.cultivation.addItem(sb.toString());
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == this.addProductBtn) {
			cultivationPanel c = new cultivationPanel();
			this.productPanel.add(c);
			this.add(c, this.productPanel.size());
			this.pack();
		}
		if (isPressed == this.addBtn) {
			try {
				Map<CultivationModel, Pair<Integer, Double>> products = new HashMap<>();
				GregorianCalendar g1 = new GregorianCalendar((Integer) this.year.getSelectedItem(),
						((Integer) this.Month.getSelectedItem()) - 1, (Integer) this.day.getSelectedItem());
				for (cultivationPanel c : this.productPanel) {
					if (c.price.getText().isEmpty() || c.weight.getText().isEmpty()) {
						throw new InputMismatchException();
					}
					if(Double.parseDouble(c.price.getText())<0||Integer.parseInt(c.weight.getText())<0){
						throw new InputMismatchException();
					}
					String[] s = ((String) c.cultivation.getSelectedItem()).split(">");
					products.put(Controller.getController().getCultivation(s[0], s[1]), new Pair<Integer, Double>(
							Integer.parseInt(c.weight.getText()), Double.parseDouble(c.price.getText())));
				}
				Controller.getController().addInvoice(this.customer, g1, products);
				this.setVisible(false);
			} catch (NumberFormatException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this,
						"INSERISCI CORRETTAMENTE I NUMERI (nel prezzo inserisci . al posto della ,)", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(this, "ERRORE, RICONTROLLA I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
