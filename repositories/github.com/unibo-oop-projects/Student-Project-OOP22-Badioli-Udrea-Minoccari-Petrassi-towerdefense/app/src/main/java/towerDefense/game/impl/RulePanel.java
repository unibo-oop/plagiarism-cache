package towerDefense.game.impl;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JButton;

import towerDefense.Constants;
import towerDefense.game.api.Panel;

public class RulePanel extends Panel{

    private BufferedImage background;

    /**
     * Creates a new rule panel, adding its background image and all of its components
     */
    public RulePanel() {

        super.startSound(Constants.rulePanel);

        try{
            this.background = ImageIO.read(this.getClass().getResource("/Assets/Backgrounds/Rules.jpg"));
        }catch(Exception e){
            System.out.println("error loading background " + e.getMessage());
        }

        JButton goBack = new JButton("Go Back");
        goBack.addActionListener((arg) -> {
            stopMusic();
            super.startSound(Constants.buttonSFX);
            GameImpl.setCurrentPanel(new MenuPanel());
        });

        this.add(goBack);
    }

    /**
     * Draws the background images 
     * @param g 
     *        graphic object used to draw all the components
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
