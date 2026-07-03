package it.unibo.coinquify.noticeboard.model;

import java.util.Date;

/**
 * postit interface.
 */
public interface PostIt {

    /**
     * 
     * @return title of postit
     */
    String getTitle();

    /**
     * 
     * @return body of postit
     */
    String getBody();

    /**
     * 
     * @return date of creation of postit
     */
    Date getDateOfCreation();

    /**
     * 
     * @param title new title of postit
     * @param body new body of postit
     */
    void editPostIt(String title, String body);

    /**
     * 
     * @return if postit contains valid fields
     */
    boolean isValid();
}
