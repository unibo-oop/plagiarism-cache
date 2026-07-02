package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Dispensa.
 */
public class Dispensa implements Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5436301004933117736L;
	
	/** The nome. */
	private String nome; //ID
    
    /** The tipo. */
    private ArrayList<String> tipo; //lista di Tipologia.ID per definire quali prodotti può contenere
    
    /** The contenuto. */
    private HashMap<String,Float> contenuto; //IDProd, quantità frazionaria
    
    /** The carichi. */
    private TreeMap<Date,HashMap<String,Float>> carichi; //IDProd, valore di consumo (frazionario)
    
    /** The scarichi. */
    private TreeMap<Date,HashMap<String,Float>> scarichi;
    
    /** The cat. */
    private Catena cat;
    
    /**
     * Instantiates a new dispensa.
     *
     * @param nome the nome
     * @param cat the cat
     */
    public Dispensa (String nome, Catena cat) {
        this.nome = nome;
        this.tipo = new ArrayList<String>();
        this.contenuto = new HashMap<String, Float>();
        this.carichi = new TreeMap<Date, HashMap<String,Float>>();
        this.scarichi = new TreeMap<Date, HashMap<String,Float>>();
        this.cat=cat;
    }
    
  /**
   * Aggiungi un tipo.
   *
   * @param tipo the tipo
   * @return the boolean
   */
  //utility di aggiunta---------------------------------------
    public Boolean aggiungiUnTipo(String tipo) { //si potrebbe aggiungere un'eccezione
        for(var t : this.tipo) {
            if(t.equals(tipo)) { //se è già presente una tipologia con lo stesso nome
                return false; //restituisco falso e non aggiungo niente
            }
        }
        this.tipo.add(tipo); //altrimenti aggiungo il tipo
        return true; //restituendo true per indicare che è tutto ok
    }
    
    /**
     * Aggiungi contenuti.
     *
     * @param cont the cont
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiContenuti(HashMap<String,Float> cont) {
        float tmp;
        HashMap<String,Float> contNonAmmessi = new HashMap<>();
        Optional<Typology> prod;
        for(var c : cont.keySet()) {
            prod = cat.ottieniDallInventario(c); //prelevo l'oggetto associato all'id dall'inventario
            if(contenuto.containsKey(c)) {
                tmp = contenuto.get(c); //salvo il valore attuale della quantità
                contenuto.put(c, (tmp+cont.get(c))); //modifico il valore aggiungendo la quantità fornita
            }
            else if (!prod.isEmpty() && tipo.contains(((ProdFornito)prod.get()).getPadre().getPadre().getPadre().getID())) {//altrimenti se ho trovato il prod nell'inventario e la tipologia del prodotto rientra nei tipi accettati dalla dispensa
            
                contenuto.put(c, cont.get(c)); //...posso aggiungere prod e quantità
            }
            else {
            
                contNonAmmessi.put(c, cont.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!contNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso
            return Optional.of(contNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di rimozione---------------------------------------
    
    /**
     * Rimuovi un tipo.
     *
     * @param tipo the tipo
     * @return the boolean
     */
    public Boolean rimuoviUnTipo(String tipo) { //si potrebbe aggiungere un'eccezione
        return this.tipo.remove(tipo); //ritorno vero se è andata a buon fine
    }
    
    /**
     * Rimuovi contenuti.
     *
     * @param cont the cont
     * @return the optional
     */
    public Optional<HashMap<String,Float>> rimuoviContenuti(HashMap<String,Float> cont) {
        float tmp;
        HashMap<String,Float> contNonAmmessi = new HashMap<>();
        for(var c : cont.keySet()) {
            if(contenuto.containsKey(c) && cont.get(c) <= contenuto.get(c)) { //se la quantità è consistente
                tmp = contenuto.get(c); //salvo il valore attuale della quantità
                contenuto.put(c, (tmp-cont.get(c))); //modifico il valore sottraendo la quantità fornita
            }
            else {
                contNonAmmessi.put(c, cont.get(c)); //altrimenti, creo la mappa da restituire per indicare quali prodotti non sono ammessi
            }
        }
        
        if(!contNonAmmessi.isEmpty()) { //se qualche prodotto non è ammesso (perché non presente o per quantità troppo alta)
            return Optional.of(contNonAmmessi); //restituisco la mappa coi prodotti non ammessi
        }
        
        return Optional.empty(); //altrimenti restituisco un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Gets the nome.
     *
     * @return the nome
     */
    /*
     * a seguire, metodi getter e setter
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Sets the nome.
     *
     * @param nome the new nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     * Gets the tipologia.
     *
     * @return the tipologia
     */
    public ArrayList<String> getTipologia() {
        return tipo;
    }
    
    /**
     * Sets the tipologia.
     *
     * @param tipo the new tipologia
     */
    public void setTipologia(ArrayList<String> tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Gets the contenuto.
     *
     * @return the contenuto
     */
    public HashMap<String,Float> getContenuto() {
        return contenuto;
    }
    
    /**
     * Sets the contenuto.
     *
     * @param contenuto the contenuto
     */
    public void setContenuto(HashMap<String,Float> contenuto) {
        this.contenuto = contenuto;
    }

    /**
     * Gets the cat.
     *
     * @return the cat
     */
    public Catena getCat() {
        return cat;
    }

    /**
     * Sets the cat.
     *
     * @param cat the new cat
     */
    public void setCat(Catena cat) {
        this.cat = cat;
    }

    /**
     * Gets the carichi.
     *
     * @return the carichi
     */
    public TreeMap<Date,HashMap<String,Float>> getCarichi() {
        return carichi;
    }

    /**
     * Sets the carichi.
     *
     * @param carichi the carichi
     */
    public void setCarichi(TreeMap<Date,HashMap<String,Float>> carichi) {
        this.carichi = carichi;
    }

    /**
     * Gets the scarichi.
     *
     * @return the scarichi
     */
    public TreeMap<Date,HashMap<String,Float>> getScarichi() {
        return scarichi;
    }

    /**
     * Sets the scarichi.
     *
     * @param scarichi the scarichi
     */
    public void setScarichi(TreeMap<Date,HashMap<String,Float>> scarichi) {
        this.scarichi = scarichi;
    }
}
