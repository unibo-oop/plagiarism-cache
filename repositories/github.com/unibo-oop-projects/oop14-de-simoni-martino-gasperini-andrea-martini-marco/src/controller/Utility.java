package controller;

import java.io.File;

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
		
		public final static String fileDatiUtenti = System.getProperty("user.dir")+File.separatorChar+"utenti.txt";
		public final static String fileLivelli = System.getProperty("user.dir")+File.separatorChar+"livelli.txt";
		
	/**
	 * 
	 * Un metodo lentissimo da usare con cura.
	 * 
	 * @param s Un array di String
	 * @return Lo stesso array di String sottoforma di unica stringa
	 */
		public final static String stringArrayToString(String[] s){
				
				String daRitornare = new String("");
				for(String t: s) { 
					daRitornare = new String(daRitornare.concat(t+" "));
				}
				
				return daRitornare;
			
			}
		/*
		 * tokenToName lo preferisco qui, il nome deve essere visualizzato allo stesso modo in tutta la GUI.
		 */
		
		/**
		 * 
		 * @param s Il token letto da file
		 * @return  Il nome come deve essere verosimilmente visualizzato. La javadoc di UserDataManager.nameToToken(String) descrive la perdita di dati.
		 * 
		 */
		public final static String tokenToName( String s ){
			
			if(s==null) return null;
			return s.replace("_", " "); 
			
		}
}
