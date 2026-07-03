package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Interfaces.SupplyModel;
import view.dialog.AddSupplyDialog;

public class SupplyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JPanel southPanel;
	private JButton backBtn;
	private JButton addSupplyBtn;
	private View view;
	
	public SupplyPanel(View v){
		this.setLayout(new BorderLayout());
		this.view = v;
		this.view.setTitle("Farm Management - Forniture");
		this.model = new DefaultTableModel(new Object[][] {},
				new String[] { "Fronitore", "Pianta", "N. Piante", "IDFornitura", "Descrizione"});		
		this.scrollpane = new JScrollPane();
		this.scrollpane.setEnabled(false);
		this.table = new JTable(){
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.table.setModel(model);
		this.table.setAutoCreateRowSorter(true);
		this.scrollpane.setViewportView(table);
		this.add(scrollpane, BorderLayout.CENTER);
		this.southPanel = new JPanel(new FlowLayout());
		
		this.backBtn = new JButton("INDIETRO");
		this.backBtn.addActionListener(this);
		this.southPanel.add(backBtn);
		
		this.addSupplyBtn = new JButton("AGGIUNGI FORNITURA");
		this.addSupplyBtn.addActionListener(this);
		this.southPanel.add(addSupplyBtn);
		
		this.add(this.southPanel,BorderLayout.SOUTH);
		this.setSupplies();
	}
	
	private void setSupplies(){
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		for(SupplyModel sm : Controller.getController().getSupplies()){
			Object[] obj = {
					sm.getSupplier().getName(), sm.getPlant().getName(), sm.getNOfPlants(), sm.getSupply(), sm.getSupplyDescription()
			};
			((DefaultTableModel)this.model).addRow(obj);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if(isPressed == this.backBtn){
			this.view.remove(this);
			this.view.add(new NavigationPanel(this.view));
			this.view.pack();
		}
		if(isPressed == this.addSupplyBtn){
			JDialog jd = new AddSupplyDialog();
			jd.setModal(true);
			jd.setVisible(true);
			this.setSupplies();
		}
	}
	
}
