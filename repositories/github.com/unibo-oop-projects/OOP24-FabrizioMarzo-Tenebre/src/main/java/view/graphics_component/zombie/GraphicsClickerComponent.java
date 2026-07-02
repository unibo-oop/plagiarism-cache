package view.graphics_component.zombie;

import java.awt.image.BufferedImage;
import java.util.List;

import model.entities.zombie.Zombie;
import view.graphics.GraphicsZombie;
import view.graphics_util.SpriteSheetLoader;

/**
 * Graphics component responsible for rendering the "Clicker" zombie type.
 * Handles loading and animating sprite frames for the zombie animations.
 */
public class GraphicsClickerComponent implements GraphicsZombieComponent {

    private static final int WIDTH_FRAME = 64;
    private static final int HEIGHT_FRAME = 32;

    private SpriteSheetLoader graphEnti = new SpriteSheetLoader();
    private List<List<BufferedImage>> animations;
    private int aniIndex, aniTick, aniSpeed = 8;

    /**
     * Constructs a GraphicsClickerComponent, loading the animations
     * for the specified zombie class.
     * 
     * @param nameClass the class name of the zombie to load animations for
     */
    public GraphicsClickerComponent(final String nameClass) {
        this.animations = graphEnti.loadZombieAnimations(nameClass, WIDTH_FRAME, HEIGHT_FRAME);
    }

    /**
     * Updates the animation frame index for the given zombie state.
     * 
     * @param zobState the current state index of the zombie
     * @return the current animation frame index
     */
    private int updateAnimations(final int zobState) {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            final int currentStateSize = animations.get(zobState).size();
            if (aniIndex >= currentStateSize) {
                aniIndex = 0;
            }
        }
        return aniIndex;
    }

    /**
     * Updates the graphics for the given zombie by drawing the current animation
     * frame.
     * 
     * @param zob      the zombie entity to draw
     * @param graphZob the graphics interface used to render the zombie
     */
    @Override
    public void update(final Zombie zob, final GraphicsZombie graphZob) {
        final int zobState = zob.getState().getIndex();
        graphZob.drawZombie(zob, animations.get(zobState).get(updateAnimations(zobState)));
    }

}
