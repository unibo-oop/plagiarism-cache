package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.InsertionPanelController;

/**
 * 
 *
 * Da questo pannello si decide una stringa fra una lista di stringhe, con la possibilità di crearne, rimuoverne, o selezionarne
 * una a scelta.
 * 
 * Nel Progetto Piante contro Zombie, le stringhe rappresentano i nomi dei giocatori. 
 * 
 * La classe implementa il pattern mvc e compone la view.
 * La presente è una classe totalmente "stupida", che definisce la sola grafica relegando al controller quasi qualsiasi istruzione
 * di logica.
 *
 * La classe è del tutto riutilizzabile, nonostante sia stata disegnata "ad hoc".
 * 
 * @author Martino De Simoni 
 * 
 */

//In un contesto di sviluppo di più progetti, mi piacerebbe considererei l'eventualità di una classe astratta MultipleChoicePanel
public class UserChoicePanel extends JPanel{
	
	//Campi
	
	//Nomi dei giocatori
	private HashSet<String> choices; //funzionante ma da riconsiderare
	//Gui
	private final BufferedImage background;
	private JList<String> list = null;
	//Bottoni
	private final JButton add = new JButton (Utility.addUser);
	private final JButton remove = new JButton (Utility.removeUser);
	private final JButton select = new JButton (Utility.selectUser);
	//Controller
	private final InsertionPanelController<String,UserChoicePanel> controller;
	//Stringhe per notificare il controller
	private final String selectedMessage;
	private final String removeMessage;
	private final String addMessage;
	

	private static final long serialVersionUID = 1L;
	//Ratio del pannello degli elementi cliccabili rispetto al pannello principale (composto da sfondo e secondo pannello)
	private final double secondPanelRatio = 0.67;
	//Ratio dei due componenti del secondo pannello
	private final double listRatio= 0.67;
	private final double buttonPanelRatio = 1 - listRatio;
	
	//Metodi private
	 
	 private String[] hashSetToOrdinatedArray( final HashSet<String> choices){
	 String [] choicesStringArray = new String[choices.size()];
		int i=0;
		for (String s:choices) choicesStringArray[i++] = s;	
		Arrays.sort(choicesStringArray);
		
		return choicesStringArray;
	 }
	 
	/**
	 * 
	 * Metodo preso da http://www.simplesoft.it/background_image_per_componenti_java_swing.html
	 * 
	 */
	
	
	 protected void paintComponent(final Graphics g) {

		    super.paintComponent(g);
		    
		    g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

	 	}
	
	 // Nota personale: per le JList consultare http://www.java2s.com/Tutorial/Java/0240__Swing/DualListBoxSample.htm
	 
	//Metodi per l'I/O
		public HashSet<String> getChoices() {
			return choices;
		}

		// Metodo per avere input tramite JDialog
		public String inputByDialog( final String title, final String msg ) {
		     
			int messageType = JOptionPane.INFORMATION_MESSAGE;
		    String answer = JOptionPane.showInputDialog(this, msg, title, messageType);
		      return answer;
		 }

		 public void messageByDialog( final String msg ) {

			JOptionPane.showMessageDialog(this, msg);
			    
		 }
			 
		 public JList<String> getList() {
			return list;
		 }

		 public void setChoices(HashSet<String> _choices) {

				this.choices = _choices;
				
			}

	//Costruttore
	public UserChoicePanel (final BufferedImage _background, final HashSet<String> _choices, final InsertionPanelController<String,UserChoicePanel> _controller, 
			final String _selectedMessage, final String _removeMessage, final String _addMessage, final Dimension maxSize){
	
		//Inizializzazione campi
		background = _background;
		controller = _controller;
		setChoices(_choices);
		//Inizializzazioni campi per notificare il controller
		selectedMessage = _selectedMessage;
		removeMessage = _removeMessage;
		addMessage=_addMessage;
		//Inizio addizione componenti

		//Inizializzazione del pannello centrale
		setLayout( new BorderLayout() );
		setPreferredSize( maxSize );
		
		JPanel cliccabili = new JPanel (new GridLayout(2,1) ); //Dio mi perdoni per l'oscenità con cui scelgo le etichette
		
		cliccabili.setPreferredSize( new Dimension ( (int) (this.getPreferredSize().width*secondPanelRatio ), 
				 (int) (this.getPreferredSize().height*secondPanelRatio) ) );
		
		cliccabili.setOpaque(false);
		
		//Inizializzazione dei bottoni
		JPanel buttonPanel = new JPanel(new FlowLayout() ); 
		
		buttonPanel.setOpaque(false);
		
		buttonPanel.add (select);
		buttonPanel.add (add);
		buttonPanel.add (remove);

		add.addActionListener(e-> {
	       controller.notifyController( addMessage );
	      });
		
		remove.addActionListener(e-> {
		   controller.notifyController( removeMessage );
		  });
		
		select.addActionListener(e-> {
		   controller.notifyController( selectedMessage );
		  });
	
		buttonPanel.setPreferredSize( new Dimension ( (int) (cliccabili.getPreferredSize().width*buttonPanelRatio ), 
				(int) (cliccabili.getPreferredSize().height*buttonPanelRatio) ) );
	

		//Inizializzazione del JList
		list = new JList<String>( hashSetToOrdinatedArray(_choices) );
		list.setLayoutOrientation(JList.VERTICAL);
		
		JScrollPane listScroller = new JScrollPane(list);
		
		listScroller.setPreferredSize( new Dimension ( (int) (cliccabili.getPreferredSize().width*listRatio), 
					(int) (cliccabili.getPreferredSize().height*listRatio) ) );

		listScroller.setOpaque(false);

		//add(s)
		cliccabili.add(listScroller);
		cliccabili.add(buttonPanel);
			
		this.add(cliccabili, BorderLayout.CENTER);
			
		
	}

	public void update(final Graphics g){
		
		list.setListData( hashSetToOrdinatedArray(this.choices) );
		super.update(g);
		
	}
	
}