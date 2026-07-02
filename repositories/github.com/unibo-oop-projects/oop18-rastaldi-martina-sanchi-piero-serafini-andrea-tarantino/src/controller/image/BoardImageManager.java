package controller.image;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Class used to initialize icon for the board and to return them right resized.
 * 
 * Andrea Serafini.
 *
 */
public final class BoardImageManager implements BoardImageManagerInterface {

    private static final String HERO_PATH = "/boardImages/heroFaceNormal.png";
    private static final String SELECTED_HERO_PATH = "/boardImages/heroFaceSelected.png";
    private static final String MINOTAURUS_PATH = "/boardImages/minotaurusFace.png";
    private static final String ICON_PATH = "/menuImages/initialMenuWallpaper.jpeg";
    private static final String BACKGROUND_PATH = "/boardImages/th.jpg";
    private static final String PAPER_PATH = "/boardImages/scrolla.png";

    private ImageIcon minotaurus;
    private ImageIcon hero;
    private ImageIcon selectedHero;
    private ImageIcon icon;
    private ImageIcon background;
    private ImageIcon paper;


    /**
     * Constructor.
     */
    public BoardImageManager() {

        this.initializeAll();

    }

    @Override
    public ImageIcon getBackground() {
        return this.background;
    }

    @Override
    public ImageIcon getHero() {
        return this.hero;
    }

    @Override
    public ImageIcon getIcon() {
        return this.icon;
    }

    private ImageIcon getImage(final String path) {

        try {
            return new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream(path)));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public ImageIcon getMinotaurus() {
        return this.minotaurus;
    }

    @Override
    public ImageIcon getPaper() {
        return this.paper;
    }

    private ImageIcon getResized(final ImageIcon image, final int width, final int height) {
        return new ImageIcon(image.getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH));
    }

    @Override
    public ImageIcon getSelectedHero() {
        return this.selectedHero;
    }

    private void initializeAll() {
        this.hero = this.getImage(HERO_PATH);
        this.selectedHero = this.getImage(SELECTED_HERO_PATH);
        this.minotaurus = this.getImage(MINOTAURUS_PATH);
        this.icon = this.getImage(ICON_PATH);
        this.background = this.getImage(BACKGROUND_PATH);
        this.paper = this.getImage(PAPER_PATH);
    }

    @Override
    public void resize(final int width, final int height) {

        this.hero = this.getImage(HERO_PATH);
        this.selectedHero = this.getImage(SELECTED_HERO_PATH);
        this.minotaurus = this.getImage(MINOTAURUS_PATH);

        this.hero = this.getResized(this.hero, width, height);
        this.selectedHero = this.getResized(this.selectedHero, width, height);
        this.minotaurus = this.getResized(this.minotaurus, width, height);
    }

}
