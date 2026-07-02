package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import migglione.model.impl.Cards;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/**
 * The class Gallery allows to see all the sprites of the cards.
 * 
 * <p>
 * It incapsulates the sprites in a gridlayout, allowing
 * the view to be put in another container that covers part
 * of the screen. The sprites are taken from the folder in
 * resources and is bound by the database (Cards.java)
 * 
 * <p>
 * Moving in the container involves the usage of a ScrollPane
 * and the container itself is completely created in the private
 * createGalleryBox method, which makes sure that only the sprites
 * are visible (looking at the values would be cheating!)
 */
public final class Gallery extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879870L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final int CARDS_WIDTH = 200;
    private static final int CARDS_HEIGHT = 250;
    private static final int BAR_INCREMENT = 20;
    private static final String BACK = "Back";

    private final transient Image galleryImage;
    private final transient Cards database;
    private JScrollPane galleryScroll;

    /**
     * Constructor of Gallery.
     * 
     * @param view is used to return back to the Menu
     */
    public Gallery(final SwingViewImpl view) {
        this.database = new Cards();
        final List<String> cardsPath = database.getCardsPaths();

        this.setLayout(new BorderLayout());
        galleryImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JPanel pGallery = createGalleryBox(cardsPath);
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);

        this.add(pGallery, BorderLayout.CENTER);
        this.add(pSouth, BorderLayout.SOUTH);
    }

    private JPanel createGalleryBox(final List<String> cardsPath) {

        final JPanel galleryBox = new JPanel(new BorderLayout());
        final JPanel cardsGrid = new JPanel(new GridLayout(0, 3, 10, 20));
        cardsGrid.setOpaque(false);

        for (final var path : cardsPath) {
            final ImageIcon card = new ImageIcon(getClass().getResource(path));
            final ImageIcon scaledCard = new ImageIcon(
                card.getImage().getScaledInstance(CARDS_WIDTH, CARDS_HEIGHT, Image.SCALE_SMOOTH)
            );
            final JLabel cardLabel = new JLabel(scaledCard);
            cardLabel.setHorizontalAlignment(JLabel.CENTER);
            cardsGrid.add(cardLabel);
        }

        galleryScroll = new JScrollPane(cardsGrid);

        galleryScroll.getViewport().setBackground(Color.BLACK);
        galleryScroll.setOpaque(false);
        galleryScroll.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 4));

        final JScrollBar bar = galleryScroll.getVerticalScrollBar();
        bar.setBackground(Color.BLACK);
        bar.setUnitIncrement(BAR_INCREMENT);

        galleryBox.add(galleryScroll, BorderLayout.CENTER);
        return galleryBox;
    }

    /**
     * Method used to reset the scroll bar.
     * 
     * <p>
     * It allows it to return to its default position
     * upon re-entering the scene
     */
    public void resetScrollBar() {
        this.galleryScroll.getVerticalScrollBar().setValue(0);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.SMASH.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return galleryImage;
    }
}
