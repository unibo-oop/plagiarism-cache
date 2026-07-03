package it.unibo.tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Class that is used inside {@link Menu} to display the tutorial.
 * extends {@link JPanel}
 */
public class Tutorial extends JPanel{
    private Image mainImage;
    private Dimension imageSize;
    private ImageIcon icon = new ImageIcon("src/res/img/tutorial.png");
    
    /**
     * Button image credits: Freepik.com
     */
    private ImageIcon backButtonImage = new ImageIcon("src/res/img/back-button.png");

    /**
     * Resize {@link JPanel} and add backButton.
     * 
     * @param parentFrame
     */
    public Tutorial(JFrame parentFrame) {
        
        mainImage = icon.getImage();
        this.setBackground(Color.black);

        /*
         * Creating JButton and setting its values.
         */
        JButton backButton = new JButton();
        backButton.setBounds(5, 5, 64, 64);
        backButton.setIcon(backButtonImage);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);

        /*
         * Adding Button to the panel.
         */
        this.add(backButton);

        /*
         * Getting the size of the main image and resizing the JPanel.
         */
        this.imageSize = getImageSize();
        setPreferredSize(imageSize);
        setMinimumSize(imageSize);
        setMaximumSize(imageSize);
        setSize(imageSize);
        setLayout(null);

        /*
         * Adding function to backButton.
         */
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setVisible(true);
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(
                                                                Tutorial.this);
                currentFrame.dispose();
            }
        });

    }

    /**
     * Draw Image and backButton.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int yOffset = backButtonImage.getIconHeight()+10; 
        g.drawImage(mainImage, 0, yOffset, this);
    }


    /**
     * This method is used to get the main image {@link Dimension} size.
     */
    public Dimension getImageSize() {
        ImageIcon image = this.icon;
        Dimension size = new Dimension(image.getIconWidth(), 
                                       image.getIconHeight() + 
                                       backButtonImage.getIconHeight()+20);
        return size;   
    }
}
