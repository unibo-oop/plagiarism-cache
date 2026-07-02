package com.biaren.sportclubmanager.corebundle.controller;

import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.FacilityImpl;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.FacilityFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link FacilityImpl} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 * 
 */
public class FacilityFormPanelController implements FormController{
    
    /*
     * Optional field: will allow to presents an empty form to insert data, or a filled
     * form to modify data.
     */
    private Optional<FacilityImpl> entity = Optional.empty();
    private FacilityFormPanel form;
    
    /**
     * Using static factory method to creates a form controller for {@link FacilityFormPanel}. 
     * The constructor gets the actual form {@link FacilityFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link FacilityImpl}.
     * @return {@link FacilityFormPanelController}
     */
    public static FacilityFormPanelController newControllerEmptyForm(final BaseForm<FacilityImpl> form) {
        return new FacilityFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using static factory method for creates a form controller for {@link FacilityFormPanel}. 
     * The constructor gets the actual form {@link FacilityFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link FacilityImpl}
     * @param entity {@link FacilityImpl} entity to fill form
     * @return {@link FacilityFormPanelController}
     */
    public static FacilityFormPanelController newControllerFilledForm(final BaseForm<FacilityImpl> form, final FacilityImpl entity) {
        return new FacilityFormPanelController(form, Optional.of(entity));
    }

    private FacilityFormPanelController(final BaseForm<FacilityImpl> form, Optional<FacilityImpl> entity) {
        this.form = (FacilityFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }

    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getFacilities().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.FACILITIES.getNameForPath(), 
                BaseModelImpl.getInstance().getFacilitiesSet());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }

    private FacilityImpl entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        @SuppressWarnings("rawtypes")
        final FacilityImpl facility = new FacilityImpl.Builder()
                .name(data.get("name"))
                .address(data.get("address"))
                .description(data.get("description"))
                .build();
        return facility;
    }
    
    private void deleteEntity(final FacilityImpl entity) {
        System.out.println("delete");
        BaseModelImpl.getInstance().getFacilities().remove(entity);
    }
}
