package fileManager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/**
 * Gestore di dati su file.
 * 
 * La filosofia di questa classe è "un fileManager, un file"
 *
 * @author Martino De Simoni
 *
 */

public abstract class FileManager {

	final protected File file;
	
	
	public FileManager(String fileName){
			
			this.file = new File (fileName);
		
		}
	
	/**
	 * Trasforma un file di testo in array di stringhe, contando lo spazio e l'a capo i blank fra parola e parola.
	 * 
	 * Questo rende l'a capo uno spazio con diversa leggibilità. 
	 * Non usare l'a capo per dare significato in fase di aggiunta/modifica, usare le parole.
	 * 
	 * Tecnicamente andrebbe un argomento con i blanks del file ma vabbè.
	 * 
	 * @return Contenuto del file di testo in formato String []
	 */
	protected String[] fileToStringArray(){
		
		FileReader fr= null;
		
		try {
		    
		      fr = new FileReader(file);
		      char[] in = new char[ (int) file.length() ];
		      fr.read(in);
		     
		      String s = new String(in);
		      
		      String z=s.replace("\n", " "); 
		     
		      String[] dati = z.split(" ");
		      return dati;
		    }
		    catch(IOException e) { 
		      e.printStackTrace();
		      return null;
		    } finally { try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			} }
		
			
	}
	
}

