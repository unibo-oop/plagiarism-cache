package model.equipment;

import utils.Position;

/**
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 * Classe che rappresenta Ombrelloni in spiaggia
 */
public class Umbrella implements Equipment {

	
	private Position position; 
	private double cost; 
	private boolean isOccupied; 
	
	/**
	 * 
	 * @param position
	 * @param cost
	 */
	public Umbrella(Position position, double cost) {
		this.position = position;
		this.cost = cost;
		this.isOccupied = false;
	}
	

	@Override
	public double getCost() {
		return new Double(this.cost);
	}

	@Override
	public Position getPosition() {
		return new Position(this.position);
	}

	@Override
	public boolean isAlreadyOccupied() {
		return new Boolean(this.isOccupied);
	}

	@Override
	public void rent() {
		this.isOccupied = true;
		
	}

	@Override
	public void giveBack() {
		this.isOccupied = false;
		
	}
	
  


}
