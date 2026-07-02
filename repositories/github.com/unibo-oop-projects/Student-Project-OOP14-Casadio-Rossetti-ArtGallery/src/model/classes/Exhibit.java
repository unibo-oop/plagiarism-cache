package model.classes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import model.interfaces.IExhibit;

/**
 * This class models a generic exhibit of the art gallery.
 * @author Elisa Casadio
 *
 */

public class Exhibit implements IExhibit, Serializable {

	private static final long serialVersionUID = -717882106580253340L;
	private static final int PRIME = 31;
	
	private final Long codeExhibit;
	private final String titleExhibit;
	private final String curator;
	private final Calendar beginning;
	private final Calendar end;
	private final List<Long> artworks;
	private final double costExhibit;
	private final double costTicket;
	
	/**
	 * Constructor.
	 * @param newCode
	 * 			the exhibit's code.
	 * @param newTitle
	 * 			the exhibit's title.
	 * @param newCurator
	 * 			the curator's name of this exhibit.
	 * @param newBeginning
	 * 			the commencement date of this exhibit.
	 * @param newEnd
	 * 			the end date of this exhibit.
	 * @param newArtworks
	 * 			the array list of the arworks codes.
	 * @param newCostExhibit
	 * 			the cost of this exhibit.
	 * @param newCostTicket
	 * 			the cost of the ticket of this exhibit.
	 */
	public Exhibit(final Long newCode, final String newTitle, 
			final String newCurator, final Calendar newBeginning, 
			final Calendar newEnd, final List<Long> newArtworks, 
			final double newCostExhibit, final double newCostTicket) {
		this.codeExhibit = newCode;
		this.titleExhibit = newTitle;
		this.curator = newCurator;
		this.beginning = newBeginning;
		this.end = newEnd;
		this.artworks = newArtworks;
		this.costExhibit = newCostExhibit;
		this.costTicket = newCostTicket;
	}
	
	@Override
	public Long getCode() {
		return this.codeExhibit;
	}
	
	@Override
	public String getTitleExhibit() {
		return this.titleExhibit;
	}
	
	@Override
	public String getCurator() {
		return this.curator;
	}
	
	@Override
	public Calendar getBeginning() {
		return this.beginning;
	}
	
	@Override
	public Calendar getEnd() {
		return this.end;
	}
	
	@Override
	public void addArtwork(final Long code) {
		this.artworks.add(code);
	}
	
	@Override
	public List<Long> getArtworks() {
		return this.artworks;
	}
	
	@Override
	public int getNumPieces() {
		return this.artworks.size();
	}
	
	@Override
	public double getCostExhibit() {
		return this.costExhibit;
	}
	
	@Override
	public double getCostTicket() {
		return this.costTicket;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result
				+ ((artworks == null) ? 0 : artworks.hashCode());
		result = PRIME * result
				+ ((beginning == null) ? 0 : beginning.hashCode());
		result = PRIME * result
				+ ((codeExhibit == null) ? 0 : codeExhibit.hashCode());
		long temp;
		temp = Double.doubleToLongBits(costExhibit);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(costTicket);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result + ((curator == null) ? 0 : curator.hashCode());
		result = PRIME * result + ((end == null) ? 0 : end.hashCode());
		result = PRIME * result
				+ ((titleExhibit == null) ? 0 : titleExhibit.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Exhibit other = (Exhibit) obj;
		if (artworks == null) {
			if (other.artworks != null) {
				return false;
			}
		} else if (!artworks.equals(other.artworks)) {
			return false;
		}
		if (beginning == null) {
			if (other.beginning != null) {
				return false;
			}
		} else if (!beginning.equals(other.beginning)) {
			return false;
		}
		if (codeExhibit == null) {
			if (other.codeExhibit != null) {
				return false;
			}
		} else if (!codeExhibit.equals(other.codeExhibit)) {
			return false;
		}
		if (Double.doubleToLongBits(costExhibit) != Double
				.doubleToLongBits(other.costExhibit)) {
			return false;
		}
		if (Double.doubleToLongBits(costTicket) != Double
				.doubleToLongBits(other.costTicket)) {
			return false;
		}
		if (curator == null) {
			if (other.curator != null) {
				return false;
			}
		} else if (!curator.equals(other.curator)) {
			return false;
		}
		if (end == null) {
			if (other.end != null) {
				return false;
			}
		} else if (!end.equals(other.end)) {
			return false;
		}
		if (titleExhibit == null) {
			if (other.titleExhibit != null) {
				return false;
			}
		} else if (!titleExhibit.equals(other.titleExhibit)) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Exhibit[title=" + this.titleExhibit + ", curators=" 
				+ this.curator + ", beginning=" + this.beginning.getTime() 
				+ ", end=" + this.end.getTime() + ", number of pieces=" 
				+ this.getNumPieces() + ", artworks=" + this.artworks 
				+ ", cost exhibit=" + this.costExhibit + "]";
	}

}
