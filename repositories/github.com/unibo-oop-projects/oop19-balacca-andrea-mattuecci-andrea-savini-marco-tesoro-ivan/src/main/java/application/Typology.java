package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Interface Typology.
 */
public interface Typology extends Serializable{

    /**
     * Aggiungi info.
     *
     * @param i the i
     * @return the optional
     */
    Optional<HashMap<String,String>> aggiungiInfo(HashMap<String,String> i);
    
    /**
     * Modifica info.
     *
     * @param i the i
     * @return the optional
     */
    Optional<HashMap<String,String>> modificaInfo(HashMap<String,String> i);
    
    /**
     * Rimuovi info.
     *
     * @param i the i
     * @return the optional
     */
    Optional<ArrayList<String>> rimuoviInfo(ArrayList<String> i);
    
    /**
     * Ottieni info.
     *
     * @param i the i
     * @return the array list
     */
    ArrayList<Optional<String>> ottieniInfo(ArrayList<String> i);
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    String getID();
}
