package com.biaren.sportclubmanager.corebundle.controller.interfaces;

import com.biaren.sportclubmanager.corebundle.views.BaseForm;

/**
 * Interfaces for a list view controller: list view refers to a view
 * with a list of value, generally some objects of certain class.
 * @author nbrunetti
 *
 * @param <T> the class of the entity's list.
 */
public interface ListViewController<T> {

    /**
     * Action Handler of a generic "new entity builder".
     * Generates a form for create a new Entity.
     * @return {@link BaseForm} to create a new Entity by fill a form.
     */
    BaseForm<T> addEntityActionHandler();
    
    /**
     * Action Handler of a generic "view entity".
     * Generates a form with all fields filled with entity's value.
     * @param entity to show in the view
     * @return  {@link BaseForm} to show with fields filled with entity's value.
     */
    BaseForm<T> viewEntityActionHandler(final T entity);
    
}
