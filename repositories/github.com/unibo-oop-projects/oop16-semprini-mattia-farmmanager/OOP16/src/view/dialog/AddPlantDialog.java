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

public class AddPlantDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel editPanel;
	private JLabel nameLabel;
	private JTextField nameField;
	private JLabel descriptionLabel;
	private JTextField descriptionField;
	private JLabel nDays;
	private JTextField nDaysField;
	private JButton okBtn;
	
	public AddPlantDialog(AddSupplyDialog dialog) {
		this.setTitle("Aggiungi Pianta");
		this.setLayout(new BorderLayout());
		//this.setSize(new Dimension(400,200));
		this.editPanel = new JPanel(new GridLayout(0, 2));
		
		this.nameLabel = new JLabel("Nome Pianta");
		this.editPanel.add(this.nameLabel);
		
		this.nameField = new JTextField(20);
		this.editPanel.add(this.nameField);
		
		this.descriptionLabel = new JLabel("Descrizione");
		this.editPanel.add(descriptionLabel);
		
		this.descriptionField = new JTextField(20);
		this.editPanel.add(descriptionField);
		
		this.nDays = new JLabel("Durata Ciclo (giorni)");
		this.editPanel.add(nDays);
		
		this.nDaysField = new JTextField(20);
		this.editPanel.add(nDaysField);
		
		this.add(editPanel,BorderLayout.CENTER);
		this.okBtn = new JButton("Aggiungi");
		this.okBtn.addActionListener(e->{
			try{
				if(this.nameField.getText().isEmpty()|| this.descriptionField.getText().isEmpty()|| this.nDaysField.getText().isEmpty()){
					throw new Exception();
				}
				Controller.getController().addToModel(ModelParam.PLANT, this.nameField.getText(), 
						this.descriptionField.getText(), Integer.parseInt(this.nDaysField.getText()));
				dialog.addItemToComboPlant(this.nameField.getText());
				this.dispose();
			}catch(NumberFormatException e1){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI UN NUMERO", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}catch(IllegalArgumentException e2){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "L'INSERIMENTO E' GIA PRESENTE", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}catch (Exception e3){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI TUTTI I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(okBtn, BorderLayout.SOUTH);
		this.pack();
	}

}
