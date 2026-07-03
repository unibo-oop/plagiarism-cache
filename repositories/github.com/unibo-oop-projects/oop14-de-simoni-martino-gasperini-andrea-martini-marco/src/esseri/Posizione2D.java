package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 *
 * I campi sono double per dare la massima libert� al programmatore del controller. Consiglio l'utilizzo di int e una griglia di piante e zombie.
 *
 *
 */

/*
 * Classe che definisce la posizione di un essere e restituisce una nuova posizione 
 * Ho diviso con x e y perche puo capitare che uno Zombi vada indietro di e ho preferito dividerle
 * 
 */
public class Posizione2D {
		
	  private double x;
	  private double y;
	
	     
	  public Posizione2D(double x, double y){
		  
		  this.x = x;
		  this.y = y;
	  }
	  
	 
	public Posizione2D(double x){
		
		this.x = x;
	} 
	  
	  		
	public Posizione2D sumPositions(Posizione2D pos){
		
		return new Posizione2D(x + pos.x,y + pos.y);
		
	}
	
	public Posizione2D setX(final double x){
		
		return new Posizione2D(this.x+x);
		
	}
	
	public Posizione2D setY(final double y){
		
		return new Posizione2D(this.y+y);
	}

    public double getX(){
		
		return this.x;
	}
	
	public double gety(){
		
		return this.y;
	}
	
	public String getPosition(){
		
		return "["+ this.x +"" + this.y +"]"; 
	}
		
}
