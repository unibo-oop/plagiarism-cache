package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class SalaryPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField nomeField;
	private JTextField surnameField;
	private JTextField cfField;
	private JComboBox<String> monthCombo;
	private JComboBox<Integer> yearCombo;
	private JTextField priceHField;
	private JTextField minutesField;
	private JTextField totSalary;
	private JPanel monthPanel;
	private JPanel dataPanel;
	private JPanel southpanel;
	private JButton calculateSalary;
	private JButton back;
	private String cf;
	private View v;

	public SalaryPanel(String cf, View v) {
		this.setLayout(new BorderLayout());
		this.cf = cf;
		this.v = v;
		
		this.v.setMinimumSize(new Dimension(400, 500));

		this.dataPanel = new JPanel(new GridLayout(0, 2));
		this.dataPanel.add(new JLabel("Nome"));
		this.nomeField = new JTextField();
		this.nomeField.setEditable(false);
		this.dataPanel.add(this.nomeField);

		this.dataPanel.add(new JLabel("Cognome"));
		this.surnameField = new JTextField();
		this.surnameField.setEditable(false);
		this.dataPanel.add(this.surnameField);

		this.dataPanel.add(new JLabel("Codice Fiscale"));
		this.cfField = new JTextField();
		this.cfField.setEditable(false);
		this.dataPanel.add(this.cfField);

		this.dataPanel.add(new JLabel("Prezzo/ORA"));
		this.priceHField = new JTextField();
		this.dataPanel.add(this.priceHField);

		this.dataPanel.add(new JLabel("Mese"));
		this.monthPanel = new JPanel(new GridBagLayout());
		this.monthCombo = new JComboBox<>(new String[] { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
				"Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" });
		this.monthPanel.add(this.monthCombo);
		this.yearCombo = new JComboBox<>(new Integer[] { 2017, 2018, 2019, 2020, 2021 });
		this.monthPanel.add(this.yearCombo);
		this.dataPanel.add(this.monthPanel);

		this.dataPanel.add(new JLabel("Totale Minuti"));
		this.minutesField = new JTextField();
		this.minutesField.setEditable(false);
		this.dataPanel.add(this.minutesField);

		this.dataPanel.add(new JLabel("Totale Stipendio"));
		this.totSalary = new JTextField();
		this.totSalary.setEditable(false);
		this.dataPanel.add(this.totSalary);

		this.add(this.dataPanel, BorderLayout.CENTER);

		this.southpanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.back = new JButton("INDIETRO");
		this.back.addActionListener(this);
		this.calculateSalary = new JButton("CALCOLA");
		this.calculateSalary.addActionListener(this);
		this.southpanel.add(this.back);
		this.southpanel.add(this.calculateSalary);

		this.add(this.southpanel, BorderLayout.SOUTH);

		this.prepareData();

	}

	private void prepareData() {
		this.nomeField.setText(Controller.getController().getModel().getEmployee(cf).getName());
		this.surnameField.setText(Controller.getController().getModel().getEmployee(cf).getSurname());
		this.cfField.setText(cf);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == this.back) {
			v.remove(this);
			v.add(new ShiftPanel(v, cf));
			this.v.setMinimumSize(new Dimension(0, 0));
			v.pack();
		}
		if (isPressed == this.calculateSalary) {
			try {
				this.totSalary.setText(Controller.getController()
						.getSalary(cf,
								new GregorianCalendar((Integer) this.yearCombo.getSelectedItem(),
										this.monthCombo.getSelectedIndex(), 1),
								Double.parseDouble(this.priceHField.getText()))
						.toString());
				this.minutesField.setText(Controller.getController()
						.getSalaryMinutes(cf, new GregorianCalendar((Integer) this.yearCombo.getSelectedItem(),
								this.monthCombo.getSelectedIndex(), 1))
						.toString());
			} catch (NumberFormatException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "IL NUMERO INSERITO E' SBAGLIATO", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			} catch (InputMismatchException e2) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "HAI INSERITO UN VALORE MINORE DI 0", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
