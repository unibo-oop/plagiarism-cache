package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controller.Admin;
import model.Paziente;

public class Patients extends JFrame {
	private static final long serialVersionUID = -3660167643857753660L;
	private static final int NUM_COLS_PATIENTS = 7;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;

	public Patients() {

		try {
			ArrayList<Paziente> list = Admin.getListaPazienti();

			initComponents();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
			addRowToPerformanceTable(model, list);

			this.setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addRowToPerformanceTable(DefaultTableModel model, ArrayList<Paziente> list) {
		Object rowData[] = new Object[NUM_COLS_PATIENTS];
		for (int i = 0; i < list.size(); i++) {
			rowData[0] = list.get(i).getNome();
			rowData[1] = list.get(i).getCognome();
			rowData[2] = list.get(i).getSesso();
			rowData[3] = list.get(i).getLuogoNascita();
			rowData[4] = list.get(i).getDataNascita();
			rowData[5] = list.get(i).getCodicefiscale();
			rowData[6] = list.get(i).getResidenza();
			model.addRow(rowData);
		}

	}

	private void initComponents() {
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		
		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Nome", "Cognome", "Sesso", "Luogo Nascita", "Data Nascita", "Codice Fiscale", "Residenza" }));
		jScrollPane1.setViewportView(jTable1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(new BorderLayout());
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(32, 32, 32)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(61, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(14, Short.MAX_VALUE)));
		pack();
	}
}
