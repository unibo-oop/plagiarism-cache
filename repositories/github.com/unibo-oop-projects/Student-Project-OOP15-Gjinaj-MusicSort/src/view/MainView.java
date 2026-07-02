package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.MainController;
import view.WestPanel;
import javax.swing.BoxLayout;

public class MainView {
	
	private JFrame frmMusicsort;
	private JPanel northPanel;
	public SouthPanel southPanel;
	public CentrePanel centrePanel;
	public WestPanel westPanel;
	JPanel cNordCentroWest = new JPanel();
	JPanel cNord_Centro = new JPanel();
	MainController controller;

	/**
	 * instance of  panels.
	 * @param controller MainController
	 */
	public MainView(MainController controller) {
		southPanel = new SouthPanel(controller);
		northPanel = new NorthPanel(controller);
		westPanel= new WestPanel(controller);
		centrePanel = new CentrePanel(controller);
		this.controller= controller;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()  {
		frmMusicsort = new JFrame();
		frmMusicsort.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusicsort.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				controller.getLibraryManager().persistPlaylists();
				}
		});
		
		frmMusicsort.setTitle("musicSort");
		frmMusicsort.setSize(800, 600);
		frmMusicsort.setLocation(50, 100);
		
		
		cNord_Centro.setLayout(new BorderLayout());
		cNord_Centro.add(northPanel, BorderLayout.NORTH);
		cNord_Centro.add(centrePanel, BorderLayout.CENTER);
		
		cNordCentroWest.setLayout(new BorderLayout());
		cNordCentroWest.add(westPanel,BorderLayout.WEST);
		cNordCentroWest.add(cNord_Centro, BorderLayout.CENTER);
		
		
		centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.X_AXIS));
		
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.X_AXIS));
		
		frmMusicsort.getContentPane().add(cNordCentroWest,BorderLayout.CENTER);
		frmMusicsort.getContentPane().add(southPanel,BorderLayout.SOUTH);
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));
		frmMusicsort.setVisible(true);
		frmMusicsort.setMinimumSize(new Dimension(265, 83));
	}
}
