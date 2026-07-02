package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Catena.
 */
public class Catena implements Serializable{
    
    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5605239396996395482L;
	
	/** The alberghi. */
	private ArrayList<Hotel> alberghi;
    
    /** The fornitori. */
    private ArrayList<Fornitore> fornitori;
    
    /** The inventario. */
    private ArrayList<Typology> inventario; //se fosse statico non sarebbe serializzabile
    
    /** The scarti. */
    private ArrayList<Scarto> scarti;
    
    /**
     * Instantiates a new catena.
     */
    public Catena() {
        this.alberghi = new ArrayList<Hotel>();
        this.fornitori = new ArrayList<Fornitore>();
        inventario = new ArrayList<Typology>();
        scarti = new ArrayList<Scarto>();
    }
    
  /**
   * Aggiungi un albergo.
   *
   * @param hotel the hotel
   * @return the optional
   */
  //utility di aggiunta---------------------------------------
    public Optional<Hotel> aggiungiUnAlbergo(Hotel hotel) { //si potrebbe aggiungere un'eccezione
        for(var d : alberghi) {
            if(hotel.getNome().equals(d.getNome())) { //se è già presente un hotel con lo stesso nome
                return Optional.of(d); //restituisco l'hotel già presente, senza aggiungere nulla
            }
        }
        alberghi.add(hotel); //altrimenti aggiungo l'hotel
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
    }
    
