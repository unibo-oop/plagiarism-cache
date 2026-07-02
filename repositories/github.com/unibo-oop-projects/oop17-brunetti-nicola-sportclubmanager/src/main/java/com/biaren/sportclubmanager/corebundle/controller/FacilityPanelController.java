package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.FacilityFormPanel;
import com.biaren.sportclubmanager.corebundle.views.FacilityPanel;

/**
 * Implementation of {@link ListViewController} to control {@link FacilityPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class FacilityPanelController implements ListViewController<FacilityImpl> {

    private final FacilityPanel view;
    
    /**
     * Creates a new {@link FacilityFormPanelController},
     * set the view to attach this controller
     * @param view to attach this controller
     */
    public FacilityPanelController(final FacilityPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    @Override
    public BaseForm<FacilityImpl> addEntityActionHandler() {
        final BaseForm<FacilityImpl> modal = new FacilityFormPanel("Aggiungi Struttura", this.view);
//        new FacilityFormPanelController(modal);
        FacilityFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<FacilityImpl> viewEntityActionHandler(final FacilityImpl entity) {
        final BaseForm<FacilityImpl> modal = new FacilityFormPanel("Aggiungi Struttura", this.view);
        modal.fillFormWithData(entity);
//        new FacilityFormPanelController(modal, entity);
        FacilityFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }

}
