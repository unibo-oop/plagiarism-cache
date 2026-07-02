package application;
import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import java.util.*;

public class ConsumiImpl implements Consumi, Serializable{
    
    private static final long serialVersionUID = 5850848936451323291L;
    private Hotel hotel;
    
    public ConsumiImpl(Hotel hotel) {
        this.hotel = hotel;
    }
    
    public NavigableMap<Date,HashMap<String,Float>> getConsumi(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound {
    	Date start = null, end = null;
    	if (dates.length == 1) {
    		start = dates[0];
    		end = start;
    	}
    	else if (dates.length == 2) {
    		start = dates[0];
    		end = dates[1];
    	}
        switch(pasto) {
        case COLAZIONE:
            switch(cliente) {
            case ENTRAMBI_COLAZIONE:
                return getColazione(start, end);
            default:
                break;
            }
            break;
        case PRANZO:
            switch(cliente) {
            case ADULTO:
                return getAdultiPranzo(start, end);
            case BAMBINO:
                return getBambiniPranzo(start, end);
            default:
                break;
            }
            break;
        case CENA:
            switch(cliente) {
            case ADULTO:
                return getAdultiCena(start, end);
            case BAMBINO:
                return getBambiniCena(start, end);
            }
            break;
        default:
            break;
        }
        throw new DateNotFound("Consumi non presenti nella data selezionata");
    }
    
    public NavigableMap<Date,HashMap<String,Float>> getConsumoProCapite(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound {
    	Date start = null, end = null;
    	if (dates.length == 1) {
    		start = dates[0];
    		end = start;
    	}
    	else if (dates.length == 2) {
    		start = dates[0];
    		end = dates[1];
    	}
        NavigableMap<Date,HashMap<String,Float>> map = UtilityConsumiPrevisioni.cloneWithoutReference(getConsumi(pasto, cliente, start, end));
        for (Entry<Date, HashMap<String, Float>> mp: map.entrySet()) {
            for (Entry<String, Float> hash: mp.getValue().entrySet()) {
                hash.setValue(hash.getValue() / UtilityConsumiPrevisioni.getnPersonePasto(hotel, mp.getKey(), pasto, cliente));
            }
        }
        return map;
    }
    
    public HashMap<String,Float> getConsumoMedioRangeDate(Pasto pasto, Cliente cliente, Date ...dates) throws DateNotFound {
    	Date start = null, end = null;
    	if (dates.length == 2) {
    		start = dates[0];
    		end = dates[1];
    	}
    	else {
    		throw new DateNotFound("Devi inserire due date");
    	}	
        NavigableMap<Date,HashMap<String,Float>> map = UtilityConsumiPrevisioni.cloneWithoutReference(getConsumi(pasto, cliente, start, end));
        HashMap<String, Float> mappa = new HashMap<String, Float>();
        HashMap<String, Integer> mapCounter = new HashMap<String, Integer>();
        //somma
        for (Map.Entry<Date, HashMap<String, Float>> mp: map.entrySet()) {
            for (Map.Entry<String, Float> hm: mp.getValue().entrySet()) {
                //se nella mappa principale è già presente l'id lo sommo, altrimenti creo un nuovo elemento (nuova key)
                String key = hm.getKey();
                Float value = hm.getValue();
                if(mappa.containsKey(key)) {
                    mappa.put(key, mappa.get(key) + value);
                }
                else {
                    mappa.put(key, value);
                }
                //se nella mappa contatore è già presente l'id incremento il contatore, altrimenti lo inizializzo ad 1
                if (mapCounter.containsKey(key)) {
                    mapCounter.put(key, mapCounter.get(key) + 1);
                }
                else {
                    mapCounter.put(key, 1);
                }
            }
        }
        //media
        mappa.replaceAll((k, v) -> (v / mapCounter.get(k)));
        return mappa;
    }
    
    //metodi privati
    private NavigableMap<Date,HashMap<String,Float>> getColazione(Date start, Date end) throws DateNotFound {
        NavigableMap<Date,HashMap<String,Float>> newMap = hotel.getConsumiColazione().subMap(
                start,
                true, 
                end,
                true
                //true per inculudere le date negli elementi selezionati
        );
        if(newMap.isEmpty()) {
            throw new DateNotFound("Consumi non presenti nelle date selezionate");
        }
        else {
            return newMap;
        }
    }
    
    private NavigableMap<Date,HashMap<String,Float>> getAdultiPranzo(Date start, Date end) throws DateNotFound {
        NavigableMap<Date,HashMap<String,Float>> newMap = hotel.getConsumiAdultiPranzo().subMap(
                start,
                true, 
                end,
                true
                //true per inculudere le date negli elementi selezionati
        );
        if(newMap.isEmpty()) {
            throw new DateNotFound("Consumi non presenti nelle date selezionate");
        }
        else {
            return newMap;
        }
    }
    
    private NavigableMap<Date,HashMap<String,Float>> getBambiniPranzo(Date start, Date end) throws DateNotFound {
        NavigableMap<Date,HashMap<String,Float>> newMap = hotel.getConsumiBimbiPranzo().subMap(
                start,
                true, 
                end,
                true
                //true per inculudere le date negli elementi selezionati
        );
        if(newMap.isEmpty()) {
            throw new DateNotFound("Consumi non presenti nelle date selezionate");
        }
        else {
            return newMap;
        }
    }
    
    private NavigableMap<Date,HashMap<String,Float>> getAdultiCena(Date start, Date end) throws DateNotFound {
        NavigableMap<Date,HashMap<String,Float>> newMap = hotel.getConsumiAdultiCena().subMap(
                start,
                true, 
                end,
                true
                //true per inculudere le date negli elementi selezionati
        );
        if(newMap.isEmpty()) {
            throw new DateNotFound("Consumi non presenti nelle date selezionate");
        }
        else {
            return newMap;
        }
    }
    
    private NavigableMap<Date,HashMap<String,Float>> getBambiniCena(Date start, Date end) throws DateNotFound {
        NavigableMap<Date,HashMap<String,Float>> newMap = hotel.getConsumiBimbiCena().subMap(
                start,
                true, 
                end,
                true
                //true per inculudere le date negli elementi selezionati
        );
        if(newMap.isEmpty()) {
            throw new DateNotFound("Consumi non presenti nelle date selezionate");
        }
        else {
            return newMap;
        }
    }
}