package zombietsunami.view.personview.impl;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import zombietsunami.Pair;
import zombietsunami.view.api.VController;
import zombietsunami.view.personview.api.DrawPerson;

/**
 * Implementation of the DrawPerson interface, responsible
 * for drawing the Person.
 */
public class DrawPersonImpl implements DrawPerson {
    private boolean change = true;
    private int changeCounter;
    private static final int FRAMESCHANGE = 15;
    private static final String SEP = "/";
    private static final String ROOT = SEP + "zombietsunami" + SEP + "person" + SEP;
    private static final String PERSON_00 = ROOT + "person_10.png";
    private static final String PERSON_01 = ROOT + "person_11.png";

    /**
     * Draws the Person on the graphics context based on the same controller.
     * 
     * @param g2              The graphics context.
     * @param personIndexList List of Person.
     * @param screenTilePos   Coordinates of the Person.
     * @param tileSize        Tile size.
     * @param controller      The controller with the game-related information.
     */
    @Override
    public void drawPersonV(final Graphics2D g2, final List<Integer> personIndexList,
            final List<Pair<Integer, Integer>> screenTilePos,
            final int tileSize, final VController controller) {
        for (int i = 0; i < personIndexList.size(); i++) {
            if (personIndexList.get(i) == 1 && screenTilePos.get(i) != null) {
                controller.setPersonFromMapC();
                drawPerson(getPerson(), g2, screenTilePos, tileSize, i);
            }
        }
    }

    /**
     * Draws the Person.
     * 
     * @param image         Image of Person.
     * @param g2            Graphics.
     * @param screenTilePos Coordinates of Person.
     * @param tileSize      Tile size.
     * @param i             Index of the Person in the list.
     */
    private void drawPerson(final BufferedImage image, final Graphics2D g2,
            final List<Pair<Integer, Integer>> screenTilePos, final int tileSize, final int i) {
        g2.drawImage(image, screenTilePos.get(i).getX(), screenTilePos.get(i).getY(), tileSize, tileSize, null);
    }

    private int getChange() {
        return changeCounter;
    }

    private int increaseChange() {
        return changeCounter++;
    }

    /**
     * Gets the image of the Person.
     */
    @Override
    public BufferedImage getPerson() {
        final Logger logger = Logger.getLogger(DrawPersonImpl.class.getName());
        BufferedImage image = null;
        try {
            if (change) {
                image = ImageIO.read(DrawPersonImpl.class.getResource(PERSON_00));
            } else {
                image = ImageIO.read(DrawPersonImpl.class.getResource(PERSON_01));
            }
            increaseChange();
            if (getChange() > FRAMESCHANGE) {
                change = !change;
                changeCounter = 0;
            }
        } catch (IOException e) {
            logger.severe("Errore durante il caricamento dell'immagine della persona: " + e.getMessage());
        }
        return image;
    }
}
