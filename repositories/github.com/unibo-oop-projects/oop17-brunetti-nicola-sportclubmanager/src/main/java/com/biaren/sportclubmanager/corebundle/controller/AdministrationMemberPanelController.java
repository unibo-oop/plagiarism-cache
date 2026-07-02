package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.views.AdministrationMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.AdministrationMemberPanel;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;

/**
 * Implementation of {@link ListViewController} to control {@link AdministrationMemberPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class AdministrationMemberPanelController implements ListViewController<AdministrationMember> {
   
    private final AdministrationMemberPanel view;
    
    /**
     * Creates a new {@link AdministrationMemberPanelController}, 
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public AdministrationMemberPanelController(final AdministrationMemberPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }

    @Override
    public BaseForm<AdministrationMember> addEntityActionHandler() {
        final BaseForm<AdministrationMember> modal = new AdministrationMemberFormPanel("Aggiungi Membro Amministrazione", this.view);
        AdministrationMemberFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<AdministrationMember> viewEntityActionHandler(final AdministrationMember entity) {
        final BaseForm<AdministrationMember> modal = new AdministrationMemberFormPanel("Modifica Membro Amministrazione", this.view);
        modal.fillFormWithData(entity);
        AdministrationMemberFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }
}
