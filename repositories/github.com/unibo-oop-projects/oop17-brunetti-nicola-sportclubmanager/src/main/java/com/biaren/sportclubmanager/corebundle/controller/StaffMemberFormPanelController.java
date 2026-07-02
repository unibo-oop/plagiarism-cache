package com.biaren.sportclubmanager.corebundle.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.StaffMember;
import com.biaren.sportclubmanager.corebundle.model.enums.SportSocietyStaffRole;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.model.EmployeeMember;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.EmployeeMemberFormPanel;
import com.biaren.sportclubmanager.corebundle.views.StaffMemberFormPanel;
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
public class StaffMemberFormPanelController implements FormController {
    
    /*
     * 
     * Optional field: will allow to presents an empty form to insert data, or a filled
     * form to modify data.
     */
    private Optional<StaffMember> entity = Optional.empty();
    private StaffMemberFormPanel form;
    
    /**
     * Using static factory method for creates a form controller for {@link StaffMemberFormPanel}. 
     * Constructor gets the actual form {@link StaffMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link StaffMember}.
     * @return new {@link StaffMemberFormPanelController} 
     */
    public static StaffMemberFormPanelController newControllerEmptyForm(final BaseForm<StaffMember> form) {
        return new StaffMemberFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using static factory method for creates a form controller for {@link StaffMemberFormPanel}. 
     * Constructor gets the actual form {@link StaffMemberFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link StaffMember}.
     * @param entity {@link StaffMember} entity to fill form
     * @return new {@link StaffMemberFormPanelController} 
     */
    public static StaffMemberFormPanelController newControllerFilledForm(final BaseForm<StaffMember> form, final StaffMember entity) {
        return new StaffMemberFormPanelController(form, Optional.of(entity));
    }
    
    private StaffMemberFormPanelController(final BaseForm<StaffMember> form, final Optional<StaffMember> entity) {
        this.form = (StaffMemberFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }
    
    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getStaff().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.STAFF.getNameForPath(), 
                BaseModelImpl.getInstance().getStaffSet());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }

    private StaffMember entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        final StaffMember member = new StaffMember.Builder()
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
                .staffMemberRole(SportSocietyStaffRole.of(
                        data.get(StaffMemberFormPanel.Fields.ROLE.getClassFieldName())))
                .build();
        return member;
    }
    
    private void deleteEntity(final StaffMember entity) {
        System.out.println("delete");
        BaseModelImpl.getInstance().getStaff().remove(entity);
    }
}
