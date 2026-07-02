package com.biaren.sportclubmanager.corebundle.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyEmployee;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.EmployeeMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.APersonFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link EmployeeMember} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 * 
 */
public class EmployeeMemberFormPanelController implements FormController {

    /*
     * Optional field: will allow to presents an empty form to insert data, or a filled
     * form to modify data.
     */
    private Optional<EmployeeMember> entity = Optional.empty();
    private EmployeeMemberFormPanel form;
    
    /**
     * Using static factory method for creates a form controller for {@link EmployeeMemberFormPanel}. 
     * Constructor gets the actual form {@link EmployeeMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link EmployeeMember}.
     * @return new {@link EmployeeMemberFormPanelController} 
     */
    public static EmployeeMemberFormPanelController newControllerEmptyForm(final BaseForm<EmployeeMember> form) {
        return new EmployeeMemberFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using static factory method for creates a form controller for {@link EmployeeMemberFormPanel}. 
     * Constructor gets the actual form {@link EmployeeMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link EmployeeMember}.
     * @param entity {@link EmployeeMember} entity to fill form
     * @return new {@link EmployeeMemberFormPanelController} 
     */
    public static EmployeeMemberFormPanelController newControllerFilledForm(final BaseForm<EmployeeMember> form, final EmployeeMember entity) {
        return new EmployeeMemberFormPanelController(form, Optional.of(entity));
    }
    
    private EmployeeMemberFormPanelController(final BaseForm<EmployeeMember> form, Optional<EmployeeMember> entity) {
        this.form = (EmployeeMemberFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());     
    }
    
    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getEmployees().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.EMPLOYEES.getNameForPath(), 
                BaseModelImpl.getInstance().getEmployeesSet());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }
    
    private EmployeeMember entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        final EmployeeMember member = new EmployeeMember.Builder()
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
                .employeeMemberRole(SportSocietyEmployee.of(
                        data.get(EmployeeMemberFormPanel.Fields.ROLE.getClassFieldName())))
                .build();
        return member;
    }
    
    private void deleteEntity(final EmployeeMember entity) {
        BaseModelImpl.getInstance().getEmployees().remove(entity);
    }
}
