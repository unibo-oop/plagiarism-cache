package pro_ristorante_oop;

import java.util.LinkedList;
import java.util.List;

public interface ITavoli {
	public Integer getPost();
	  
    public int getID();
    public void setOrdine(Ordine o);
    
    public void ChoicePlate(LinkedList<Piatti> p);
     public boolean Isfree();
     public void occuped();
     public void free();
     public void setOrderDrink(LinkedList<Drink> d);
     public List<Piatti> getPiattiList();
     
}
