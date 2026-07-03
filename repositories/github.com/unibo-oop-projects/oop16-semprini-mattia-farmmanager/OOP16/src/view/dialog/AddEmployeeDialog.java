package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import controller.ModelParam;

public class AddEmployeeDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField cfField;
	private JTextField phoneField;
	private JButton addBtn;
	private JPanel editPanel;

	public AddEmployeeDialog() {
		this.setLayout(new BorderLayout());

		this.editPanel = new JPanel(new GridLayout(0, 2));
		this.editPanel.add(new JLabel("Nome"));
		this.nameField = new JTextField(15);
		this.editPanel.add(this.nameField);

		this.editPanel.add(new JLabel("Cognome"));
		this.surnameField = new JTextField(15);
		this.editPanel.add(this.surnameField);

		this.editPanel.add(new JLabel("Codice Fiscale"));
		this.cfField = new JTextField(20);
		this.editPanel.add(this.cfField);

		this.editPanel.add(new JLabel("Telefono"));
		this.phoneField = new JTextField(10);
		this.editPanel.add(this.phoneField);

		this.addBtn = new JButton("Aggiungi");
		this.addBtn.addActionListener(e -> {
			try {
				if (this.nameField.getText().isEmpty() || this.surnameField.getText().isEmpty()
						|| this.cfField.getText().length() != 16 || this.phoneField.getText().isEmpty()) {
					throw new InputMismatchException();
				} else {
					Controller.getController().addToModel(ModelParam.EMPLOYEES, this.nameField.getText(),
							this.surnameField.getText(), this.phoneField.getText(), this.cfField.getText());
					this.setVisible(false);
				}
			} catch (InputMismatchException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI TUTTI I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch(IllegalArgumentException e2){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ESISTE UN ALTRO LAVORATORE CON GLI STESSI DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.addBtn, BorderLayout.SOUTH);

		this.add(this.editPanel, BorderLayout.CENTER);
		this.pack();
	}

}
