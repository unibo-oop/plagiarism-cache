package com.biaren.sportclubmanager.corebundle.controller.interfaces;

/**
 * Home controller interfaces.
 * Declare methods for menu actions and other home actions.
 * @author nbrunetti
 *
 */
public interface HomeController {

    /**
     * Performs logout action
     */
    void logoutAction();
    
    /**
     * Performs home menu action
     */
    void homeMenuAction();
        
    /**
     * Performs players menu action
     */
    void playersMenuAction();
    
    /**
     * Performs staff menu action
     */
    void staffMenuAction();
    
    /**
     * Performs society menu action
     */
    void societyMenuAction();
    
    /**
     * Performs employees menu action
     */
    void employeesMenuAction();
    
    /**
     * Performs facilities menu action
     */
    void facilitiesMenuAction();
    
    /**
     * Performs sponsor menu action
     */
    void sponsorMenuAction();
    
    /**
     * Performs matches menu action
     */
    void matchesMenuAction();
    
    /**
     * Performs statistics menu action
     */
    void statisticsMenuAction();
    
    /**
     * Performs actions to create a list for match
     */
    void createListForMatch();
}
