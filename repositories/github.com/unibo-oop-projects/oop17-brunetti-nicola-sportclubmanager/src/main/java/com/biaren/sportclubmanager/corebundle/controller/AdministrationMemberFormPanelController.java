package com.biaren.sportclubmanager.corebundle.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.AdministrationMember;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.enums.AdministrationMemberRole;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.AdministrationMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.APersonFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link AdministrationMember} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 *
 */
public class AdministrationMemberFormPanelController implements FormController {

    /*
     * Optional field: will allow to presents an empty form to insert data, or a filled
     * form to modify data.
     */
    private Optional<AdministrationMember> entity = Optional.empty();
    private AdministrationMemberFormPanel form;
    
    /**
     * Using static factory method for creates a form controller for {@link AdministrationMemberFormPanel}. 
     * Constructor gets the actual form {@link AdministrationMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link AdministrationMember}.
     * @return new {@link AdministrationMemberFormPanelController}
     */
    public static AdministrationMemberFormPanelController newControllerEmptyForm(final BaseForm<AdministrationMember> form) {
        return new AdministrationMemberFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using static factory method for creates a form controller for {@link AdministrationMemberFormPanel}. 
     * Constructor gets the actual form {@link AdministrationMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link AdministrationMember}.
     * @param entity {@link AdministrationMember} entity to fill form
     * @return new {@link AdministrationMemberFormPanelController}
     */
    public static AdministrationMemberFormPanelController newControllerFilledForm(final BaseForm<AdministrationMember> form, final AdministrationMember entity) {
        return new AdministrationMemberFormPanelController(form, Optional.of(entity));
    }
    
    private AdministrationMemberFormPanelController(final BaseForm<AdministrationMember> form, Optional<AdministrationMember> entity) {
        this.form = (AdministrationMemberFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }
    
    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getAdministration().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.ADMINISTRATION.getNameForPath(), 
                BaseModelImpl.getInstance().getAdministrationSet());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }
    
    private AdministrationMember entityActionHandler() {     
        final Map<String, String> data = this.form.getFieldsData();
        final AdministrationMember member = new AdministrationMember.Builder()
                .name(data.get(APersonFormPanel.Fields.NAME.getClassFieldName()))
                .surname(data.get(APersonFormPanel.Fields.SURNAME.getClassFieldName()))
                .birthDate(LocalDate.parse(data.get(APersonFormPanel.Fields.BIRTHDATE.getClassFieldName())))
                .birthPlace(data.get(APersonFormPanel.Fields.BIRTHPLACE.getClassFieldName()))
                .nationality(data.get(APersonFormPanel.Fields.NATIONALITY.getClassFieldName()))
                .residenceDistrict(data.get(APersonFormPanel.Fields.DISTRICT.getClassFieldName()))
                .residenceAddress(data.get(APersonFormPanel.Fields.ADDRESS.getClassFieldName()))
                .residenceCity(data.get(APersonFormPanel.Fields.CITY.getClassFieldName()))
                .residenceNation(data.get(APersonFormPanel.Fields.NATION.getClassFieldName()))
                .fiscalCode(data.get(APersonFormPanel.Fields.FISCALCODE.getClassFieldName()))
                .administrationMemberRole(AdministrationMemberRole.of(
                        data.get(AdministrationMemberFormPanel.Fields.ROLE.getClassFieldName())))
                .build();
        return member;
    }
    
    private void deleteEntity(final AdministrationMember entity) {
        BaseModelImpl.getInstance().getAdministration().remove(entity);
    }
}
