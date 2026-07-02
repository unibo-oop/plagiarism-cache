package controller.implementation;

import java.util.ArrayList;

import exception.ExceptionEqualName;
import model.Implementations.ImplMerce;

/**
 * Gestore Merce per inserire, visualizzare e cancellare una tipologia di merce
 * @author Helena Zaccarelli
 *
 */

public class GestioneMerce extends GestioneFile<ImplMerce> {
	private static GestioneMerce merci = null;
    public final static String PERCORSO = "merce.file";

    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza merci
     */
    public static GestioneMerce creaIst() {
        if (GestioneMerce.merci == null) {
        	GestioneMerce.merci = new GestioneMerce();
        	GestioneMerce.merci.carica();
        }
        return GestioneMerce.merci;
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
     * Metodo che inserisce una nuova tipologia di merce
     * @param merce
     */
    public void aggiungiMerce(ImplMerce merce) throws ExceptionEqualName {
        for (ImplMerce m : this.getList()) {
            if (m.getNome().equals(merce.getNome())) {
                throw new ExceptionEqualName();
            }
        }
        merce.setCod(this.assegnaC());
        this.file.put(merce.getCod(), merce);
        this.salva();
    } 
    
    /**
     * Metodo che permette la ricerca per nome
     * @author  Elisa Chiappini
     * 
     * @param s
     * @return lista
     */
    public ArrayList<ImplMerce> cerca(String s) {
        ArrayList<ImplMerce> lista = new ArrayList<>(20);
        for (ImplMerce merce : getList()) {
            if (merce.getNome().contains(s)) {
                lista.add(merce);
            }
        }
        return lista;
    }
    
    /**
     * Metodo che rimuove una tipologia di merce
     * @param codice
     */
    public void rimuoviMerce(int codice){
        this.file.remove(codice);
        salva();
    }
}