package com.biaren.sportclubmanager.soccerbundle.controller;

import com.biaren.sportclubmanager.corebundle.controller.interfaces.ListViewController;
import com.biaren.sportclubmanager.corebundle.views.BaseForm;
import com.biaren.sportclubmanager.soccerbundle.models.SoccerPlayer;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerPlayerFormPanel;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerPlayerPanel;

/**
 * Implementation of {@link ListViewController} to control {@link SoccerPlayerPanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class SoccerPlayerPanelController implements ListViewController<SoccerPlayer>{
    
    private final SoccerPlayerPanel view;
    
    /**
     * Creates a new {@link SoccerPlayerPanelController}, 
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public SoccerPlayerPanelController(final SoccerPlayerPanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    @Override
    public BaseForm<SoccerPlayer> addEntityActionHandler() {
        final BaseForm<SoccerPlayer> modal = new SoccerPlayerFormPanel("Aggiungi Calciatore", this.view);
        SoccerPlayerFormPanelController.newControllerEmptyForm(modal);
        return modal;
    }

    @Override
    public BaseForm<SoccerPlayer> viewEntityActionHandler(final SoccerPlayer entity) {
        final BaseForm<SoccerPlayer> modal = new SoccerPlayerFormPanel("Modifica Calciatore", this.view);
        modal.fillFormWithData(entity);
        SoccerPlayerFormPanelController.newControllerFilledForm(modal, entity);
        return modal;
    }
}
