package utilities.factory;

import java.util.Optional;
/**
 *  Describe a film with some property to save.
 * */
public interface Film {

    /**
     * Get film id.
     * @return id
     */
    int getID();
    /**
     * Get film name.
     * @return name
     */
    String getName();
    /**
     * Get film genre.
     * @return genre
     */
    String getGenre();
    /**
     * Get film duration.
     * @return duration
     */
    int getDuration();
    /**
     * Get optional cover path where image is stored.
     * @return coverImagePath
     */
    Optional<String> getCoverPath(); // returns an optional coverFilmPath
    /**
     * Get film description.
     * @return description
     */
    String getDescription(); 
}
