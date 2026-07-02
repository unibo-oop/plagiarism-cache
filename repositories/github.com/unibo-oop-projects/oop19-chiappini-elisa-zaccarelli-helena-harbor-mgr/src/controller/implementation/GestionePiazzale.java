package controller.implementation;

import exception.ExceptionNegativeQty;
import model.Implementations.ImplPiazzale;

/**
 * Gestore Piazzale per creare il piazzale e aggiungere e rimuovere merce dal piazzale in base alle prenotazioni e partenze nave
 * @author Helena Zaccarelli
 *
 */

public class GestionePiazzale extends GestioneFile<ImplPiazzale> {
	public final static int CODICE_PIAZZALE = 1; 
    public final static int INIT = 2000;
	private static GestionePiazzale piazzale = null;
    public final static String PERCORSO = "piazzale.file";


    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza piazzale 
     */
    public static GestionePiazzale creaIst() {
        if (GestionePiazzale.piazzale == null) {
        	GestionePiazzale.piazzale = new GestionePiazzale();
        	GestionePiazzale.piazzale.carica();
        	GestionePiazzale.piazzale.creaPiazzale(new ImplPiazzale(INIT));
        }
        return GestionePiazzale.piazzale;
    }

    /**
     * Metodo che individua il percorso del file .txt di archivio
     * @return Percorso file
     **/
    @Override
    public String individuaPercorso() {
        return PERCORSO;
    }

    /**
     * Metodo che crea il piazzale
     * @param p
     */
    public void creaPiazzale(ImplPiazzale p) {
    	if (this.file.isEmpty()) {
    		this.file.put(CODICE_PIAZZALE, p);
    		salva();
        }
    } 
    
    /**
     * Metodo che aggiunge merce al piazzale aggiornando il campo "SpazioLibero"
     * 
     * @param quantita
     * @throws ExceptionNegativeQty
     */
    public void depositaMerce(int quantita) throws ExceptionNegativeQty {
    	//In commento comandi di stampa su console utili per eventuale verifica del corretto funzionamento 
        int preventivo;
        ImplPiazzale piaz = this.file.get(CODICE_PIAZZALE);
        int spazioLibero = piaz.getSpazioLibero();
    	//System.out.println ("-----Spazio libero prima dell'operazione:" + spazioLibero);
        if (quantita > spazioLibero) {
            throw new ExceptionNegativeQty();
        } 
        preventivo =  spazioLibero - quantita;
        piaz.setSpazioLibero(preventivo);
        this.file.remove(CODICE_PIAZZALE);
        this.file.put(CODICE_PIAZZALE, piaz);
    	//System.out.println ("-----Spazio libero dopo l'operazione:" + piaz.getSpazioLibero());
		salva();
    }
    
    /**
     * Metodo che elimina merce dal piazzale aggiornando il campo "SpazioLibero"
     * @param quantita
     */
    public void eliminaMerce(int quantita) {
    	//In commento comandi di stampa su console utili per eventuale verifica del corretto funzionamento 
        int preventivo;
        ImplPiazzale piaz = this.file.get(CODICE_PIAZZALE);
        int spazioLibero = piaz.getSpazioLibero();
    	//System.out.println ("-----Spazio libero prima dell'operazione:" + spazioLibero);
        preventivo =  spazioLibero + quantita;
        piaz.setSpazioLibero(preventivo);
        this.file.remove(CODICE_PIAZZALE);
        this.file.put(CODICE_PIAZZALE, piaz);
    	//System.out.println ("-----Spazio libero dopo l'operazione:" + piaz.getSpazioLibero());
		salva();
    }
}