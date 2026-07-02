package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.adminbundle.controller.LoginController;
import com.biaren.sportclubmanager.corebundle.controller.interfaces.HomeController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.AdministrationMemberPanel;
import com.biaren.sportclubmanager.corebundle.views.EmployeeMemberPanel;
import com.biaren.sportclubmanager.corebundle.views.FacilityPanel;
import com.biaren.sportclubmanager.corebundle.views.HomePanel;
import com.biaren.sportclubmanager.corebundle.views.SponsorPanel;
import com.biaren.sportclubmanager.corebundle.views.StaffMemberPanel;
import com.biaren.sportclubmanager.corebundle.views.StatisticsPanel;
import com.biaren.sportclubmanager.soccerbundle.controller.SoccerMatchDataPanelController;
import com.biaren.sportclubmanager.soccerbundle.controller.SoccerPlayerPanelController;
import com.biaren.sportclubmanager.soccerbundle.views.MatchListPanel;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerMatchDataPanel;
import com.biaren.sportclubmanager.soccerbundle.views.SoccerPlayerPanel;
import com.biaren.utility.BiarenUtil;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Implementation of {@link HomeController} to control {@link HomePanel}.
 * Handles action on specific panel setted with constructor.
 * @author nbrunetti
 *
 */
public class HomeControllerImpl implements HomeController{
    
    private final HomePanel view;
    
    /**
     * Creates a {@link HomeControllerImpl},
     * set the view to attach this controller.
     * @param view to attach this controller.
     */
    public HomeControllerImpl(final HomePanel view) {
        this.view = view;
        this.view.attachViewObserver(this);
    }
    
    /**
     * Performs a logout to current user.
     */
    public void logoutAction() {
        LoginController.getInstance().logoutUser();
    }

    /**
     * Performs a "back home" action.
     */
    public void homeMenuAction() {
        Stage stage = (Stage) this.view.getScene().getWindow();
        new MainController(stage);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link SoccerPlayerPanel},
     * setting current content and creating a controller for the panel.
     */
    public void playersMenuAction() {
        final SoccerPlayerPanel content = new SoccerPlayerPanel();
        new SoccerPlayerPanelController(content);
        this.view.setCurrentContent(content);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link StaffMemberPanel},
     * setting current content and creating a controller for the panel.
     */
    public void staffMenuAction() {
        final StaffMemberPanel content = new StaffMemberPanel();
        new StaffMemberPanelController(content);
        this.view.setCurrentContent(content);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link AdministrationMemberPanel},
     * setting current content and creating a controller for the panel.
     */
    public void societyMenuAction() {
        final AdministrationMemberPanel content = new AdministrationMemberPanel();
        new AdministrationMemberPanelController(content);
        this.view.setCurrentContent(content);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link EmployeeMemberPanel},
     * setting current content and creating a controller for the panel.
     */
    public void employeesMenuAction() {
        final EmployeeMemberPanel content = new EmployeeMemberPanel();
        new EmployeeMemberPanelController(content);
        this.view.setCurrentContent(content);
    }

    /**
     * Performs a "go to panel" action, to show {@link FacilityPanel},
     * setting current content and creating a controller for the panel.
     */
    public void facilitiesMenuAction() {
        final FacilityPanel content = new FacilityPanel();
        new FacilityPanelController(content);
        this.view.setCurrentContent(content);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link SponsorPanel},
     * setting current content and creating a controller for the panel.
     */
    public void sponsorMenuAction() {
        SponsorPanel content = new SponsorPanel();
        new SponsorPanelController(content);
        this.view.setCurrentContent(content);
    }
    
    /**
     * Performs a "go to panel" action, to show {@link SoccerMatchDataPanel},
     * setting current content and creating a controller for the panel.
     * Check if user can performs this action, and shown an Alert if user can't do it.
     */
    public void matchesMenuAction() {
        if (BaseModelImpl.getInstance().getPlayers().isEmpty()) {
            BiarenUtil.showBasicAlert(AlertType.ERROR, "Devi prima avere giocatori per giocare partite!", "", "");
        } else {
            SoccerMatchDataPanel content = new SoccerMatchDataPanel();
            new SoccerMatchDataPanelController(content);
            this.view.setCurrentContent(content);
        }    
    }
    
    /**
     * Performs a "go to panel" action, to show {@link StatisticsPanel},
     * setting current content and creating a controller for the panel.
     */
    public void statisticsMenuAction() {
        this.view.setCurrentContent(new StatisticsPanel());
    }
    
    /**
     * Performs a "go to panel" action, to show {@link MatchListPanel},
     * setting current content and creating a controller for the panel.
     */
    public void createListForMatch() {
        this.view.setCurrentContent(new MatchListPanel());
    }

}
