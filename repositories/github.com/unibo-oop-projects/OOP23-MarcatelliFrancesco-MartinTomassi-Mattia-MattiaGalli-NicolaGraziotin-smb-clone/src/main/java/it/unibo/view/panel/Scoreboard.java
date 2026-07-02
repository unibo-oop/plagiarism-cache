package it.unibo.view.panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.view.window.GameWindow;
import it.unibo.controller.GameController;
import it.unibo.view.window.GameWindow.PanelType;
/**
 * The Scoreboard class represents the panel where the User can view statistic after passing the level.
 * It extends JPanel and provides the number of deaths and the time that the User managed to pass the level.
 */
public class Scoreboard extends JPanel  {

    public static final long serialVersionUID = 1;
    private static final int ROWS = 4;
    private static final int COLS = 1;
    private final transient GameController controller;
    private final JLabel deathsField;
    private final JLabel timeRecordField;

    /**
     * Constructs a new instance of Scoreboard with the GameController and GameWindow.
     * 
     * @param controller the GameController associated with the window.
     * @param window the GameWindow that can switch to change panel.
     */
    public Scoreboard(final GameController controller, final GameWindow window) {
        this.setLayout(new GridLayout(ROWS, COLS, 0, GameMenu.VERTICAL_GAP));
        this.controller = controller;
        final JLabel levelLabel = new JLabel("LEVEL 1");
        deathsField = new JLabel("TOTAL DEATHS: " + this.controller.getDeaths());
        timeRecordField = new JLabel(String.format("BEST TIME RECORD: %d:%02d",
            this.controller.getTimeRecord() / 100, this.controller.getTimeRecord() % 100));

        final JButton backButton = createButton("BACK", e -> window.switchPanel(PanelType.MENU));

        this.setBackground(Color.BLACK);
        this.add(levelLabel);
        levelLabel.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        levelLabel.setFont(GameMenu.TITLE_TEXT_FONT);
        levelLabel.setForeground(Color.WHITE);
        setupLable(deathsField);
        setupLable(timeRecordField);
        setupButton(backButton);
    }

    private void setupButton(final JButton button) {
        button.setFont(GameMenu.TEXT_FONT);
        button.setBackground(GameMenu.BUTTON_COLOR);
        button.setForeground(Color.BLACK);
        this.add(button);
    }

    private void setupLable(final JLabel label) {
        label.setFont(GameMenu.TEXT_FONT);
        label.setForeground(Color.WHITE);
        this.add(label);
    }

    /**
     * Update the number of deaths every time the player die.
     */
    public void updateDeaths() {
        this.deathsField.setText("TOTAL DEATHS: " + this.controller.getDeaths());
    }

    /**
     * Update the time record every time the player ends the level with a better time than before.
     */
    public void updateTimeRecord() {
        this.timeRecordField.setText(String.format("BEST TIME RECORD: %d:%02d",
            this.controller.getTimeRecord() / 100, this.controller.getTimeRecord() % 100));
    }

    /**
     * @inheritDoc
     */
    @Override
    public void addNotify() {
        super.addNotify();
        updateDeaths();
        updateTimeRecord();
        repaint();
    }

    private JButton createButton(final String text, final ActionListener actionListener) {
        final JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }
}
