package giocoscudetto.model.api;

import giocoscudetto.model.api.match.Match;

/**
 * This interface represents the boxes of the game, each box has a position, an event, a name, an image and a description.
 */
public interface Box {

    /**
     * this method returns the position of the box.
     * 
     * @return a int that represents the position of the box.
     */
    int getPosition();

    /**
     * this is the method for the event of a single boxe.
     * 
     * @param match the match of the game for change a score o something else.
     */
    void event(Match match);

    /**
     * this method is for get the name of a box.
     * 
     * @return a string that represents the name of the box.
     */
    String getName();

    /**
     * this method is for get the image of a box.
     *
     * @return an Image that represents the image of the box
     */
    String getImage();

    /**
     * this method is for get the description of a box.
     * 
     * @return a string that represents the description of the box.
     */
    String getDescription();
}
