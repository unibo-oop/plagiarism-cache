package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/**
 * The class Tutorial is used to show the tutorial of the game.
 * 
 * <p>
 * It shows a series of images that explain how to play the game,
 * with a back and forward button to navigate through the images.
 * The images are taken from the folder in resources and are named 1.png, 2.png, etc.
 * The music is a looping track that plays while the tutorial is shown.
 */
public final class Tutorial extends AbstractGamePanel implements MusicProvider {
    private static final long serialVersionUID = 9879879879L;
    private static final String TUTORIAL_IMAGES_PATH = "/images/tutorial/";
    private static final String BACK = "Back";
    private static final String FORWARD = "Forward";
    private static final int MAX_IMAGE_INDEX = 11;
    private transient Image tutorialImage;
    private int currentImageIndex = 1;

    /**
     * Constructor for the Tutorial class.
     * 
     * @param view is used to return to Menu
     */
    public Tutorial(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());
        tutorialImage = new ImageIcon(getClass().getResource(TUTORIAL_IMAGES_PATH + currentImageIndex + ".png")).getImage();

        final JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton back = new GenericButton(BACK, b -> updateTutorialImage(-1, view));
        final JButton forward = new GenericButton(FORWARD, b -> updateTutorialImage(1, view));

        buttonsPanel.setOpaque(false);
        buttonsPanel.add(back);
        buttonsPanel.add(forward);

        this.add(buttonsPanel, BorderLayout.SOUTH);
    }

    /**
     * Method used to update the tutorial image when the back or forward button is pressed.
     * 
     * @param index used to determine if back or forward was pressed
     * @param view used to return to Menu if the index is "out of bounds"
     */
    private void updateTutorialImage(final int index, final SwingViewImpl view) {
        currentImageIndex += index;
        if (currentImageIndex == 0) {
            currentImageIndex = 1;
            view.setScene(Scenes.MENU.getScene());
        } else if (currentImageIndex == MAX_IMAGE_INDEX) {
            currentImageIndex = 1;
            view.setScene(Scenes.MENU.getScene());
        }
        tutorialImage = new ImageIcon(
            getClass().getResource(TUTORIAL_IMAGES_PATH + currentImageIndex + ".png")).getImage();
        repaint();
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return tutorialImage;
    }
}
