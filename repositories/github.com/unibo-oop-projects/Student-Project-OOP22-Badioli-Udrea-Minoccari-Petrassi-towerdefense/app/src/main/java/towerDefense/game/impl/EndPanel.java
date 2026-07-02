package towerDefense.game.impl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JTextField;

import towerDefense.Constants;
import towerDefense.entities.impl.TowerSingleton;
import towerDefense.game.api.Panel;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import java.awt.Graphics;

public class EndPanel extends Panel{

    private static Path saveFile;
    private BufferedImage background;
    private BufferedImage castle;

    /**
     * Panel called at the end of the game
     * Used to save the score of the current game and/or exit the application 
     */
    public EndPanel() {

        super.startSound(Constants.gameOverSFX);
        super.startSound(Constants.endPanel);

        try {
            this.castle = ImageIO.read(this.getClass().getResource("/Assets/Backgrounds/castle2.png"));
            this.background = ImageIO.read(this.getClass().getResource("/Assets/Backgrounds/End.png"));
        } catch (IOException e) {
            System.out.println("error loading background " + e.getMessage());
        }

        JButton Exit = new JButton("Exit");
        Exit.addActionListener((arg) ->System.exit(0));
        this.add(Exit);

        saveFile = Paths.get("").toAbsolutePath().resolve("app/src/main/resources/Assets/SaveFile.txt");
        JButton saveScore = new JButton("Save Score");
        JTextField nameScore = new JTextField("             ");
        TowerSingleton tower = TowerSingleton.getInstance();

        saveScore.addActionListener((arg) -> {
            try {
                Files.writeString(saveFile, Files.readString(saveFile) + 
                    "\n" + 
                    nameScore.getText() +
                    String.valueOf(tower.getScore()), 
                    StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveScore.setEnabled(false);
        });

        this.add(saveScore);
        this.add(nameScore);
    }  

    /**
     * Draws the background images 
     * @param g 
     *        graphic object used to draw all the components
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(castle,(int)(Constants.width-265),295,null);
    }
}
