package frogger.model.implementations;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import frogger.common.Constants;
import frogger.common.GameState;
import frogger.common.LoadSave;
import frogger.model.interfaces.Button;

/**
 * The {@code MenuButtons} class represents a button in the game's menu interface.
 * Each button displays a different image depending on its state (default, hovered, pressed)
 * and is associated with a specific {@link GameState} that is applied when the button is activated.
 * <p>
 * The button's appearance is determined by a sprite sheet, and its position and bounds
 * are specified at construction. The class provides methods to update its state based on
 * mouse interactions and to reset its state.
 * </p>
 *
 * <ul>
 *   <li>Handles mouse over and pressed states for visual feedback.</li>
 *   <li>Updates the current image based on interaction state.</li>
 *   <li>Applies the associated game state when activated.</li>
 * </ul>
 *
 * @see Button
 * @see GameState
 */
public class MenuButtons implements Button {

    //Represents the x and y position of the button, the row index in the sprite sheet,
    private final int xPos, yPos, rowIndex;
    //and the index of the current image to display
    private int index;
    private BufferedImage[] imgs;
    private final GameState state;
    private boolean mousePressed, mouseOver;
    private Rectangle bounds;

    /**
     * Constructs a MenuButtons instance with specified position, row index, and game state.
     *
     * @param xPos the x-coordinate of the button's position
     * @param yPos the y-coordinate of the button's position
     * @param rowIndex the row index in the sprite sheet for this button's images
     * @param state the game state to apply when this button is activated
     */
    public MenuButtons(final int xPos, final int yPos, final int rowIndex, final GameState state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.state = state;
        this.rowIndex = rowIndex;
        loadImgs();
        initBounds();
    }

    /**
     * Loads the button images from the sprite sheet based on the specified row index.
     * Each button image is extracted from the sprite sheet and stored in an array.
     */
    private void loadImgs() {
        this.imgs = new BufferedImage[3];
        final BufferedImage temp = LoadSave.getSprite("menu_buttons.png");
        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * Constants.BUTTON_WIDTH_DEFAULT, 
                                        rowIndex * Constants.BUTTON_HEIGHT_DEFAULT, 
                                        Constants.BUTTON_WIDTH_DEFAULT, 
                                        Constants.BUTTON_HEIGHT_DEFAULT);
        }
    }

    /**
     * Initializes the bounds of the button based on its position and dimensions.
     * The bounds are used for collision detection with mouse events.
     */
    private void initBounds() {
        bounds = new Rectangle(xPos, yPos, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BufferedImage getCurrentImg() {
        return imgs[index];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getXPos() {
        return xPos;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getYPos() {
        return yPos;
    }

    /**
    * {@inheritDoc}
    */
    @Override
    public void update() {
        index = 0;
        if (mouseOver) {
            index = 1;
        }
        if (mousePressed) {
            index = 2;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMouseOver() {
        return mouseOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMouseOver(final boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isMousePressed() {
        return mousePressed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMousePressed(final boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(this.bounds);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void applyGameState() {
        GameState.setState(state);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}
