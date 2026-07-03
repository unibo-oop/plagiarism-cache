package view.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Implementations.State;
import model.Interfaces.TreatmentModel;

public class CultivationDetailDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel fieldsPanel;
	private JPanel treatmentsPanel;
	private JTextField startDate;
	private JTextField endDate;
	private JTextField nPlants;
	private JTextField idSupply;
	private JTextField idField;
	private JTextArea annotation;
	private JComboBox<model.Implementations.State> StateCombo;
	private JButton okbtn;
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JButton addTreatment;

	public CultivationDetailDialog(String fieldID, String supply) {
		this.setTitle("Dettaglio Coltivazione");
		this.setLayout(new BorderLayout());
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

		this.fieldsPanel = new JPanel(new GridLayout(0, 2));

		this.fieldsPanel.add(new JLabel("Data Inizio"));
		this.startDate = new JTextField();
		this.startDate.setText(
				format.format(Controller.getController().getCultivation(fieldID, supply).getStartDate().getTime()));
		this.startDate.setEditable(false);
		this.fieldsPanel.add(this.startDate);

		this.fieldsPanel.add(new JLabel("Data Fine"));
		this.endDate = new JTextField();
		this.endDate.setText(
				format.format(Controller.getController().getCultivation(fieldID, supply).getEndDate().getTime()));
		this.endDate.setEditable(false);
		this.fieldsPanel.add(this.endDate);

		this.fieldsPanel.add(new JLabel("Numero di piante"));
		this.nPlants = new JTextField();
		this.nPlants.setText(
				((Integer) Controller.getController().getCultivation(fieldID, supply).getNOfPlants()).toString());
		this.nPlants.setEditable(false);
		this.fieldsPanel.add(this.nPlants);

		this.fieldsPanel.add(new JLabel("N. Fornitura"));
		this.idSupply = new JTextField(supply);
		this.idSupply.setEditable(false);
		this.fieldsPanel.add(this.idSupply);

		this.fieldsPanel.add(new JLabel("ID Campo"));
		this.idField = new JTextField(fieldID);
		this.idField.setEditable(false);
		this.fieldsPanel.add(this.idField);

		this.fieldsPanel.add(new JLabel("Annotazioni"));
		this.annotation = new JTextArea(4, 5);
		this.annotation.setText(Controller.getController().getCultivation(fieldID, supply).getAnnotation());
		this.annotation.setEditable(false);
		this.fieldsPanel.add(this.annotation);

		this.fieldsPanel.add(new JLabel("Stato"));
		this.StateCombo = new JComboBox<>(State.values());
		this.StateCombo.setSelectedItem(Controller.getController().getCultivation(fieldID, supply).getState());
		this.fieldsPanel.add(this.StateCombo);

		this.add(this.fieldsPanel, BorderLayout.CENTER);
		this.okbtn = new JButton("OK");
		this.okbtn.addActionListener(e -> {
			try {
				Controller.getController().getCultivation(fieldID, supply)
						.setState((State) this.StateCombo.getSelectedItem());
				this.setVisible(false);

			} catch (IllegalArgumentException e1) {
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "NON SI PUO CAMBIARE LO STATO DI UNA COLTIVAZIONE GIA' TERMINATA",
						"ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.add(this.okbtn, BorderLayout.SOUTH);

		this.treatmentsPanel = new JPanel(new BorderLayout());
		this.model = new DefaultTableModel(new Object[][] {},
				new String[] { "Prodotto", "Data Trattamento", "Giorni Rimanenti", "Descrizione" });
		this.scrollpane = new JScrollPane();
		this.scrollpane.setEnabled(true);
		this.table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.table.setModel(model);
		this.scrollpane.setViewportView(this.table);
		this.table.setAutoCreateRowSorter(true);
		this.treatmentsPanel.add(this.scrollpane, BorderLayout.CENTER);
		this.addTreatments(fieldID, supply);
		this.treatmentsPanel.add(new JLabel("Lista Trattamenti:"), BorderLayout.NORTH);

		this.addTreatment = new JButton("Aggiungi Trattamento");
		this.addTreatment.addActionListener(e->{
			if(StateCombo.getSelectedItem() == State.GROWING){
				JDialog jd = new AddTreatmentDialog(fieldID, supply, this);
				jd.setModal(true);
				jd.setVisible(true);	
				this.refreshTreatments(fieldID, supply);
			}else{
				new JOptionPane();
				JOptionPane.showMessageDialog(this, "NON SI POSSONO AGGIUNGERE TRATTAMENTI A UNA COLTIVAZIONE NON IN CRESCITA",
						"ERRORE", JOptionPane.ERROR_MESSAGE);
			}
		});
		this.treatmentsPanel.add(this.addTreatment, BorderLayout.SOUTH);

		this.add(this.treatmentsPanel, BorderLayout.EAST);
		this.pack();

	}

	private void addTreatments(String fieldID, String supply) {
		for (TreatmentModel tm : Controller.getController().getCultivation(fieldID, supply).getTreatments()) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Object[] obj = { tm.getPhytosanitary().getName(), format.format(tm.getDate().getTime()), tm.getLeftDays(),
					tm.getDescription() };
			((DefaultTableModel) this.model).addRow(obj);
		}
	}
	
	private void refreshTreatments(String fieldID, String supply){
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		this.addTreatments(fieldID, supply);
	}

}
