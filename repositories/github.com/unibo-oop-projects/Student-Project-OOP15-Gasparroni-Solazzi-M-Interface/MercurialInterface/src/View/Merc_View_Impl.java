package View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Control;

/**
 * Classe View principale del programma
 * in cui vengono visualizzati tutti i bottoni per le varie operazioni
 *
 * @author Filippo Solazzi
 *
 */
public class Merc_View_Impl extends JFrame implements Merc_View{

	private static final long serialVersionUID = -8840270355536191957L;
	private Control ctr;
	private List<JButton> bList;
	private File repo;
	final JLabel repoChosen = new JLabel();
	final JLabel repoClone = new JLabel();
	final GridBagConstraints  cnst = new  GridBagConstraints ();
	private ActionListener myListen;

		/**
		* Costruttore che inizializza la propria View
		* con i rispettivi eventi
		*
		* @param username
		*          Stringa contenente l'username dell'utente
		*/
		public Merc_View_Impl(String username){
		
		this.setTitle("Mercurial Interface - Utente = " + username);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setSize(600,135);
		this.setLocationRelativeTo(null);
		
		JPanel bPanel = new JPanel();
		bPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		JPanel gridPanel = new JPanel();
		gridPanel.setLayout(new GridBagLayout());
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		bottomPanel.add(gridPanel);

		repoChosen.setText("No Repository");
		repoClone.setText("");
		
		cnst.gridy = 0;                     // 1-a riga
		cnst.insets = new  Insets(3,3,3,3);          //  spazio  attorno  al comp.
		cnst.fill = GridBagConstraints.HORIZONTAL;     //  estensione  in  orizzont.
		gridPanel.add((repoClone),cnst);
		cnst.gridy ++;                     //  prossima  riga
		gridPanel.add((repoChosen),cnst);
		
		bList = new ArrayList<>();
		bList.add(new JButton("Copy"));
		bList.add(new JButton("Add"));
		bList.add(new JButton("Commit"));
		bList.add(new JButton("Log"));
		bList.add(new JButton("Clone"));
		bList.add(new JButton("Repository"));
		
		for(JButton element: bList){
			element.setEnabled(false);
			switch (element.getText()){
			
			case "Repository":
				element.setToolTipText("Selezionare la Repository");
				element.setEnabled(true);
				break;
			
			case "Add":
				element.setToolTipText("Selezionare il file da aggiungere alla repository");
				break;
			
			case "Copy":
				element.setToolTipText("Selezionare il file da copiare all'interno della repository");
				break;
			
			case "Clone":
				element.setToolTipText("Digitare l'URL da cui prendere il repository remoto");
				break;
				
			case "Commit":
				element.setToolTipText("Digitare un commento per eseguire il commit");
				break;
				
			case "Log":
				element.setToolTipText("Visualizzare la cronologia");
				break;
			}
			bPanel.add(element);
		}

		this.add(bPanel,BorderLayout.NORTH);
		this.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	@Override
	public void set_Control(Control ctr){
		this.ctr = ctr;
		myListen = new MyActionListener(this.ctr);
		set_Listen();
	}
	
	/**
	* Metodo utilizzato per assegnare il Listener ai bottoni
	*/
	private void set_Listen(){
		for(JButton element: bList){
			element.setActionCommand(element.getText());
			element.addActionListener(myListen);
		}
	}
	
	@Override
	public void set_Repo(File repo){
		this.repo = repo;
		repoChosen.setText("Repository = " + this.repo.toString());
		for(JButton element: bList){
			element.setEnabled(true);
		}
	}
	
	@Override
	public void set_Clone(String url){
		repoClone.setText("Cloned " + url);
	}
}
