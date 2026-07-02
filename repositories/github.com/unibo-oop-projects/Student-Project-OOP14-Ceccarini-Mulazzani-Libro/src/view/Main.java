package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import utilities.ControllerUtilities.TipoController;
import utilities.GUIUtilities;
import controller.BookController;
import controller.IBookController;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public class Main implements ActionListener {

    private JPanel cardHolder;
    private final JButton magazzino = new JButton("Magazzino");
    private final JButton ordini = new JButton("Ordini");
    private final JButton fatturato = new JButton("Fatturato e guadagni");
    private final JButton cartasoci = new JButton("Carta Soci");
    private final JButton statistiche = new JButton("Statistiche");
    private final IBookController controller = BookController.getIstance();
 
  /**
   * 
   * @return the CardHolder JPanel
   */
    public JPanel createCardHolderPanel() {
    	cardHolder = new JPanel();
    	cardHolder.setLayout(new CardLayout());
        cardHolder.setBorder(BorderFactory.createTitledBorder("Scegli l'azione"));
        cardHolder.add(createWareHousePanel(), "Magazzino");
        cardHolder.add(createOrderPanel(), "Ordini");
        cardHolder.add(createEconomyPanel(), "Fatturato e Guadagni");
        cardHolder.add(createFidelityPanel(), "Carta Soci");
        cardHolder.add(createStatisticPanel(), "Statistiche");
        
        return cardHolder;
    }
    
    
    private JPanel createEconomyPanel() {
    	
    	return new	EconomyMenuGUI().getPane();
    }
    
    private JPanel createStatisticPanel() {
    	
    	return new StatisticGUI().getPane();
    }

    private JPanel createWareHousePanel() {


    	return new MagazGUI().getPane();
    }
    
    private JPanel createOrderPanel() {
    	

    	return new OrdGUI().getPane();
    }
    
    private JPanel createFidelityPanel() {
    	
    	return new FidelityCardGUI().getPane();
    }
     
    /**
     * 
     * @return the Button JPanel
     */
    public JPanel createButtonPanel() {
        final JPanel buttonPanel = new JPanel(new GridLayout(5, 0, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Scegli la sezione"));
        buttonPanel.add(magazzino);
        buttonPanel.add(ordini);
        buttonPanel.add(statistiche); 
        buttonPanel.add(fatturato);
        buttonPanel.add(cartasoci);
        magazzino.addActionListener(this);
        ordini.addActionListener(this);
        fatturato.addActionListener(this);
        cartasoci.addActionListener(this);
        statistiche.addActionListener(this);
        return buttonPanel;
    }
    /**
     * 
     * @return the main ContentPane
     */
    public JPanel createContentPane() {
    	final JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Menù principale"));
        panel.add(createButtonPanel(), BorderLayout.WEST);
        panel.add(createCardHolderPanel(), BorderLayout.CENTER);
        return panel;
    }
    /**
     * 
     * @param frame is the JFrame
     * @return the JMenuBar
     */
    public JMenuBar createMenuBar(final JFrame frame) {
    	final JMenuBar menuBar = new JMenuBar();
        menuBar.add(new FileTabMenuGUI(frame));
        return menuBar;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final CardLayout cardLayout = (CardLayout) (cardHolder.getLayout());
        if (e.getSource() == magazzino) {
        	controller.setType(TipoController.MAGAZZINO);
            cardLayout.show(cardHolder, "Magazzino");
        }
        if (e.getSource() == ordini) {
        	controller.setType(TipoController.ORDINI);
            cardLayout.show(cardHolder, "Ordini");
        }
        if (e.getSource() == fatturato) {
        	controller.setType(TipoController.MAGAZZINO);
        	cardHolder.repaint();
            cardLayout.show(cardHolder, "Fatturato e Guadagni");
        }
        if (e.getSource() == cartasoci) {
            cardLayout.show(cardHolder, "Carta Soci");
        }
        if (e.getSource() == statistiche) {
        	cardLayout.show(cardHolder, "Statistiche");
        }
    }
    /**
     * 	THE MAIN METHOD OF THE SOFTWARE!
     */
    public static void createAndShowGUI() {
        final JFrame frame = new JFrame("Libro di Chiara Ceccarini e Alberto Mulazzani");
        try {
			//UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
        	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  	
        } catch (Exception e) {	
			e.printStackTrace();
		} 
        
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        frame.addWindowListener(new WindowAdapter() {
        	  @Override
        	  public void windowClosing(final WindowEvent we) { 
        	    final String[] objButtons = {"Sì", "No"};
        	    final int promptResult = JOptionPane.showOptionDialog(null, 
        	        "Sei sicuro di voler uscire? I dati non salvati saranno persi", "Sei proprio sicuro?", 
        	        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
        	        objButtons, objButtons[1]);
        	    if (promptResult == 0) {
        	      System.exit(0);          
        	    }
        	  }
        	});    
        
        frame.setResizable(false);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    final int x = (dim.width + dim.width / 3) / 3;
	    final int y = (dim.height + dim.height / 6) / 2;
	    frame.setPreferredSize(new Dimension(x, y));
        final Main main = new Main();
        frame.setJMenuBar(main.createMenuBar(frame));
        frame.add(main.createContentPane());
        frame.setIconImage(GUIUtilities.getCommonImage());
        frame.pack();
        
        frame.setVisible(true);
    }
    
    

}