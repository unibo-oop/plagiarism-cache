package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
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
import model.Interfaces.CultivationModel;
import model.Interfaces.FieldModel;
import model.Interfaces.SupplyModel;

public class AddCultivationDialog extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel editPanel;
	private JPanel dataPanel;
	private JComboBox<String> supplyCombo;
	private JComboBox<String> fieldCombo;
	private JButton addFieldBtn;
	private JComboBox<Integer> day;
	private JComboBox<Integer> Month;
	private JComboBox<Integer> year;
	private JTextField nOfPlants;
	private JTextArea annotationField;
	private JComboBox<model.Implementations.State> State;
	private JButton addBtn;

	public AddCultivationDialog() {
		this.setTitle("Aggiungi Coltivazione");
		this.setLayout(new BorderLayout());
		this.editPanel = new JPanel(new GridLayout(0, 3));

		this.editPanel.add(new JLabel("Fornitura"));
		this.supplyCombo = new JComboBox<>();
		this.addSupplyToCombo();
		this.editPanel.add(supplyCombo);
		this.editPanel.add(new JLabel(""));

		this.editPanel.add(new JLabel("Campo"));
		this.fieldCombo = new JComboBox<>();
		this.addtoFieldCombo();
		this.editPanel.add(this.fieldCombo);
		this.addFieldBtn = new JButton("...");
		this.addFieldBtn.addActionListener(this);
		this.editPanel.add(this.addFieldBtn);

		this.editPanel.add(new JLabel("Data"));
		this.dataPanel = new JPanel(new GridBagLayout());
		this.day = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
				20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31 });
		this.dataPanel.add(this.day);
		this.Month = new JComboBox<>(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12 });
		this.dataPanel.add(this.Month);
		this.year = new JComboBox<>(new Integer[] {2017,2018,2019,2020,2021});
		this.dataPanel.add(this.year);
		this.editPanel.add(this.dataPanel);
		this.editPanel.add(new JLabel(""));

		this.editPanel.add(new JLabel("Numero di Piante"));
		this.nOfPlants = new JTextField(10);
		this.editPanel.add(this.nOfPlants);
		this.editPanel.add(new JLabel(""));

		this.editPanel.add(new JLabel("Annotazioni"));
		this.annotationField = new JTextArea(4, 20);
		this.annotationField.setLineWrap(true);
		this.editPanel.add(new JScrollPane(this.annotationField));
		this.editPanel.add(new JLabel(""));

		this.editPanel.add(new JLabel("Stato"));
		this.State = new JComboBox<>(model.Implementations.State.values());
		this.editPanel.add(this.State);

		this.addBtn = new JButton("AGGIUNGI");
		this.add(this.addBtn, BorderLayout.SOUTH);
		this.addBtn.addActionListener(this);

		this.add(this.editPanel, BorderLayout.CENTER);
		this.pack();

	}

	private void addSupplyToCombo() {
		boolean flag = false;
		for (SupplyModel sm : Controller.getController().getSupplies()) {
			flag = false;
			for(CultivationModel cm : Controller.getController().getCultivations()){
				if(cm.getSupply().getSupply().equals(sm.getSupply())){
					flag = true;
				}
			}
			if (flag == false){
				StringBuilder s = new StringBuilder(sm.getSupply());
				s.append(">");
				s.append(sm.getPlant().getName());
				this.supplyCombo.addItem(s.toString());
			}
		}
	}

	private void addtoFieldCombo() {
		for (FieldModel fm : Controller.getController().getModel().getFields()) {
			this.fieldCombo.addItem(fm.getFieldID());
		}
		
	}
	
	/**
	 * this method is used to add an item to the combo
	 * @param item
	 */
	public void addItemToFieldCombo(String item){
		this.fieldCombo.addItem(item);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == this.addBtn) {
			try {
				Calendar c2 = new GregorianCalendar((Integer)this.year.getSelectedItem(),
						((Integer)this.Month.getSelectedItem())-1, (Integer) this.day.getSelectedItem());
				if(Integer.parseInt(this.nOfPlants.getText())<=0){
					throw new InputMismatchException();
				}
				String [] s = ((String)this.supplyCombo.getSelectedItem()).split(">");
				Controller.getController().addCultivation(c2, Integer.parseInt(this.nOfPlants.getText()),
						s[0], (String) this.fieldCombo.getSelectedItem(),
						this.annotationField.getText(), (model.Implementations.State) this.State.getSelectedItem());
				this.setVisible(false);

			} catch (NumberFormatException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "INSERISCI CORRETTAMENTE I NUMERI", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e2) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this,
						"ESISTE UN'ALTRA COLTIVAZIONE NELLO STESSO CAMPO NON ANCORA TERMINATA", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			} catch (InputMismatchException e3) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this,
						"ERRORE - RICONTROLLA I DATI", "ERRORE",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if(isPressed == this.addFieldBtn){
			JDialog jd = new AddFieldDialog(this);
			jd.setModal(true);
			jd.setVisible(true);
		}
	}

}
