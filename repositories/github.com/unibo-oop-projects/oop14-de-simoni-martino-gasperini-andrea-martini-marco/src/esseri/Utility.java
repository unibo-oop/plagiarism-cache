package esseri;

/**
 * 
 * @author Martino De Simoni
 *	
 *	La seguente Ä� una classettona di costanti public final static e di metodi static vari che possono essere usate, anche solo
 *  idealmente, in DUE O PIU' classi.
 *  
 *  La classe Ä� ottima per il debug, la stesura del codice e per la traduzione da lingua a lingua.
 *  
 *  L'utilizzo di questa classe espone il programma a due problemi:
 *  
 *  - A tempo di run, un oggetto A potrebbe utilizzare un oggetto X qui definito, e B dovrebbe utilizzare lo stesso oggetto di A, 
 *  cambiando oggetto in uso al variare dell'oggetto di A, mentre invece utilizza lo stesso oggetto della classe utility.
 *  Nel caso A e B non cambino oggetto, il programmatore che mantiene il codice, dovendo cambiare solo quello di A o solo di B,
 *  dovrÅ• fare attenzione.
 *  
 *  - Durante il mantenimento, nel caso in cui la stessa immagine abbia due funzioni diverso ( per esempio, usare il logo anche 
 *    come sfondo), utilizzando la stessa etichetta "logo", nel momento in cui si vorrÅ• cambiare lo sfondo, si cambierÅ• anche il 
 *    logo. Un po' come associare il contatore di un ciclo e quello dei thread utilizzati allo stesso int. Le variabili sono
 *    definite per semantica e non per valore.
 *
 *	In un ambiente di buona programmazione mi sento di raccomandare questa classe.
 *
 *  A mio avviso, Ä� meglio mettere qui le costanti relative alle piante e agli zombie, piÅ¯ altre entitÅ• che potrebbero in seguito essere aggiunte, piuttosto che nelle relative astratte, per semplicitÅ• di programmazione e per avere un confronto rapido sui cambiamenti in corso. Inoltre, le prestazioni sono le medesime.
 *
 * */

public final class Utility {
	
	private Utility(){};
	
	public final static int TRENTOTTO = 38; //usare con cura.
		
	
	//Zombie
	
	
	public final static double ZOMBIE_VITA_BASSA=10D;
	public final static double ZOMBIE_VITA_MEDIA=20D;
	public final static double ZOMBIE_VITA_ALTA=30D;
	
	public final static double ZOMBIE_DANNO_BASSO=1D;
	public final static double ZOMBIE_DANNO_MEDIO=2D;
	public final static double ZOMBIE_DANNO_ALTA=3D;
	
	
	//Piante
	
	
	public final static double PIANTA_VITA_BASSA=10D;
	public final static double PIANTA_VITA_MEDIA=20D;
	public final static double PIANTA_VITA_ALTA=30D;
	
	public final static double PIANTA_DANNO_BASSO=1D;
	public final static double PIANTA_DANNO_MEDIO=2D;
	public final static double PIANTA_DANNO_ALTO=3D;
	
	
	
}
