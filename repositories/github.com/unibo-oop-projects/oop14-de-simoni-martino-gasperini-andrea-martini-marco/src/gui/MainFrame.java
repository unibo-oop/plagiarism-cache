package gui;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * 
 * Classe istanziabile del frame da utilizzare, preferibilmente non più d'uno per istanza del programma.
 * 
 * @author Martino De Simoni
 */

@SuppressWarnings("unused")
public class MainFrame extends MyFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MainFrame(final String title, BufferedImage icon){
		
		
		
		super(title,icon);

		this.setMainPanel(new JPanel(new BorderLayout()) {
		    /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override public void paintComponent(Graphics g) {
		        g.drawImage(Utility.sfondoInCaricamento, 0, 0, null);
		    }
		},true);
		
		/*
		 * Stralcio di codice preso online
		 * 
		 */
		
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent we)
			{ 
			 String ObjButtons[] = {Utility.close,Utility.cancel};
			 int PromptResult = JOptionPane.showOptionDialog(null,Utility.messageForExit,Utility.title,JOptionPane.DEFAULT_OPTION,
					 										JOptionPane.WARNING_MESSAGE,null,ObjButtons,ObjButtons[1]);
			  if(PromptResult==JOptionPane.YES_OPTION)
			  {
			    
				  System.exit(0);
			  }
			}
			});

	}
	
}
