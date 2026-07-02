package controller.implementation;

import exception.ExceptionNegativeQty;
import model.Implementations.ImplPrenotazione;
import controller.implementation.GestioneViaggio;

import java.util.Collection;
import java.util.Iterator;

import controller.implementation.GestionePiazzale;

/**
 * Gestore Prenotazioni per inserire, visualizzare, inserire e cancellare le prenotazioni
 * @author Helena Zaccarelli
 *
 */

public class GestionePrenotazione extends GestioneFile<ImplPrenotazione> {
	private static GestionePrenotazione prenotazioni = null;
    public final static String PERCORSO = "prenotazioni.file";

    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza prenotazioni
     */
    public static GestionePrenotazione creaIst() {
        if (GestionePrenotazione.prenotazioni == null) {
        	GestionePrenotazione.prenotazioni = new GestionePrenotazione();
        	GestionePrenotazione.prenotazioni.carica();
        }
        return GestionePrenotazione.prenotazioni;
    }

    /**
     * Metodo che individua il percorso del file .txt di archivio
     * @return Percorso file
     */
    @Override
    public String individuaPercorso() {
        return PERCORSO;
    }
    
    /**
     * Metodo che inserisce una nuova prenotazione 
     * @param codice
     */
    public void aggiungiPrenotazione(ImplPrenotazione prenotazione) throws ExceptionNegativeQty{
        GestioneViaggio.creaIst().aggiornaSpaziPeso(prenotazione.getColli(), prenotazione.getPeso(), prenotazione.getId());
        prenotazione.setCodice(this.assegnaC());
        this.file.put(prenotazione.getCodice(), prenotazione);
        this.salva();
    } 

    /**
     * Metodo che rimuove una prenotazione
     * @param codice
     */
    public void rimuoviPrenotazione(int codice){
    	for (ImplPrenotazione p : this.getList()) {
    		if (p.getCodice() == (codice))
    		{
    			int colli = p.getColli();
    			GestionePiazzale.creaIst().eliminaMerce(colli);
    			GestioneViaggio.creaIst().liberaSpaziPeso(colli, p.getPeso(), p.getId());
    		}
    	}
        this.file.remove(codice);
        salva();
    }
   
    
    /**
     * Metodo che elimina tutte le prenotazioni relative ad un dato viaggio
     * 
     * @param id
     */
    public void aggiornamentoDataBase(int id) {
    	//In commento comandi di stampa su console utili per la verifica del corretto funzionamento del metodo 
    	//System.out.println ("Entro in aggiornamentoDataBase cercando tutte le prenotazioni relative al viaggio con id:" + id);
    	Collection<ImplPrenotazione> prenotazioni = this.getList(); 
    	Iterator<ImplPrenotazione> it = prenotazioni.iterator();   	
    	//System.out.println ("Inizio il ciclo while per scorrere le prenotazioni");
        while (it.hasNext()) {
        	ImplPrenotazione p = it.next();
        	//System.out.println ("-----Controllo la prenotazione con codice:" +p.getCodice() + ", id viaggio = " + p.getId() + ", colli =" + p.getColli());
            if (p.getId() == (id)) {
            	int quantita = p.getColli(); 
                it.remove();
            	//System.out.println ("-----Rimuovo la prenotazione con codice:" +p.getCodice());
                GestionePiazzale.creaIst().eliminaMerce(quantita);
            	//System.out.println ("-----Rimuovo dal piazzale la quantita:" + quantita);
            }
        }
        salva();
    }
}



