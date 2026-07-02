package pro_ristorante_oop;


import java.util.*;

public interface IOrdine {
	
	public Map<Pair<Integer,Integer>,List<Piatti>> getMap();
	
	 public void cancOrd(Pair<Integer,Integer> key);
	 
	 public void addOrd(List<Piatti> p,Integer i);
    
    public Ordine getAOrd();
	
    public boolean IsEmpty();
    public List<Piatti> getFirstPtOfOrders();
    public Pair<Integer,Integer> getFirstNumOfTableOfOrders();
}
