package view;
import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import controller.Admin;
import model.Prestazione;

public class Performances extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7033684337462412628L;
    private static final int NUM_COLS_PERFORMANCES = 8;
    private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable jTable1;
	
	public Performances() {

		
        try {
        	
			ArrayList<Prestazione> list = Admin.getListaPrestazioni();
	        
			initComponents();
			DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
	        addRowToPerformanceTable(model, list);
	        
	        this.setVisible(true);
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	
	
	public void addRowToPerformanceTable(DefaultTableModel model, ArrayList<Prestazione> list)
    {     
        Object rowData[] = new Object[NUM_COLS_PERFORMANCES];
        for(int i = 0; i < list.size(); i++)
        {
            rowData[0] = list.get(i).getPaziente();
            rowData[1] = list.get(i).getDottore();
            rowData[2] = list.get(i).getTipo();
            rowData[3] = list.get(i).getData();
            rowData[4] = list.get(i).getOra();
            rowData[5] = list.get(i).getStato();
            rowData[6] = list.get(i).getMacchinario();
            rowData[7] = list.get(i).getAmbulatorio();
            model.addRow(rowData);
        }
                
    }
	
	
	private void initComponents() {
		
		jScrollPane1 = new javax.swing.JScrollPane();
		jTable1 = new javax.swing.JTable();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jTable1.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Paziente", "Dottore", "Tipo", "Data", "Ora", "Stato", "Macchinario", "Ambulatorio" }));
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
