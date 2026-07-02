package zombietsunami.view.personview.api;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;

import zombietsunami.Pair;
import zombietsunami.view.api.VController;

/**
 * Interfaces representing the drawing of Person.
 */
public interface DrawPerson {

    /**
     * Draws the Person on the graphics context based on the same controller.
     * 
     * @param g2         The graphics context.
     * @param personIndexList List of Person.
     * @param screenTilePos Coordinates of the Person.
     * @param tileSize Tile size.
     * @param controller The controller with the game-related information.
     */
    void drawPersonV(Graphics2D g2, List<Integer> personIndexList, List<Pair<Integer, Integer>> screenTilePos,
            int tileSize, VController controller);

    /**
     * Gets the image representation of the Person.
     * 
     * @return The BufferedImage representing the Person.
     */
    BufferedImage getPerson();
}
