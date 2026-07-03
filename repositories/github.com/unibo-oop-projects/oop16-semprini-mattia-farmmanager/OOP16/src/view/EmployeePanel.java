package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Interfaces.EmployeeModel;
import view.dialog.AddEmployeeDialog;

public class EmployeePanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JButton backButton;
	private JButton addEmployeeBtn;
	private JPanel southPannel;
	private View view;
	
	public EmployeePanel(View v){
		this.setLayout(new BorderLayout());
		this.view = v;
		this.view.setTitle("Farm Manager - Lavoratori");
		
		this.model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Cognome", "CF",
				"Telefono"});
		this.scrollpane = new JScrollPane();
		this.table = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.table.setModel(this.model);
		this.table.setAutoCreateRowSorter(true);
		this.table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JTable target = (JTable) e.getSource();
				int row = target.getSelectedRow();
				/*ancora una volta non so come fare se non fare questa schifezza*/
				EmployeePanel panel = ((EmployeePanel) ((JScrollPane) ((JViewport) ((JTable) e.getSource()).getParent()).getParent())
						.getParent());
				panel.view.remove(panel);
				panel.view.add(new ShiftPanel(panel.view, (String)model.getValueAt(row, 2)));
				panel.view.pack();
			}
		});
		this.scrollpane.setViewportView(table);
		this.prepareTable();
		
		
		this.southPannel = new JPanel(new FlowLayout());
		this.backButton = new JButton("INDIETRO");
		this.backButton.addActionListener(this);
		this.addEmployeeBtn = new JButton("Aggiungi Lavoratore");
		this.addEmployeeBtn.addActionListener(this);
		this.southPannel.add(this.backButton);
		this.southPannel.add(this.addEmployeeBtn);
		
		
		this.add(scrollpane, BorderLayout.CENTER);
		this.add(this.southPannel, BorderLayout.SOUTH);
		
	}
	
	private void prepareTable(){
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		for(EmployeeModel em : Controller.getController().getModel().getEmployees()){
			Object[] obj = { em.getName(),em.getSurname(),em.getCF(), em.getTelephone() };
			((DefaultTableModel) this.model).addRow(obj);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if(isPressed == this.backButton){
			this.view.remove(this);
			this.view.add(new NavigationPanel(this.view));
			this.view.pack();
		}
		if (isPressed == this.addEmployeeBtn){
			JDialog jd = new AddEmployeeDialog();
			jd.setModal(true);
			jd.setVisible(true);
			this.prepareTable();
		}
	}
	
	
}
