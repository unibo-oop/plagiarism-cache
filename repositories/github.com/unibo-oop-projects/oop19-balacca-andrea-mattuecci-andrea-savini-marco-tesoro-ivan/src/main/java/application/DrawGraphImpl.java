package application;

import java.io.Serializable;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import application.Consumi.*;

public class DrawGraphImpl implements DrawGraph, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3549966704106689214L;
	
	private Consumi cons;
	private Catena catena;
	private Previsioni prev;

	//consumi
	public DrawGraphImpl(Consumi cons, Catena catena) {
		this.cons = cons;
		this.catena = catena;
	}
	//previsioni
	public DrawGraphImpl(Previsioni prev, Catena catena) {
		this.prev = prev;
		this.catena = catena;
	}
	
	private NavigableMap<Date, HashMap<String, Float>> getGraphPastoeClienteSingoloConsumi(Date start, Date end, String ID, Pasto pasto, Cliente cliente) throws DateNotFound {
		float count = 0;
		NavigableMap<Date, HashMap<String, Float>> map = new TreeMap<Date, HashMap<String,Float>>(
				UtilityConsumiPrevisioni.cloneWithoutReference(
						cons.getConsumi(pasto, cliente, start, end)));
		NavigableMap<Date, HashMap<String, Float>> ret = new TreeMap<Date, HashMap<String,Float>>();
		for (Map.Entry<Date, HashMap<String, Float>> m: map.entrySet()) {
			for (Map.Entry<String, Float> h: m.getValue().entrySet()) {
				Optional<Typology> tip = catena.ottieniDallInventario(h.getKey());
				if (!tip.isEmpty()) {
					Typology t = tip.get();
					if (t instanceof ProdFornito) {
						ProdFornito p = (ProdFornito) t;
						if (ID.equals(p.getPadre().getPadre().getPadre().getID()) || 
								ID.equals(p.getPadre().getPadre().getID()) || 
								ID.equals(p.getPadre().getID()) || 
								ID.equals(p.getID())) {
							count += h.getValue() * p.getValoreAssoluto();
						}
					}
				}
			}
			HashMap<String, Float> hm = new HashMap<String, Float>();
			hm.put(ID, count);
			ret.put(m.getKey(), hm);
			count = 0;
		}
		return ret;
	}
	private NavigableMap<Date, HashMap<String, Float>> getGraphPastoeClienteSingoloPrevisioni(Date start, Date end, String ID, Pasto pasto, Cliente cliente, int nClienti) throws DateNotFound, ForecastNotAvailable {
		float count = 0;
		NavigableMap<Date, HashMap<String, Float>> map = new TreeMap<Date, HashMap<String,Float>>(
				UtilityConsumiPrevisioni.cloneWithoutReference(
						prev.getPrevisioni(pasto, cliente, nClienti, start, end)));
		NavigableMap<Date, HashMap<String, Float>> ret = new TreeMap<Date, HashMap<String,Float>>();
		for (Map.Entry<Date, HashMap<String, Float>> m: map.entrySet()) {
			for (Map.Entry<String, Float> h: m.getValue().entrySet()) {
				Optional<Typology> tip = catena.ottieniDallInventario(h.getKey());
				if (!tip.isEmpty()) {
					Typology t = tip.get();
					if (t instanceof ProdFornito) {
						//System.out.println("prodFornito");
						ProdFornito p = (ProdFornito) t;
						if (ID.equals(p.getPadre().getPadre().getPadre().getID()) || 
								ID.equals(p.getPadre().getPadre().getID()) || 
								ID.equals(p.getPadre().getID()) || 
								ID.equals(p.getID())) {
							count += h.getValue() * p.getValoreAssoluto();
						}
					}
				}
			}
			HashMap<String, Float> hm = new HashMap<String, Float>();
			hm.put(ID, count);
			ret.put(m.getKey(), hm);
			count = 0;
		}
		return ret;
	}
	public NavigableMap<Date, HashMap<String, Float>> getGraphConsumi(Date start, Date end, String ID) throws DateNotFound {
		NavigableMap<Date, HashMap<String, Float>> ret = new TreeMap<Date, HashMap<String,Float>>();
		NavigableMap<Date, HashMap<String, Float>> tmpNavMap = new TreeMap<Date, HashMap<String,Float>>();
		for (Pasto p: Pasto.values()) {
			for (Cliente c: Cliente.values()) {
				try {
					tmpNavMap.clear();
					tmpNavMap = UtilityConsumiPrevisioni.cloneWithoutReference(getGraphPastoeClienteSingoloConsumi(start, end, ID, p, c));
					if (ret.isEmpty()) {
						ret.putAll(UtilityConsumiPrevisioni.cloneWithoutReference(tmpNavMap));
						tmpNavMap.clear();
					}
				}catch (Exception e) {
					break;
				}
				for (Map.Entry<Date, HashMap<String, Float>> r: ret.entrySet()) {
					for (Map.Entry<Date, HashMap<String, Float>> t: tmpNavMap.entrySet()) {
						Date rKey = r.getKey();
						HashMap<String, Float> rValue = r.getValue();
						Date tKey = t.getKey();
						HashMap<String, Float> tValue = t.getValue();
						if(rKey.equals(tKey)) {
							rValue.forEach((k, v) -> tValue.merge(k, v, Float::sum));
							ret.put(rKey, tValue);
						}
					}
				}
			}
		}
		return ret;
	}
	public NavigableMap<Date, HashMap<String, Float>> getGraphPrevisioni(Date start, Date end, String ID, int nClienti) throws DateNotFound {
		NavigableMap<Date, HashMap<String, Float>> ret = new TreeMap<Date, HashMap<String,Float>>();
		NavigableMap<Date, HashMap<String, Float>> tmpNavMap = new TreeMap<Date, HashMap<String,Float>>();
		for (Pasto p: Pasto.values()) {
			for (Cliente c: Cliente.values()) {
				try {
					tmpNavMap.clear();
					tmpNavMap = UtilityConsumiPrevisioni.cloneWithoutReference(getGraphPastoeClienteSingoloPrevisioni(start, end, ID, p, c, nClienti));
					if (ret.isEmpty()) {
						ret.putAll(UtilityConsumiPrevisioni.cloneWithoutReference(tmpNavMap));
						tmpNavMap.clear();
					}
				}catch (Exception e) {
					break;
				}
				for (Map.Entry<Date, HashMap<String, Float>> r: ret.entrySet()) {
					for (Map.Entry<Date, HashMap<String, Float>> t: tmpNavMap.entrySet()) {
						Date rKey = r.getKey();
						HashMap<String, Float> rValue = r.getValue();
						Date tKey = t.getKey();
						HashMap<String, Float> tValue = t.getValue();
						if(rKey.equals(tKey)) {
							rValue.forEach((k, v) -> tValue.merge(k, v, Float::sum));
							ret.put(rKey, tValue);
						}
					}
				}
			}
		}
		return ret;
	}
}
