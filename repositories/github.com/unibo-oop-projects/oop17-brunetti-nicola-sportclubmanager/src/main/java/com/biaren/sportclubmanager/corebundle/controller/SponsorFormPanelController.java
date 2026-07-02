package com.biaren.sportclubmanager.corebundle.controller;

import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.SponsorImpl;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.SponsorFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link SponsorImpl} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 * 
 */
public class SponsorFormPanelController implements FormController {

    /*
     * Optional field: will allow to presents an empty form to insert data, or a filled
     * form to modify data.
     */
    private Optional<SponsorImpl> entity = Optional.empty();
    private SponsorFormPanel form;
    
    /**
     * Using static factory method fro creates a form controller for {@link SponsorFormPanel}. 
     * Constructor gets the actual form {@link SponsorFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SponsorImpl}.
     * @return new {@link SponsorFormPanelController} 
     */
    public static SponsorFormPanelController newControllerEmptyForm(final BaseForm<SponsorImpl> form) {
        return new SponsorFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using static factory method for creates a form controller for {@link SponsorFormPanel}. 
     * Constructor gets the actual form {@link SponsorFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SponsorImpl}.
     * @param entity {@link SponsorImpl} entity to fill form
     * @return new {@link SponsorFormPanelController} 
     */
    public static SponsorFormPanelController newControllerFilledForm(final BaseForm<SponsorImpl> form, final SponsorImpl entity) {
        return new SponsorFormPanelController(form, Optional.of(entity));
    }
    
    private SponsorFormPanelController(final BaseForm<SponsorImpl> form, final Optional<SponsorImpl> entity) {
        this.form = (SponsorFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }

    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getSponsor().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.SPONSOR.getNameForPath(), 
                BaseModelImpl.getInstance().getSponsorSet());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }

    private SponsorImpl entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        final SponsorImpl facility = new SponsorImpl.Builder()
                .name(data.get("name"))
                .description(data.get("description"))
                .build();
        return facility;
    }
    
    private void deleteEntity(final SponsorImpl entity) {
        System.out.println("delete");
        BaseModelImpl.getInstance().getSponsor().remove(entity);
    }
}
