package controller.implementation;

import java.util.ArrayList;
import exception.ExceptionEqualName;
import model.Implementations.ImplNave;


/**
 * Gestore Nave per inserire, visualizzare e cancellare una nave dall'archivio
 * @author Helena Zaccarelli
 *
 */

public class GestioneNave extends GestioneFile<ImplNave> {
	private static GestioneNave navi = null;
    public final static String PERCORSO = "navi.file";

    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza nave
     */
    public static GestioneNave creaIst() {
        if (GestioneNave.navi == null) {
        	GestioneNave.navi = new GestioneNave();
        	GestioneNave.navi.carica();
        }
        return GestioneNave.navi;
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
     * Metodo che inserisce una nuova nave
     * @param nave
     */
    public void aggiungiNave(ImplNave nave) throws ExceptionEqualName {
        for (ImplNave n : this.getList()) {
            if (n.getNome().equals(nave.getNome())) {
                throw new ExceptionEqualName();
            }
        }
        nave.setCod(this.assegnaC());
        this.file.put(nave.getCod(), nave);
        this.salva();
    } 
    
    /**
     * Metodo che permette la ricerca per nome
     * @author  Elisa Chiappini
     * 
     * @param s
     * @return lista
     */
    public ArrayList<ImplNave> cerca(String s) {
        ArrayList<ImplNave> lista = new ArrayList<>(20);
        for (ImplNave nave : getList()) {
            if (nave.getNome().contains(s)) {
                lista.add(nave);
            }
        }
        return lista;
    }
   	 /**
     * Metodo che rimuove una nave
     * @param codice
     */
    public void rimuoviNave(int codice){
        this.file.remove(codice);
        salva();
    }
}