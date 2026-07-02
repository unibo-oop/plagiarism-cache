package unibo.exiled.view.character;

import unibo.exiled.utilities.ConstantsAndResourceLoader;
import unibo.exiled.utilities.Direction;

import java.awt.Graphics;
import java.awt.Image;
import java.io.Serial;
import java.util.List;
import javax.annotation.concurrent.Immutable;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The CharacterView class represents a graphical view of a character in the
 * game.
 * It extends the JLabel class to display images within a Swing component.
 * The class handles the sprites of the character's movement.
 */
@Immutable
public final class CharacterView extends JLabel {
    @Serial
    private static final long serialVersionUID = 5L;
    private static final String FIRST_IMAGE = "_1.png";
    private static final String SECOND_IMAGE = "_2.png";
    private final String upSprite;
    private final String downSprite;
    private final String rightSprite;
    private final String leftSprite;
    private final String path;
    private transient Image image;

    /**
     * Constructs a new CharacterView instance with the specified sprite images.
     *
     * @param sprites A list of strings representing the sprite images for the
     *                character,
     *                where the first element is the folder name, the second is the
     *                up sprite,
     *                the third is the down sprite, the fourth is the left sprite,
     *                and the fifth
     *                is the right sprite.
     */
    public CharacterView(final List<String> sprites) {
        this.path = ConstantsAndResourceLoader.IMAGES_PATH + sprites.get(0);
        this.image = new ImageIcon(ConstantsAndResourceLoader.getResourceURLFromPath(this.path
                + sprites.get(2) + FIRST_IMAGE)).getImage();

        this.upSprite = sprites.get(1);
        this.downSprite = sprites.get(2);
        this.leftSprite = sprites.get(3);
        this.rightSprite = sprites.get(4);
    }

    /**
     * Changes the character's image based on the specified direction.
     *
     * @param dir      The direction in which the character is moving.
     * @param isMoving If the character is currently in its moving state.
     */
    public void changeImage(final Direction dir, final boolean isMoving) {
        final String imgAnimationName;
        switch (dir) {
            case NORTH -> {
                imgAnimationName = checkAnimation(this.upSprite, isMoving);
            }

            case SOUTH -> {
                imgAnimationName = checkAnimation(this.downSprite, isMoving);
            }

            case WEST -> {
                imgAnimationName = checkAnimation(this.leftSprite, isMoving);
            }

            case EAST -> {
                imgAnimationName = checkAnimation(this.rightSprite, isMoving);
            }

            default -> {
                throw new IllegalArgumentException("Unknown direction");
            }
        }
        this.image = new ImageIcon(ConstantsAndResourceLoader.getResourceURLFromPath(this.path + imgAnimationName))
                .getImage();
    }

    /**
     * Checks the animation frame and updates the image name accordingly.
     *
     * @param animationName The base name of the animation sprite.
     * @param isMoving      If the character is currently in the moving state.
     * @return A string with the suffix of the sprite based on the animation.
     */
    private String checkAnimation(final String animationName, final boolean isMoving) {
        if (!isMoving) {
            return animationName + FIRST_IMAGE;
        } else {
            return animationName + SECOND_IMAGE;
        }
    }

    /**
     * Overrides the paintComponent method to rescale the image within the JPanel
     * when the main window is resized.
     *
     * @param g The graphics context used for painting.
     */
    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final int width = getWidth();
        final int height = getHeight();

        if (image != null) {
            final int imgWidth, imgHeight;
            final int originalWidth = image.getWidth(null);
            final int originalHeight = image.getHeight(null);
            final double aspectRatio = (double) originalWidth / originalHeight;

            if (width / aspectRatio <= height) {
                imgWidth = width;
                imgHeight = (int) (width / aspectRatio);
            } else {
                imgWidth = (int) (height * aspectRatio);
                imgHeight = height;
            }

            final int x = (width - imgWidth) / 2;
            final int y = (height - imgHeight) / 2;

            g.drawImage(image, x, y, imgWidth, imgHeight, null);
        }
    }
}
