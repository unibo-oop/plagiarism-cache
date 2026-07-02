package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class ProdFornito.
 */
public class ProdFornito implements Typology, Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6384574810421388633L;
	
	/** The id. */
	private String ID; //così composto: super.ID + this.ID (es. branzino100gGeloService)
    
    /** The scarti. */
    private ArrayList<Scarto> scarti; //lista di scarti (es. 10% testa, 15% cottura)
    
    /** The info. */
    private HashMap<String,String> info; //IDInfo, valore associato (es. COLORE, rosso), a questo livello possono iniziare a essere definiti
    
    /** The ID fornitore. */
    private String IDFornitore; //ID del fornitore
    
    /** The prezzo. */
    private Float prezzo; //prezzo per questo prodotto
    
    /** The prezzo effettivo. */
    private Float prezzoEffettivo; //prezzoEffettivo = prezzo + prezzo/100*(100-percentualeNetto)
    
    /** The valore assoluto. */
    private Float valoreAssoluto; //a seconda del prodotto, espresso in kg/litri
    
    /** The valore netto. */
    private Float valoreNetto; //valoreAssoluto - scarto1.quantita - ... - scartoN.quantita (con scarto.percentuale = false)
    
    /** The percentuale netto. */
    private Float percentualeNetto; //valoreNetto/valoreAssoluto*100 OPPURE 100 - scarto1.quantita - ... - scartoN.quantita (con scarto.percentuale = true)
    
    /** The padre. */
    private ProdConcreto padre;
    
    /**
     * Instantiates a new prod fornito.
     *
     * @param ID the id
     * @param padre the padre
     */
    public ProdFornito(String ID, ProdConcreto padre) {
        this.ID = ID;
        this.scarti = new ArrayList<Scarto>();
        this.info = new HashMap<String, String>();
        this.padre = padre;
        this.IDFornitore = "";
        this.prezzo = Float.valueOf(0);
        this.prezzoEffettivo = Float.valueOf(0);
        this.valoreAssoluto = Float.valueOf(0);
        this.valoreNetto = Float.valueOf(0);
        this.percentualeNetto = Float.valueOf(0);
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
            if(!info.containsKey(c) && !padre.getInfo().containsKey(c) && !padre.getPadre().getInfo().containsKey(c) && !padre.getPadre().getPadre().getInfo().containsKey(c)) { //se la info non è presente
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
            for(var mys : padre.getPadre().getScarti()) { //verifico tutti gli scarti già presenti nel padre del padre
                if(s.getID().equals(mys.getID())) { //se l'ID è gia presente
                    cnt++; //incremento il contatore
                }
            }
            for(var mys : padre.getScarti()) { //verifico tutti gli scarti già presenti nel padre
                if(s.getID().equals(mys.getID())) { //se l'ID è gia presente
                    cnt++; //incremento il contatore
                }
            }
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
            if(info.containsKey(c) || padre.getInfo().containsKey(c) || padre.getPadre().getInfo().containsKey(c) || padre.getPadre().getPadre().getInfo().containsKey(c)) { //se la info è presente
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
            	mys = (Scarto)it.next(); //verifico tutti gli scarti già presenti a questo livello
                if(s.getID().equals(mys.getID())) { //se l'ID è presente
                    cnt++; //incremento il contatore
                }
            }
            
            if(cnt!=0) {
            	scarti.set(scarti.indexOf(mys), s); //aggiorno lo scarto
            }
            
            if(cnt==0) { //se non ho trovato omonimi, cerco nel padre
                for(var myss : padre.getScarti()) { //verifico tutti gli scarti già presenti
                    if(s.getID().equals(myss.getID()) && myss.getQuantita() == 0) { //se l'ID è presente e la quantità non è definita
                    	Scarto nuovos = new Scarto(myss.getID()); //creo un nuovo oggetto copia
                    	nuovos.setPercentuale(s.getPercentuale());
                    	nuovos.setQuantita(s.getQuantita());
                        scarti.add(nuovos); //aggiungo lo scarto nuovo a questo livello e lo definisco
                        cnt++; //incremento il contatore
                    }
                }
            }
            
            if(cnt==0) { //se non ho trovato omonimi, cerco nel padre del padre
                for(var myss : padre.getPadre().getScarti()) { //verifico tutti gli scarti già presenti
                    if(s.getID().equals(myss.getID()) && myss.getQuantita() == 0) { //se l'ID è presente e la quantità non è definita
                        //scarti.add(mys); //aggiungo lo scarto a questo livello e lo definisco
                    	Scarto nuovos = new Scarto(myss.getID()); //creo un nuovo oggetto copia
                    	nuovos.setPercentuale(s.getPercentuale());
                    	nuovos.setQuantita(s.getQuantita());
                        scarti.add(nuovos); //aggiungo lo scarto nuovo a questo livello e lo definisco
                        cnt++; //incremento il contatore
                    }
                }
            }
            
            if(cnt==0) { //se non ho trovato omonimi nemmeno nel padre del padre
                scartiNonAmmessi.add(s); //posso aggiungere lo scarto alla lista di non ammessi
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
                if(s.equals(mys.getID())) { //se l'ID è presente
                    cnt++; //incremento il contatore
                }
            }
            if(cnt==0) { //se non ho trovato omonimi
                scartiNonAmmessi.add(s); //posso aggiungere lo scarto alla lista di non ammessi
            }
            else {
            	scarti.remove(mys); //rimuovo lo scarto
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
        
        ArrayList<Optional<Scarto>> infoRet = padre.ottieniScarti(i);
        int x=0;
        for(var s : infoRet) { //per ogni valore restituito
            if(s.isEmpty() || (!s.isEmpty() && s.get().getQuantita() == 0)) { //se lo scarto non è stato prelevato dal padre o se nel padre vale zero
                for(var mys : scarti) { //verifico tutti gli scarti già presenti a questo livello
                    if(i.get(x).equals(mys.getID())) { //se l'ID è presente
                        infoRet.set(x, Optional.of(mys)); //imposto lo scarto da ritornare
                    }
                }
            }
            x++;
        }
        return infoRet; //restituisco la lista con gli scarti
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
    	
    	setkey.addAll(padre.getPadre().getPadre().getInfo().keySet());
    	setkey.addAll(padre.getPadre().getInfo().keySet());
    	setkey.addAll(padre.getInfo().keySet());
    	setkey.addAll(info.keySet()); //prelevo le chiavi del bisnonno, dal nonno, del padre e attuali
    	
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
    
    /**
     * Ottieni scarti totali.
     *
     * @return the array list
     */
    public ArrayList<Scarto> ottieniScartiTotali() {
    	int cnt = 0;
    	ArrayList<String> tmp = new ArrayList<>();
    	ArrayList<Optional<Scarto>> tmpopt = new ArrayList<>();
    	ArrayList<Scarto> tmp2 = new ArrayList<Scarto>(); //strutture d'appoggio
    	Set setkey = new HashSet<>();
    	
    	
    	//tmp2.keySet().addAll(padre.getInfo().keySet());
    	//tmp2.keySet().addAll(info.keySet()); //prelevo le chiavi del padre e attuali
    	
    	for(var s : padre.getPadre().getScarti()) {
    		setkey.add(s.getID());
    	}
    	
    	for(var s : padre.getScarti()) {
    		setkey.add(s.getID());
    	}
    	
    	for(var s : scarti) {
    		setkey.add(s.getID());
    	}
    	
    	//gay.addAll(padre.getInfo().keySet());
    	//gay.addAll(info.keySet()); //prelevo le chiavi del padre e attuali
    	
    	tmp.addAll(List.copyOf(setkey)); //costruisco una list per chiedere tutte gli scarti al mio metodo
    	
    	tmpopt = ottieniScarti(tmp); //ottengo gli scarti
    	
    	if(!tmpopt.isEmpty()) {
    		for (var i : setkey.toArray()) { //per ogni id
    			tmp2.add(tmpopt.get(cnt).get()); //associo il valore restituito
    			cnt++; //incremento il contatore dei valori restituiti
    		}
    	}
    	
    	
        return tmp2;
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
     * Gets the ID fornitore.
     *
     * @return the ID fornitore
     */
    public String getIDFornitore() {
        return IDFornitore;
    }
    
    /**
     * Sets the ID fornitore.
     *
     * @param iDFornitore the new ID fornitore
     */
    public void setIDFornitore(String iDFornitore) {
        IDFornitore = iDFornitore;
    }

    /**
     * Gets the prezzo.
     *
     * @return the prezzo
     */
    public Float getPrezzo() {
        return prezzo;
    }
    
    /**
     * Sets the prezzo.
     *
     * @param prezzo the new prezzo
     */
    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    /**
     * Gets the prezzo effettivo.
     *
     * @return the prezzo effettivo
     */
    public Float getPrezzoEffettivo() {
    	if (prezzo != Float.valueOf(0) && getPercentualeNetto() != Float.valueOf(0)) {
    		prezzoEffettivo = prezzo + prezzo/100*(100-getPercentualeNetto()); //se netto = 100%, il prezzo effettivo = prezzo
    	}
        return prezzoEffettivo;
    }
    
    /**
     * Sets the prezzo effettivo.
     *
     * @param prezzoEffettivo the new prezzo effettivo
     */
    public void setPrezzoEffettivo(Float prezzoEffettivo) {
        this.prezzoEffettivo = prezzoEffettivo;
    }

    /**
     * Gets the valore assoluto.
     *
     * @return the valore assoluto
     */
    public Float getValoreAssoluto() {
        return valoreAssoluto;
    }
    
    /**
     * Sets the valore assoluto.
     *
     * @param valoreAssoluto the new valore assoluto
     */
    public void setValoreAssoluto(Float valoreAssoluto) {
        this.valoreAssoluto = valoreAssoluto;
    }

    /**
     * Gets the valore netto.
     *
     * @return the valore netto
     */
    public Float getValoreNetto() {
    	Float tmpnetto = this.valoreAssoluto;
    	
    	if(this.valoreAssoluto != 0 && !this.ottieniScartiTotali().isEmpty()) {
    		var sca = this.ottieniScartiTotali();//se il valore assoluto è definito
    		for(var s : sca) {															//e sono presenti scarti
    			if(s.getQuantita() != 0) { //e lo scarto è definito
    				if(s.getPercentuale() == false) { //in maniera assoluta
    					tmpnetto = tmpnetto - s.getQuantita();
    				}
    				else { //in maniera percentuale
    					tmpnetto = tmpnetto - this.valoreAssoluto/Float.valueOf(100)*s.getQuantita();
    				}
    			}
    		}
    	}
    	
    	valoreNetto = tmpnetto; //restituisco il valore netto (in assenza di scarti è = valoreAssoluto)
        return valoreNetto;
    }
    
    /**
     * Sets the valore netto.
     *
     * @param valoreNetto the new valore netto
     */
    public void setValoreNetto(Float valoreNetto) {
        this.valoreNetto = valoreNetto;
    }

    /**
     * Gets the percentuale netto.
     *
     * @return the percentuale netto
     */
    public Float getPercentualeNetto() {
    	
    	if(getValoreNetto() != 0 && valoreAssoluto != 0) { //se ho i dati su cui calcolare
    		percentualeNetto = (Float) getValoreNetto()/valoreAssoluto*Float.valueOf(100); //restituisco il valore
    	}
    	else {
    		percentualeNetto = Float.valueOf(100); // altrimenti restituisco 100 (netto = assoluto)
    	}
    	
        return percentualeNetto;//altrimenti presumiblimente 100 (non ci sono scarti)
    }
    
    /**
     * Sets the percentuale netto.
     *
     * @param percentualeNetto the new percentuale netto
     */
    public void setPercentualeNetto(Float percentualeNetto) {
        this.percentualeNetto = percentualeNetto;
    }
    
    /**
     * Gets the padre.
     *
     * @return the padre
     */
    public ProdConcreto getPadre() {
        return this.padre;
    }
}
