package view.classes;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import view.interfaces.IBalanceView;
import controller.classes.BalanceController;

/**
 * This view shows every exhibit of the current year with the related income.
 * Moreover it is possible to choose a previous year and see it's balance.
 * @author Sofia Rosetti
 *
 */
public class BalanceView extends JFrame implements IBalanceView {

	private static final long serialVersionUID = -1897550428533305715L;
	private static final int EDGE_50 = 50;
	private static final int EDGE_5 = 5;
	private static final int EDGE_30 = 30;
	private static final int TOTAL_ROWS = 7;
	private static final int FONT_SIZE_18 = 18;
	private static final int FONT_SIZE_14 = 14;
	private static final String FONT_NAME = "Century SchoolBook";
	private JTable table;
	private final JLabel title = new JLabel("BILANCIO " 
	+ Calendar.getInstance().get(Calendar.YEAR));
	private final JButton currentTotal = new JButton("Totale " 
	+ Calendar.getInstance().get(Calendar.YEAR));
	private final JLabel currentTotalImport = new JLabel("€ ");
	private final JLabel text1 = new JLabel("      Inserisci un anno compreso tra il 2002 e il " 
									+ (Calendar.getInstance().get(Calendar.YEAR) - 1) + "     ");
	private final JLabel text2 = new JLabel("    e premi 'Cerca' per visualizzarne il saldo. ");
	private final JTextField year = new JTextField();
	private final JButton search = new JButton("Cerca");
	private final JLabel specificImport = new JLabel("€ ");
	private final JButton close = new JButton("HOME");	
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	private BalanceController ctrl;
	
	/**
	 * Constructor.
	 */
	public BalanceView() {
		super("Balance View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setLocationByPlatform(true);
		final Font font = new Font(FONT_NAME, Font.BOLD, FONT_SIZE_18);
		final Font font1 = new Font(FONT_NAME, Font.ITALIC, 15);
		this.title.setFont(font);
		this.currentTotal.setFont(font);
		this.currentTotalImport.setFont(font);
		this.text1.setFont(font1);
		this.text2.setFont(font1);
		this.search.setFont(font);
		this.specificImport.setFont(font);
		this.year.setColumns(EDGE_5);
		this.year.setFont(font);
		this.close.setFont(font);
		this.close.setIcon(new ImageIcon(this.getClass().getResource("/home_33x33.png")));
		this.setHandlers();
	}
	
	@Override
	public void attachController(final BalanceController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		final DefaultTableModel tm = this.ctrl.uploadTable();
		this.table = new JTable(tm) {
			private static final long serialVersionUID = -1443749897958314652L;

			@Override
			public boolean isCellEditable(final int row, final int column) {
				return false;
			}
		};
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE_14));
		this.table.setFont(new Font(FONT_NAME, Font.PLAIN, FONT_SIZE_14));
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
		this.adder.addComponent(panel, this.title, 0, row++, 4, 1, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, new JScrollPane(this.table), 0, row, 1, TOTAL_ROWS, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, this.currentTotal, 1, row, 1, 2, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.currentTotalImport, 2, row++, 1, 2, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.text1, 1, ++row, 2, 1, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.text2, 1, ++row, 2, 1, 
				GridBagConstraints.NORTH, EDGE_5, 0, layout);
		this.adder.addComponent(panel, this.year, 1, ++row, 1, 1, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.search, 2, row++, 1, 1, 
				GridBagConstraints.NORTH, EDGE_50, 0, layout);
		this.adder.addComponent(panel, this.specificImport, 1, row++, 3, 1, 
				GridBagConstraints.NORTH, EDGE_30, 0, layout);
		this.adder.addComponent(panel, this.close, 1, row, 3, 1, 
				GridBagConstraints.SOUTH, EDGE_50, EDGE_50, layout);
		
		this.getContentPane().add(panel);
		this.setVisible(true);
	}
	
	@Override
	public void updateTotal(final double newTotal) {
		this.specificImport.setText("€ " + newTotal);
	}
	
	/**
	 * This method sets the performed action relative to each component.
	 */
	private void setHandlers() {
		this.currentTotal.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				currentTotalImport.setText("€ " + ctrl.computeProfit());
			}
		});
		
		this.search.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.commandSearch(year.getText());
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
