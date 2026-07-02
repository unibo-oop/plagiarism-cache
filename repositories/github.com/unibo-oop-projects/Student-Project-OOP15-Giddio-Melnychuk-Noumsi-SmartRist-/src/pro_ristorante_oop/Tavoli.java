package pro_ristorante_oop;

import java.util.LinkedList;
import java.util.List;

public class Tavoli implements ITavoli {
    
	final private static int MAX_Post = 20;
	Integer num_post;
	Integer ID;
	List<Piatti> piattiOrd;
	List<Drink> drink;
	Scontrino sc;
	
	boolean libero;
    
     public Tavoli(int num,int id) throws SurplusSeatsExceptions{
        if (num < MAX_Post){
    	 this.num_post=num;
    	 this.ID = id;
    	 this.libero= true;
    	 this.piattiOrd = new LinkedList<>();
    	 this.drink= new LinkedList<>();
    	 }
         else
    	 throw new SurplusSeatsExceptions();
     }
    public Integer getPost(){
    	 return this.num_post;
     }
	  
     public int getID(){
    	 return this.ID;
     }
	
     public void setOrdine(Ordine o){
    	 if(o==null)
    		 throw new IllegalArgumentException();
    	 
    	 o.addOrd(this.piattiOrd,this.ID);
     }
     
     public void ChoicePlate(LinkedList<Piatti> p){
    	 if(p==null)
    		 throw new IllegalArgumentException();
    	 
    	 this.piattiOrd = p;
     }
     
      public boolean Isfree(){
    	  return this.libero;
      }
      public void occuped(){
    	  this.libero=false;
      }
      
      public void free(){
    	  this.libero=true;
    	  this.drink.clear();
    	  this.piattiOrd.clear();
      }
      
      public void setOrderDrink(LinkedList<Drink> d){
    	  if(d==null)
     		 throw new IllegalArgumentException();
    	  this.drink=d;
      }
      
      public List<Piatti> getPiattiList(){
    	  return this.piattiOrd;
      }
      public List<Drink> getDrinkList(){
    	  return this.drink;
      }
     
      public void setsc(){
    	  sc.setTot(this.piattiOrd, this.drink);
      }
      public String stringSc(){
    	  return sc.toString();
      }
     
}
