package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controller.Controller;
import model.Interfaces.ShiftModel;
import view.dialog.AddShiftDialog;

public class ShiftPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel dataPanel;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField cfField;
	private JTextField phoneField;

	private JPanel shiftPanel;
	private JPanel tablePanel;
	private JPanel filterPanel;
	private JComboBox<String> monthField;
	private JTextField yearField;
	private JButton applyFilter;
	private JTable table;
	private TableModel model;
	private JScrollPane scrollpane;

	private JButton okBtn;
	private JButton addShiftBtn;
	private JButton salaryBtn;

	private JPanel southPanel;
	private String cf;
	private View v;

	public ShiftPanel(View v, String cf) {
		this.setLayout(new BorderLayout());
		this.v = v;
		this.v.setTitle("Farm Manager - Dettaglio Lavoratore");
		this.cf = cf;

		this.dataPanel = new JPanel(new GridLayout(0, 2));
		this.dataPanel.add(new JLabel("Nome"));
		this.nameField = new JTextField(16);
		this.nameField.setEditable(false);
		this.dataPanel.add(nameField);

		this.dataPanel.add(new JLabel("Cognome"));
		this.surnameField = new JTextField();
		this.surnameField.setEditable(false);
		this.dataPanel.add(this.surnameField);

		this.dataPanel.add(new JLabel("Codice Fiscale"));
		this.cfField = new JTextField();
		this.cfField.setEditable(false);
		this.dataPanel.add(this.cfField);

		this.dataPanel.add(new JLabel("Telefono"));
		this.phoneField = new JTextField();
		this.phoneField.setEditable(false);
		this.dataPanel.add(this.phoneField);

		this.add(this.dataPanel, BorderLayout.WEST);

		this.shiftPanel = new JPanel(new BorderLayout());
		this.shiftPanel.add(new JLabel("Turni"), BorderLayout.NORTH);

		this.tablePanel = new JPanel(new BorderLayout());
		this.filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

		this.monthField = new JComboBox<>(new String[] { "", "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio",
				"Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre" });
		this.filterPanel.add(this.monthField);
		this.yearField = new JTextField(10);
		this.filterPanel.add(this.yearField);
		this.applyFilter = new JButton("Applica");
		this.applyFilter.addActionListener(this);
		this.filterPanel.add(this.applyFilter);
		this.tablePanel.add(this.filterPanel, BorderLayout.NORTH);

		this.shiftPanel.add(this.tablePanel, BorderLayout.CENTER);

		this.addShiftBtn = new JButton("Aggiungi Turno");
		this.addShiftBtn.addActionListener(this);
		this.shiftPanel.add(this.addShiftBtn, BorderLayout.SOUTH);

		this.model = new DefaultTableModel(new Object[][] {},
				new String[] { "Data", "Ora Inizio", "Ora Fine", "Descrizione", "Minuti" });
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
		this.tablePanel.add(this.scrollpane);
		this.setTable(cf, String.valueOf(this.monthField.getSelectedIndex()), this.yearField.getText());

		this.add(this.shiftPanel, BorderLayout.CENTER);

		this.southPanel = new JPanel(new FlowLayout());
		this.okBtn = new JButton("OK");
		this.okBtn.addActionListener(this);
		this.southPanel.add(this.okBtn);
		this.salaryBtn = new JButton("Calcola Stipendio");
		this.salaryBtn.addActionListener(this);
		this.southPanel.add(this.salaryBtn);
		this.add(this.southPanel, BorderLayout.SOUTH);

		this.setFields(cf);
	}

	private void setFields(String cf) {
		this.nameField.setText(Controller.getController().getModel().getEmployee(cf).getName());
		this.surnameField.setText(Controller.getController().getModel().getEmployee(cf).getSurname());
		this.cfField.setText(cf);
		this.phoneField.setText(Controller.getController().getModel().getEmployee(cf).getTelephone());
	}

	private void setTable(String cf) {
		for (ShiftModel sm : Controller.getController().getShifts(cf)) {
			SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
			Object[] obj = { format.format(sm.getData().getTime()), sm.getStartHour(), sm.getEndHour(),
					sm.getDescription(), sm.getMinutes() };
			((DefaultTableModel) this.model).addRow(obj);
		}
	}

	private void setTable(String cf, String month, String year) {
		if (this.model.getRowCount() > 0) {
			for (int i = this.model.getRowCount() - 1; i > -1; i--) {
				((DefaultTableModel) this.model).removeRow(i);
			}
		}
		if (month.equals("0") || year.equals("")) {
			this.setTable(cf);
		} else {
			for (ShiftModel sm : Controller.getController().getShifts(cf)) {
				try{
				if (sm.getData().get(Calendar.YEAR) == Integer.parseInt(year)&&sm.getData().get(Calendar.MONTH) == (Integer.parseInt(month)-1)) {
					SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
					Object[] obj = { format.format(sm.getData().getTime()), sm.getStartHour(), sm.getEndHour(),
							sm.getDescription(), sm.getMinutes() };
					((DefaultTableModel) this.model).addRow(obj);
				}
				}catch(NumberFormatException e){
					new JOptionPane();
					JOptionPane.showMessageDialog(this, "IL NUMERO INSERITO E' SBAGLIATO", "ERRORE", JOptionPane.ERROR_MESSAGE);
					this.setTable(cf);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object isPressed = e.getSource();
		if (isPressed == this.okBtn) {
			this.v.remove(this);
			this.v.add(new EmployeePanel(this.v));
			this.v.pack();
		}
		if(isPressed == this.applyFilter){
			this.setTable(cf, String.valueOf(this.monthField.getSelectedIndex()), this.yearField.getText());
		}
		if (isPressed == this.addShiftBtn){
			JDialog jd = new AddShiftDialog(this.cf);
			jd.setModal(true);
			jd.setVisible(true);
			this.setTable(cf, "0", "");
		}
		if (isPressed == this.salaryBtn){
			this.v.remove(this);
			this.v.add(new SalaryPanel(cf, v));
			this.v.pack();
		}
	}

}
