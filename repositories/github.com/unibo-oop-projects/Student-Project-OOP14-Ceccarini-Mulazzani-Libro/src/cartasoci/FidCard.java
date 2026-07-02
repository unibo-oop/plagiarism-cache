package cartasoci;

import java.io.Serializable;

/**
 * Basic class of a Fidelity Card itself.
 * 
 * @author Alberto Mulazzani
 *
 */
public class FidCard implements IFidCard, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1960894684015828412L;
	private Integer points;
	
	/**
	 * 
	 */
	public FidCard() {
		this.points = 0;
	}

	@Override
	public void addPoints(final int npoints) {
		this.points += npoints;

	}

	@Override
	public Integer getPoints() {
	
		return this.points;
	}


}
