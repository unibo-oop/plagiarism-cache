package application;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.NavigableMap;
import java.util.TreeMap;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

public class UtilityConsumiPrevisioni {
    @SuppressWarnings("unchecked")
    public static NavigableMap<Date,HashMap<String,Float>> cloneWithoutReference(NavigableMap<Date,HashMap<String,Float>> map) {
        return (NavigableMap<Date, HashMap<String, Float>>) new TreeMap<Date, HashMap<String,Float>>(map).clone();
    }
    public static int getnPersonePasto(Hotel hotel, Date data, Pasto pasto, Cliente cliente) throws DateNotFound {
        int codCliente;
        switch(cliente) {
        case ADULTO:
            codCliente = 0;
            break;
        case BAMBINO:
            codCliente = 1;
            break;
        default:
            codCliente = -1;
            break;
        }
        switch(pasto) {
        case COLAZIONE:
            return hotel.getnColazioneClientiGiornaliero().get(data)[codCliente];
        case PRANZO:
            return hotel.getnPranzoClientiGiornaliero().get(data)[codCliente];
        case CENA:
            return hotel.getnCenaClientiGiornaliero().get(data)[codCliente];
        default:
            throw new DateNotFound("Numero persone non presente nelle data selezionata");
        }
    }
}
