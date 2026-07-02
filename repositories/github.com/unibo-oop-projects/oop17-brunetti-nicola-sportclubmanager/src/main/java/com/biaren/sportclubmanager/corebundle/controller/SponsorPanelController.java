package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.model.SponsorImpl;
import com.biaren.sportclubmanager.corebundle.views.SponsorPanel;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.SponsorFormPanel;

/**
 * Implementation of {@link ListViewController} to control {@link SponsorPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class SponsorPanelController implements ListViewController<SponsorImpl> {

    private final SponsorPanel view;
    
    /**
     * Creates a new {@link SponsorPanelController}, 
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public SponsorPanelController(final SponsorPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    @Override
    public BaseForm<SponsorImpl> addEntityActionHandler() {
        final BaseForm<SponsorImpl> modal = new SponsorFormPanel("Aggiungi Sponsor", this.view);
        SponsorFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<SponsorImpl> viewEntityActionHandler(final SponsorImpl entity) {
        final BaseForm<SponsorImpl> modal = new SponsorFormPanel("Aggiungi Sponsor", this.view);
        modal.fillFormWithData(entity);
        SponsorFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }

}
