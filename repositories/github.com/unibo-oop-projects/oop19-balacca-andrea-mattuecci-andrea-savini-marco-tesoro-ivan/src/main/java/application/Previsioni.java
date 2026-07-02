package application;

import java.util.Date;
import java.util.HashMap;
import java.util.NavigableMap;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

public interface Previsioni {
    
	/**
     * @param pasto
     * @param cliente
     * @param nClienti
     * @return le previsioni nelle date selezionate
     */
    public NavigableMap<Date,HashMap<String, Float>> getPrevisioni(Pasto pasto, Cliente cliente, int nClienti, Date ...dates) throws ForecastNotAvailable;
}
