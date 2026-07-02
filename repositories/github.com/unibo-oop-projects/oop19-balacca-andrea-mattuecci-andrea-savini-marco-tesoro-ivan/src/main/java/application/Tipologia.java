package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Tipologia.
 */
public class Tipologia implements Typology, Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6310689409882539474L;
	
	/** The id. */
	private String ID; //(es. Carne, Pesce...)
    
    /** The info. */
    private HashMap<String,String> info; //IDInfo, valore associato (es. COLORE, rosso), a questo livello si dichiarano ma non si definiscono
    //eventualmente si possono prevedere metodi specifici per l'aggiunta/rimozione di singoli elementi dalle map
    
    /**
     * Instantiates a new tipologia.
     *
     * @param ID the id
     */
    public Tipologia(String ID) {
        this.ID=ID;
        this.info = new HashMap<String, String>();
    }
    
    //utility di aggiunta---------------------------------------
    
    /**
     * Aggiungi info.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<HashMap<String,String>> aggiungiInfo(HashMap<String,String> i) {
        
        HashMap<String,String> infoNonAmmesse = new HashMap<>(); //sono quelle info che risulterebbero duplicate (es. due "COLORE")
        for(var c : i.keySet()) {
            if(!info.containsKey(c)) { //se la info non è presente
                info.put(c, (i.get(c))); //la inserisco
            }
            else {
                infoNonAmmesse.put(c, i.get(c)); //altrimenti, creo la mappa da restituire per indicare quali info non sono ammesse
            }
        }
        
        if(!infoNonAmmesse.isEmpty()) { //se qualche info non è ammessa
            return Optional.of(infoNonAmmesse); //restituisco la mappa con le info non ammesse
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di modifica---------------------------------------
    
    /**
     * Modifica info.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<HashMap<String,String>> modificaInfo(HashMap<String,String> i) {
        
        HashMap<String,String> infoNonAmmesse = new HashMap<>(); //sono quelle info che non sono presenti
        for(var c : i.keySet()) {
            if(info.containsKey(c)) { //se la info è presente
                info.put(c, (i.get(c))); //la modifico
            }
            else {
                infoNonAmmesse.put(c, i.get(c)); //altrimenti, creo la mappa da restituire per indicare quali info non sono ammesse
            }
        }
        
        if(!infoNonAmmesse.isEmpty()) { //se qualche info non è ammessa
            return Optional.of(infoNonAmmesse); //restituisco la mappa con le info non ammesse
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di rimozione---------------------------------------
    
    /**
     * Rimuovi info.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<ArrayList<String>> rimuoviInfo(ArrayList<String> i) { //vuole in pasto una lista di IDInfo
        
        ArrayList<String> infoNonAmmessi = new ArrayList<>();
        for(var c : i) {
            if(info.containsKey(c)) { //se il prodotto è presente
                info.remove(c); //lo rimuovo
            }
            else {
                infoNonAmmessi.add(c); //altrimenti, creo la lista da restituire per indicare quali info non sono presenti
            }
        }
        
        if(!infoNonAmmessi.isEmpty()) { //se qualche info non è presente
            return Optional.of(infoNonAmmessi); //restituisco la lista con le info non ammesse
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di rimozione---------------------------------------
    
    /**
     * Ottieni info.
     *
     * @param i the i
     * @return the array list
     */
    public ArrayList<Optional<String>> ottieniInfo(ArrayList<String> i) {
        
        ArrayList<Optional<String>> infoRet = new ArrayList<>();
        for(var c : i) {
            if(info.containsKey(c)) { //se il valore è presente
                infoRet.add(Optional.of(info.get(c))); //lo aggiungo
            }
            else {
                infoRet.add(Optional.empty()); //altrimenti, aggiungo un empty per indicare che la key non è presente
            }
        }
        
        return infoRet; //restituisco la lista con le info
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    /*
     * a seguire, metodi getter e setter
     */
    public String getID() {
        return ID;
    }
    
    /**
     * Sets the id.
     *
     * @param iD the new id
     */
    public void setID(String iD) {
        ID = iD;
    }
    
    /**
     * Gets the info.
     *
     * @return the info
     */
    public HashMap<String,String> getInfo() {
        return info;
    }
    
    /**
     * Sets the info.
     *
     * @param info the info
     */
    public void setInfo(HashMap<String,String> info) {
        this.info = info;
    }
}
