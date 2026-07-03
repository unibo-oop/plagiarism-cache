package fileManager;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;

/**
 * FileManager per gestire i file degli utenti.
 * 
 *  @author Martino De Simoni
 */

/*_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 * 
 * In BNF, la grammatica dei file è definita come segue:
 * 
 * Sia a^n il token a scritto n volte (a^2=aa, a^3=aaa..).
 * Siano lo spazio il carattere di blank per i token, ma non lo spazio del linguaggio descritto.
 * 
 * Sia T il token iniziale.
 * 
 * Siano token terminali le stringhe all'interno dei doppi apici ("),
 * nome, livello, soldi e la sequenza di parole pianta() di taglia n indicizzato da 0;
 * 
 * Siano " " e "\n" i caratteri di blank.
 * Sia nihil la stringa vuota.
 * 
 * T:=altriG;
 * 
 * altriG:= nihil | Giocatore altriG;
 * 
 * 
 * Giocatore:="Inizio_dati_utente\n" nome altriCampi "Fine_dati_utente\n";  
 * 
 * nome:= "Nome: " nome "\n";
 * 
 * altriCampi := campo altriCampi | nihil; //per semplicità, con "campo" ci si riferisce a quelli diversi dal nome, il quale è tassativo. Facoltativi gli altri
 * 
 * campo := "Livello: " livello "\n" altriCampi | "Soldi: " soldi "\n" altriCampi |
 * 			| "Piante sbloccate: " a^n piante "fine_piante\n" altriCampi;
 * 
 * a^k piante :=  pianta(n-k) " " a^(k-1) piante ; //se k=n viene "sputato fuori" a membro destro pianta(n-n)=pianta(0), 
 * 												   //seguono gli n-1 elementi rimanenti.
 * 
 * pianta(n-1) piante := pianta(n-1); //pianta(n-1) è l'ultimo elemento del vettore, che cancella il token piante
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 * 
 * 
 * Si noti, anche solo per curiosità, come indicizzare il vettore da 1 avrebbe portato a uno stato finale pianta(n) a piante, 
 * implementativamente un po' più lungo da gestire ( pianta(n) a piante:= pianta(n) ).
 *
 * Si apprezzi come l'ordine degli elementi non sia importante.
 * 
 * Sono considerate legali sequenze bislacche con campi ripetuti o senza campi. In effetti, nel caso di aggiunte successive,
 * i vecchi dati utenti mancheranno di campi. Le sequenze con campi ripetuti sono da togliere ma non so come renderlo in BNF.
 * 
 * Si prega di aggiornare la BNF ogniqualvolta necessario.
 * 
 *
 * Manca un campo per la versione del programma. Inoltre, se da una versione all'altra venisse aumentato il numero di campi,
 * i dati utente di una vecchia versione mancherebbero di qualche campo. Manca un metodo per colmare questo "vuoto". 
 * Consiglio un costruttore per la classe Giocatore; poi quello che deve essere aggiornato viene letto da file.
 * 
 * Sconsiglio fortemente di dare importanza all'ordine dei campi, sia per inutilità, sia per il bisogno di interpretare una 
 * grammatica di Chomsky contestuale, sia per indebolimento del codice. 
 */

//TODO nella classe non vengono lanciate eccezioni

public class UserDataManager extends FileManager{
	
	private final static String inizioUtente = "Inizio_dati_utente"; //inizioUtente e fineUtente sono buoni per passare da un utente all'altro, in caso nel futuro ci sia bisogno
	private final static String fineUtente = "Fine_dati_utente";     
	private final static String nomeUtente = "Nome:"; 
	private final static String livelloUtente = "Livello:"; 
	private final static String pianteUtente = "Piante_sbloccate:"; 
	private final static String finePiante = "fine_piante";
	private final static String soldiUtente = "Soldi:"; 
	//Blank da restituire da getter. Qui si possono anche non usare
	public static char blank1 = ' ';
	public static char blank2 = '\n'; 
	
	public UserDataManager(final String filePath){
			
			super(filePath);
		
		}
	
	/**
	 * Appende i dati del giocatore su file.
	 * @param g I dati del giocatore da salvare.
	 */
	
