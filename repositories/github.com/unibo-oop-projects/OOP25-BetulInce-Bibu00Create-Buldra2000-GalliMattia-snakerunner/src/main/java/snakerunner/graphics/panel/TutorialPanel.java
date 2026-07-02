package snakerunner.graphics.panel;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import snakerunner.controller.NavigationController;
import snakerunner.core.GameConfiguration;
import snakerunner.graphics.impl.AbstractBasePanel;

/**
 * TutorialPanel define TutorialView.
 */
public final class TutorialPanel extends AbstractBasePanel {

    private static final long serialVersionUID = 1L;
    private static final String COMMAND = "COMMAND";
    private static final String DESCRIPTION = "All you need to play Snake Runner are the arrow keys on your keyboard!";
    private static final String BACK = "Back";
    private static final Integer VALUE_STRUT = GameConfiguration.VALUE_STRUT;
    private static final Integer SIZE_DESC = GameConfiguration.SIZE_DESC;
    private static final Integer SIZE_COMM = GameConfiguration.SIZE_COMM;
    private final transient NavigationController navigationController;
    private final JLabel command;
    private final JLabel description;
    private final JButton back;

    /**
     * Constructor TutorialPanel.
     * 
     * @param navigationController NavigationController.
     */
    public TutorialPanel(final NavigationController navigationController) {
        super();
        this.navigationController = navigationController;
        command = new JLabel(COMMAND);
        description = new JLabel(DESCRIPTION);
        description.setFont(new Font("Monospaced", Font.PLAIN, SIZE_DESC));
        command.setFont(new Font("Arial", Font.BOLD, SIZE_COMM));
        back = createButton(BACK);
        initPanel();
        add(command);
        add(Box.createVerticalStrut(VALUE_STRUT));
        add(description);
        add(Box.createVerticalStrut(VALUE_STRUT));
        add(back);
        command.setAlignmentX(CENTER_ALIGNMENT);
        description.setAlignmentX(CENTER_ALIGNMENT);
        back.setAlignmentX(CENTER_ALIGNMENT);
        this.addActionListeners();
    }

    @Override
    public void setLayoutPanel() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    @Override
    public void addActionListeners() {
       back.addActionListener(e -> navigationController.onBackMenu());
    }

}
