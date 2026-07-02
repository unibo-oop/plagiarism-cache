package view.classes;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.interfaces.ITicketOfficeView;
import model.classes.Discounts;
import controller.classes.TicketOfficeController;

/**
 * This is the view where the purchase and the payments for tickets can be managed.
 * @author Sofia Rosetti
 *
 */
public class TicketOfficeView extends JFrame implements ITicketOfficeView {

	private static final long serialVersionUID = -6429600782407568259L;
	private static final int EDGE_50 = 50;
	private static final int EDGE_20 = 20;
	private static final int TOTAL_ROWS = 7;
	private static final int FONT_SIZE_15 = 15;
	private static final int FONT_SIZE_18 = 18;
	private static final int FONT_SIZE_14 = 14;
	private JTable table;
	private final JComboBox<String> perc = new JComboBox<String>();
	private final JLabel ticketOffice = new JLabel("BIGLIETTERIA");
	private final JLabel discount = new JLabel("Sconto: ");
	private final JLabel tickets = new JLabel("   Numero biglietti: ");
	private final JLabel total = new JLabel("Totale: ");
	private final JTextField textFieldTickets = new JTextField();
	private final JLabel totalImport = new JLabel();
	private final JButton confirm = new JButton("CONFERMA");
	private final JButton close = new JButton("HOME");
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	private TicketOfficeController ctrl;
	
	
	/**
	 * Constructor.
	 */
	public TicketOfficeView() {
		super("Ticket Office View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationByPlatform(true);
		final Font font = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_15);
		final Font fontTotal = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_18);
		this.ticketOffice.setFont(fontTotal);
		this.discount.setFont(font);
		this.tickets.setFont(font);
		this.textFieldTickets.setFont(font);
		this.total.setFont(fontTotal);
		this.totalImport.setFont(fontTotal);
		this.confirm.setFont(font);
		this.textFieldTickets.setColumns(3);
		
		for (final Discounts d: Discounts.values()) {
			this.perc.addItem(d.getDescription());
		}
		
		this.perc.setFont(font);
		this.confirm.setIcon(new ImageIcon(this.getClass().getResource("/ok_23x23.png")));
		this.close.setIcon(new ImageIcon(this.getClass().getResource("/home_33x33.png")));
		this.close.setFont(font);	
		this.setHandlers();
	}
	
	@Override
	public void attachController(final TicketOfficeController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		final DefaultTableModel tm = this.ctrl.uploadExhibits();
		this.table = new JTable(tm) {
			private static final long serialVersionUID = -1443749897958314652L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setFont(new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_14));
		this.table.setFont(new Font("Century SchoolBook", Font.PLAIN, FONT_SIZE_14));
		this.buildLayout();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * This method builds the view's layout using a GridBagLayout.
	 */
	private void buildLayout() {
		final GridBagLayout layout = new GridBagLayout();
		final JPanel panel = new JPanel();
		panel.setLayout(layout);
		
		int row = 0;
		this.adder.addComponent(panel, this.ticketOffice, 0, row++, 3, 1, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, new JScrollPane(this.table), 0, row++, 1, TOTAL_ROWS, 
				GridBagConstraints.NORTH, EDGE_20, 0, layout);
		this.adder.addComponent(panel, this.discount, 1, row, 1, 2, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.perc, 2, row++, 1, 2, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.tickets, 1, ++row, 1, 1, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, this.textFieldTickets, 2, row++, 1, 1, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, this.total, 1, row, 1, 1, 
				GridBagConstraints.SOUTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.totalImport, 2, row++, 1, 1, 
				GridBagConstraints.SOUTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.confirm, 1, row++, 2, 1, 
				GridBagConstraints.SOUTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.close, 1, row, 2, 1, 
				GridBagConstraints.SOUTH, EDGE_50, 100, layout);
		
		this.getContentPane().add(panel);
		this.setVisible(true);
	}
	
	/**
	 * This method shows a new ErrorDialog which asks to select a table row.
	 */
	private void showErrorDialog() {
		JOptionPane.showMessageDialog(this, "Seleziona una riga nella tabella", 
				"Errore", JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public void updateTotal(final double newTotal) {
		this.totalImport.setText("€ " + newTotal);
	}
	
	/**
	 * This method sets the performed action relative to each component.
	 */
	private void setHandlers() {
		this.confirm.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					ctrl.commandConfirm((String) table.getModel().getValueAt(table.getSelectedRow(), 0), 
							perc.getSelectedItem().toString(), textFieldTickets.getText(), 
							(double) table.getModel().getValueAt(table.getSelectedRow(), 1));
				} catch (ArrayIndexOutOfBoundsException exc) {
					showErrorDialog();					
				}
			}
		});
		
		this.close.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.commandClose();
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(final WindowEvent e) {
				ctrl.commandClose();
			}
		});
	}

}
