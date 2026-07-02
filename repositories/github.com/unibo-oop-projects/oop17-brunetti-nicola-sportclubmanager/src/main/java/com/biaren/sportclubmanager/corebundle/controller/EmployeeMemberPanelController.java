package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.EmployeeMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.EmployeeMemberPanel;

/**
 * Implementation of {@link ListViewController} to control {@link EmployeeMemberPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class EmployeeMemberPanelController implements ListViewController<EmployeeMember> {
    
    private final EmployeeMemberPanel view;
    
    /**
     * Creates a new {@link EmployeeMemberFormPanelController},
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public EmployeeMemberPanelController(final EmployeeMemberPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }

    @Override
    public BaseForm<EmployeeMember> addEntityActionHandler() {
        final BaseForm<EmployeeMember> modal = new EmployeeMemberFormPanel("Aggiungi Dipendente", this.view);
        EmployeeMemberFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<EmployeeMember> viewEntityActionHandler(final EmployeeMember entity) {
        final BaseForm<EmployeeMember> modal = new EmployeeMemberFormPanel("Aggiungi Dipendente", this.view);
        modal.fillFormWithData(entity);
        EmployeeMemberFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }
}
