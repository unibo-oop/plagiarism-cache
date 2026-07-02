package application;

import java.util.*;
import java.io.*;

// TODO: Auto-generated Javadoc
/**
 * The Class Scarto.
 */
public class Scarto implements Serializable{

    /** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5162917157102276506L;
	
	/** The id. */
	private String ID;
    
    /** The quantita. */
    private Float quantita; //empty = dichiarato ma definibile solo da un livello in basso, "" = dichiarato e definibile solo a quel livello
    
    /** The percentuale. */
    private Boolean percentuale; //definisce se si tratta di uno scarto percentuale (true) o assoluto (false)
    
    /**
     * Instantiates a new scarto.
     *
     * @param ID the id
     */
    public Scarto(String ID) {
        this.ID = ID;
        this.quantita = Float.valueOf(0);
        this.percentuale = false;
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
     * Gets the quantita.
     *
     * @return the quantita
     */
    public Float getQuantita() {
        return quantita;
    }
    
    /**
     * Sets the quantita.
     *
     * @param quantita the new quantita
     */
    public void setQuantita(Float quantita) {
        this.quantita = quantita;
    }
    
    /**
     * Gets the percentuale.
     *
     * @return the percentuale
     */
    public Boolean getPercentuale() {
        return percentuale;
    }
    
    /**
     * Sets the percentuale.
     *
     * @param percentuale the new percentuale
     */
    public void setPercentuale(Boolean percentuale) {
        this.percentuale = percentuale;
    }
}