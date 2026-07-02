package snakerunner.graphics.panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import snakerunner.audio.AudioPlayer;
import snakerunner.controller.NavigationController;
import snakerunner.graphics.impl.AbstractBasePanel;

/**
 * OptionPanel define the OptionView for the application.
 */
public final class OptionPanel extends AbstractBasePanel {

    private static final long serialVersionUID = 1L;
    private static final String APPLY = "Apply";
    private static final String BACK = "Back";
    private static final String TUTORIAL = "Show Tutorial";
    private static final String SOUND = "Sound On / Off";
    private final transient NavigationController navigationController;
    private final JButton apply;
    private final JButton back;
    private final JButton tutorial;
    private final JCheckBox checkbox;
    private final JLabel label;

    /**
     * Constructor OptionPanel.
     * 
     * @param navigationController NavigationController.
     */
    public OptionPanel(final NavigationController navigationController) {
        super();
        initPanel();
        this.navigationController = navigationController;
        checkbox = new JCheckBox();
        label = new JLabel(SOUND);
        setLayoutPanel();
        checkbox.setAlignmentX(CENTER_ALIGNMENT);
        setSoundPanel();
        add(Box.createVerticalGlue());
        tutorial = createButton(TUTORIAL);
        apply = createButton(APPLY);
        back = createButton(BACK);
        add(tutorial);
        add(apply);
        add(back);
        this.addActionListeners();
    }

    private void setSoundPanel() {
        final JPanel soundPanel = new JPanel();
        soundPanel.setLayout(new BoxLayout(soundPanel, BoxLayout.X_AXIS));
        soundPanel.setAlignmentX(CENTER_ALIGNMENT);
        soundPanel.setOpaque(false);
        soundPanel.add(label);
        soundPanel.add(checkbox);
        add(soundPanel);
    }

    @Override
    public void setLayoutPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    @Override
    public void addActionListeners() {
        tutorial.addActionListener(e -> navigationController.onTutorial());
        apply.addActionListener(e -> navigationController.onBackMenu());
        back.addActionListener(e -> navigationController.onBackMenu());
        checkbox.addActionListener(e -> {
            final boolean enable = checkbox.isSelected();
            AudioPlayer.setSoundEnabled(enable);
        });
    }
}
