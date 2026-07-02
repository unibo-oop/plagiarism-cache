package view.graphics_component.armory;

import java.awt.image.BufferedImage;

import model.armory.munition.Munition;
import view.graphics.GraphicsMunition;
import view.graphics_util.SpriteSheetLoader;

/**
 * Graphics component for rendering Parabellum-type munitions.
 * Loads the munition sprite image and delegates the drawing
 * to the provided GraphicsMunition interface.
 */
public class GraphicsParabellumComponent implements GraphicsMunitionComponent {

    private SpriteSheetLoader graphEnti = new SpriteSheetLoader();
    private BufferedImage munition;

    /**
     * Constructs a GraphicsParabellumComponent with the specified munition class
     * name.
     * Loads the sprite image for the munition.
     * 
     * @param nameClass the class name of the munition used to load the
     *                  corresponding sprite image
     */
    public GraphicsParabellumComponent(final String nameClass) {
        this.munition = graphEnti.loadMunition(nameClass);

    }

    /**
     * Updates the graphical representation of the munition by drawing
     * the loaded sprite image using the given GraphicsMunition interface.
     * 
     * @param mun  the munition instance to draw
     * @param grsy the graphics interface used to perform the drawing
     */
    @Override
    public void update(final Munition mun, final GraphicsMunition grsy) {
        grsy.drawMunition(mun, munition);
    }

}
