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
 * Class Credits scene of the application,
 * it simply presents the creators and gives
 * credits to whoever was involved in the project.
 */
public final class Credits extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879879L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/credits.png";
    private static final String BACK = "Back";
    private final transient Image creditsImage;

    /**
     * Constructor of Credits.
     * 
     * @param view is used to return back to the Menu
     */
    public Credits(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());

        creditsImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));

        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return creditsImage;
    }
}
