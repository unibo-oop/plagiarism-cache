package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Prodotto.
 */
public class Prodotto implements Typology, Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3295780539358894721L;
	
	/** The id. */
	private String ID; //così composto: super.ID + this.ID (es. branzino)
    
    /** The scarti. */
    private ArrayList<Scarto> scarti; //lista di scarti (es. 10% testa, 15% cottura)
    
    /** The info. */
    private HashMap<String,String> info; //IDInfo, valore associato (es. COLORE, rosso), a questo livello possono iniziare a essere definiti
    
    /** The padre. */
    private Tipologia padre;
    
    /**
     * Instantiates a new prodotto.
     *
     * @param ID the id
     * @param padre the padre
     */
    public Prodotto(String ID, Tipologia padre) {
        this.ID = ID;
        this.scarti = new ArrayList<Scarto>();
        this.info = new HashMap<String, String>();
        this.padre = padre;
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
            if(!info.containsKey(c) && !padre.getInfo().containsKey(c)) { //se la info non è presente
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
    
    /**
     * Aggiungi scarti.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<ArrayList<Scarto>> aggiungiScarti(ArrayList<Scarto> i) {
        
        ArrayList<Scarto> scartiNonAmmessi = new ArrayList<Scarto>(); //sono quelli scarti che risulterebbero duplicati (es. due "TESTA")
        int cnt = 0;
        for(var s : i) { //per ogni scarto in ingresso
            for(var mys : scarti) { //verifico tutti gli scarti già presenti
                if(s.getID().equals(mys.getID())) { //se l'ID è gia presente
                    cnt++; //incremento il contatore
                }
            }
            if(cnt==0) { //se non ho trovato omonimi
                scarti.add(s); //posso aggiungere lo scarto alla lista
            }
            else {
                scartiNonAmmessi.add(s); //altrimenti no e lo restituirò
            }
            cnt=0; //riazzero contatore
        }
        
        if(!scartiNonAmmessi.isEmpty()) {
            return Optional.of(scartiNonAmmessi);
        }
        
        return Optional.empty();
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
            if(info.containsKey(c) || padre.getInfo().containsKey(c)) { //se la info è presente
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
    
    /**
     * Modifica scarti.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<ArrayList<Scarto>> modificaScarti(ArrayList<Scarto> i) {
        
        ArrayList<Scarto> scartiNonAmmessi = new ArrayList<Scarto>(); //sono quelli scarti che non sono presenti
        int cnt = 0;
        Scarto mys = new Scarto("");
        for(var s : i) { //per ogni scarto in ingresso
        	Iterator it = scarti.iterator();
            while(it.hasNext()) {
            	mys = (Scarto)it.next(); //verifico tutti gli scarti già presenti
                if(s.getID().equals(mys.getID())) { //se l'ID è presente
                    cnt++; //incremento il contatore
                }
            }
            if(cnt==0) { //se non ho trovato omonimi
                scartiNonAmmessi.add(s); //posso aggiungere lo scarto alla lista
            }
            else {
            	scarti.set(scarti.indexOf(mys), s); //aggiorno lo scarto
            }
            cnt=0; //riazzero contatore
        }
        
        if(!scartiNonAmmessi.isEmpty()) {
            return Optional.of(scartiNonAmmessi);
        }
        
        return Optional.empty();
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
    
    /**
     * Rimuovi scarti.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<ArrayList<String>> rimuoviScarti(ArrayList<String> i) { //vuole in pasto una lista di IDScarti
        
        ArrayList<String> scartiNonAmmessi = new ArrayList<>();
        int cnt = 0;
        Scarto mys = new Scarto("");
        for(var s : i) { //per ogni IDScarto in ingresso
        	Iterator it = scarti.iterator();
            while(it.hasNext()) {
            	mys = (Scarto)it.next(); //verifico tutti gli scarti già presenti
                if (s.equals(mys.getID())) { //se l'ID è presente
                    cnt++; //incremento il contatore
                }
            }
            if(cnt==0) { //se non ho trovato omonimi
                scartiNonAmmessi.add(s); //posso aggiungere lo scarto alla lista
            }
            else {
            	scarti.remove(mys);
            }
            cnt=0; //riazzero contatore
        }
        
        if(!scartiNonAmmessi.isEmpty()) {
            return Optional.of(scartiNonAmmessi);
        }
        
        return Optional.empty();
    }
    
    /**
     * Ottieni info.
     *
     * @param i the i
     * @return the array list
     */
    //utility di get----------------------------------
    public ArrayList<Optional<String>> ottieniInfo(ArrayList<String> i) {
        
        ArrayList<Optional<String>> infoRet = padre.ottieniInfo(i);
        int x=0;
        
        for(var c : infoRet) { //per ogni valore restituito
            if(c.isEmpty() || c.get().equals("")) { //se il valore non è stato prelevato dal padre o è vuoto
                if(info.containsKey(i.get(x))) { //controllo se la chiave è presente a questo livello
                    infoRet.set(x, Optional.of(info.get(i.get(x)))); //nel caso la imposto
                }
            }
            x++;
        }
        return infoRet; //restituisco la lista con le info
    }
    
    /**
     * Ottieni scarti.
     *
     * @param i the i
     * @return the array list
     */
    public ArrayList<Optional<Scarto>> ottieniScarti(ArrayList<String> i) {
        
        ArrayList<Optional<Scarto>> scartiRet = new ArrayList<>();
        
        for(var s : i) { //per ogni IDScarto in ingresso
            for(var mys : scarti) { //verifico tutti gli scarti già presenti
                if(s.equals(mys.getID())) { //se l'ID è presente
                    scartiRet.add(Optional.of(mys)); //ritorno lo scarto
                }
                else
                {
                    scartiRet.add(Optional.empty());
                }
            }
        }
        return scartiRet;
    }
    
    /**
     * Ottieni info totali.
     *
     * @return the hash map
     */
    public HashMap<String,String> ottieniInfoTotali() {
    	int cnt = 0;
    	ArrayList<String> tmp = new ArrayList<>();
    	ArrayList<Optional<String>> tmpopt = new ArrayList<>();
    	HashMap<String,String> tmp2 = new HashMap<String, String>(); //strutture d'appoggio
    	Set setkey = new HashSet<>();
    	
    	
    	//tmp2.keySet().addAll(padre.getInfo().keySet());
    	//tmp2.keySet().addAll(info.keySet()); //prelevo le chiavi del padre e attuali
    	
    	setkey.addAll(padre.getInfo().keySet());
    	setkey.addAll(info.keySet()); //prelevo le chiavi del padre e attuali
    	
    	tmp.addAll(List.copyOf(setkey)); //costruisco una list per chiedere tutte le info associate al mio metodo
    	
    	tmpopt = ottieniInfo(tmp); //ottengo i valori
    	
    	for (var i : setkey.toArray()) { //per ogni chiave
    			if(!tmpopt.get(cnt).isEmpty()) { //controllo che non sia vuoto il valore restituito da ottieniInfo()
    				tmp2.put((String)i,tmpopt.get(cnt).get()); //associo il valore restituito
    			}
    			cnt++; //incremento il contatore dei valori restituiti
    	}
    	
        return tmp2;
    }
    
    /*public void aggiornaInfo() { //preleva le info da salvare, dichiarate a livello superiore
        for(var superkey : super.getInfo().keySet()) {
            if(!this.info.containsKey(superkey)) { //se la "superchiave" non è presente nella lista a questo livello
                this.info.put(superkey, ""); //aggiungo chiave e inizializzo il valore a "" (stringa vuota)
            }
        }
    }*/
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    /*
     * a seguire, metodi getter e setter
     */
    public String getID() {
        return this.ID;
    }
    
    /**
     * Sets the id.
     *
     * @param ID the new id
     */
    public void setID(String ID) {
        this.ID = ID; //notare la concatenazione automatica con ID padre
    }
    
    /**
     * Gets the scarti.
     *
     * @return the scarti
     */
    public ArrayList<Scarto> getScarti() {
        return scarti;
    }
    
    /**
     * Sets the scarti.
     *
     * @param scarti the new scarti
     */
    public void setScarti(ArrayList<Scarto> scarti) {
        this.scarti = scarti;
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
    
    /**
     * Gets the padre.
     *
     * @return the padre
     */
    public Tipologia getPadre() {
        return this.padre;
    }
        
}
