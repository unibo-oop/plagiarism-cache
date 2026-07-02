package com.biaren.sportclubmanager.soccerbundle.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.FormController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.services.DataPersistance;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerMatchDataFormPanel;
import com.biaren.sportclubmanager.utility.enums.JsonFileName;
import com.biaren.utility.BiarenPathHandler;

/**
 * Form Controller for {@link SoccerMatchData} entity. 
 * Implements {@link FormController}.
 * Handle users inputs and choices.
 * @author nbrunetti
 *
 */
public class SoccerMatchDataFormPanelController implements FormController{

    private Optional<SoccerMatchData> entity = Optional.empty();
    private SoccerMatchDataFormPanel form;
    
    /**
     * Using factory static method for creates a form controller for {@link SoccerMatchDataFormPanel}. 
     * Constructor gets the actual form {@link SoccerMatchDataFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SoccerMatchData}.
     * @return {@link SoccerMatchDataFormPanelController}
     */
    public static SoccerMatchDataFormPanelController newControllerEmptyForm(final BaseForm<SoccerMatchData> form) {
        return new SoccerMatchDataFormPanelController(form, Optional.empty());
    }
    
    /**
     * Using factory static method for creates a form controller for {@link SoccerMatchDataFormPanel}. 
     * Constructor gets the actual form {@link SoccerMatchDataFormPanel} displayed
     * on the screen and attach this controller class to it.
     * Checks for entity's presence to display or not delete button 
     * (useless in empty for to add new entity).
     * @param form {@link BaseForm} parameterized {@link SoccerMatchData}.
     * @param entity {@link SoccerMatchData} entity to fill form
     * @return {@link SoccerMatchDataFormPanelController}
     */
    public static SoccerMatchDataFormPanelController newControllerFilledForm(final BaseForm<SoccerMatchData> form, final SoccerMatchData entity) {
        return new SoccerMatchDataFormPanelController(form, Optional.of(entity));
    }
    
    private SoccerMatchDataFormPanelController(final BaseForm<SoccerMatchData> form, final Optional<SoccerMatchData> entity) {
        this.form = (SoccerMatchDataFormPanel) form;
        this.form.attachFormController(this);
        this.entity = entity;
        this.form.setDeleteButtonVisibility(this.entity.isPresent());

    }

    /**
     * Performs operation to submit a new entity. To modifiy an old entity, 
     * delete the old one and creates a new entity with new value.
     */
    public void submitActionEvent() {
        this.deleteActionEvent();
        BaseModelImpl.getInstance().getMatches().add(this.entityActionHandler());
        DataPersistance.saveData(
                BiarenPathHandler.getJsonResourcesPathString() + JsonFileName.MATCHES.getNameForPath(), 
                BaseModelImpl.getInstance().getMatchesSet());
    }
    
    /**
     * Delete current entity.
     */
    public void deleteActionEvent() {
        if (this.entity.isPresent()) {
            this.deleteEntity(this.entity.get());
        }
    }
    
    private SoccerMatchData entityActionHandler() {
        final Map<String, String> data = this.form.getFieldsData();
        final Map<String, List<SoccerPlayer>> comboData = this.form.getComboBoxData();
        final SoccerMatchData member = new SoccerMatchData.Builder()
                .date(LocalDate.parse(data.get(SoccerMatchDataFormPanel.Fields.DATE.getClassFieldName())))
                .homeTeam(data.get(SoccerMatchDataFormPanel.Fields.HOMETEAM.getClassFieldName()))
                .awayTeam(data.get(SoccerMatchDataFormPanel.Fields.AWAYTEAM.getClassFieldName()))
                .homeTeamGoal(Integer.parseInt(data.get(SoccerMatchDataFormPanel.Fields.HOMEGOAL.getClassFieldName())))
                .awayTeamGoal(Integer.parseInt(data.get(SoccerMatchDataFormPanel.Fields.AWAYGOAL.getClassFieldName())))
                .goals(comboData.get(SoccerMatchDataFormPanel.Fields.GOALS.getClassFieldName()) != null ?
                        comboData.get(SoccerMatchDataFormPanel.Fields.GOALS.getClassFieldName()) : Collections.emptyList())
                .warnings(comboData.get(SoccerMatchDataFormPanel.Fields.WARNINGS.getClassFieldName()) != null ?
                        comboData.get(SoccerMatchDataFormPanel.Fields.WARNINGS.getClassFieldName()) : Collections.emptyList())
                .expulsions(comboData.get(SoccerMatchDataFormPanel.Fields.EXPULSION.getClassFieldName()) != null ?
                        comboData.get(SoccerMatchDataFormPanel.Fields.EXPULSION.getClassFieldName()) : Collections.emptyList())
                .build();                      
        return member;
    }
    
    private void deleteEntity(final SoccerMatchData entity) {
        System.out.println("delete");
        BaseModelImpl.getInstance().getMatches().remove(entity);
    }

}
