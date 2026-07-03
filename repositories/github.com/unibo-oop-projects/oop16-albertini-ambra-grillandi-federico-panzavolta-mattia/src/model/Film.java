package model;

import utilities.Genre;

/**
 * This interface represents a film.
 * 
 */

public interface Film {

     /**
     * Return the film's name.
     * 
     * @return the name of this film
     */
    String getName();

     /**
     * Return the film's length.
     * 
     * @return the length of this film
     */
    int getLength();

     /**
     * Return the film's genre.
     * 
     * @return the genre of this film
     */
     Genre getGenre();

     /**
     * Return true if the film is 14 rated.
     * 
     * @return true if the film is for people with an age over 14
     */
     Boolean isOver14();

     /**
     * Return true if the film is in 3D.
     * 
     * @return true if the film can be seen in 3D
     */
     Boolean is3D();

}
