package it.unibo.arkanoid.view;

import it.unibo.arkanoid.subject.Subject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Graphic representation of {@link Subject}. USED IN DEBUG AND INITIAL PHASE.
 *
 */
public class Sprite {

    private final Image image;
    private final Subject subject;
    private final String imageName;

    /**
     * Create sprite entity with image and subject.
     * 
     * @param fileName
     *            path for image that represents graphically the subject.
     * @param subject
     *            from game's world.
     */
    public Sprite(final String fileName, final Subject subject) {
        this.image = new Image(getClass().getResource(fileName).toExternalForm(), subject.getWidth(), subject.getHeight(), false, false);
        this.subject = subject;
        this.imageName = fileName;
    }

    /**
     * Rendering sprite in Canvas.
     * 
     * @param gc
     *            create in Canvas.
     */
    public void render(final GraphicsContext gc) {
        gc.drawImage(image, subject.getPosition().getX(), subject.getPosition().getY());
    }

    /**
     * Getter for sprite's image.
     * 
     * @return image.
     */
    public Image getImage() {
        return this.image;
    }

    /**
     * Getter for sprite's subject.
     * 
     * @return subject.
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Getter for sprite's image name.
     * 
     * @return image name.
     */
    public String getImageName() {
        return this.imageName;
    }

}
