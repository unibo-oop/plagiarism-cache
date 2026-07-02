package com.biaren.sportclubmanager.corebundle.views.interfaces;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;

/**
 * Interfaces for form view, provides 
 * method to attach controller and control the form
 * and to fill form with data from an entity.
 * @author nbrunetti
 *
 * @param <T> type of data to handle
 */
public interface FormView<T> {

    /**
     * Attach controller to form to controls it.
     * @param controller {@link FormController} to control form
     */
    void attachFormController(final FormController controller);
    
    /**
     * Fill form fields with data getted from entity.
     * @param entity to get data to fill form fields.
     */
    void fillFormWithData(final T entity);
        
    /**
     * Default method for create a message with max length of a specified field
     * @param maxLen of specified field
     * @return {@link String} message of max length of a field
     */
    public default String getMaxFieldLengthMessage(final int maxLen) {
        return "Massimo " + maxLen + " caratteri";
    }
    
    /**
     * Default method for create a message to warn the user to insert
     * only integer numbers in field
     * @return {@link String} message of only integer numbers in field
     */
    public default String getOnlyIntegerNumberAcceptedMessage() {
        return "Inserire solo numeri interi";
    }
}
