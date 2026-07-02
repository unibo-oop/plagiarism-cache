package controller.implementation;

import model.Implementations.ImplPorto;

import java.util.ArrayList;

import exception.ExceptionEqualName;

/**
 * Gestore Porto per inserire, visualizzare e cancellare un porto dall'archivio
 * @author Helena Zaccarelli
 *
 */

public class GestionePorto extends GestioneFile<ImplPorto> {
	private static GestionePorto porti = null;
    public final static String PERCORSO = "porto.file";

    /**
     * Metodo che crea un'istanza caricando i dati dal file (o creandolo in caso non fosse già presente)
     * @return istanza porti
     */
    public static GestionePorto creaIst() {
        if (GestionePorto.porti == null) {
        	GestionePorto.porti = new GestionePorto();
        	GestionePorto.porti.carica();
        }
        return GestionePorto.porti;
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
     * Metodo che inserisce un nuovo porto
     * @param porto
     */
    public void aggiungiPorto(ImplPorto porto) throws ExceptionEqualName {
        for (ImplPorto p : this.getList()) {
            if (p.getNome().equals(porto.getNome())) {
                throw new ExceptionEqualName();
            }
        }
        porto.setCod(this.assegnaC());
        this.file.put(porto.getCod(), porto);
        this.salva();
    } 
    
    /**
     * Metodogo che permette la ricerca per nome
     * @author  Elisa Chiappini
     * 
     * @param s
     * @return lista
     */
    public ArrayList<ImplPorto> cerca(String s) {
        ArrayList<ImplPorto> lista = new ArrayList<>(20);
        for (ImplPorto porto : getList()) {
            if (porto.getNome().contains(s)) {
                lista.add(porto);
            }
        }
        return lista;
    }
    
    /**
     * Metodo che rimuove un porto
     * @param codice
     */
    public void rimuoviPorto(int codice){
        this.file.remove(codice);
        salva();
    }
}