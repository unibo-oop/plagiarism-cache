package view.playerview;

import java.awt.*;
import javax.swing.*;

import controller.resources.Resources;
import utilities.*;

class SelectedPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int GAP = (int) Math.round(Utilities.getScreenRatio() * 10);
    private static final int SELECTED_FONT_SIZE = (int) Math.round(Utilities.getScreenRatio() * 13);
    private static final int ATTEMPTS_FONT_SIZE = (int) Math.round(Utilities.getScreenRatio() * 25);

    private final JLabel selectedLabel;
    private final JLabel attemptsLabel;

    SelectedPanel(final Integer startingAttempts) {
        super();
        Utilities.requireNonNull(startingAttempts);
        selectedLabel = new JLabel("Please select");
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints constraints = new GridBagConstraints();
        final Font defaultFont = selectedLabel.getFont();
        final ImageIcon icon = new ImageIcon(Resources.getDefaultImage());
        selectedLabel.setFont(new Font(defaultFont.getFontName(), defaultFont.getStyle(), SELECTED_FONT_SIZE));
        selectedLabel.setIcon(icon);
        selectedLabel.setIconTextGap(GAP);
        selectedLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Selected"));
        attemptsLabel = new JLabel(String.valueOf(startingAttempts), JLabel.CENTER); 
        attemptsLabel.setFont(new Font(defaultFont.getFontName(), defaultFont.getStyle(), ATTEMPTS_FONT_SIZE));
        attemptsLabel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY), "Attempts"));
        this.setLayout(layout);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.gridx = 0;
        constraints.gridwidth = 2;
        layout.setConstraints(selectedLabel, constraints);
        this.add(selectedLabel);
        constraints.gridx = 3;
        constraints.gridwidth = 1;
        layout.setConstraints(attemptsLabel, constraints);
        this.add(attemptsLabel);
    }

    public void setSelected(final String name) {
        Utilities.requireNonNull(name);
        selectedLabel.setText(name);
        selectedLabel.setIcon(new ImageIcon(Resources.getCharacterImage(name)));
    }

    public void decreaseAttempts() {
        attemptsLabel.setText(String.valueOf(Integer.parseInt(attemptsLabel.getText()) - 1));
    }

}
