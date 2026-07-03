package view.Components;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * jframe predefinito per il programma
 */
public class PrisonManagerJFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4583640093618196192L;

	/**logo programma*/
	String logoPath="res/logo.png";
	ImageIcon img = new ImageIcon(logoPath);
	
	/**costruttore jframe*/
	public PrisonManagerJFrame(){
		this.setIconImage(img.getImage());
		setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
