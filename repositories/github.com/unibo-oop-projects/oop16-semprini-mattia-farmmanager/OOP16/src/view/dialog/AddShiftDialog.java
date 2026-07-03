package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Controller;

public class AddShiftDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField cfEmployee;
	private JPanel editorPanel;
	private JComboBox<Integer> day;
	private JComboBox<String> month;
	private JComboBox<Integer> year;
	private JPanel datapanel;
	private JTextField startH;
	private JTextField endH;
	private JTextField startM;
	private JTextField endM;
	private JPanel startPanel;
	private JPanel endPanel;
	private JTextArea desc;
	private JButton okBtn;
	private String cf;

	public AddShiftDialog(String cf) {
		this.setLayout(new BorderLayout());

		this.editorPanel = new JPanel(new GridLayout(0, 2));
		this.editorPanel.add(new JLabel("CF Lavoratore"));
		this.cfEmployee = new JTextField(Controller.getController().getModel().getEmployee(cf).getCF());
		this.cfEmployee.setEditable(false);
		this.editorPanel.add(this.cfEmployee);
		this.cf = cf;

		this.editorPanel.add(new JLabel("Data"));
		this.datapanel = new JPanel(new GridBagLayout());
		this.day = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });
		this.datapanel.add(this.day);
		this.month = new JComboBox<>(new String[] { "", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
				"Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" });
		this.datapanel.add(this.month);
		this.year = new JComboBox<>(new Integer[] { 2017, 2018, 2019, 2020, 2021 });
		this.datapanel.add(this.year);
		this.editorPanel.add(this.datapanel);

		this.editorPanel.add(new JLabel("Ora / Minuto inizio"));
		this.startPanel = new JPanel(new GridBagLayout());
		this.startH = new JTextField(3);
		this.startM = new JTextField(3);
		this.startPanel.add(this.startH);
		this.startPanel.add(this.startM);
		this.editorPanel.add(this.startPanel);

		this.editorPanel.add(new JLabel("Ora / Minuto inizio"));
		this.endPanel = new JPanel(new GridBagLayout());
		this.endH = new JTextField(3);
		this.endM = new JTextField(3);
		this.endPanel.add(this.endH);
		this.endPanel.add(this.endM);
		this.editorPanel.add(this.endPanel);

		this.editorPanel.add(new JLabel("Descrizione"));
		this.desc = new JTextArea(4, 20);
		this.desc.setLineWrap(true);
		this.editorPanel.add(new JScrollPane(this.desc));

		this.add(this.editorPanel, BorderLayout.CENTER);
		this.okBtn = new JButton("Conferma");
		this.okBtn.addActionListener(e -> {
			try {
				this.checkFields();
				Integer day = (Integer) this.day.getSelectedItem();
				Integer month = this.month.getSelectedIndex()-1;
				Integer year = (Integer) this.year.getSelectedItem();

				Controller.getController().addShift(new GregorianCalendar(year, month, day),
						new GregorianCalendar(year, month, day, Integer.parseInt(this.startH.getText()),
								Integer.parseInt(this.startM.getText())),
						new GregorianCalendar(year, month, day, Integer.parseInt(this.endH.getText()),
								Integer.parseInt(this.endM.getText())),
						this.desc.getText(), this.cf);
				this.setVisible(false);
			} catch (InputMismatchException e1) {
				JOptionPane.showMessageDialog(this, "COMPLETA CORRETTAMENTE TUTTI I CAMPI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(this, "INSERISCI SOLO NUMERI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e2) {
				if (e2.getMessage().equals("end date before start date")) {
					JOptionPane.showMessageDialog(this, "DATA FINE PRECEDENTE A QUELLA DI INIZIO", "ERRORE",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "QUESTO TURNO E' GIA STATO INSERITO", "ERRORE",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.add(this.okBtn, BorderLayout.SOUTH);
		this.pack();
	}

	private void checkFields() {
		if (this.month.getSelectedIndex() == 0 || this.startH.getText().isEmpty() || this.startM.getText().isEmpty()
				|| this.endH.getText().isEmpty() || this.endM.getText().isEmpty()) {
			throw new InputMismatchException();
		}
		if (Integer.parseInt(this.startH.getText()) > 24 || Integer.parseInt(this.endH.getText()) > 24
				|| Integer.parseInt(this.startM.getText()) > 60 || Integer.parseInt(this.endM.getText()) > 60) {
			throw new InputMismatchException();
		}
		if (Integer.parseInt(this.startH.getText()) < 0 || Integer.parseInt(this.endH.getText()) < 0
				|| Integer.parseInt(this.startM.getText()) < 0 || Integer.parseInt(this.endM.getText()) < 0) {
			throw new InputMismatchException();
		}
	}

}
