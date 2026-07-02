package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Fornitore.
 */
public class Fornitore implements Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2028564397034424723L;
	
	/** The id. */
	private String ID;
    
    /** The prodotti. */
    private HashMap<String,Float> prodotti; //IDProd, prezzo
    
    /** The migliori prodotti. */
    private HashMap<String,Float> miglioriProdotti; //IDProd, prezzoEffettivo -> conterrà i prodotti il cui prezzoEffettio risulta il migliore
    
    /**
     * Instantiates a new fornitore.
     *
     * @param ID the id
     */
    public Fornitore(String ID) {
        this.ID = ID;
        this.prodotti = new HashMap<String, Float>();
        this.miglioriProdotti = new HashMap<String, Float>();
    }
    
    //utility di aggiunta---------------------------------------
    
    /**
     * Aggiungi prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiProdotti(HashMap<String,Float> prod) {
        
        HashMap<String,Float> prodNonAmmessi = new HashMap<>();
        for(var c : prod.keySet()) {
            if(!prodotti.containsKey(c)) { //se il prodotto non è presente
                prodotti.put(c, (prod.get(c))); //lo inserisco
            }
            else {
                prodNonAmmessi.put(c, prod.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Aggiungi migliori prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiMiglioriProdotti(HashMap<String,Float> prod) {
        
        HashMap<String,Float> prodNonAmmessi = new HashMap<>();
        for(var c : prod.keySet()) {
            if(!miglioriProdotti.containsKey(c)) { //se il prodotto non è presente
                miglioriProdotti.put(c, (prod.get(c))); //lo inserisco
            }
            else {
                prodNonAmmessi.put(c, prod.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di modifica---------------------------------------

    /**
     * Modifica prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<HashMap<String,Float>> modificaProdotti(HashMap<String,Float> prod) {
        
        HashMap<String,Float> prodNonAmmessi = new HashMap<>();
        for(var c : prod.keySet()) {
            if(prodotti.containsKey(c)) { //se il prodotto è presente
                prodotti.put(c, (prod.get(c))); //lo modifico
            }
            else {
                prodNonAmmessi.put(c, prod.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Modifica migliori prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<HashMap<String,Float>> modificaMiglioriProdotti(HashMap<String,Float> prod) {
        
        HashMap<String,Float> prodNonAmmessi = new HashMap<>();
        for(var c : prod.keySet()) {
            if(miglioriProdotti.containsKey(c)) { //se il prodotto è presente
                miglioriProdotti.put(c, (prod.get(c))); //lo inserisco
            }
            else {
                prodNonAmmessi.put(c, prod.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }

    //utility di rimozione---------------------------------------
    
    /**
     * Rimuovi prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<ArrayList<String>> rimuoviProdotti(ArrayList<String> prod) { //vuole in pasto una lista di IDProd
        
        ArrayList<String> prodNonAmmessi = new ArrayList<>();
        for(var c : prod) {
            if(miglioriProdotti.containsKey(c)) { //se il prodotto è presente
                miglioriProdotti.remove(c); //lo rimuovo
            }
            else {
                prodNonAmmessi.add(c); //altrimenti, creo la lista da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la lista coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Rimuovi migliori prodotti.
     *
     * @param prod the prod
     * @return the optional
     */
    public Optional<ArrayList<String>> rimuoviMiglioriProdotti(ArrayList<String> prod) {
        
        ArrayList<String> prodNonAmmessi = new ArrayList<>();
        for(var c : prod) {
            if(miglioriProdotti.containsKey(c)) { //se il prodotto è presente
                miglioriProdotti.remove(c); //lo rimuovo
            }
            else {
                prodNonAmmessi.add(c); //altrimenti, creo la lista da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!prodNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(prodNonAmmessi); //restituisco la lista coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Gets the id.
     *
     * @return the id
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
     * Gets the prodotti.
     *
     * @param cat the cat
     * @return the prodotti
     */
    //date le numerose ed evenutali modifiche riguardanti prodotti e relativi prezzi e relativi fornitori, aggiorno di volta in volta
    public HashMap<String,Float> getProdotti(Catena cat) {
    	HashMap<String,Float> tmpprods = new HashMap<String, Float>();
    	ProdFornito tmp;
    	for(var p : cat.getInventario()) {
    		if(p instanceof ProdFornito) { //se è un prodfornito
    			tmp = (ProdFornito) p;
    			if(tmp.getIDFornitore().equals(this.ID)) { //controllo il suo fornitore
    				tmpprods.put(tmp.getID(), tmp.getPrezzo()); //se matcha lo considero
    			}
    		}
    	}
    	this.prodotti = tmpprods;
        return prodotti;
    }
    
    /**
     * Sets the prodotti.
     *
     * @param prodotti the prodotti
     */
    public void setProdotti(HashMap<String,Float> prodotti) {
        this.prodotti = prodotti;
    }
    
    /**
     * Gets the migliori prodotti.
     *
     * @param cat the cat
     * @return the migliori prodotti
     */
    public HashMap<String, Float> getMiglioriProdotti(Catena cat) {
    	
    	HashMap<String,Float> tmpprods = new HashMap<String, Float>();
    	ProdConcreto tmp;
    	for(var p : cat.getInventario()) {
    		if(p instanceof ProdConcreto) { //se è un prodconcreto
    			tmp = (ProdConcreto) p;
    			if(tmp.getIDMigliorFornitore(cat).equals(this.ID)) { //controllo il suo miglior fornitore
    				tmpprods.put(tmp.getID(), tmp.getPrezzoEffettivoMigliore(cat)); //se matcha lo considero
    			}
    		}
    	}
    	
    	this.miglioriProdotti = tmpprods;
    	
        return miglioriProdotti;
    }
    
    /**
     * Sets the migliori prodotti.
     *
     * @param miglioriProdotti the migliori prodotti
     */
    public void setMiglioriProdotti(HashMap<String,Float> miglioriProdotti) {
        this.miglioriProdotti = miglioriProdotti;
    }
}
