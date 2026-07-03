package view.dialog;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;
import model.Interfaces.PlantModel;
import model.Interfaces.SupplierModel;

public class AddSupplyDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel editPanel;
	private JLabel plantName;
	private JComboBox<String> plantCombo;
	private JButton addPlantButton;
	private JLabel supplierLabel;
	private JComboBox<String> supplierCombo;
	private JButton addSupplierBtn;
	private JLabel nPlantsLabel;
	private JTextField nPlantField;
	private JLabel supplyIdLabel;
	private JTextField supplyIDField;
	private JLabel descriptionLabel;
	private JTextField descriptionField;
	private JButton okBtn;
	

	public AddSupplyDialog() {
		this.setTitle("Aggiungi Fornitura");
		this.setLayout(new BorderLayout());
		this.editPanel = new JPanel();
		this.editPanel.setLayout(new GridLayout(0, 3));
		this.setSize(new Dimension(500, 250));


		this.plantName = new JLabel("Nome Pianta");
		this.editPanel.add(plantName);

		this.plantCombo = new JComboBox<>();
		this.addToPlantCombo();
		this.editPanel.add(plantCombo);

		this.addPlantButton = new JButton("...");
		this.editPanel.add(addPlantButton);
		this.addPlantButton.addActionListener(this);

		this.supplierLabel = new JLabel("Fornitore");
		this.editPanel.add(supplierLabel);

		this.supplierCombo = new JComboBox<>();
		this.addToSupplierCombo();
		this.editPanel.add(supplierCombo);

		this.addSupplierBtn = new JButton("...");
		this.editPanel.add(addSupplierBtn);
		this.addSupplierBtn.addActionListener(this);

		this.nPlantsLabel = new JLabel("N.Piante");
		this.editPanel.add(nPlantsLabel);

		this.nPlantField = new JTextField();
		this.nPlantField.setColumns(50);
		this.editPanel.add(nPlantField);

		this.editPanel.add(new JLabel(""));

		this.supplyIdLabel = new JLabel("ID Fornitura");
		this.editPanel.add(supplyIdLabel);

		this.supplyIDField = new JTextField();
		this.supplyIDField.setColumns(50);
		this.editPanel.add(supplyIDField);

		this.editPanel.add(new JLabel(""));

		this.descriptionLabel = new JLabel("Descrizione");
		this.editPanel.add(descriptionLabel);

		this.descriptionField = new JTextField();
		this.descriptionField.setColumns(100);
		this.editPanel.add(descriptionField);

		this.add(editPanel, BorderLayout.CENTER);
		this.okBtn = new JButton("CONFERMA");
		this.okBtn.addActionListener(this);
		this.add(okBtn, BorderLayout.SOUTH);

	}

	private void addToPlantCombo() {
		for (PlantModel pm : Controller.getController().getModel().getPlants()) {
			this.plantCombo.addItem(pm.getName());
		}
	}
	
	/**
	 * this method is used to add a plant to this combo
	 * @param item
	 */
	
	public void addItemToComboPlant(String item){
		this.plantCombo.addItem(item);
	}
	

	private void addToSupplierCombo() {
		for (SupplierModel sm : Controller.getController().getModel().getSuppliers()) {
			this.supplierCombo.addItem(sm.getName());
		}
	}
	
	/**
	 * this method is used to add an item to this combo
	 * @param item
	 */
	
	public void addItemToSupplierCombo(String item){
		this.supplierCombo.addItem(item);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if(isPressed == this.okBtn){
			try{
				if(this.supplyIDField.getText().isEmpty()||this.descriptionField.getText().isEmpty()||this.nPlantField.getText().isEmpty() ||
						Integer.parseInt(this.nPlantField.getText())<=0){
					throw new InputMismatchException();
				}
				Controller.getController().addSupply(this.supplierCombo.getSelectedItem().toString(), 
						this.plantCombo.getSelectedItem().toString(), this.supplyIDField.getText(), 
						this.descriptionField.getText(), Integer.parseInt(this.nPlantField.getText()));
				this.setVisible(false);
			}catch (InputMismatchException e1){
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "RIGUARDA I DATI", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
			catch (Exception e2) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "ERRORE - CONTROLLA CHE LA FORNITURA NON SIA GIA INSERITA", "ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (isPressed == this.addPlantButton){
			JDialog jd = new AddPlantDialog(this);
			jd.setModal(true);
			jd.setVisible(true);
		}
		if (isPressed == this.addSupplierBtn){
			System.out.println("cioanesadadsa");
			JDialog jd = new AddSupplierDialog(this);
			jd.setModal(true);
			jd.setVisible(true);
		}
	}

}
