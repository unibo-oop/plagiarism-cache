package controller.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.HashMap;

/**
 * Classe astratta che gestisce i salvataggi, i caricamenti e l'individuazione del percorso del file di archivio
 * @author Helena Zaccarelli
 * 
 * @param <S> 
 * 
 */

public abstract class GestioneFile<S>{
	protected HashMap<Integer, S> file;

    protected GestioneFile() {
        this.file = new HashMap<Integer, S>(20);
    }
    
    /**
     * Metodo che assegna un codice univoco a ogni elemento
     * 
     * @return c
     */
    public int assegnaC() {
        int c = this.file.values().size();
        while (this.file.get(c) != null) {
            c++;
        }
        return c;
    }

	/**
     * Metodo che ritorna il percorso file per salvataggio e caricamento
     * 
     * @return Percorso file
     */
    public abstract String individuaPercorso();

    
    /**
     * Metodo che si occupa del salvataggio su file
     */
    public void salva() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(individuaPercorso());
            ObjectOutputStream out = new ObjectOutputStream(fileOutput);
            out.writeObject(this.file);
            out.close();
            fileOutput.close();
        } catch (Exception i) {
            i.printStackTrace();
        }
    }

    /**
     * Metodo che si occupa del caricamento da file
     */
	@SuppressWarnings("unchecked")
	public void carica() {
        try {
            File f = new File(individuaPercorso());
            if (!f.exists()) {
                return;
            }

            FileInputStream fileInput = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fileInput);
            Object o = in.readObject();
            this.file = (HashMap<Integer, S>) o;
            in.close();
            fileInput.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            System.out.println("Archivio non trovato");
            c.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
     * Metodo che restituisce un elenco degli elementi presenti nel file
     * 
     * @return lista elementi salvati
     */
    public Collection<S> getList() {
        return (Collection<S>) this.file.values();
    }
   
    /**
     * Metodo che permette la stampa su console
     */
    @Override
    public String toString() {
        String s = "";
        for (S object : this.getList()) {
            s += object.toString() + "\n";
        }
        return s;
    }
}

