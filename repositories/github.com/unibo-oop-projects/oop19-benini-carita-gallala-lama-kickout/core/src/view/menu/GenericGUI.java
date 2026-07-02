package view.menu;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
/**
 *  Used to manage the menu's GUI
 */
public interface GenericGUI {
    /**
     * Chooses the GUI architecture
     * @return  the table in which the chosen architecture will be applied
     */
    public Table GUI();
    /**
     * Used by the controller to switch between images
     * @return the table in which the image will be loaded
     */
    public Table getTable();
    /**
     * @return whether the right arrow key has been pressed
     */
    public ImageButton getArrowImageDx();
    /**
     * @return whether the left arrow key has been pressed
     */
    public ImageButton getArrowImageSx();
    /**
     * Sets a listener
     * @param listener
     */
    public void setListener(final ClickListener listener);
    /**
     * Sets the first image to be loaded
     * @param selected
     *          The image to load
     */
    public void setSelected(final Sprite selected);
    /**
     * Sets the current index
     * @param indexSelected
     */
    public void setIndexSelected(final int indexSelected);
}