	public void appendiDatiGiocatore (final Giocatore g){
				
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			fw.append(inizioUtente);
			fw.append("\n");
			fw.append(nomeUtente+" "+ nameToToken(g.nome) );
			fw.append("\n");
			fw.append(livelloUtente+" "+g.livello);
			fw.append("\n");
			fw.append(pianteUtente+" ");
			
			String[] daScrivere = g.pianteSbloccate.toArray(new String[0]);
			for(String s: daScrivere) { fw.append(s); fw.append(" ");}
	
			fw.append(finePiante);
			fw.append("\n");
			fw.append(soldiUtente+" "+g.soldi);
			fw.append("\n");
			fw.append(fineUtente);
			fw.append("\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally { try {
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} }
		
		
		
		}
	/*
	 * Metodo molto più delicato di Writer: tenere conto ad ogni modifica.
	 * Era meglio un'hashmap, ma ormai ho fatto.
	 */
	/**
	 * Legge i dati dei vari giocatori sui file
	 * @return I dati di tutti i giocatori su file.
	 */
	public HashSet<Giocatore> leggiDatiGiocatori (){

		String[] dati = fileToStringArray();

	      int contatore=0;
	      HashSet<Giocatore> giocatori = new HashSet<Giocatore>();
	      
	      while(contatore<dati.length-1){ //Lettura di tutti i giocatori
	    	  
	    	  Giocatore g = new Giocatore("");

	    	  while(!dati[contatore].matches(fineUtente)){ //Lettura di un singolo utente
	    		  
	    		  switch(dati[contatore]){
	    		  	case nomeUtente:
	    		  		g.nome=dati[contatore+1]; contatore++;  break;
	    		  	
	    		  	case livelloUtente: 
	    		  		g.livello=dati[contatore+1]; contatore++; break;
	    		  	
	    		  	case pianteUtente: 
	    		  		contatore++; //cominciano le piante
	    		  		while(!dati[contatore].matches(finePiante) ) { 
	    		  			g.pianteSbloccate.add(dati[contatore]);
	    		  			contatore++;
	    		  		} 
	    		  		contatore++; //il while finisce quando dati[contatore]==finePiante, si passa alla prossima parola
	    		  		break;
	    		  	
	    		  	case soldiUtente:
	    		  		g.soldi=Integer.parseInt(dati[contatore+1]); contatore++; break;
	    		  		 
	    		  		
	    		  	case inizioUtente:
	    		  		contatore++;break; //Non mi viene in mente nessun utilizzo, fineUtente basta e avanza, addirittura si potrebbe lasciare semplicemente il nome come parola di blank fra un utente e l'altro. Comunque, ho visto questo genere di grammatiche spesso e seguo la regola.
	    		  
	    		  	default: {  contatore++; break;} //Per le parole "imprevedibili" (Luca, 50, Girasole..)
	    		  }
	    		
	    	  }
	    	  contatore++; // sono su fineUtente, passo avanti
	    	  
	    	  
	    	  giocatori.add(g);
	      }
	     
	      return giocatori;
	    
		}

	/**
	 * 
	 * 
	 * La complessità del seguente metodo cresce all'aumentare degli utenti e della conseguente lunghezza del file associato. Gli utenti sono, secondo una mia personale esperienza, mai maggiori di 10 su pc personale.
	 * 
	 * @author Martino De Simoni
	 * @param cancellando Nome del giocatore da rimuovere dal file
	 * @return true se l'operazione è andata a buon fine, false altrimenti.
	 * 
	 */
	
		public boolean cancellaDatiGiocatore(final String _cancellando){
		
			String cancellando=this.nameToToken( _cancellando );

			
			FileWriter fw = null;
			try {
				
				HashSet<Giocatore> giocatori = leggiDatiGiocatori();
				
				
				fw = new FileWriter(file, false); //sovrascrive->cancella e scrive
				
				
				for (Giocatore g: giocatori){
					
					if(!g.nome.equals(cancellando) ) appendiDatiGiocatore(g);
					
					}
			
				return true;
		
			} catch (IOException e) {
				e.printStackTrace();
				return false;
		} finally { try {
				fw.close();
		} catch (IOException e) {
				e.printStackTrace();
			} }
			
			
		}
	
		/**
		 * Salva i dati del giocatore, sovrascrivendo se deve.
		 * @param g Dati del giocatore da salvare.
		 */
	
	public void salvaGiocatore( final Giocatore g ) {
		
		this.cancellaDatiGiocatore( g.nome );
		this.appendiDatiGiocatore( g );
		
	}
	
	/*
	 * Chiamando "nome" "key", e "daRitornare" "value", si capisce che sarebbe stato meglio usare le HashMap. Ad ogni modo il lavoro è 
	 * finito e quasi del tutto nascosto al programmatore.
	 *
	 */
	/**
	 * Ritorna un giocatore da file dato il nome
	 * @param _nome Nome del giocatore
	 * @return Giocatore ricercato
	 */
	public Giocatore cercaGiocatoreInFile( final String _nome){

		String nome=this.nameToToken(_nome);

		
		HashSet<Giocatore> giocatori = this.leggiDatiGiocatori();
		Giocatore daRitornare = NessunGiocatore.nessunGiocatore;
		
		for(Giocatore g:giocatori){
			
			if(g.nome.matches(nome) ) daRitornare = g;
			
		}
		return daRitornare;
	}
	
	/*
	 * Mi sembra opportuno cambiare l'underscore in spazio per ogni underscore. Nel caso l'utente voglia davvero l'underscore, 
	 * la sua scelta non verrà considerata. Ci vorrebbe un metodo più sofisticato.	 
	 * Nel caso prestare attenzione sia a nameToToken (qui) che a tokenToName (utility).
     */	
	/**
	 * 
	 * @param s Il nome come inserito da utente.
	 * @return  Il nome come deve essere inserito su file. L'underscore ( _ ) inserito da utente diventa uno spazio.
	 */
	private String nameToToken( String s){
		
		if(s==null) return null; //tecnicamente non viene mai passato null a questo metodo..
		return s.replace("\n", "_").replace(" ", "_");
		
	}
	
	public char[] getBlanks(){
		
		char[] blanks = new char[2];
		blanks[1] = blank1;
		blanks[2] = blank2;
		
		return blanks;
		
	}
	
}

