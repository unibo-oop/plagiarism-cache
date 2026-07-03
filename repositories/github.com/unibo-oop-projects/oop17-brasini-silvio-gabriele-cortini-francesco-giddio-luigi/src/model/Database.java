package model;
/**
 * Interfaccia che modella un oggetto di tipo Database.
 * @author silviobrasini
 *
 */
public interface Database {
    /**
     * Metodo che ritorna il nome del database.
     * 
     * @return nome del database
     */
    String getName();

    /**
     * Metodo che ritorna l'indirizzo del database.
     * 
     * @return iindirizzo del database
     */
    String getPath();

    /**
     * Metodo che ritorna il tipo del database.
     * 
     * @return tipo del database
     */
    String getType();
}
