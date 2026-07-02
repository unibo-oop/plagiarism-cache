package com.biaren.sportclubmanager.corebundle.controller;

import com.biaren.sportclubmanager.adminbundle.controller.LoginController;
import com.biaren.sportclubmanager.corebundle.model.BaseModelImpl;
import com.biaren.sportclubmanager.corebundle.views.LoginPanel;
import com.biaren.sportclubmanager.corebundle.views.SignupPanel;
import com.biaren.sportclubmanager.corebundle.views.WelcomeScreenPanel;

/**
 * Controls the welcome screen, allow user to signup o login, based on
 * first-use or not.
 * @author nbrunetti
 *
 */
public class WelcomeScreenController {
    
    private WelcomeScreenPanel view;
    
    /**
     * Create a {@link WelcomeScreenController} to show the correct view
     * to user.
     * @param view to switch correct content.
     */
    public WelcomeScreenController(final WelcomeScreenPanel view) {
        this.view = view;
        if (BaseModelImpl.isFirstOpening() && ! LoginController.getInstance().getUserLoggedIn().isPresent()) {
            SignupPanel content = new SignupPanel();
            new SignupFormController(content);
            this.view.switchContent(content);
        } else {
            LoginPanel content = new LoginPanel();
            new LoginFormController(content);
            this.view.switchContent(content);
        }
    }
}
