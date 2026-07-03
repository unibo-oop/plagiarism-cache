package it.lttply.view;

import javafx.scene.control.Label;

/**
 * This class represent the label factory.
 */
public interface LabelFactory {

    /**
     * Create the label for simple text.
     *
     * @param text
     *            The text that be displayed
     *
     * @return return new label
     */
    Label createLabelText(String text);

    /**
     * Create the label for title movie.
     *
     * @param text
     *            The text that be displayed
     *
     * @return new label
     */
    Label createLabelTitleMovie(String text);

    /**
     * Create the label for the title's paragraph.
     *
     * @param text
     *            The text that be displayed
     * @return new label
     */
    Label createLabelTitle(String text);

}