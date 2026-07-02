package view.classes;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

import controller.interfaces.IControllerArtwork;
import view.interfaces.IArtworkView;

/**
 * This view where the user can see the artworks that are or have been in the 
 * art gallery and allows him to add, modify or delete them.
 * @author Elisa Casadio
 *
 */

public class ArtworkView extends JFrame implements IArtworkView {
	
	private static final long serialVersionUID = 7984094585956376155L;
	private static final Object[][] INIT_DATA = new Object[][] {};
	private static final String[] PROPS = new String[] {"Codice", "Titolo", 
		"Autore", "Anno", "Disc.Art.", "Tecn/Mat", "Dimensioni"};
	private static final String FONT_NAME = "Century SchoolBook";
	private static final String ERROR = "ERRORE";
	private static final String NO_LINE_SELECTED_EDIT = "Selezionare"
			+ " una riga della tabella per poter modificare un'opera d'arte.";
	private static final String NO_LINE_SELECTED_SHOW = "Selezionare"
			+ " una riga della tabella per poter vedere i dati di un'opera d'arte.";
	private static final String NO_LINE_SELECTED_DELETE = "Selezionare"
			+ " una riga della tabella per poter cancellare un'opera d'arte.";
	private static final int FONT_SIZE = 14;
	
	private final DefaultTableModel dtm = new DefaultTableModel(INIT_DATA, PROPS);
	private final JTable tableArt;
	private final JButton home = new JButton("  Home", 
			new ImageIcon(this.getClass().getResource("/home_33x33.png")));
	private final JButton addArt = new JButton("  Nuovo", 
			new ImageIcon(this.getClass().getResource("/add_33x33.png")));
	private final JButton editArt = new JButton("  Modifica", 
			new ImageIcon(this.getClass().getResource("/edit_33x33.png")));
	private final JButton showArt = new JButton("  Mostra", new 
			ImageIcon(this.getClass().getResource("/show_33x33.png")));
	private final JButton deleteArt = new JButton("  Elimina", 
			new ImageIcon(this.getClass().getResource("/delete_33x33.png")));
	
	private IControllerArtwork controller;
	
	/**
	 * Constructor.
	 * Constructs a table where the artworks are shown.
	 */
	public ArtworkView() {
		super("Archivio opere d'arte");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		final JPanel panel = new JPanel(new BorderLayout());
		
		final JPanel buttonPanel = new JPanel();
		final FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);		
		buttonPanel.setLayout(layout);
		buttonPanel.add(this.home);
		buttonPanel.add(new JSeparator(JSeparator.VERTICAL));
		buttonPanel.add(this.addArt);
		buttonPanel.add(this.editArt);
		buttonPanel.add(this.showArt);
		buttonPanel.add(this.deleteArt);	
		
		this.tableArt = new JTable(this.dtm) {
			private static final long serialVersionUID = -1443749897958314652L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		this.tableArt.getTableHeader().setReorderingAllowed(false);
		final JScrollPane jsp = new JScrollPane(this.tableArt, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel.add(buttonPanel, BorderLayout.NORTH);
		panel.add(jsp, BorderLayout.CENTER);
		
		final Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
		this.home.setFont(font);
		this.addArt.setFont(font);
		this.editArt.setFont(font);
		this.showArt.setFont(font);
		this.deleteArt.setFont(font);
		this.tableArt.getTableHeader().setFont(font);
		this.tableArt.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE));
		
		this.setHandlers();
		
		this.getContentPane().add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/**
	 * Adds the action to each component of this view.
	 */
	private void setHandlers() {
		this.home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandClose(ArtworkView.this);
			}
		});
		this.addArt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				controller.commandNew(ArtworkView.this);
			}
		});
		this.editArt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableArt.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ArtworkView.this, 
							NO_LINE_SELECTED_EDIT, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandEdit(tableArt.getSelectedRow(), ArtworkView.this);
				}
				
			}
		});
		this.showArt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableArt.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ArtworkView.this, 
							NO_LINE_SELECTED_SHOW, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandShow(tableArt.getSelectedRow(), ArtworkView.this);
				}
			}
		});
		this.deleteArt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableArt.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ArtworkView.this, 
							NO_LINE_SELECTED_DELETE, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandDelete(tableArt.getSelectedRow(), ArtworkView.this);
				}
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				controller.commandClose(ArtworkView.this);
			}
		});
	}
	
	
	@Override
	public void attachController(final IControllerArtwork ctrl) {
		this.controller = ctrl;
	}
	
	@Override
	public void addData(final Object... field) {
		this.dtm.addRow(field);
	}
	
	@Override
	public void editData(final int row, final Object... field) {
		this.dtm.removeRow(row);
		this.dtm.insertRow(row, field);
	}
	
	@Override
	public void clearData(final int row) {
		this.dtm.removeRow(row);
	}

}
