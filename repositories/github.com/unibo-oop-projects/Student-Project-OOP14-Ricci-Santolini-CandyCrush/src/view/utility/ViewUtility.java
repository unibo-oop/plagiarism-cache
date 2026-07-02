package view.utility;

import java.awt.Image;

import javax.swing.ImageIcon;
/**
 * Classe contenente costanti riservate alla view.
 * 
 * @author Beatrice Ricci
 *
 */
public final class ViewUtility {
	/**
	 * Immagine per il JButton di uscita.
	 * @return 
	 */
	public static final ImageIcon CLOSE_IM = new ImageIcon(ViewUtility.class.getResource(("/image/close.jpg")));
	
	/**
	 * Sfondo per il pannello del menù iniziale.
	 */
	public static final Image CARAMELLE = new ImageIcon(ViewUtility.class.getResource("/image/caramelle.jpg")).getImage();
	/**
	 * Sfondo per il pannello di scelta del livello di difficoltà.
	 */
	public static final Image POIS = new ImageIcon(ViewUtility.class.getResource("/image/pois.jpg")).getImage();
	
	/**
	 * Immagine per la schermata di shuffle.
	 */
	public static final ImageIcon SHUFFLE = new ImageIcon(ViewUtility.class.getResource("/image/shuffle.jpg"));
	
	/**
	 * Icona del JButtons START del menu iniziale.
	 */
	public static final ImageIcon IC_START = new ImageIcon(ViewUtility.class.getResource("/image/start.jpg"));
	/**
	 * Icona del JButtons INSTRUCTION del menu iniziale.
	 */
	public static final ImageIcon IC_INSTR = new ImageIcon(ViewUtility.class.getResource("/image/instr.jpg"));
	/**
	 * Icona del JButtons EXIT del menu iniziale.
	 */
	public static final ImageIcon IC_EXIT = new ImageIcon(ViewUtility.class.getResource("/image/exit.jpg"));
	
	/**
	 * Percorso file istruzioni.
	 */
	public static final String FILE_PATH = "/doc/instr.txt";
	
	/**
	 * Icona per notificare la sconfitta.
	 */
	public static final ImageIcon ICON_LOSE = new ImageIcon(ViewUtility.class.getResource("/image/loser.jpg"));
	
	/**
	 * Icona per notificare la vittoria.
	 */
	public static final ImageIcon ICON_WIN = new ImageIcon(ViewUtility.class.getResource("/image/win.jpg"));
	
	private ViewUtility() { }
}
