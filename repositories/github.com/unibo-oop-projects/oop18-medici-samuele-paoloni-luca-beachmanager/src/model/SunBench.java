package model;

import utils.Position;

/**
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 *         Classe che rappresenta gli stendini
 */
public class SunBench implements Equipment {

	private final double cost = 8.5;
	private boolean isOccupied;
	private Position position;

	/**
	 * Costruttore per lo stendino
	 * 
	 * @param position Posizione dello stendino
	 */
	public SunBench(Position position) {
		this.position = position;
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
