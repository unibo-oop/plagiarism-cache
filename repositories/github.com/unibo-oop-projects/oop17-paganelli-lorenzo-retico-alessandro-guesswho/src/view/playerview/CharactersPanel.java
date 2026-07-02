package view.playerview;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.stream.Collectors;
import java.util.List;
import java.util.*;
import model.character.Character;
import utilities.Utilities;

import javax.swing.*;

import controller.resources.Resources;

class CharactersPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int CHARACTERS_PER_ROW = (int) Math.round(Utilities.getScreenRatio() * 5);

    private final List<CharacterButton> characters;

    CharactersPanel() {
        super();
        this.setLayout(new GridLayout(0, CHARACTERS_PER_ROW));
        characters = Resources.getNamesAndImages().entrySet().stream()
                .map(entry -> new CharacterButton(entry.getKey(), entry.getValue()))
                .peek(comp -> this.add(comp))
                .collect(Collectors.toList());
    }

    public void addActionListener(final ActionListener al) {
        characters.forEach(c -> c.getButton().addActionListener(al));
    }

    public void removeActionListener(final ActionListener al) {
        characters.forEach(c -> c.getButton().removeActionListener(al));
    }

    public void setEnabled(final boolean b) {
        characters.forEach(c -> c.getButton().setEnabled(b));
    }

    public void update(final Set<Character> remaining) {
        characters.removeAll(characters.stream()
            .filter(cB -> !remaining.stream().anyMatch(c -> c.getName().equals(cB.getButton().getText())))
            .peek(cB -> cB.disableImage())
            .collect(Collectors.toSet()));
    }

    private class CharacterButton extends JComponent {

        private static final long serialVersionUID = 1L;
        private static final int MANTAIN_ASPECT_RATIO = -1;

        private final int border = (int) Math.round(Utilities.getScreenRatio() * 2);
        private final JButton button;
        private final JLabel imageLabel;
        private boolean isDisabled;

        CharacterButton(final String name, final Image image) {
            super();
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(final ComponentEvent e) {
                    final Dimension componentDimension = new Dimension(e.getComponent().getHeight(), e.getComponent().getHeight() - button.getHeight());
                    if (componentDimension.width >= componentDimension.height) {
                        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(MANTAIN_ASPECT_RATIO, componentDimension.height - border, Image.SCALE_SMOOTH)));
                    } else {
                        imageLabel.setIcon(new ImageIcon(image.getScaledInstance(componentDimension.width - border, MANTAIN_ASPECT_RATIO, Image.SCALE_SMOOTH)));
                    }
                    if (isDisabled) {
                        imageLabel.setIcon(imageLabel.getDisabledIcon());
                    }
                }
            });
            button = new JButton(name);
            imageLabel = new JLabel(new ImageIcon(image));
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            imageLabel.setAlignmentX(CENTER_ALIGNMENT);
            button.setAlignmentX(CENTER_ALIGNMENT);
            this.add(imageLabel);
            this.add(button);
        }

        public JButton getButton() {
            return button;
        }

        public void disableImage() {
            this.isDisabled = true;
            imageLabel.setIcon(imageLabel.getDisabledIcon());
        }

    }

}
