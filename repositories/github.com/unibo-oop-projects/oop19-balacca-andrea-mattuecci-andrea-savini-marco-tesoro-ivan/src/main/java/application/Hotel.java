package application;

import java.io.*;
import java.util.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Hotel.
 */
public class Hotel implements Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8117965274203286006L;

	/** The nome. */
    private String nome; //ID
    
    /** The info. */
    private String info; //eventuali info generiche/aggiuntive
    
    /** The dispense. */
    private ArrayList<Dispensa> dispense;
    
    /** The n tot clienti giornaliero. */
    private TreeMap<Date,Integer[]> nTotClientiGiornaliero; //di seguito riportato il formato di data richiesto
    
    /** The n colazione clienti giornaliero. */
    /*
     * String currentDateString = "27/02/2020";
     * SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
     * Date currentDate = sd.parse(currentDateString);
     */
    private TreeMap<Date,Integer[]> nColazioneClientiGiornaliero; //il primo int riservato per n° adulti
    
    /** The n pranzo clienti giornaliero. */
    private TreeMap<Date,Integer[]> nPranzoClientiGiornaliero; //il secondo int per n° bambini
    
    /** The n cena clienti giornaliero. */
    private TreeMap<Date,Integer[]> nCenaClientiGiornaliero;
    
    /** The consumi colazione. */
    private TreeMap<Date,HashMap<String,Float>> consumiColazione; //IDProd, valore di consumo (frazionario)
    
    /** The consumi adulti pranzo. */
    private TreeMap<Date,HashMap<String,Float>> consumiAdultiPranzo; //IDProd, valore di consumo (frazionario)
    
    /** The consumi bimbi pranzo. */
    private TreeMap<Date,HashMap<String,Float>> consumiBimbiPranzo; //IDProd, valore di consumo (frazionario)
    
    /** The consumi adulti cena. */
    private TreeMap<Date,HashMap<String,Float>> consumiAdultiCena; //IDProd, valore di consumo (frazionario)
    
    /** The consumi bimbi cena. */
    private TreeMap<Date,HashMap<String,Float>> consumiBimbiCena; //IDProd, valore di consumo (frazionario)
    
    /**
     * Instantiates a new hotel.
     *
     * @param nome the nome
     */
    public Hotel(String nome) {
        this.nome = nome;
        this.info = "";
        this.dispense = new ArrayList<Dispensa>();
        this.nTotClientiGiornaliero = new TreeMap<Date, Integer[]>();
        this.nColazioneClientiGiornaliero = new TreeMap<Date, Integer[]>();
        this.nPranzoClientiGiornaliero = new TreeMap<Date, Integer[]>();
        this.nCenaClientiGiornaliero = new TreeMap<Date, Integer[]>();
        this.consumiColazione = new TreeMap<Date, HashMap<String,Float>>();
        this.consumiAdultiPranzo = new TreeMap<Date, HashMap<String,Float>>();
        this.consumiBimbiPranzo = new TreeMap<Date, HashMap<String,Float>>();
        this.consumiAdultiCena = new TreeMap<Date, HashMap<String,Float>>();
        this.consumiBimbiCena = new TreeMap<Date, HashMap<String,Float>>();
    }
    
    /**
     * Aggiungi una dispensa.
     *
     * @param disp the disp
     * @return the optional
     */
    //utility di aggiunta---------------------------------------
    public Optional<Dispensa> aggiungiUnaDispensa(Dispensa disp) { //si potrebbe aggiungere un'eccezione
        for(var d : dispense) {
            if(disp.getNome().equals(d.getNome())) { //se è già presente una dispensa con lo stesso nome
                return Optional.of(d); //restituisco la dispensa già presente, senza aggiungere nulla
            }
        }
        dispense.add(disp); //altrimenti aggiungo la dispensa
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
    }
    
    /**
     * Aggiungi un N tot.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the optional
     */
    public Optional<Integer[]> aggiungiUnNTot(Date data, Integer n1, Integer n2) {
        if(nTotClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            Integer[] n = new Integer[2];
            n = nTotClientiGiornaliero.get(data);
            return Optional.of(n); //restituisco i valori relativi a quella data
        }
        Integer[] numeri = new Integer[2];
        numeri[0] = n1;
        numeri[1] = n2;
        nTotClientiGiornaliero.put(data, numeri); //altrimenti aggiungo la data e i valori relativi
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
    }
    
    /**
     * Aggiungi un N pranzo.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the optional
     */
    public Optional<Integer[]> aggiungiUnNPranzo(Date data, Integer n1, Integer n2) {
        if(nPranzoClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            Integer[] n = new Integer[2];
            n = nPranzoClientiGiornaliero.get(data);
            return Optional.of(n); //restituisco i valori relativi a quella data
        }
        Integer[] numeri = new Integer[2];
        numeri[0] = n1;
        numeri[1] = n2;
        nPranzoClientiGiornaliero.put(data, numeri); //altrimenti aggiungo la data e i valori relativi
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
    }
    
    /**
     * Aggiungi un N cena.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the optional
     */
    public Optional<Integer[]> aggiungiUnNCena(Date data, Integer n1, Integer n2) {
        if(nCenaClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            Integer[] n = new Integer[2];
            n = nCenaClientiGiornaliero.get(data);
            return Optional.of(n); //restituisco i valori relativi a quella data
        }
        Integer[] numeri = new Integer[2];
        numeri[0] = n1;
        numeri[1] = n2;
        nCenaClientiGiornaliero.put(data, numeri); //altrimenti aggiungo la data e i valori relativi
        return Optional.empty(); //restituendo un optional vuoto per indicare che è tutto ok
    }
    
    /**
     * Aggiungi un consumo colazione.
     *
     * @param data the data
     * @param cons the cons
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiUnConsumoColazione(Date data, HashMap<String,Float> cons) {
        if(consumiColazione.containsKey(data)) { //se sono già presenti i consumi per una data
            return Optional.of(cons); //restituisco i consumi relativi a quella data
        }
        consumiColazione.put(data, cons); //altrimenti aggiungo i consumi
        return Optional.empty(); //restituendo un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Aggiungi un consumo adulti pranzo.
     *
     * @param data the data
     * @param cons the cons
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiUnConsumoAdultiPranzo(Date data, HashMap<String,Float> cons) {
        if(consumiAdultiPranzo.containsKey(data)) { //se sono già presenti i consumi per una data
            return Optional.of(cons); //restituisco i consumi relativi a quella data
        }
        consumiAdultiPranzo.put(data, cons); //altrimenti aggiungo i consumi
        return Optional.empty(); //restituendo un opzionale vuoto per indicare che tutto è ok
    }
    
    /**
     * Aggiungi un consumo bimbi pranzo.
     *
     * @param data the data
     * @param cons the cons
     * @return the optional
     */
    public Optional<HashMap<String,Float>> aggiungiUnConsumoBimbiPranzo(Date data, HashMap<String,Float> cons) {
        if(consumiBimbiPranzo.containsKey(data)) { //se sono già presenti i consumi per una data
            return Optional.of(cons); //restituisco i consumi relativi a quella data
        }
        consumiBimbiPranzo.put(data, cons); //altrimenti aggiungo i consumi
        return Optional.empty(); //restituendo un opzionale vuoto per indicare che tutto è ok
    }
    
    //utility di modifica---------------------------------------
    
    /**
     * Modifica una dispensa.
     *
     * @param disp the disp
     * @return the boolean
     */
    public Boolean modificaUnaDispensa(Dispensa disp) { //si potrebbe aggiungere un'eccezione
        int i;
        for(var d : dispense) {
            if(disp.getNome().equals(d.getNome())) { //se è già presente una dispensa con lo stesso nome
                i = dispense.indexOf(d); //pesco il suo indice
                dispense.set(i, disp); //la modifico assegnando al suo indice la dispensa modificata
                return true; //restituisco true
            }
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un N tot.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the boolean
     */
    public Boolean modificaUnNTot(Date data, Integer n1, Integer n2) {
        if(nTotClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nTotClientiGiornaliero.get(data)[0]=n1;
            nTotClientiGiornaliero.get(data)[1]=n2; //modifico i valori relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un N pranzo.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the boolean
     */
    public Boolean modificaUnNPranzo(Date data, Integer n1, Integer n2) {
        if(nPranzoClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nPranzoClientiGiornaliero.get(data)[0]=n1;
            nPranzoClientiGiornaliero.get(data)[1]=n2; //modifico i valori relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un N cena.
     *
     * @param data the data
     * @param n1 the n 1
     * @param n2 the n 2
     * @return the boolean
     */
    public Boolean modificaUnNCena(Date data, Integer n1, Integer n2) {
        if(nCenaClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nCenaClientiGiornaliero.get(data)[0]=n1;
            nCenaClientiGiornaliero.get(data)[1]=n2; //modifico i valori relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un consumo colazione.
     *
     * @param data the data
     * @param cons the cons
     * @return the boolean
     */
    public Boolean modificaUnConsumoColazione(Date data, HashMap<String,Float> cons) {
        if(consumiColazione.containsKey(data)) { //se sono già presenti i consumi per una data
            consumiColazione.put(data, cons); //modifico i consumi relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un consumo adulti pranzo.
     *
     * @param data the data
     * @param cons the cons
     * @return the boolean
     */
    public Boolean modificaUnConsumoAdultiPranzo(Date data, HashMap<String,Float> cons) {
        if(consumiAdultiPranzo.containsKey(data)) { //se sono già presenti i consumi per una data
            consumiAdultiPranzo.put(data, cons); //modifico i consumi relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    /**
     * Modifica un consumo bimbi pranzo.
     *
     * @param data the data
     * @param cons the cons
     * @return the boolean
     */
    public Boolean modificaUnConsumoBimbiPranzo(Date data, HashMap<String,Float> cons) {
        if(consumiBimbiPranzo.containsKey(data)) { //se sono già presenti i consumi per una data
            consumiBimbiPranzo.put(data, cons); //modifico i consumi relativi
            return true; //restituisco true
        }
        return false; //altrimenti false
    }
    
    //utility di rimozione---------------------------------------
    
    /**
     * Rimuovi una dispensa.
     *
     * @param nome the nome
     * @return the boolean
     */
    public Boolean rimuoviUnaDispensa(String nome) { //si potrebbe aggiungere un'eccezione
        int i;
        for(var d : dispense) {
            if(d.getNome().equals(nome)) { //se è già presente una dispensa con lo stesso nome
                i = dispense.indexOf(d); //pesco il suo indice
                dispense.remove(i);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Rimuovi un N tot clienti giornaliero.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnNTotClientiGiornaliero(Date data) {
        if(nTotClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nTotClientiGiornaliero.remove(data);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un N pranzo.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnNPranzo(Date data) {
        if(nPranzoClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nPranzoClientiGiornaliero.remove(data);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un N cena.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnNCena(Date data) {
        if(nCenaClientiGiornaliero.containsKey(data)) { //se è già presente una data uguale
            nCenaClientiGiornaliero.remove(data);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un consumo colazione.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnConsumoColazione(Date data) {
        if(consumiColazione.containsKey(data)) { //se è già presente una data uguale
            consumiColazione.remove(data);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un consumo adulti pranzo.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnConsumoAdultiPranzo(Date data) {
        if(consumiAdultiPranzo.containsKey(data)) { //se è già presente una data uguale
            consumiAdultiPranzo.remove(data);
            return true;
        }
        return false;
    }
    
    /**
     * Rimuovi un consumo bimbi pranzo.
     *
     * @param data the data
     * @return the boolean
     */
    public Boolean rimuoviUnConsumoBimbiPranzo(Date data) {
        if(consumiBimbiPranzo.containsKey(data)) { //se è già presente una data uguale
            consumiBimbiPranzo.remove(data);
            return true;
        }
        return false;
    }
    
 //utility di get----------------------------------
    
    /**
  * Ottieni una dispensa.
  *
  * @param nome the nome
  * @return the optional
  */
 public Optional<Dispensa> ottieniUnaDispensa(String nome) { //si potrebbe aggiungere un'eccezione
        for(var d : dispense) {
            if(nome.equals(d.getNome())) { //se è già presente una dispensa con lo stesso nome
                return Optional.of(d); //restituisco la dispensa già presente
            }
        }

        return Optional.empty(); //altrimenti restituisco un optional vuoto
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
     * Gets the info.
     *
     * @return the info
     */
    public String getInfo() {
        return info;
    }
    
    /**
     * Sets the info.
     *
     * @param info the new info
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
    /**
     * Gets the dispense.
     *
     * @return the dispense
     */
    public ArrayList<Dispensa> getDispense() {
        return dispense;
    }
    
    /**
     * Sets the dispense.
     *
     * @param dispense the new dispense
     */
    public void setDispense(ArrayList<Dispensa> dispense) {
        this.dispense = dispense;
    }
    
    /**
     * Gets the n tot clienti giornaliero.
     *
     * @return the n tot clienti giornaliero
     */
    public TreeMap<Date,Integer[]> getnTotClientiGiornaliero() {
        return nTotClientiGiornaliero;
    }
    
    /**
     * Setn tot clienti giornaliero.
     *
     * @param nTotClientiGiornaliero the n tot clienti giornaliero
     */
    public void setnTotClientiGiornaliero(TreeMap<Date,Integer[]> nTotClientiGiornaliero) {
        this.nTotClientiGiornaliero = nTotClientiGiornaliero;
    }
    
    /**
     * Gets the n colazione clienti giornaliero.
     *
     * @return the n colazione clienti giornaliero
     */
    public TreeMap<Date,Integer[]> getnColazioneClientiGiornaliero() {
        return nColazioneClientiGiornaliero;
    }
    
    /**
     * Setn colazione clienti giornaliero.
     *
     * @param nColazioneClientiGiornaliero the n colazione clienti giornaliero
     */
    public void setnColazioneClientiGiornaliero(TreeMap<Date,Integer[]> nColazioneClientiGiornaliero) {
        this.nColazioneClientiGiornaliero = nColazioneClientiGiornaliero;
    }
    
    /**
     * Gets the n pranzo clienti giornaliero.
     *
     * @return the n pranzo clienti giornaliero
     */
    public TreeMap<Date,Integer[]> getnPranzoClientiGiornaliero() {
        return nPranzoClientiGiornaliero;
    }
    
    /**
     * Setn pranzo clienti giornaliero.
     *
     * @param nPranzoClientiGiornaliero the n pranzo clienti giornaliero
     */
    public void setnPranzoClientiGiornaliero(TreeMap<Date,Integer[]> nPranzoClientiGiornaliero) {
        this.nPranzoClientiGiornaliero = nPranzoClientiGiornaliero;
    }
    
    /**
     * Gets the n cena clienti giornaliero.
     *
     * @return the n cena clienti giornaliero
     */
    public TreeMap<Date,Integer[]> getnCenaClientiGiornaliero() {
        return nCenaClientiGiornaliero;
    }
    
    /**
     * Setn cena clienti giornaliero.
     *
     * @param nCenaClientiGiornaliero the n cena clienti giornaliero
     */
    public void setnCenaClientiGiornaliero(TreeMap<Date,Integer[]> nCenaClientiGiornaliero) {
        this.nCenaClientiGiornaliero = nCenaClientiGiornaliero;
    }
    
    /**
     * Gets the consumi colazione.
     *
     * @return the consumi colazione
     */
    public TreeMap<Date,HashMap<String,Float>> getConsumiColazione() {
        return consumiColazione;
    }
    
    /**
     * Sets the consumi colazione.
     *
     * @param consumiColazione the consumi colazione
     */
    public void setConsumiColazione(TreeMap<Date,HashMap<String,Float>> consumiColazione) {
        this.consumiColazione = consumiColazione;
    }
    
    /**
     * Gets the consumi adulti pranzo.
     *
     * @return the consumi adulti pranzo
     */
    public TreeMap<Date,HashMap<String,Float>> getConsumiAdultiPranzo() {
        return consumiAdultiPranzo;
    }
    
    /**
     * Sets the consumi adulti pranzo.
     *
     * @param consumiAdultiPranzo the consumi adulti pranzo
     */
    public void setConsumiAdultiPranzo(TreeMap<Date,HashMap<String,Float>> consumiAdultiPranzo) {
        this.consumiAdultiPranzo = consumiAdultiPranzo;
    }
    
    /**
     * Gets the consumi bimbi pranzo.
     *
     * @return the consumi bimbi pranzo
     */
    public TreeMap<Date,HashMap<String,Float>> getConsumiBimbiPranzo() {
        return consumiBimbiPranzo;
    }
    
    /**
     * Sets the consumi bimbi pranzo.
     *
     * @param consumiBimbiPranzo the consumi bimbi pranzo
     */
    public void setConsumiBimbiPranzo(TreeMap<Date,HashMap<String,Float>> consumiBimbiPranzo) {
        this.consumiBimbiPranzo = consumiBimbiPranzo;
    }
    
    /**
     * Gets the consumi adulti cena.
     *
     * @return the consumi adulti cena
     */
    public TreeMap<Date, HashMap<String, Float>> getConsumiAdultiCena() {
        return consumiAdultiCena;
    }

    /**
     * Sets the consumi adulti cena.
     *
     * @param consumiAdultiCena the consumi adulti cena
     */
    public void setConsumiAdultiCena(TreeMap<Date, HashMap<String, Float>> consumiAdultiCena) {
        this.consumiAdultiCena = consumiAdultiCena;
    }

    /**
     * Gets the consumi bimbi cena.
     *
     * @return the consumi bimbi cena
     */
    public TreeMap<Date, HashMap<String, Float>> getConsumiBimbiCena() {
        return consumiBimbiCena;
    }

    /**
     * Sets the consumi bimbi cena.
     *
     * @param consumiBimbiCena the consumi bimbi cena
     */
    public void setConsumiBimbiCena(TreeMap<Date, HashMap<String, Float>> consumiBimbiCena) {
        this.consumiBimbiCena = consumiBimbiCena;
    }
}