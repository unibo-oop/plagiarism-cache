package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import controller.ModelParam;

public class AddSupplierDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel editPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel addressLabel;
	private JTextField addressField;
	private JLabel telephoneLabel;
	private JTextField telephoneField;
	private JButton okBtn;

	public AddSupplierDialog(AddSupplyDialog dialog){
		this.setTitle("Aggiungi Fornitore");
		this.setLayout(new BorderLayout());
		this.editPanel = new JPanel(new GridLayout(0, 2));
		
		this.nameLabel = new JLabel("Nome");
		this.editPanel.add(this.nameLabel);
		
		this.nameField = new JTextField(20);
		this.editPanel.add(this.nameField);
		
		this.addressLabel = new JLabel("Indirizzo");
		this.editPanel.add(this.addressLabel);
		
		this.addressField = new JTextField(20);
		this.editPanel.add(this.addressField);
		
		this.telephoneLabel = new JLabel("Telefono");
		this.editPanel.add(this.telephoneLabel);
		
		this.telephoneField = new JTextField(20);
		this.editPanel.add(this.telephoneField);
		
		this.add(editPanel, BorderLayout.CENTER);
		
		this.okBtn = new JButton("CONFERMA");
		this.okBtn.addActionListener(e->{
			try{
				if(this.nameField.getText().isEmpty() || this.addressField.getText().isEmpty()|| this.telephoneField.getText().isEmpty()){
					throw new Exception();
				}
				Controller.getController().addToModel(ModelParam.SUPPLIER, this.nameField.getText(), this.addressField.getText(), this.telephoneField.getText());
				
				dialog.addItemToSupplierCombo(this.nameField.getText());
				
				this.dispose();
			}catch(IllegalArgumentException e1){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "L'INSERIMENTO E' GIA PRESENTE", "ERRORE", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI TUTTI I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.okBtn, BorderLayout.SOUTH);
		this.pack();
		
	}
	
	
}
