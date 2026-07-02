package view.graphics_component.survivor;

import java.util.List;

import model.entities.survivor.Survivor;

import java.awt.image.BufferedImage;

import view.graphics.GraphicsSurvivor;
import view.graphics_util.SpriteSheetLoader;

/**
 * Component responsible for managing and updating the graphical animations
 * of a common survivor entity.
 * <p>
 * It loads sprite animations for the survivor and handles the animation timing
 * and frame updates.
 * </p>
 */
public class GraphicsCommonComponent implements GraphicsSurvivorComponent {

    private static final int WIDTH_FRAME = 48;
    private static final int HEIGHT_FRAME = 64;

    private SpriteSheetLoader graphEnti = new SpriteSheetLoader();
    private List<List<BufferedImage>> animations;
    private int aniIndex, aniTick, aniSpeed = 5;

    /**
     * Constructs a GraphicsCommonComponent that loads animations for a survivor
     * based on the given class name.
     *
     * @param nameClass the class name of the survivor used to load the
     *                  corresponding animations
     */
    public GraphicsCommonComponent(final String nameClass) {
        System.err.println("Import the Image, and set all Animations");
        this.animations = graphEnti.loadSurvivorAnimations(nameClass, WIDTH_FRAME, HEIGHT_FRAME);
    }

    /**
     * Updates the animation frame index based on the survivor's current state.
     *
     * @param surState the current animation state index of the survivor
     */
    private void updateAnimations(final int surState) {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            int currentStateSize = animations.get(surState).size();
            if (aniIndex >= currentStateSize) {
                aniIndex = 0;
            }
        }
    }

    /**
     * Updates the graphical representation of the survivor by advancing the
     * animation frame
     * and instructing the GraphicsSurvivor instance to draw the correct frame.
     *
     * @param sur    the survivor entity whose graphics need to be updated
     * @param grySur the graphics interface used to draw the survivor
     */
    @Override
    public void update(final Survivor sur, final GraphicsSurvivor grySur) {
        int surState = sur.getState().getIndex();
        updateAnimations(surState);
        grySur.drawSurvivor(sur, animations.get(surState).get(aniIndex));
    }

}
