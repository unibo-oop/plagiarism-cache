package model.classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.interfaces.IArtGallery;

/**
 * This class models a generic archive of an art gallery.
 * @author Elisa Casadio
 *
 */

public class ArtGallery implements IArtGallery, Serializable {

	private static final long serialVersionUID = 1877939269484883678L;
	private final List<Artwork> artworks;
	private final List<Exhibit> exhibits;
	
	/**
	 * Constructor.
	 */
	public ArtGallery() {
		this.artworks = new ArrayList<>();
		this.exhibits = new ArrayList<>();
	}
	
	@Override
	public void addArtwork(final Artwork artwork) {
		this.artworks.add(artwork);
	}
	
	@Override
	public List<Artwork> getArtwork() {
		return this.artworks;
	}
	
	@Override
	public void addExhibit(final Exhibit exhibit) {
		this.exhibits.add(exhibit);
	}
	
	@Override
	public List<Exhibit> getExhibit() {
		return this.exhibits;
	}

}
