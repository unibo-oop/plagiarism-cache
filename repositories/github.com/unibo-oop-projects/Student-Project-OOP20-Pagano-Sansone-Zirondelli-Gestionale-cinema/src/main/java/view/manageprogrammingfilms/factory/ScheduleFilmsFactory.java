package view.manageprogrammingfilms.factory;

import java.awt.event.ActionListener;
import javax.swing.JPanel;

import controller.managefilms.FilmsController;
import viewimpl.manageprogrammingfilms.factory.DatePanel;
import viewimpl.manageprogrammingfilms.factory.InfoProgrammationPanel;
import viewimpl.manageprogrammingfilms.factory.TimePanel;
/** 
 * 
 * Factory to create components for schedule films view.*/
public interface ScheduleFilmsFactory {
    /** 
     * Get time panel.
     * @return time panel
     * */
    TimePanel getTimePanel();
    /** 
     * Get date panel.
     * @return date panel
     * */
    DatePanel getDatePanel();
    /** 
     * Get bottom panel.
     * @param al action listener to add for button
     * @return bottom panel
     * */
    JPanel getBottomPanel(ActionListener al);
    /** 
     * Get info programmation panel.
     * @param filmsController films controller
     * @return panel with info programmation
     * */
    InfoProgrammationPanel getInfoProgrammationPanel(FilmsController filmsController);

}
