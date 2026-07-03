package it.unibo.coinquify.noticeboard.model;

import java.io.Serializable;
import java.util.Date;

/**
 * a PostIt Implementation.
 */
public class PostItImpl implements PostIt, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String title;
    private String body;
    private final Date dateOfCreation;

    /**
     * Default constructor for implementation of PostIt.
     * @param title of postit
     * @param body of body
     */
    public PostItImpl(final String title, final String body) {
        this.title = title;
        this.body = body;
        this.dateOfCreation = new Date();
    }

    @Override
    public void editPostIt(final String title, final String body) {
        if (title == null || body == null || title.isEmpty()) {
            throw new IllegalArgumentException();
        }
        setTitle(title);
        setBody(body);
    }

    private void setTitle(final String title) {
        this.title = title;
    }

    private void setBody(final String body) {
        this.body = body;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    @Override
    public boolean isValid() {
        return (!this.title.isEmpty() && this.dateOfCreation != null);
    }

}
