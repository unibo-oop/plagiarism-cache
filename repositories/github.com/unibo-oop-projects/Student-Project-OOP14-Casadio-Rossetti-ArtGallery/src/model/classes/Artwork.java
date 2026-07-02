package model.classes;

import java.io.Serializable;

import model.interfaces.IArtwork;

/**
 * This class models a generic artwork.
 * @author Elisa Casadio
 *
 */

public class Artwork implements IArtwork, Serializable {

	private static final long serialVersionUID = -152183137102222118L;
	private static final int PRIME = 31;
	
	private final Long code;
	private final String title;
	private final String author;
	private final int year;
	private final String artisticDiscipline;
	private final String technique;
	private final double height;
	private final double width;
	private final double depth;
	private final String description;
	
	/**
	 * Constructor.
	 * @param newCode
	 * 			the artwork's code.
	 * @param newTitle
	 * 			the artwork's title.
	 * @param newAuthor
	 * 			the author's name of this artwork.
	 * @param newYear
	 * 			the year of the artwork.
	 * @param newArtDiscipline
	 * 			the type of artistic discipline of the artwork.
	 * @param newTechnique
	 * 			the painting technique or material of the artwork.
	 * @param newHeight
	 * 			the height of artwork.
	 * @param newWidth
	 * 			the width of artwork.
	 * @param newDepth
	 * 			the depth of artwork.
	 * @param newDescription
	 * 			the description of artwork.
	 */
	public Artwork(final Long newCode, final String newTitle,
			final String newAuthor, final int newYear,
			final String newArtDiscipline, final String newTechnique,
			final double newHeight, final double newWidth,
			final double newDepth, final String newDescription) {
		this.code = newCode;
		this.title = newTitle;
		this.author = newAuthor;
		this.year = newYear;
		this.artisticDiscipline = newArtDiscipline;
		this.technique = newTechnique;
		this.height = newHeight;
		this.width = newWidth;
		this.depth = newDepth;
		this.description = newDescription;
	}
	
	@Override
	public Long getCode() {
		return this.code;
	}
	
	@Override
	public String getTitle() {
		return this.title;
	}
	
	@Override
	public String getAuthor() {
		return this.author;
	}
	
	@Override
	public int getYear() {
		return this.year;
	}
	
	@Override
	public String getArtisticDiscipline() {
		return this.artisticDiscipline;
	}
	
	@Override
	public String getTechnique() {
		return this.technique;
	}
	
	@Override
	public double getHeight() {
		return this.height;
	}
	
	@Override
	public double getWidth() {
		return this.width;
	}
	
	@Override
	public double getDepth() {
		return this.depth;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public int hashCode() {
		int result = 1;
		result = PRIME * result
				+ ((artisticDiscipline == null) ? 0 : artisticDiscipline
						.hashCode());
		result = PRIME * result + ((author == null) ? 0 : author.hashCode());
		result = PRIME * result + ((code == null) ? 0 : code.hashCode());
		long temp;
		temp = Double.doubleToLongBits(depth);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result
				+ ((description == null) ? 0 : description.hashCode());
		temp = Double.doubleToLongBits(height);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result
				+ ((technique == null) ? 0 : technique.hashCode());
		result = PRIME * result + ((title == null) ? 0 : title.hashCode());
		temp = Double.doubleToLongBits(width);
		result = PRIME * result + (int) (temp ^ (temp >>> 32));
		result = PRIME * result + year;
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
		final Artwork other = (Artwork) obj;
		if (artisticDiscipline == null) {
			if (other.artisticDiscipline != null) {
				return false;
			}
		} else if (!artisticDiscipline.equals(other.artisticDiscipline)) {
			return false;
		}
		if (author == null) {
			if (other.author != null) {
				return false;
			}
		} else if (!author.equals(other.author)) {
			return false;
		}
		if (code == null) {
			if (other.code != null) {
				return false;
			}
		} else if (!code.equals(other.code)) {
			return false;
		}
		if (Double.doubleToLongBits(depth) != Double
				.doubleToLongBits(other.depth)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (Double.doubleToLongBits(height) != Double
				.doubleToLongBits(other.height)) {
			return false;
		}
		if (technique == null) {
			if (other.technique != null) {
				return false;
			}
		} else if (!technique.equals(other.technique)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		if (Double.doubleToLongBits(width) != Double
				.doubleToLongBits(other.width)) {
			return false;
		}
		if (year != other.year) {
			return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "Artwork[code=" + this.code + ", title=" + this.title 
				+ ", author=" + this.author + ", year=" + this.year 
				+ ", artistic discipline=" + this.artisticDiscipline 
				+ ", technique=" + this.technique + ", height=" + this.height 
				+ ", width=" + this.width + ", depth=" + this.depth 
				+ ", description=" + this.description + "]";
	}

}
