package view.classes;

import java.awt.Dimension;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.interfaces.IMainView;
import controller.interfaces.IMainViewController;

/**
 * This is the home view of the program, where the user can choose what he 
 * wants to do.
 * @author Sofia Rosetti
 * @author Elisa Casadio
 *
 */

public class MainView extends JFrame implements IMainView {

	private static final long serialVersionUID = 6709954522924991930L;
	private static final int TOP_EDGE = 30;
	private static final int BOTTOM_EDGE = 20;
	private static final int SIZE_FONT = 15;
	private static final String FONT_NAME = "Century SchoolBook";
	private static final String QUIT = "USCITA";
	private static final String QUIT_MESSAGE = "Sei sicuro di voler uscire?";
	
	private final JLabel welcome = new JLabel(
			new ImageIcon(this.getClass().getResource("/logo.png")));
	private final JButton artwork = new JButton("  ARCHIVIO OPERE D'ARTE", 
			new ImageIcon(this.getClass().getResource("/archive_artwork_48x48.png")));
	private final JButton exhibit = new JButton("  ARCHIVIO ESPOSIZIONI", 
			new ImageIcon(this.getClass().getResource("/archive_exhibit_48x48.png")));
	private final JButton ticketOffice = new JButton("  GESTIONE BIGLIETTERIA", 
			new ImageIcon(this.getClass().getResource("/ticket_48x48.png")));
	private final JButton balance = new JButton("   BILANCIO GALLERIA     ", 
			new ImageIcon(this.getClass().getResource("/balance_48x48.png")));
	private final JButton classification = new JButton("  CLASSIFICA ESPOSIZIONI", 
			new ImageIcon(this.getClass().getResource("/classification_48x48.png")));
	private final AdderComponentPanel adder = AdderComponentPanel.getAdder();
	
	private IMainViewController ctrl;
	
	/**
	 * Constructor.
	 */
	public MainView() {
		super("Art Gallery Management");
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		final Font font = new Font(FONT_NAME, Font.BOLD, SIZE_FONT);
		this.artwork.setFont(font);
		this.exhibit.setFont(font);
		this.ticketOffice.setFont(font);
		this.balance.setFont(font);
		this.classification.setFont(font);
				
		final JPanel panel = new BGPanel();
		
		final GridBagLayout layout = new GridBagLayout();
		panel.setLayout(layout);
		
		final Dimension dim = this.classification.getPreferredSize();
		int row = 0;
		
		this.adder.addComponent(panel, this.welcome, 0, row++, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, BOTTOM_EDGE, layout);
		
		this.artwork.setPreferredSize(dim);
		this.adder.addComponent(panel, this.artwork, 0, row++, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, 0, layout);
		
		this.exhibit.setPreferredSize(dim);
		this.adder.addComponent(panel, this.exhibit, 0, row++, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, 0, layout);

		this.ticketOffice.setPreferredSize(dim);
		this.adder.addComponent(panel, this.ticketOffice, 0, row++, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, 0, layout);

		this.balance.setPreferredSize(dim);
		this.adder.addComponent(panel, this.balance, 0, row++, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, 0, layout);

		this.adder.addComponent(panel, this.classification, 0, row, 1, 1, 
				GridBagConstraints.NORTH, TOP_EDGE, BOTTOM_EDGE, layout);
		
		this.setHandlers();
		this.getContentPane().add(panel);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	@Override
	public void attachController(final IMainViewController controller) {
		this.ctrl = controller;
	}
	
	/**
	 * Adds the action to each component of this view.
	 */
	private void setHandlers() {
		this.artwork.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.artworkCmd();
			}
		});
		this.exhibit.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.exhibitCmd();
			}
		});
		this.ticketOffice.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.ticketOfficeCmd();
			}
		});
		
		this.balance.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.balanceCmd();
			}
		});
		
		this.classification.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				ctrl.classificationCmd();
			}
		});
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				MainView.this.quitHandler();
			}
		});
	}
	
	/**
	 * This method shows a message which asks to confirm the quitting act.
	 */
	private void quitHandler() {
		final int n = JOptionPane.showConfirmDialog(this, QUIT_MESSAGE, QUIT,
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			this.ctrl.commandQuit();
		}
	}	
	
}
