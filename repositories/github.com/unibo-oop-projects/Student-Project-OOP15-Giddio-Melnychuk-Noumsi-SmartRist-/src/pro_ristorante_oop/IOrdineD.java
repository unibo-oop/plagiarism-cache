package pro_ristorante_oop;

import java.util.List;
import java.util.Map;

public interface IOrdineD {
	public void add(Integer ID,Piatti p);
	
	public Map<Integer,List<Piatti>> getOrdD();
	
	 public List<Integer> GetIdOfTables();
	 
	 public List<Piatti> GetPiattiOfATable(Integer id);
	 
	 public void cancOrd(Integer i);
	 
	 public String toString();
}
