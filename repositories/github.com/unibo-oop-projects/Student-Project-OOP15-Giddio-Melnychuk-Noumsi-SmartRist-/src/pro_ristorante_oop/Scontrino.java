package pro_ristorante_oop;

import java.util.List;

public class Scontrino implements IScontrino {
	
	private Double tot;
	List<Piatti> plate;
	List<Drink> drinks;
	
	public void setTot(List<Piatti> p,List<Drink> d){
		if(p==null)
			throw new IllegalArgumentException();
		
		plate=p;
		drinks=d;
		plate.forEach(x-> this.tot+=x.getcost_iva());
		//drinks.forEach(x-> this.tot+=x.getcost_iva());
	}
	
	public Double getTot(){
		return this.tot;
	}
	
    public String ToString(){
    	String scon="SCONTRINO\n\n";
        for(Drink dd : drinks)
        	scon+=dd.getname()+"\n"+"               "+dd.getcost_iva()+"  +";
        for(Piatti pp: plate){
        	scon+=pp.getname()+"\n"+"               "+pp.getcost_iva()+"  +";
        }
    	return  scon;
    }
    
    public void clear(){
    	this.drinks.clear();
    	this.plate.clear();
    	this.tot=0.0;
    }
    
}
