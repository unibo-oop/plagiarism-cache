package pro_ristorante_oop;

import java.util.*;

public class Cameriere extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7852913670706386173L;
	boolean occupato=false;
	List<Piatti> piatti= new LinkedList<>();
	List<Drink> drink=new LinkedList<>();
	int NumT;
	
	public Cameriere(String name , String surname , boolean sex ){
		super(name,surname,sex);
		this.occupato=false;
	}
     
	public void piattoAR(){
		this.occupato=true;
	}
	public void cameriereInAtt(){
		this.occupato=false;
		piatti.clear();
	}
	public List<Piatti>  getPiatti(){
		return this.piatti;
	}
	
	public void setPiatti(Integer IDTable,OrdineD o){ //controllo su ordine se ce solo un oggetto nella Map
		
		if(o.GetPiattiOfATable(IDTable).isEmpty())
			o.cancOrd(IDTable);
		
		this.NumT = IDTable;
	    this.piatti.add(o.GetPiattiOfATable(IDTable).get(0));
		 o.GetPiattiOfATable(IDTable).remove(0);
	}
	public void setDrink(List<Drink>d){
		this.drink=d;
		
	}
	
}