    /**
     * Aggiungi un fornitore.
     *
     * @param forn the forn
     * @return the optional
     */
    public Optional<Fornitore> aggiungiUnFornitore(Fornitore forn) { //si potrebbe aggiungere un'eccezione
        for(var d : fornitori) {
            if(forn.getID().equals(d.getID())) { //se è già presente un forn con lo stesso nome
                return Optional.of(d); //restituisco il forn già presente, senza aggiungere nulla
            }
        }
        fornitori.add(forn); //altrimenti aggiungo il forn
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
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
    
    /**
     * Aggiungi all inventario.
     *
     * @param i the i
     * @return the optional
     */
    public Optional<ArrayList<Typology>> aggiungiAllInventario(ArrayList<Typology> i) {
        
        ArrayList<Typology> typoNonAmmesse = new ArrayList<>(); //sono quelle typology che risulterebbero duplicate (es. due "PESCE")
        int cnt = 0;
        for(var s : i) { //per ogni typo in ingresso
            for(var mys : inventario) { //verifico tutte le typo già presenti
                if(s.getID().equals(mys.getID())) { //se l'ID è gia presente
                    cnt++; //incremento il contatore
                }
            }
            if(cnt==0) { //se non ho trovato omonimi
                inventario.add(s); //posso aggiungere il typo alla lista
            }
            else {
                typoNonAmmesse.add(s); //altrimenti no e lo restituirò
            }
            cnt=0; //riazzero contatore
        }
        
        if(!typoNonAmmesse.isEmpty()) {
            return Optional.of(typoNonAmmesse);
        }
        
        return Optional.empty();
    }
    
  //utility di rimozione---------------------------------------
    
    /**
   * Rimuovi un albergo.
   *
   * @param nome the nome
   * @return the boolean
   */
  public Boolean rimuoviUnAlbergo(String nome) { //si potrebbe aggiungere un'eccezione
        int i=0;
        int cnt=0;
        Iterator it = alberghi.iterator();
        while(it.hasNext()) {
        	Hotel d = (Hotel)it.next();
            if(d.getNome().equals(nome)) { //se è già presente un albergo con lo stesso nome
                i = alberghi.indexOf(d); //pesco il suo indice
                cnt++;
            }
        }
        if (cnt!=0) {
        	alberghi.remove(i);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un fornitore.
     *
     * @param ID the id
     * @return the boolean
     */
    public Boolean rimuoviUnFornitore(String ID) { //si potrebbe aggiungere un'eccezione
        int i=0;
        int cnt=0;
        Iterator it = fornitori.iterator();
        while(it.hasNext()) {
        	Fornitore d = (Fornitore)it.next();
            if(d.getID().equals(ID)) { //se è già presente un fornitore con lo stesso nome
                i = fornitori.indexOf(d); //pesco il suo indice
                cnt++;
            }
        }
        if(cnt!=0) {
        	fornitori.remove(i);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi dall inventario.
     *
     * @param ID the id
     * @return the boolean
     */
    public Boolean rimuoviDallInventario(String ID) { //si potrebbe aggiungere un'eccezione
        int i=0;
        int cnt=0;
        Iterator it = inventario.iterator();
        while(it.hasNext()) {
        	Typology d = (Typology)it.next();
            if(d.getID().equals(ID)) { //se è già presente una typo con lo stesso nome
                i = inventario.indexOf(d); //pesco il suo indice
                cnt++;
            }
        }
        if(cnt!=0) {
        	inventario.remove(i);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi uno scarto.
     *
     * @param ID the id
     * @return the boolean
     */
    public Boolean rimuoviUnoScarto(String ID) { //si potrebbe aggiungere un'eccezione
        int i=0;
        int cnt=0;
        Iterator it = scarti.iterator();
        while(it.hasNext()) {
        	Scarto d = (Scarto)it.next();
            if(d.getID().equals(ID)) { //se è già presente uno scarto con lo stesso nome
                i = scarti.indexOf(d); //pesco il suo indice
                cnt++;
            }
        }
        if(cnt!=0) {
        	scarti.remove(i);
            return true;
        }
        return false;
    }
    
  //utility di get----------------------------------
    
    /**
   * Ottieni un albergo.
   *
   * @param nome the nome
   * @return the optional
   */
  public Optional<Hotel> ottieniUnAlbergo(String nome) { //si potrebbe aggiungere un'eccezione
        for(var d : alberghi) {
            if(nome.equals(d.getNome())) { //se è già presente un hotel con lo stesso nome
                return Optional.of(d); //restituisco l'hotel già presente
            }
        }

        return Optional.empty(); //altrimenti restituisco un optional vuoto
    }
    
    /**
     * Ottieni un fornitore.
     *
     * @param ID the id
     * @return the optional
     */
    public Optional<Fornitore> ottieniUnFornitore(String ID) { //si potrebbe aggiungere un'eccezione
        for(var d : fornitori) {
            if(ID.equals(d.getID())) { //se è già presente un forn con lo stesso ID
                return Optional.of(d); //restituisco il forn già presente
            }
        }

        return Optional.empty(); //altrimenti restituisco un optional vuoto
    }
    
    /**
     * Ottieni dall inventario.
     *
     * @param ID the id
     * @return the optional
     */
    public Optional<Typology> ottieniDallInventario(String ID) { //si potrebbe aggiungere un'eccezione
        for(var d : inventario) {
            if(ID.equals(d.getID())) { //se è già presente un hotel con lo stesso nome
                return Optional.of(d); //restituisco l'hotel già presente
            }
        }

        return Optional.empty(); //altrimenti restituisco un optional vuoto
    }
    
    /**
     * Ottieni uno scarto.
     *
     * @param ID the id
     * @return the optional
     */
    public Optional<Scarto> ottieniUnoScarto(String ID) { //si potrebbe aggiungere un'eccezione
        for(var d : scarti) {
            if(ID.equals(d.getID())) { //se è già presente un hotel con lo stesso nome
                return Optional.of(d); //restituisco l'hotel già presente
            }
        }

        return Optional.empty(); //altrimenti restituisco un optional vuoto
    }
    
    /**
     * Gets the alberghi.
     *
     * @return the alberghi
     */
    //a seguire, metodi getter e setter
    public ArrayList getAlberghi() {
        return alberghi;
    }

    /**
     * Sets the alberghi.
     *
     * @param alberghi the new alberghi
     */
    public void setAlberghi(ArrayList alberghi) {
        this.alberghi = alberghi;
    }

    /**
     * Gets the fornitori.
     *
     * @return the fornitori
     */
    public ArrayList getFornitori() {
        return fornitori;
    }

    /**
     * Sets the fornitori.
     *
     * @param fornitori the new fornitori
     */
    public void setFornitori(ArrayList fornitori) {
        this.fornitori = fornitori;
    }

    /**
     * Gets the inventario.
     *
     * @return the inventario
     */
    public ArrayList getInventario() {
        return inventario;
    }

    /**
     * Sets the inventario.
     *
     * @param inventariox the new inventario
     */
    public void setInventario(ArrayList inventariox) {
        inventario = inventariox;
    }
    
    /**
     * Gets the scarti.
     *
     * @return the scarti
     */
    public ArrayList getScarti() {
        return scarti;
    }

    /**
     * Sets the scarti.
     *
     * @param scartix the new scarti
     */
    public void setScarti(ArrayList scartix) {
        scarti = scartix;
    }
}
