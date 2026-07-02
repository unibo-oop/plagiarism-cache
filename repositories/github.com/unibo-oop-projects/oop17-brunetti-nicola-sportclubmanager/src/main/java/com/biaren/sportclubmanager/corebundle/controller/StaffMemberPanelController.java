package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.model.StaffMember;
import com.biaren.sportclubmanager.corebundle.views.StaffMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.StaffMemberPanel;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;

public class StaffMemberPanelController implements ListViewController<StaffMember>{

    private final StaffMemberPanel view;
    
    public StaffMemberPanelController(final StaffMemberPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    public BaseForm<StaffMember> addEntityActionHandler() {
        final BaseForm<StaffMember> modal = new StaffMemberFormPanel("Aggiungi Membro Staff", this.view);
        StaffMemberFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<StaffMember> viewEntityActionHandler(final StaffMember entity) {
        final BaseForm<StaffMember> modal = new StaffMemberFormPanel("Modifica Membro Staff", this.view);
        modal.fillFormWithData(entity);
        StaffMemberFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }

}
