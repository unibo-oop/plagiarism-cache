package view.classes;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.interfaces.IControllerManageArtworkExhibit;
import view.interfaces.IManageArtworkExhibit;

/**
 * The view where the user can manage the artworks of an exhibit. The user can
 * add an artwork from the archive of the artworks to the selected exhibit or
 * can remove an artwork from the selected exhibit. 
 * @author Elisa Casadio
 *
 */

public class ManageArtworkExhibit extends JDialog implements IManageArtworkExhibit {

	private static final long serialVersionUID = 4492710008898682772L;
	private static final String[] PROPS = new String[] {"Cod.", "Titolo",
		"Autore", "Disc.Art.", "Tecn/Mat"};
	private static final Object[][] INIT_DATA = new Object[][] {};
	private static final int TOP_EDGE = 10;
	private static final int FONT_SIZE = 14;
	private static final String FONT_NAME = "Century SchoolBook";
	private static final String ERROR = "ERRORE";
	private static final String NO_SELECTED_LINE_SX = "Selezionare una riga "
			+ "dalla tabella di sinistra per poter cancellare un'opera d'arte"
			+ " dall'esposizione.";
	private static final String NO_SELECTED_LINE_DX = "Selezionare una riga "
			+ "dalla tabella di destra per poter aggiungere un'opera d'arte"
			+ " all'esposizione.";
	
	private final JLabel titleSXLabel = new JLabel("Elenco opere dell'esposizione:");
	private final JLabel titleDXLabel = new JLabel("Elenco opere d'arte:");
	private final DefaultTableModel dtmSx = new DefaultTableModel(INIT_DATA, PROPS);
	private final DefaultTableModel dtmDx = new DefaultTableModel(INIT_DATA, PROPS);
	private final JTable tableSX;
	private final JTable tableDX;
	private final JButton addButton = new JButton(
			new ImageIcon(this.getClass().getResource("/arrow_33x33.png")));
	private final JButton removeButton = new JButton(
			new ImageIcon(this.getClass().getResource("/remove_33x33.png")));
	private final Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE);
	private final Font fontCell = new Font(FONT_NAME, Font.PLAIN, FONT_SIZE);
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	
	private IControllerManageArtworkExhibit controller;
	
	/**
	 * Constructor.
	 * Constructs a view that shows the possible artworks to add to the exhibit.
	 * 
	 * @param frame
	 * 			the frame that this JDialog refers to.
	 */
	public ManageArtworkExhibit(final JFrame frame) {
		super(frame);
		this.setTitle("Opere d'arte");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		final JPanel panel = new JPanel(new FlowLayout());
		
		final GridBagLayout layoutSX = new GridBagLayout();
		final JPanel panelSX = new JPanel(layoutSX);
		this.tableSX = this.setTable(panelSX, layoutSX, this.titleSXLabel,
				this.dtmSx);
		panel.add(panelSX);
		
		final GridBagLayout layoutButton = new GridBagLayout();
		final JPanel panelButton = new JPanel(layoutButton);
		this.adder.addComponent(panelButton, this.addButton, 0, 0, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE, 0, layoutButton);
		this.adder.addComponent(panelButton, this.removeButton, 0, 1, 1, 1,
				GridBagConstraints.NORTH, TOP_EDGE, 0, layoutButton);
		this.addButton.setToolTipText("Aggiungi");
		this.removeButton.setToolTipText("Cancella");
		panel.add(panelButton);
		
		final GridBagLayout layoutDX = new GridBagLayout();
		final JPanel panelDX = new JPanel(layoutDX);
		this.tableDX = this.setTable(panelDX, layoutDX, this.titleDXLabel,
				this.dtmDx);
		panel.add(panelDX);
		
		this.setHandlers();
		this.getContentPane().add(panel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
	}
	
	/**
	 * Sets the table of the view and adds the font to the components.
	 * 
	 * @param panel
	 * 		the panel where the table must be added.
	 * @param layout
	 * 		the layout of the panel.
	 * @param titleTable
	 * 		the title of the table.
	 * @param modelT
	 * 		the model of the table.
	 * 
	 * @return the set table.
	 */
	private JTable setTable(final JPanel panel, final GridBagLayout layout, 
			final JLabel titleTable, final DefaultTableModel modelT) {
		this.adder.addComponent(panel, titleTable, 0, 0, 1, 1,
				GridBagConstraints.NORTHWEST, TOP_EDGE, 0, layout);
		
		final JTable table = new JTable(modelT) {
			private static final long serialVersionUID = 6272702269758796145L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		table.getTableHeader().setReorderingAllowed(false);
		
		final JScrollPane jsp = new JScrollPane(table, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.adder.addComponent(panel, jsp, 0, 1, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, 0, layout);
		
		titleTable.setFont(this.font);
		table.getTableHeader().setFont(this.font);
		table.setFont(this.fontCell);
		
		return table;
	}
	
	/**
	 * Adds the action to each component.
	 */
	private void setHandlers() {
		this.addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableDX.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ManageArtworkExhibit.this, 
							NO_SELECTED_LINE_DX, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandAdd(tableDX.getSelectedRow());
				}
			}
		});
		this.removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				if (tableSX.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(ManageArtworkExhibit.this, 
							NO_SELECTED_LINE_SX, ERROR, JOptionPane.ERROR_MESSAGE);
				} else {
					controller.commandDelete(tableSX.getSelectedRow());
				}
			}
		});	
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				ManageArtworkExhibit.this.setVisible(false);
			}
		});
	}
	
	
	@Override
	public void attachController(final IControllerManageArtworkExhibit ctrl) {
		this.controller = ctrl;
	}
	
	@Override
	public void addData(final boolean isDX, final Object... fields) {
		if (isDX) {
			this.dtmDx.addRow(fields);
		} else {
			this.dtmSx.addRow(fields);
		}
	}
	
	@Override
	public void clearData(final int row) {
		this.dtmSx.removeRow(row);
	}

}
