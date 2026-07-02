package it.unibo.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import it.unibo.model.Scale;
import it.unibo.view.interfaces.MenuRulesInterface;

/**
 * MenuRules class represents the "Rules" screen of the game.
 * It displays the game rules, a scrollable area for the text, and a back button.
 */
public class MenuRules extends JPanel implements MenuRulesInterface {
    /**
     * Button to go back to the previous screen.
     */
    private final JButton backButton; 
    /**
     * Background image.
     */
    private Image backgroundImage; 
    /**
     * First decoration image of a person.
     */
    private Image decoration1; 
    /**
     * Second decoration image of a person.
     */
    private Image decoration2; 
    /**
     * Scale value to adjust element sizes.
     */
    private final int scale; 

    /**
     * Constructor for MenuRules. Initializes the panel and loads necessary images.
     * @param scaleObj The Scale object to adjust the UI scale.
     */
    public MenuRules(Scale scaleObj) {
        this.scale = scaleObj.getScale();
        this.setLayout(new BorderLayout());

        try {
            backgroundImage = new ImageIcon(getClass().getResource("/images/rulesimg.jpg")).getImage();
            decoration1 = new ImageIcon(getClass().getResource("/images/pers1.png")).getImage();
            decoration2 = new ImageIcon(getClass().getResource("/images/pers2.png")).getImage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JTextArea rulesText = new JTextArea(
                "Game Rules:\n" +
                        "- Use the cannon to shoot Puyos into the grid.\n" +
                        "- Combine at least 2 Puyos of the same color to increase the score multiplier.\n" +
                        "Be quick! The game ends if the grid fills up.\n" +
                        "You must reach at least one star to pass the level.\n" +
                        "- Puyos might auto-freeze. During freezing, you cannot destroy them. If the cannon's loading bar is full,\n" +
                        "you can shoot a 'thermal shot' to unfreeze them.\n\n" +
                        "Difficulty levels: \n" +
                        "- There are 3 levels where the difficulty increases with more balls falling faster.\n\n" +
                        "Controls:\n" +
                        "- Arrow keys to move the aiming.\n" +
                        "- Spacebar to shoot and P to pause.");
        rulesText.setEditable(false);
        rulesText.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 40)); 
        rulesText.setLineWrap(true);
        rulesText.setWrapStyleWord(true);
        rulesText.setOpaque(false);
        rulesText.setForeground(Color.BLACK);
        rulesText.setCaretColor(Color.BLACK);
        rulesText.setBorder(BorderFactory.createEmptyBorder(scale / 35, scale / 35, scale / 35, scale / 35));

        JScrollPane scrollPane = new JScrollPane(rulesText); 
        scrollPane.setPreferredSize(new Dimension(scale, scale / 2));
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);

        backButton = new JButton("Back"); 
        styleButton(backButton, new Color(50, 130, 255), scale); 

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, scale / 35, scale / 35));
        bottomPanel.add(backButton); 
        this.add(scrollPane, BorderLayout.CENTER); 
        this.add(bottomPanel, BorderLayout.SOUTH); 
    }

    /**
     * Customizes the appearance of buttons.
     * @param button The button to style.
     * @param backgroundColor The background color of the button.
     * @param scale The scale factor to adjust button size.
     */
    private void styleButton(JButton button, Color backgroundColor, int scale) {
        button.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, scale / 30));
        button.setBackground(backgroundColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(null);
        button.setPreferredSize(new Dimension(scale / 2, scale / 15)); 
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMargin(new Insets(scale / 35, scale / 18, scale / 35, scale / 25));

        /**
         * Add mouse listener for hover effect to darkener.
         */
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(backgroundColor.darker()); 
            }
        });

        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
    }

    /**
     * Adds an ActionListener to the "Back" button.
     * @param listener The ActionListener to be added.
     */
    @Override
    public void addBackButtonListener(ActionListener listener) {
        backButton.addActionListener(listener);
    }

    /**
     * Paints the custom background and decorative images on the panel.
     * @param g The Graphics object used to paint the component.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        if (backgroundImage != null) {
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        /**
         * Draw the decorative images with scaling.
         */
        if (decoration1 != null && decoration2 != null) {
            double scaleX = 0.3; 
            double scaleY = 0.4; 

            int scaledWidth = scale / 3;
            int scaledHeight = scale / 2;

            /**
             * Create transformation for decoration1.
             */
            AffineTransform transform1 = new AffineTransform();
            transform1.translate(scale / 30, getHeight() - scaledHeight + (scale / 7));
            transform1.scale(0.2, 0.3);

            /**
             * Create transformation for decoration2.
             */
            AffineTransform transform2 = new AffineTransform();
            transform2.translate(getWidth() - scaledWidth + (scale / 10), getHeight() - scaledHeight + (scale / 20));
            transform2.scale(scaleX, scaleY);

            g2d.drawImage(decoration1, transform2, this);
            g2d.drawImage(decoration2, transform1, this);
        }
    }
}
