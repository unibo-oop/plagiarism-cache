package view;

import java.awt.Image;
import java.io.IOException;

import model.entitiesutil.MappedEntity;

/**
 * Interface that controls the update of the images.
 */
public interface UpdateManager {

	/**
	 * Method that associates its image with the entity
	 * @param entity	is the entity to which the image will be associated
	 * @return			the image associated
	 */
	Image drawEntity(MappedEntity e) throws IOException;

}