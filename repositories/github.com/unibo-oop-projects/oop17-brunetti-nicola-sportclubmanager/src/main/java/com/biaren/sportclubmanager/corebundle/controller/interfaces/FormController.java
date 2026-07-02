package com.biaren.sportclubmanager.corebundle.controller.interfaces;

/**
 * Interfaces for form's controller.
 * Declares basic form's action to handle.
 * @author nbrunetti
 *
 */
public interface FormController {
    
    /**
     * Performs some delete action operation to associate to a form's field.
     */
    void deleteActionEvent();
        
    /**
     * Performs some submit action operation to associate to a form's field.
     */
    void submitActionEvent();
}
