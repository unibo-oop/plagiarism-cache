package it.unibo.view;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.Optional;

import javax.swing.ImageIcon;

import it.unibo.model.Scale;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;
import it.unibo.view.interfaces.ViewInterface;

import java.awt.Font;
import java.awt.Color;

/**
 * The EndView class is responsible for displaying the end-of-game screen. This
 * includes rendering
 * the game outcome (such as "Level Complete!" or "Level Failed") and showing
 * the final score.
 * The class also uses stars to visually represent the game's performance
 * rating.
 */
public class EndView implements ViewInterface {

    /**
     * Model that holds the current game status (whether the game is ended or not).
     */
    private final StatusModel statusModel;

    /**
     * Provides scaling information for rendering elements on the screen.
     */
    private final Scale scale;

    /**
     * The score model that provides the final score after the game ends.
     */
    private final ScoreModel score;

    /**
     * Array that holds images for star ratings (0, 1, 2, and 3 stars).
     */
    private final Image[] stars;

    /**
     * The width of the star images.
     */
    private int imageWidth;

    /**
     * The height of the star images.
     */
    private int imageHeight;

    /**
     * Constructor for EndView.
     * Initializes the status, scale, score models and loads the star images.
     * 
     * @param statusModel The StatusModel object containing the current game status.
     * @param scale       The Scale object used to scale elements on the screen.
     * @param score       The ScoreModel object representing the player's score.
     */
    public EndView(StatusModel statusModel, Scale scale, ScoreModel score) {
        this.statusModel = statusModel;
        this.scale = scale;
        this.score = score;
        this.stars = new Image[4];

        /**
         * Load the star images (0, 1, 2, and 3 stars)
         */
        for (int i = 0; i <= 3; i++) {
            URL image_path = getClass().getClassLoader().getResource("images/" + String.valueOf(i) + "stars.png");
            this.stars[i] = new ImageIcon(image_path).getImage();
        }

        /**
         * Get the dimensions of the first star image (used for scaling)
         */
        this.imageWidth = this.stars[0].getWidth(null);
        this.imageHeight = this.stars[0].getHeight(null);
    }

    /**
     * Draws the end-of-game screen, including the outcome, star rating, and final
     * score.
     * 
     * @param g The Graphics object used to render the screen elements.
     */
    @Override
    public final void draw(final Graphics g) {
        /**
         * Calculate the new width and height for the star images based on the scale
         */
        int newWidth = this.scale.getScale() / 2;
        int newHeight = (newWidth * this.imageHeight) / this.imageWidth;
        int cellsize = this.scale.getScale() / 16;
        /**
         * Check if the game has ended
         */
        if (this.statusModel.isGameEnded()) {
            /**
             * Draw a translucent background to overlay the game outcome and
             * set up the coordinates for text placement.
             */
            g.setColor(new Color(0, 0, 0, 100));
            g.fillRect(0, 0, this.scale.getScale(), this.scale.getScale());
            int xstring = this.scale.getScale() / 9;
            int ystring = this.scale.getScale() / 2;

            /**
             * Set the font for displaying the result and final score and
             * define the strings to display based on the game result.
             */
            Font result = new Font("Arial", Font.BOLD, this.scale.getScale() / 12);
            Font points = new Font("Arial", Font.BOLD, this.scale.getScale() / 20);
            String[] string = { "LEVEL FAILED...", "LEVEL COMPLETE!", "NOT BAD." };
            /**
             * Check the number of stars to display
             */
            if (this.statusModel.getEndStars().isEmpty()) {
                g.drawImage(
                        this.stars[0],
                        xstring,
                        ystring - cellsize * 5,
                        xstring + newWidth,
                        ystring - cellsize * 5 + newHeight,
                        0, 0,
                        imageWidth, imageHeight, null);

                g.setColor(Color.RED);
                g.setFont(result);
                g.drawString(string[0], xstring, ystring);

            } else if (this.statusModel.getEndStars().equals(Optional.of(3))) {
                g.drawImage(
                        this.stars[3],
                        xstring,
                        ystring - cellsize * 5,
                        xstring + newWidth,
                        ystring - cellsize * 5 + newHeight,
                        0, 0,
                        imageWidth, imageHeight, null);

                g.setColor(Color.GREEN);
                g.setFont(result);
                g.drawString(string[1], xstring, ystring);

            } else {
                if (this.statusModel.getEndStars().equals(Optional.of(1))) {
                    g.drawImage(
                            this.stars[1],
                            xstring,
                            ystring - cellsize * 5,
                            xstring + newWidth,
                            ystring - cellsize * 5 + newHeight,
                            0, 0,
                            imageWidth, imageHeight, null);
                } else if (this.statusModel.getEndStars().equals(Optional.of(2))) {
                    g.drawImage(
                            this.stars[2],
                            xstring,
                            ystring - cellsize * 5,
                            xstring + newWidth,
                            ystring - cellsize * 5 + newHeight,
                            0, 0,
                            imageWidth, imageHeight, null);
                }

                g.setColor(Color.YELLOW);
                g.setFont(result);
                g.drawString(string[2], xstring, ystring);
            }

            g.setColor(Color.WHITE);
            g.setFont(points);
            g.drawString("Final score: \n" + String.valueOf(this.score.getScore()), xstring,
                    ystring + this.scale.getScale() / 16);
        }
    }
}
