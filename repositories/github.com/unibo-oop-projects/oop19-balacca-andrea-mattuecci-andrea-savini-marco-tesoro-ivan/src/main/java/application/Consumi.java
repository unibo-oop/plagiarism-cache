package application;

import java.io.*;
import java.util.*;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

public interface Consumi {
    
    public enum Cliente {
        ADULTO,
        BAMBINO,
        ENTRAMBI_COLAZIONE
    }
    public enum Pasto {
        COLAZIONE,
        PRANZO,
        CENA
    }
    
    /**
     * @param pasto
     * @param cliente
     * @param dates
     * @return i consumi nelle date selezionate
     */
    public NavigableMap<Date,HashMap<String,Float>> getConsumi(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound;
    
    /**
     * @param pasto
     * @param cliente
     * @param dates
     * @return la media dei consumi nelle date selezionate
     */
    public NavigableMap<Date,HashMap<String,Float>> getConsumoProCapite(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound;
    
    /**
     * @param pasto
     * @param cliente
     * @param dates
     * @return i consumi medi nelle date selezionate
     */
    public HashMap<String,Float> getConsumoMedioRangeDate(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound;
}
