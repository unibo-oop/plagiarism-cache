package pro_ristorante_oop;

import java.util.*;
import java.util.stream.Collectors;

public class Piatti implements IPiatti {

	
	private static final long serialVersionUID = 1973608807164617716L;
	final static private Double IVA = 0.22; 
     private  String nomeP;
     private Double cost;
     private String desc;
     private TypePlate tipo; 
     private Integer ID;
    
    public String getname(){
    	return this.nomeP;
    }
    
    public Double getcost(){
    	return this.cost;
    }
    
    public Integer getID(){
    	return this.ID;
    }
    
    public Piatti(String name,Double cost,Integer ID,TypePlate tipo){
    	if(cost<0 || ID<0|| name==""||tipo == null)
    		throw new IllegalArgumentException();
    	this.nomeP=name;
    	this.cost=cost;
    	this.desc= "";
    	this.tipo=tipo;
    	this.ID=ID;
    	
    }
    
    public void setdesc( String msg){
         this.desc=msg;
    	
    }
    
    public String getdesc(){
        return this.desc;
   	
   }
    public double getcost_iva(){
    	return (cost*IVA)+cost;
    }
    
    public TypePlate getType(){
    	return this.tipo;
    }
    static List<Piatti> sortByType(List<Piatti> pl){
    	if(pl != null){
    	List<Piatti> npl =new LinkedList<>();
    	for(TypePlate p:TypePlate.values())
    		npl.addAll(pl.stream().filter(x-> x.getType()==p).collect(Collectors.toList()));
    	return npl;}
    	throw new IllegalArgumentException();
    }
    
    public String toString(){
    	return ""+getType().toString()+":"+getname();
    			
    }
    
	

}
