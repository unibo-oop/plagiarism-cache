package com.biaren.sportclubmanager.soccerbundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerMatchData;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerMatchDataFormPanel;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerMatchDataPanel;

/**
 * Implementation of {@link ListViewController} to control {@link SoccerMatchDataPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class SoccerMatchDataPanelController implements ListViewController<SoccerMatchData> {

    private final SoccerMatchDataPanel view;
    
    /**
     * Creates a new {@link SoccerMatchDataPanelController}, 
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public SoccerMatchDataPanelController(final SoccerMatchDataPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    /**
     * Show modal to add a new entity
     */
    public BaseForm<SoccerMatchData> addEntityActionHandler() {
        final BaseForm<SoccerMatchData> modal = new SoccerMatchDataFormPanel("Aggiungi partita", this.view);
        SoccerMatchDataFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }
    
    @Override
    public BaseForm<SoccerMatchData> viewEntityActionHandler(final SoccerMatchData entity) {
        final BaseForm<SoccerMatchData> modal = new SoccerMatchDataFormPanel("Modifica Partita", this.view);
        modal.fillFormWithData(entity);
        SoccerMatchDataFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }
}
