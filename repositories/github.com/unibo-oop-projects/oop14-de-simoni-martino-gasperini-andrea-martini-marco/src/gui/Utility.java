package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/*
 * I campi sono inizializzati in italiano. Sarebbe bello un metodo che, presa in input una lingua, li inizializzasse in tale lingua.
 * 
 */

/**
 * 	
 *	La presente è una classettona di costanti public final static e di metodi static vari che possono essere usate, anche solo
 *  idealmente, in DUE O PIU' classi.
 *  
 *  La classe è ottima per il debug, la stesura del codice e per la traduzione da lingua a lingua.
 *  
 *  L'utilizzo di questa classe espone il programma a due problemi:
 *  
 *  - A tempo di run, un oggetto A potrebbe utilizzare un oggetto X qui definito, e B dovrebbe utilizzare lo stesso oggetto di A, 
 *  cambiando oggetto in uso al variare dell'oggetto di A, mentre invece utilizza lo stesso oggetto della classe utility.
 *  Nel caso A e B non cambino oggetto, il programmatore che mantiene il codice, dovendo cambiare solo quello di A o solo di B,
 *  dovrà fare attenzione.
 *  
 *  - Durante il mantenimento, nel caso in cui la stessa immagine abbia due funzioni diverso ( per esempio, usare il logo anche 
 *    come sfondo), utilizzando la stessa etichetta "logo", nel momento in cui si vorrà cambiare lo sfondo, si cambierà anche il 
 *    logo. Un po' come associare il contatore di un ciclo e quello dei thread utilizzati allo stesso int: insensato. Le variabili
 *    devono essere definite per semantica e non per valore.
 *
 *	In un ambiente di buona programmazione mi sento di raccomandare questa classe.
 *  
 *  @author Martino De Simoni
 *
 * */

public final class Utility {
	
	private Utility(){};
	
	private static final String cartellaImmagini = System.getProperty("user.dir")+File.separatorChar+"img"+File.separatorChar; //infallibile
																									       
	public static final boolean FULLSCREEN = true;
	public static final int razioWhenNotFullScreen = 2;
		//Scelta discutibile mettere le immagini qui, dato che verranno utilizzate dal controller. Nel progetto "Piante contro Zombie", è assolutamente meglio qui.
	public static final BufferedImage logo = initImg("logo.jpg");
	public static final BufferedImage sfondo = initImg("sfondo.jpg"); 
	public static final BufferedImage sfondoInCaricamento = initImg("loading.jpg"); 
	public static final BufferedImage pala = initImg("pala.jpg"); 
	public static final BufferedImage sole = initImg("sun.jpg"); 
	public static final BufferedImage erba = initImg("grass.jpg"); 

	
	//Stringhe per i bottoni
	public static final String addUser = "Nuovo";
	public static final String removeUser = "Rimuovi";
	public static final String selectUser = "Seleziona";

	public final static String close= "Chiudi";
	public final static String cancel= "Annulla";
	public static final String exit = "Esci";
	public static final String saveAndExit = "Salva ed esci";
	
	public static final String choose = "Scegli le tue piante!";
	public static final String play = "Gioca!";
	public static final String playCampaign = "Campagna";
	public static final String options = "Opzioni";
	public static final String backToUserChoice = "Torna alla scelta utente";
	//Stringhe per il resto della gui
	public final static String title = "Piante contro Zombie";
	public final static String addUserQuestion = "Inserisci il nome del nuovo utente";
	public final static String messageForExit= "Sicuro di voler uscire (i dati non salvati andranno persi) ?";
	public final static String userAlreadyExistError= "Errore: nome utente già selezionato";
	
	public static final String name = "Nome";
	public static final String money = "Soldi";

/**
 * 
 * @param fileName Percorso del file da cui prendere l'immagine.
 * @return L'immagine.
 */
private static BufferedImage initImg(String fileName){
	
	BufferedImage img = null;
	try{
		img = ImageIO.read(Files.newInputStream(Paths.get(cartellaImmagini + fileName)));
	} catch(IOException e){
		System.out.println(e);
	}
	
	return img;
	}

/*
 * Metodo preso da http://stackoverflow.com/questions/15558202/how-to-resize-image-in-java
 */
/**
 * 
 * @param img L'immagine di cui si vuole fare il resize
 * @param newW La nuova larghezza
 * @param newH La nuova altezza
 * @return L'immagine ridimensionata
 * @author http://stackoverflow.com/questions/15558202/how-to-resize-image-in-java
 */
	public static BufferedImage resizeImg(BufferedImage img, int newW, int newH){
	
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(img, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;      
		}

	/**
	 * Prende in input due immagini e le unisce in un'unica immagine
	 * 
	 * @author http://stackoverflow.com/questions/2318020/merging-two-images
	 * @param image L'immagine a cui verrà sovrapposta la seconda.
	 * @param overlay L'immagine che sarà sovrapposta alla prima.
	 * @return La combinazione delle due immagini.
	 */
	
	public static BufferedImage mergeImage(BufferedImage image, BufferedImage overlay){
		// create the new image, canvas size is the max. of both image sizes
		int w = Math.max(image.getWidth(), overlay.getWidth());
		int h = Math.max(image.getHeight(), overlay.getHeight());
		BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

		// paint both images, preserving the alpha channels
		Graphics g = combined.getGraphics();
		g.drawImage(image, 0, 0, null);
		g.drawImage(overlay, 0, 0, null);
		
		return combined;
		
		}

}