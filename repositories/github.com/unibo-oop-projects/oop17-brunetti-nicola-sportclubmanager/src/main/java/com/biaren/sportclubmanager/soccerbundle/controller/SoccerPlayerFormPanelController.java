package com.biaren.sportclubmanager.soccerbundle.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.corebundle.views.abstractviews.APersonFormPanel;
import com.biaren.sportclubmanager.soccerbundle.enums.SoccerRole;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerPlayerFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link SoccerPlayer} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 *
 */
public class SoccerPlayerFormPanelController implements FormController{

    private Optional<SoccerPlayer> entity = Optional.empty();
    private SoccerPlayerFormPanel form;
    
    /**
     * Using template method for creates a form controller for {@link SoccerPlayerFormPanel}. 
     * Constructor gets the actual form {@link SoccerPlayerFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SoccerPlayer}.
     * @return {@link SoccerPlayerFormPanelController}
     */
    public static SoccerPlayerFormPanelController newControllerEmptyForm(final BaseForm<SoccerPlayer> form) {
        return new SoccerPlayerFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using template method for creates a form controller for {@link SoccerPlayerFormPanel}. 
     * Constructor gets the actual form {@link SoccerPlayerFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SoccerPlayer}.
     * @param entity {@link SoccerPlayer} entity for show data on modal
     * @return {@link SoccerPlayerFormPanelController}
     */
    public static SoccerPlayerFormPanelController newControllerFilledForm(final BaseForm<SoccerPlayer> form, final SoccerPlayer entity) {
        return new SoccerPlayerFormPanelController(form, Optional.of(entity));
    }
    
    private SoccerPlayerFormPanelController(final BaseForm<SoccerPlayer> form, final Optional<SoccerPlayer> entity) {
        this.form = (SoccerPlayerFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }

    @Override
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getPlayers().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.PLAYERS.getNameForPath(), 
                BaseModelImpl.getInstance().getPlayersSet());
        this.form.setDeleteButtonVisibility(this.entity.isPresent());
    }
    
    @Override
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }

    private SoccerPlayer entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        final SoccerPlayer member = new SoccerPlayer.Builder()
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
                .height(Double.parseDouble(data.get(SoccerPlayerFormPanel.Fields.HEIGHT.getClassFieldName())))
                .weight(Double.parseDouble(data.get(SoccerPlayerFormPanel.Fields.WEIGHT.getClassFieldName())))
                .mainRole(SoccerRole.of(data.get(SoccerPlayerFormPanel.Fields.MAINROLE.getClassFieldName())))
                .build();
        return member;
    }
    
    private void deleteEntity(final SoccerPlayer entity) {
        System.out.println("delete");
        BaseModelImpl.getInstance().getPlayers().remove(entity);
    }
    
}
