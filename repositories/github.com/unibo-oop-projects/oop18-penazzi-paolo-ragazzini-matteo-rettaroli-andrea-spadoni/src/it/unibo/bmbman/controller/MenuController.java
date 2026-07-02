package it.unibo.bmbman.controller;
/**
 * Generic interface for our menù controller.
 * @param <X> an enum with all the options for this menù
 */
public interface MenuController<X> {
    /**
     * Method that notify to the controller which options has been selected.
     * @param optionSelected the option selected
     */
    void setOptionSelected(X optionSelected);
}
