package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.adminbundle.controller.LoginController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.HomePanel;
import com.biaren.sportclubmanager.corebundle.views.LoginPanel;
import com.biaren.sportclubmanager.corebundle.views.SetupPanel;
import com.biaren.sportclubmanager.corebundle.views.WelcomeScreenPanel;
import com.biaren.sportclubmanager.utility.enums.TextStrings;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main controller of the system. Initialize the entire system to
 * performs the correct step and finally show the application and
 * pass the command to the home controller.
 * @author nbrunetti
 *
 */
public class MainController {
    
    private Stage primaryStage;
    private Scene primaryScene;
    
    /**
     * Create a {@link MainController},
     * set the primary stage to show,
     * check and performs the correct action to initialize system.
     * If it's the very first opening, performs a little setup of:
     *  -- registration of a new user -- -- login of the user --
     *  -- registration of Team Name --
     * If setup it's already done, shows a classic form to login in.
     * If user is already logged in, shows the Home.
     * @param primaryStage {@link Stage}
     */
    public MainController(final Stage primaryStage) {
        this.primaryStage = primaryStage;        
        if(LoginController.getInstance().getUserLoggedIn().isPresent()) {
            this.workingSystem();
        } else {
            this.initSystem();
        }
    }
    
    private void viewWelcomeScreen() {
        final WelcomeScreenPanel view = new WelcomeScreenPanel();
        new WelcomeScreenController(view);
        this.primaryScene = new Scene(view);
        this.setStage(this.primaryScene);
    }
    
    private void viewSetup() {
        final SetupPanel view = new SetupPanel();
        new SetupController(view);
        this.primaryScene = new Scene(view);
        this.setSmallSceneDefaultSize();
        this.setStage(this.primaryScene);
    }
    
    @SuppressWarnings("unused")
    private void viewLogin() {
        final LoginPanel view = new LoginPanel();
        new LoginFormController(view);
        this.primaryScene = new Scene(view);
        this.setSmallSceneDefaultSize();
        this.setStage(this.primaryScene);
    }
    
    private void viewHome() {
        final HomePanel view = new HomePanel();
        new HomeControllerImpl(view);
        this.primaryScene = new Scene(view);
        this.setLargeSceneDefaultSize();
        this.setStage(this.primaryScene);
    }
  
    private void initSystem() {
        this.viewWelcomeScreen();
    }
    
    private void workingSystem() {
        if (BaseModelImpl.shouldDoSetup()) {
            this.viewSetup();
        } else {
            this.viewHome();
        }
    }
    
    private void setSmallSceneDefaultSize() {
        this.primaryStage.setHeight(600.0);
        this.primaryStage.setWidth(800.0);
    }
    
    private void setLargeSceneDefaultSize() {
        this.primaryStage.setHeight(768);
        this.primaryStage.setWidth(1024.0);
    }
    
    private void setStage(final Scene scene) {
        this.primaryStage.setScene(scene);
        this.primaryStage.setTitle(TextStrings.SPORTCLUBMANAGER_MAIN_TITLE.toString());
        this.primaryStage.show();
    }
}
