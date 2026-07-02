package view.start.world;

import javax.swing.JLabel;

import view.menu.data.setting.AddElemValue;

/**
 * Interface that will be implemented by the class that will generate the main window of the program.
 *
 */
public interface ApplicationFrame {
    /*
     * Shows the main frame of the application.
     */
    void show();

    /*
     * Method that returns the visible object of the world that will have to be updated.
     * @return visible object of the world
     */
    UpdateWorld getWorld();

    /*
     * Method that returns the button with which you can stop the application.
     * @return object button of stop 
     */
    AddElemValue<Boolean> getButton();

    /*
     * Method that returns the JLabel for the time stamped watch.
     * @return object viewClock 
     */
    JLabel getClockView();

    /*
     * Method that returns an object that updates world specification counts.
     * @return object that updates specification 
     */
    UpdateSpecific getSpecific();

}
