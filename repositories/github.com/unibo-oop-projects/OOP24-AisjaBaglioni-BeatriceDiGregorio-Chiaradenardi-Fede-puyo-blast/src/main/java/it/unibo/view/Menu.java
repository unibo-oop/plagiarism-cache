package it.unibo.view;

import javax.swing.*;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.MenuInterface;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The Menu class represents the main menu of the game where users can select the level,
 * view game controls, and start the game.
 * It handles the creation and layout of the menu components like buttons, dropdowns, and labels.
 */
public class Menu extends JPanel implements MenuInterface {
    /**
     * Button to start the game.
     */
    private final JButton startButton; 
    /**
     * Button to view controls.
     */
    private final JButton controlsButton; 
    /**
     * Dropdown to select the level.
     */
    private final JComboBox<String> levelsDropdown; 
    /**
     * Background image for the menu.
     */
    private Image backgroundImage; 
    /**
     * Scale value to adjust element sizes.
     */
    private final int scale; 

    /**
     * Constructs the Menu with level options and the scale object for responsive design.
     *
     * @param levels Array of available levels.
     * @param scaleObj The Scale object to adjust the UI scaling.
     */
    public Menu(String[] levels, Scale scaleObj) {
        this.scale = scaleObj.getScale(); 
        this.setLayout(new BorderLayout()); 
        this.setBackground(new Color(28, 28, 28)); 

        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/puyomenu.jpg")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false); 

        JLabel titleLabel = new JLabel("PUYO POP: BLAST!");
        titleLabel.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 17)); 
        titleLabel.setForeground(new Color(51, 73, 112)); 

        JPanel titleWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, scale / 45, scale / 15));
        titleWrapper.setOpaque(false); 
        titleWrapper.add(titleLabel);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(false); 
        titlePanel.add(titleWrapper, BorderLayout.SOUTH);

        /**
         *  Dropdown for selecting the level.
         */
        levelsDropdown = new JComboBox<>(levels);
        levelsDropdown.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 35)); 
        levelsDropdown.setMaximumSize(new Dimension(scale / 3, scale / 15)); 
        levelsDropdown.setAlignmentX(Component.LEFT_ALIGNMENT);
        levelsDropdown.setBackground(new Color(57, 143, 192)); 
        levelsDropdown.setForeground(Color.BLACK); 

        JLabel levelLabel = new JLabel("Select the level:"); 
        levelLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 35));
        levelLabel.setForeground(Color.BLACK);
        levelLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        /**
         * Buttons for starting the game and viewing controls.
         */
        startButton = new JButton("Start the game!");
        controlsButton = new JButton("Rules");
        styleButton(startButton, new Color(57, 143, 191), scale); 
        styleButton(controlsButton, new Color(57, 143, 191), scale); 

        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 7))); 
        centerPanel.add(levelLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 35))); 
        centerPanel.add(levelsDropdown);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 20))); 
        centerPanel.add(startButton);
        centerPanel.add(Box.createRigidArea(new Dimension(10, scale / 35))); 
        centerPanel.add(controlsButton);

        this.add(centerPanel, BorderLayout.CENTER); 
        this.add(titlePanel, BorderLayout.SOUTH); 
    }

    /**
     * Styles the buttons (start and controls) to match the game's theme.
     *
     * @param button The button to be styled.
     * @param backgroundColor The background color of the button.
     * @param scale The scale value to adjust the button size.
     */
    private void styleButton(JButton button, Color backgroundColor, int scale) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, scale / 30)); 
        button.setBackground(backgroundColor); 
        button.setForeground(Color.BLACK); 
        button.setFocusPainted(false); 
        button.setPreferredSize(new Dimension(scale / 2, scale / 10)); 
        button.setAlignmentX(Component.LEFT_ALIGNMENT); 

        /**
         * Mouse hover effect: darken the button color on hover.
         */
        button.addMouseListener(new MouseAdapter() {
            private Color hoverColor = backgroundColor.darker(); 

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor); 
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(backgroundColor); 
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        button.setContentAreaFilled(true); 
    }

    /**
     * Override the paintComponent method to draw the background image.
     *
     * @param g The Graphics object used to paint the component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
        }
    }

    /**
     * Returns the start button.
     *
     * @return The start button.
     */
    @Override
    public JButton getStartButton() {
        return startButton; 
    }

    /**
     * Returns the controls button.
     *
     * @return The controls button.
     */
    @Override
    public JButton getControlsButton() {
        return controlsButton; 
    }

    /**
     * Returns the selected level from the dropdown.
     *
     * @return The selected level as a String.
     */
    @Override
    public String getSelectedLevel() {
        return (String) levelsDropdown.getSelectedItem(); 
    }
}
