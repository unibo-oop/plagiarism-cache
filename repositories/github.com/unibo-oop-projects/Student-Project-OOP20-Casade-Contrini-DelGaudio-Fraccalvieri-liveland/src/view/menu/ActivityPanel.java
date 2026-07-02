package view.menu;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.EnvironmentControllerImpl;
import view.model.activity.ViewActivityImpl;

public class ActivityPanel extends JPanel {

    private static final long serialVersionUID = -2659591942183195854L;
    private static final int TEXT_AREA_WIDTH = 100;
    private static final int TEXT_AREA_HEIGHT = 15;
    private final JPanel activityPanelInt = new JPanel(new BorderLayout());
    private final ActivityInsertionPanelBox activityInsertionPanel;
    private final JLabel activityLabel = new JLabel("Activity list: (you must add at least one to launch the simulation)");
    private final JTextArea activityList = new JTextArea("\n\t***No activity chosen yet***\t\n");

    public ActivityPanel(final EnvironmentControllerImpl view) {
        activityPanelInt.add(activityLabel, BorderLayout.NORTH);
        activityList.setSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        activityList.setEditable(false);
        activityPanelInt.add(activityList, BorderLayout.SOUTH);
        this.add(activityPanelInt, BorderLayout.WEST);
        this.activityInsertionPanel = new ActivityInsertionPanelBox(view, this);
        this.activityInsertionPanel.enableDisableButtons(false);
        this.add(activityInsertionPanel, BorderLayout.EAST);
    }

    /**
     * @param activity whose textual representation should be added 
     * to the dedicated text area
     */
    public void setActivityList(final ViewActivityImpl activity) {
        this.activityList.append(activity.getName()
                + ": " + activity.getActivityType() + "\n");
    }

    /**
     * It reverts the initial appearance of the menu.
     */
    public void reset() {
        this.activityList.setText("\n\t***No activity chosen yet***\t\n");
        this.activityInsertionPanel.enableDisableButtons(false);
    }

    /**
     * It enables buttons to add a new activity once "validate" button
     * is pressed.
     */
    public void enableButtons() {
        this.activityInsertionPanel.enableDisableButtons(true);
        this.activityList.setText("\n");
    }

}
