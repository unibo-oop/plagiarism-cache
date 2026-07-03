/*
 * Classe che modella un oggetto di tipo costo extra da aggiungere al nostro albergo ( pensato a servizi come parcheggio interno dell'hotel, servizio aria condizionata etc...)
 * 
 */
package model;

import java.io.Serializable;

import model.interfaces.IExtra;

// TODO: Auto-generated Javadoc
/**
 * The Class Extra.
 */
public class Extra implements IExtra, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3185860964611252474L;

	/** The extra name. */
	private String extraName;

	/** The extra cost. */
	private double extraCost;

	/**
	 * Instantiates a new extra.
	 *
	 * @param name
	 *            the name
	 * @param cost
	 *            the cost
	 */
	public Extra(final String name, final double cost) {
		this.extraName = name;
		this.extraCost = cost;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.interfaces.IExtra#getCost()
	 */
	public Double getCost() {
		return new Double(this.extraCost);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.interfaces.IExtra#getName()
	 */
	public String getName() {
		return new String(this.extraName);
	}

	/**
	 * Gets the extra.
	 *
	 * @return the extra
	 */
	public Extra getExtra() {
		return new Extra(this.getName(), this.getCost());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.interfaces.IExtra#setCost(double)
	 */
	public void setCost(final double cost) {
		this.extraCost = cost;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see model.interfaces.IExtra#setName(java.lang.String)
	 */
	public void setName(final String name) {
		this.extraName = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "[VOCE EXTRA] " + this.getName() + " [COSTO] " + this.getCost();
	}

}
