package pro_ristorante_oop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OrdineD implements IOrdineD {

	Map<Integer,List<Piatti>> OReady = new HashMap<>();
	
	
	private List<Piatti> mergeLT(Integer intT){
  	   return this.OReady.merge(intT,new LinkedList<>(),(x,y)->x);
    }
	
    public void add(Integer ID,Piatti p){
    	if(ID>0 ||p==null )
    	  mergeLT(ID).add(p);
    	else
    	 throw new IllegalArgumentException();
    }
    
    public Map<Integer,List<Piatti>> getOrdD(){
    	return this.OReady;
    }
    public List<Integer> GetIdOfTables(){
    	return this.OReady.entrySet().stream().map(x->x.getKey()).collect(Collectors.toList());
    }
     
    public List<Piatti> GetPiattiOfATable(Integer id){
    	return this.OReady.entrySet().stream().filter(x->x.getKey()== id).map(x->x.getValue()).collect(Collectors.toList()).get(0);
    }
    
    public void cancOrd(Integer i){
    	this.OReady.remove(i);
    }
    
    public String toString(){
    	String o = "";
    	for(Integer i :this.OReady.entrySet().stream().map(x->x.getKey()).collect(Collectors.toSet())){
    		o="tavolo "+i+"\n";
    		for(Piatti pi : mergeLT(i))
    			o+= pi.toString()+"\n";
    		o+="\n\n";
    	}
    	return o;
    }
    /*public static void main(String [ ] args){
    	OrdineD o= new OrdineD();
        o.add(1,new Piatti("pasta",12.00,1,TypePlate.PRIMO));
        o.add(1,new Piatti("carne",130.05,2,TypePlate.SECONDO));
        o.add(1,new Piatti("dolce",15.45,3,TypePlate.DOLCE));
        System.out.println(o.toString());
    	
    }*/
    
}
