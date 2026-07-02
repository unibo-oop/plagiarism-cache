package it.unibo.infomanager.infomng.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.infomanager.infomng.view.interfaces.ObserverInterface;
import it.unibo.infomanager.infomng.view.tabelle.PannelloTabelle;
import it.unibo.infomanager.infomng.view.toolbar.MyToolbar;

import java.awt.Toolkit;
/**
 * Classe che definisce viewMagazzino.
 * 
 * @author Alessandro
 *
 */
public class MagazzinoGUI extends InitializeFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9052481395137391704L;
	private JPanel contentPane;
	private PannelloTabelle jPanel = new PannelloTabelle();
	private MyToolbar toolBar;
	private MagazzinoGUI frame = this;
	private static final String TITOLO = "Menu";
	private static final LayoutManager LAYOUT = new BorderLayout();
	private static final Dimension DIMFRAME = new Dimension(488, 512);


/**
 * 	Costruttore del MagazzinGUI frame.
 * @param o
 * 			Oggetto ObserverInterface
 */
	//CHECKSTYLE:OFF: checkstyle:magicnumber    
	public MagazzinoGUI(final ObserverInterface o) {
		super(TITOLO, LAYOUT, DIMFRAME);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(MagazzinoGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		this.setTitle("Magazzino");
		this.toolBar =  new MyToolbar(o, frame);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		this.contentPane.add(toolBar, BorderLayout.NORTH);
		this.contentPane.add(jPanel, BorderLayout.WEST);
		this.setContentPane(contentPane);
	}

}
