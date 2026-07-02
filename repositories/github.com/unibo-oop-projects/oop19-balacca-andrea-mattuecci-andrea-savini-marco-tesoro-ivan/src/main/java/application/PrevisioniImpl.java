package application;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import application.Consumi.Cliente;
import application.Consumi.Pasto;

public class PrevisioniImpl implements Previsioni, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2433070113321601164L;
    
    private Consumi cons;
    
    public PrevisioniImpl(Consumi cons) {
        this.cons = cons;
    }
    
    @Override
    public NavigableMap<Date,HashMap<String, Float>> getPrevisioni(Pasto pasto, Cliente cliente, int nClienti, Date ...dates) throws ForecastNotAvailable {
    	boolean check = true;
    	Date start = null, end = null;
    	if (dates.length == 1) {
    		start = dates[0];
    		end = start;
    	}
    	else if (dates.length == 2) {
    		start = dates[0];
    		end = dates[1];
    	}
    	NavigableMap<Date, HashMap<String, Float>> ret = new TreeMap<Date, HashMap<String,Float>>();
    	for (Date d = start; d.before(end) || d.equals(end); d = UtilityDate.sumDay(d, 1)) {
    		NavigableMap<Date, HashMap<String, Float>> map = new TreeMap<Date, HashMap<String,Float>>();
            int count = 1;
            HashMap<String, Float> mappa = new HashMap<String, Float>();
            HashMap<String, Integer> mapCounter = new HashMap<String, Integer>();
            do {
                try {
                    map.putAll(UtilityConsumiPrevisioni.cloneWithoutReference(
                            cons.getConsumoProCapite(
                            		pasto, cliente, UtilityDate.subtractYear(d, count++))));
                }catch (Exception e) {
                    break;
                }
            }while(true);
            count = 1;
            if (map.isEmpty()) {
                //throw new ForecastNotAvailable("Impossibile effettuare previsioni");
                do {
                    try {
                        map.putAll(UtilityConsumiPrevisioni.cloneWithoutReference(
                                cons.getConsumoProCapite(
                                		pasto, cliente, UtilityDate.subtractMonth(d, count++))));
                    }catch (Exception e) {
                        break;
                    }
                }while(true);   
            }
            count = 1;
            if (map.isEmpty()) {
                //throw new ForecastNotAvailable("Impossibile effettuare previsioni");
                do {
                    try {
                        map.putAll(UtilityConsumiPrevisioni.cloneWithoutReference(
                                cons.getConsumoProCapite(
                                		pasto, cliente, UtilityDate.subtractDay(d, count++))));
                    }catch (Exception e) {
                        break;
                    }
                }while(true);
            }
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
            if (!mappa.isEmpty()) {
            	mappa.replaceAll((k, v) -> (v * nClienti));
                ret.put(d, mappa);
            }
    	}
    	if (ret.isEmpty()) {
            throw new ForecastNotAvailable("Impossibile effettuare previsioni");
        }
        return ret;
    }
}
