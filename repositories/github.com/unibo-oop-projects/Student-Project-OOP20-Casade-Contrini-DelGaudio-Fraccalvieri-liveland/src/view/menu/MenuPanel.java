package view.menu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.EnvironmentControllerImpl;
import view.model.activity.ViewActivityImpl;

/**
 * This panel is responsible for displaying components correctly into 
 * the main frame, and also for coordination between panels.
 */
public class MenuPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int TEXT_FIELD_WIDTH = 5; 
    private final JPanel visitorsPanel;
    private final JLabel capacity = new JLabel("Choose the number of visitors for this simulation (max. 300):");
    private final JButton validate = new JButton("Validate");
    private final JTextField visitors = new JTextField("", TEXT_FIELD_WIDTH);
    private final ActivityPanel activityPanel;

    public MenuPanel(final EnvironmentControllerImpl view, final GraphicalUserInterface gui) {
        this.activityPanel = new ActivityPanel(view);
        this.visitorsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.visitorsPanel.add(capacity);
        this.visitorsPanel.add(visitors);
        this.visitorsPanel.add(validate);
        this.setLayout(new BorderLayout());
        this.add(visitorsPanel, BorderLayout.NORTH);
        this.add(activityPanel, BorderLayout.CENTER);

        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    view.setVisitorsNumber(Integer.parseInt(visitors.getText()));
                    validate.setEnabled(false);
                    MenuPanel.this.activityPanel.enableButtons();
                    gui.getBottomPanel().enableDisableButtons(true);
                } catch (NumberFormatException exc) {
                    MenuPanel.this.visitors.setText("");
                    gui.getWelcomePanel().setWelcomeText("Please, set visitors as a number!");
                } catch (VisitorsOutOfBoundException exc) {
                    MenuPanel.this.visitors.setText("");
                    gui.getWelcomePanel().setWelcomeText(exc.getMessage());
                }
            }
        });

    }

    /**
     * It reverts the frame's initial state.
     */
    public void reset() {
        this.visitors.setText("");
        this.validate.setEnabled(true);
        this.activityPanel.reset();
    }

    /**
     * It shows that the new activity has been correctly added.
     * @param activity added
     */
    public void setActList(final ViewActivityImpl activity) {
        this.activityPanel.setActivityList(activity);
    }

}
