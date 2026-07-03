package fileManager;

import java.util.HashMap;

/*
 * 
 * Da interfaccia di gioco, sarebbe davvero possibile aggiungere un editor di livelli. Lascio l'eventualità del levelWriter 
 * in un package gameDesignToolKit a parte.
 * 
 */

/**
 * 
 * Lettore del file dei livelli.
 * 
 * @author Martino De Simoni
 */

/*
 * Definisco qui la grammatica in BNF.
 * Premetto che nel file possono non esserci livelli, o livelli senza zombie, per semplicità di programmazione. Confido nel 
 * gameDesigner.
 *
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 *  
 * Sia T il token iniziale
 * 
 * Siano " " e "\n" blank.
 * 
 * Siano token terminali nomeLivello,tempo_in_ms, nome_zombie, lane, soldi, e le stringhe fra doppi apici (").
 * 
 * Sia nihil la stringa vuota.
 * 
 * T:= altriLivelli;
 * altriLivelli:= Livello "\n" altriLivelli | nihil;
 *
 * Livello:= "Inizio_livello\n" nome altriCampi "Fine_livello" altriLivelli ;
 * 
 * nome:= "Livello: " nomeLivello "\n";
 * 
 * altriCampi:= "Elenco_zombie\n" altriZombie "Fine_elenco" altriCampi | Ricompensa altriCampi | nihil;
 * 
 * altriZombie := tempo_in_ms nome_zombie lane "\n" altriZombie | nihil;
 * 
 * Ricompensa:= "Soldi: " soldi "\n" | nihil; //Attenzione, soldi è un terminale. Se non c'è nessuna ricompensa, scrivere 0 nel file o come indicato indicato nel metodo di lettura del LevelReader.
 * 
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________
 * 
 * 
 * Si apprezzi come l'ordine degli elementi non sia importante.
 * 
 * Sono considerate legali sequenze bislacche con campi ripetuti o senza campi. In effetti, nel caso di aggiunte successive,
 * i vecchi dati utenti mancheranno di campi. Questo non è necessamario, ma confidando nel Writer non si avrà motivo di lanciare 
 * Exceptions.
 * 
 * Si prega di aggiornare la BNF ogniqualvolta necessario.
 * 
 *
 */

/*
 * Sconsiglio fortemente di dare importanza all'ordine dei campi, sia per inutilità, sia per il bisogno di interpretare una 
 * grammatica di Chomsky contestuale, sia per indebolimento del codice. 
 */

public class LevelReader extends FileManager {

	protected final static String inizioLivello = "Inizio_Livello"; //Ricordo che se il pattern un token->una parola è più facile da programmare
	protected final static String fineLivello = "Fine_Livello";
	protected final static String nomeLivello = "Livello:";
	protected final static String ricompensaInDenaro = "Soldi:";
	protected final static String inizioElencoZombie = "Elenco_zombie";
	protected final static String fineElencoZombie = "Fine_elenco";
	
	/**
	 * 
	 * @param filePath Percorso del file
	 */
	public LevelReader(final String filePath){
	
		super(filePath);

	}
	
	/**
	 * @return La descrizione di ogni livello, presa dal file associata al LevelReader.
	 */
	
	public HashMap<String,Livello> leggiDatiLivelli(){
		
		String[] dati = fileToStringArray();

		int contatore=0;
		HashMap<String, Livello> livelli = new HashMap<>();
		      
		while(contatore<dati.length-1){ //Lettura di tutti i livelli fino alla fine del file.
		    	  
		    	  Livello l = new Livello();

		    	  while(!dati[contatore].matches(fineLivello)){ //Lettura di un singolo livello
		    		  
		    		  switch(dati[contatore]){
		    		  	case nomeLivello:
		    		  		l.nome=dati[contatore+1]; contatore++;  break;
		    		  	
		    		  	case inizioElencoZombie: 
		    		  		contatore++; //cominciano gli zombie. Forma aspettata: tempo_in_ms nomeZombie lane
		    		  		while(!dati[contatore].matches(fineElencoZombie) ) { 
		    		  			l.zombie.add(new EntrataZombie(Integer.parseInt(dati[contatore])
		    		  					,dati[contatore+1],
		    		  					Integer.parseInt(dati[contatore+2]) ) );
		    		  			contatore= contatore +3;
		    		  		} 
		    		  		contatore++; //il while finisce quando dati[contatore]==fineElenco, si passa alla prossima parola
		    		  		break;
		    		  	
		    		  	case ricompensaInDenaro:
		    		  		l.ricompensa_in_denaro=Integer.parseInt(dati[contatore+1]); contatore++; break;
		    		  		 
		    		  		
		    		  	case inizioLivello:
		    		  		contatore++;break; //Non mi viene in mente nessun utilizzo, fineUtente basta e avanza, addirittura si potrebbe lasciare semplicemente il nome come parola di blank fra un utente e l'altro. Comunque, ho visto questo genere di grammatiche spesso e seguo la regola.
		    		  
		    		  	default: {  contatore++; break;} // Per le parole "imprevedibili" (livello_bonus, ZombieConGiornale..)
		    		  }
		    		
		    	  }
		    	  contatore++; // sono su fineUtente, passo avanti
		    	 
		    	  livelli.put(l.nome,l);
		      }
		     
		      return livelli;
		    
			}
	
	
	}
	