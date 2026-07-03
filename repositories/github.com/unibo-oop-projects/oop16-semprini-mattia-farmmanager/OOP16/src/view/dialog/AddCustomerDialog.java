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

public class AddCustomerDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField addressField;
	private JButton addBtn;
	private JPanel labelPanel;
	private JPanel editPanel;

	public AddCustomerDialog() {
		this.setLayout(new BorderLayout());
		
		this.editPanel = new JPanel(new GridLayout(0, 1));
		this.labelPanel = new JPanel(new GridLayout(0, 1));
		this.labelPanel.add(new JLabel("Nome   "));
		this.nameField = new JTextField(20);
		this.editPanel.add(this.nameField);
		
		this.labelPanel.add(new JLabel("Indirizzo   "));
		this.addressField = new JTextField(40);
		this.editPanel.add(this.addressField);
		
		this.labelPanel.add(new JLabel("Telefono    "));
		this.phoneField = new JTextField();
		this.editPanel.add(this.phoneField);
		
		this.add(this.labelPanel, BorderLayout.WEST);
		this.add(this.editPanel, BorderLayout.CENTER);
		
		this.addBtn = new JButton("Conferma");
		this.addBtn.addActionListener(e->{
			try{
				if(this.nameField.getText().isEmpty()||this.addressField.getText().isEmpty()|| this.phoneField.getText().isEmpty()){
					throw new InputMismatchException();
				}
				Integer.parseInt(this.phoneField.getText());
				Controller.getController().addToModel(ModelParam.CUSTOMER, this.nameField.getText(), this.addressField.getText(), this.phoneField.getText());
				this.setVisible(false);
			}catch(InputMismatchException e1){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI TUTTI I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}catch(NumberFormatException e2){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI CORRETTAMENTE IL NUMERO DI TELEFONO", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}catch(IllegalArgumentException e3){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ESISTE GIA' UN CLIENTE CON LO STESSO NOMINATIVO", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.addBtn, BorderLayout.SOUTH);
		this.pack();
	}
}
