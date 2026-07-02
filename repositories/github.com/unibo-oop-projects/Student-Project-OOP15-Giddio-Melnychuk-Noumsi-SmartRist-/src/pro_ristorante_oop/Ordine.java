package pro_ristorante_oop;

import java.util.*;

public class Ordine implements IOrdine {
	Map<Pair<Integer,Integer>,List<Piatti>> orders = new HashMap<>();
    Integer count = 0;
	
	public Map<Pair<Integer,Integer>,List<Piatti>> getMap(){
		
		return  this.orders;
	}
    
    public  void cancOrd(Pair<Integer,Integer> key){
    	if(key!=null)
    	     orders.remove(key);
    	else
    		throw new IllegalArgumentException();
    }
    
    public void addOrd(List<Piatti> p,Integer numT){
    	//Piatti.sortByType(p)
    	if(p !=null || numT>0){
    	 orders.put(new Pair<Integer,Integer>(numT,count),Piatti.sortByType(p));
    	 count++;
    	}
    	else
    		throw new IllegalArgumentException();
    }
    public void addOrd(Ordine o){
    	//Piatti.sortByType(p)
    	if(o!=null){
    	 orders.put(o.getFirstNumOfTableOfOrders(),Piatti.sortByType(o.getFirstPtOfOrders()));
    	 }
    	else
    	throw new IllegalArgumentException();
    }
    
    private List<Piatti> mergeLT(Pair<Integer,Integer> intT){
    	  return this.orders.merge(intT,new LinkedList<>(),(x,y)->x);
    }
    
    public Ordine getAOrd(){
        Ordine o = new Ordine();
        o.addOrd(getFirstPtOfOrders(),getFirstNumOfTableOfOrders().getX());
        orders.remove(orders.keySet().toArray()[0]);
  		return o;
    }
	
    public boolean IsEmpty(){
		return orders.isEmpty();
    	
    }
    public List<Piatti> getFirstPtOfOrders(){
    	
    	return mergeLT(getFirstNumOfTableOfOrders());
    }
    
    public Pair<Integer,Integer> getFirstNumOfTableOfOrders(){
    	
      return this.orders.keySet().iterator().next();
    }
	
}
