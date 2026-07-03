package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Implementations.Pair;

import model.Interfaces.InvoiceModel;
import view.dialog.AddInvoiceDialog;

public class CustomerDetailPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField phoneField;
	private JPanel fieldPanel;
	private JPanel tablePanel;
	private String name;
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;
	private JButton addInvoice;
	private JButton backBtn;
	
	public CustomerDetailPanel(String name, View v){
		this.setLayout(new BorderLayout());
		this.name = name;
		
		this.fieldPanel = new JPanel(new GridLayout(0, 2));
		this.fieldPanel.add(new JLabel("Nome"));
		this.nameField = new JTextField();
		this.nameField.setEditable(false);
		this.fieldPanel.add(this.nameField);
		
		this.fieldPanel.add(new JLabel("Indirizzo"));
		this.addressField = new JTextField();
		this.addressField.setEditable(false);
		this.fieldPanel.add(this.addressField);
		
		this.fieldPanel.add(new JLabel("Telefono"));
		this.phoneField = new JTextField();
		this.phoneField.setEditable(false);
		this.fieldPanel.add(this.phoneField);
		
		this.setFields();
		this.add(this.fieldPanel, BorderLayout.WEST);
		
		this.tablePanel = new JPanel(new BorderLayout());
		
		this.model = new DefaultTableModel(new Object[][] {},
				new String[] { "Numero Progressivo", "Data", "Totale Fattura"});
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
		this.tablePanel.add(this.scrollpane, BorderLayout.CENTER);
		this.add(this.tablePanel, BorderLayout.CENTER);
		this.addInvoice = new JButton("Aggiungi Fattura");
		this.addInvoice.addActionListener(e->{
			JDialog jd = new AddInvoiceDialog(this.name);
			jd.setModal(true);
			jd.setVisible(true);
			this.prepareTable();
		});
		this.tablePanel.add(this.addInvoice, BorderLayout.SOUTH);
		this.prepareTable();
		
		this.backBtn = new JButton("INDIETRO");
		this.backBtn.addActionListener(e->{
			v.remove(this);
			v.add(new CustomersPanel(v));
			v.pack();
		});
		this.add(this.backBtn, BorderLayout.SOUTH);
		v.pack();
	}
	
	private void setFields(){
		this.nameField.setText(Controller.getController().getModel().getCustomer(this.name).getName());
		this.addressField.setText(Controller.getController().getModel().getCustomer(this.name).getAddress());
		this.phoneField.setText(Controller.getController().getModel().getCustomer(this.name).getTelephone());
	}
	
	private void prepareTable(){
		if (this.model.getRowCount() > 0) {
		    for (int i = this.model.getRowCount() - 1; i > -1; i--) {
		        ((DefaultTableModel)this.model).removeRow(i);
		    }
		}
		for(Pair<Integer, InvoiceModel> cm : Controller.getController().getInvoice(this.name)){
			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Object[] obj = { cm.getX(), sf.format(cm.getY().getData().getTime()), cm.getY().getTotal() };
			((DefaultTableModel) this.model).addRow(obj);
		}
	}
}
