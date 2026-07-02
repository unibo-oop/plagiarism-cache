package view.classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.interfaces.IControllerExhibit;
import view.interfaces.IExhibitView;

/**
 * The view where the user can see the list of exhibits and where allows him to
 * add, modify and delete them and add the exhibit's artworks.
 * @author Elisa Casadio
 *
 */

public class ExhibitView extends JFrame implements IExhibitView {

	private static final long serialVersionUID = 8645740806974401230L;
	private static final String[] PROPS = new String[] {"Codice", "Titolo", 
		"Curatore/i", "Data inizio", "Data fine", "Num. pezzi", 
		"Costo esposizione", "Costo biglietto"};
	private static final Object[][] INIT_DATA = new Object[][] {};
	private static final int N_COLUMN_PIECES = 5;
	private static final int FONT_SIZE = 14;
	private static final String FONT_NAME = "Century SchoolBook";
	private static final String DATE_FORMAT = "dd/MM/yyyy";
	private static final String ERROR = "ERRORE";
	private static final String NO_LINE_SELECTED_EDIT = "Selezionare una riga"
			+ " della tabella per poter modificare un'esposizione.";
	private static final String NO_LINE_SELECTED_DELETE = "Selezionare una riga"
			+ " della tabella per poter cancellare un'esposizione.";
	private static final String NO_LINE_SELECTED_ARTW = "Selezionare una riga"
			+ " della tabella per gestire le opere d'arte di un'esposizione.";
	
	private final DefaultTableModel dtm = new DefaultTableModel(INIT_DATA, PROPS);
	private final JTable tableEx;
	private final JButton homeButton = new JButton("  Home", 
			new ImageIcon(this.getClass().getResource("/home_33x33.png")));
	private final JButton addButton = new JButton("  Nuovo", 
			new ImageIcon(this.getClass().getResource("/add_33x33.png")));
	private final JButton editButton = new JButton("  Modifica", 
			new ImageIcon(this.getClass().getResource("/edit_33x33.png")));
	private final JButton deleteButton = new JButton("  Elimina", 
			new ImageIcon(this.getClass().getResource("/delete_33x33.png")));
	private final JButton artworkButton = new JButton("  Opere d'arte", 
			new ImageIcon(this.getClass().getResource("/artwork_33x33.png")));
	
	private IControllerExhibit controller;
	
	/**
	 * Constructor.
	 * Constructs a table where the exhibits are shown.
	 */
	public ExhibitView() {
		super("Archivio esposizioni");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		final JPanel panel = new JPanel(new BorderLayout());
		
		final JPanel buttonPanel = new JPanel();
		final FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);		
		buttonPanel.setLayout(layout);
		buttonPanel.add(this.homeButton);
		buttonPanel.add(new JSeparator(JSeparator.VERTICAL));
		buttonPanel.add(this.addButton);
		buttonPanel.add(this.editButton);
		buttonPanel.add(this.deleteButton);
		buttonPanel.add(new JSeparator(JSeparator.VERTICAL));
		buttonPanel.add(this.artworkButton);
		
		this.tableEx = new JTable(this.dtm) {
			private static final long serialVersionUID = -1443749897958314652L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		this.tableEx.getTableHeader().setReorderingAllowed(false);
		
		final JScrollPane jsp = new JScrollPane(this.tableEx, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(jsp, BorderLayout.CENTER);

		final Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		this.homeButton.setFont(font);
		this.addButton.setFont(font);
		this.editButton.setFont(font);
		this.deleteButton.setFont(font);
		this.artworkButton.setFont(font);
		this.tableEx.getTableHeader().setFont(font);
		this.tableEx.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
		
		this.setHandlers();
		
		this.getContentPane().add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Adds the action to each component.
	 */
	private void setHandlers() {		
		this.homeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandClose(ExhibitView.this);				
			}
		});
		this.addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandNew(ExhibitView.this);
			}
		});
		this.editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {				
				if (tableEx.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ExhibitView.this, 
							NO_LINE_SELECTED_EDIT, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandEdit(tableEx.getSelectedRow(), ExhibitView.this);
				}
			}
		});
		this.deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableEx.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ExhibitView.this, 
							NO_LINE_SELECTED_DELETE, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandDelete(tableEx.getSelectedRow(), ExhibitView.this);
				}
			}
		});
		this.artworkButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableEx.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ExhibitView.this, 
							NO_LINE_SELECTED_ARTW, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandArtwork(tableEx.getSelectedRow(), ExhibitView.this);
				}
				
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				controller.commandClose(ExhibitView.this);
			}
		});
	}
	
	
	@Override
	public void attachController(final IControllerExhibit ctrl) {
		this.controller = ctrl;
	}
	
	@Override
	public void addData(final Long code, final String title, 
			final String curator, final Calendar dateB, final Calendar dateE,
			final int nPieces, final double costEx, final double costTicket) {
		this.dtm.addRow(new Object[] {code, title, curator, 
				new SimpleDateFormat(DATE_FORMAT, Locale.ITALY).format(dateB.getTime()),
				new SimpleDateFormat(DATE_FORMAT, Locale.ITALY).format(dateE.getTime()),
				nPieces, costEx, costTicket});
	}
	
	@Override
	public void editData(final int row, final Long code, final String title,
			final String curator, final Calendar dateB, final Calendar dateE,
			final int nPieces, final double costEx, final double costTicket) {
		this.dtm.removeRow(row);
		this.dtm.insertRow(row, new Object[] {code, title, curator, 
				new SimpleDateFormat(DATE_FORMAT, Locale.ITALY).format(dateB.getTime()),
				new SimpleDateFormat(DATE_FORMAT, Locale.ITALY).format(dateE.getTime()),
				nPieces, costEx, costTicket});
	}
	
	@Override
	public void editNPiecesCell(final int nPieces, final int row) {
		this.dtm.setValueAt(nPieces, row, N_COLUMN_PIECES);
	}
	
	@Override
	public void clearData(final int row) {
		this.dtm.removeRow(row);
	}

}
