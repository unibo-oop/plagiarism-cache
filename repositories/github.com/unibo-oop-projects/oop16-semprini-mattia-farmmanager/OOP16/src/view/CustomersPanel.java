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
import model.Interfaces.CustomerModel;
import view.dialog.AddCustomerDialog;

public class CustomersPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JButton backButton;
	private JButton addCustomerBtn;
	private JPanel southPannel;
	private View view;

	public CustomersPanel(View v) {
		this.setLayout(new BorderLayout());
		this.view = v;
		this.view.setTitle("Farm Manager - Clienti");
		
		this.model = new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Indirizzo","Telefono" });
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
				CustomersPanel panel = ((CustomersPanel) ((JScrollPane) ((JViewport) ((JTable) e.getSource()).getParent()).getParent())
						.getParent());
				panel.view.remove(panel);
				panel.view.add(new CustomerDetailPanel((String)model.getValueAt(row, 0), panel.view));
				panel.view.pack();
			}
		});
		this.scrollpane.setViewportView(table);
		this.prepareTable();
		this.add(scrollpane, BorderLayout.CENTER);
		
		this.southPannel = new JPanel(new FlowLayout());
		this.backButton = new JButton("INDIETRO");
		this.backButton.addActionListener(this);
		this.addCustomerBtn = new JButton("Aggiungi Cliente");
		this.addCustomerBtn.addActionListener(this);
		this.southPannel.add(this.backButton);
		this.southPannel.add(this.addCustomerBtn);
		
		this.add(this.southPannel, BorderLayout.SOUTH);
	}
	
	private void prepareTable(){
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		for(CustomerModel cm : Controller.getController().getModel().getCustomers()){
			Object[] obj = { cm.getName(),cm.getAddress(), cm.getTelephone() };
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
		if(isPressed == this.addCustomerBtn){
			JDialog jd = new AddCustomerDialog();
			jd.setModal(true);
			jd.setVisible(true);
			this.prepareTable();
		}
	}

}
