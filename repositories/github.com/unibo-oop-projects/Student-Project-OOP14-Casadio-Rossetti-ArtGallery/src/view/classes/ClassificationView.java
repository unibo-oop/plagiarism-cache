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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import view.interfaces.IClassificationView;
import controller.classes.ClassificationController;

/**
 * This is the view containing the classification of the most profitable exhibits.
 * @author Sofia Rosetti
 *
 */
public class ClassificationView extends JFrame implements IClassificationView {

	private static final long serialVersionUID = -3100286873694107747L;
	private static final int GUI_SIZE = 800;
	private static final int FONT_SIZE_18 = 18;
	private static final int FONT_SIZE_14 = 14;
	private static final int EDGE_50 = 50;
	private static final int EDGE_30 = 30;
	private final JLabel title = new JLabel("CLASSIFICA DELLE ESPOSIZIONI");
	private JTable table;
	private final JButton close = new JButton("HOME");
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	private ClassificationController ctrl;
	
	/**
	 * Constructor.
	 */
	public ClassificationView() {
		super("Classification View");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setSize(GUI_SIZE, GUI_SIZE);
		this.setLocationByPlatform(true);
		final Font font = new Font("Century SchoolBook", Font.BOLD, FONT_SIZE_18);
		this.title.setFont(font);
		this.close.setFont(font);
		this.close.setIcon(new ImageIcon(this.getClass().getResource("/home_33x33.png")));
		this.setHandlers();
	}
	
	@Override
	public void attachController(final ClassificationController controller) {
		this.ctrl = controller;
	}
	
	@Override
	public void createTab() {
		this.ctrl.createClassificationStructure();
		final DefaultTableModel tm = this.ctrl.uploadClassification();
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
		
		this.adder.addComponent(panel, this.title, 0, 0, 1, 1, 
				GridBagConstraints.NORTH, 10, 0, layout);
		this.adder.addComponent(panel, new JScrollPane(this.table), 0, 1, 1, 1, 
				GridBagConstraints.NORTH, EDGE_30, 0, layout);
		this.adder.addComponent(panel, this.close, 0, 2, 1, 1, 
				GridBagConstraints.SOUTH, EDGE_50, EDGE_50, layout);
		
		this.getContentPane().add(panel);
		this.setVisible(true);
	}
	
	/**
	 * This method sets the performed action relative to each component.
	 */
	private void setHandlers() {
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
